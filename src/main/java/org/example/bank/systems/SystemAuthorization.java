package org.example.bank.systems;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.controller.Bank;
import org.example.bank.database.DatabaseAuth;
import org.example.bank.database.DatabaseGetter;
import org.example.bank.until.ErrorDialog;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemAuthorization {


    private Button registration;
    private TextField loginL;
    private PasswordField passwordL;
    private Label errorL;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SystemAuthorization.class);

    public SystemAuthorization(Button registration, TextField loginL, PasswordField passwordL, Label errorL) {
        this.registration = registration;
        this.loginL = loginL;
        this.passwordL = passwordL;
        this.errorL = errorL;
    }

    public void setLogin() {
        if (validateLogin()) {
            saveToken();
            proceedToBank();
            monitorToken();

        } else {
            showLoginError();
        }
    }

    private void saveToken() {
        int userId = DatabaseGetter.getUserId(loginL.getText());
        String token = JWToken.generateToken(userId);
        DatabaseAuth.saveTokenToDatabase(userId, token);
    }

    private void monitorToken() {
        int userId = DatabaseGetter.getUserId(loginL.getText());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            String token = DatabaseGetter.getTokenDB(userId);
            if (token != null && JWToken.isExpiresToken(token)) {
                logger.info("Токен истёк! Перенаправляем на другую сцену.");
                Platform.runLater(() -> {
                    StageManager.switchScene("/org/example/bank/fxml/lobby.fxml");
                    ErrorDialog.showErrorDialog("");
                });
                scheduler.shutdown();
            }
        }, 0, 10, TimeUnit.MINUTES);

    }

    private boolean validateLogin() {
        String login = loginL.getText();
        String password = passwordL.getText();

        String hashedPassword = DatabaseGetter.getHashedPasswordDB(login);

        if (hashedPassword != null && BCrypt.checkpw(password, hashedPassword)) {
            return true;
        } else {
            return false;
        }

    }

    private void proceedToBank() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bank/fxml/bank.fxml"));
        try {
            Parent root = loader.load();

            Bank bankController = loader.getController();
            bankController.setUserLogin(loginL.getText());
            StageManager.switchScene(registration, root);

            logger.info("Авторизация прошла успешно. Перенаправление на страницу банка.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showLoginError() {
        errorL.setVisible(true);
        errorL.setText("Incorrect login or password");
        logger.warn("Неверный логин или пароль. Логин: {}", loginL.getText());
    }


}
