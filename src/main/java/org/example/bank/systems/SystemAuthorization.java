package org.example.bank.systems;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.database.DatabaseAuthorizationAccount;
import org.example.bank.database.DatabaseR;
import org.example.bank.until.ErrorDialog;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemAuthorization {


    private Button registration;
    private TextField loginL;
    private PasswordField passwordL;
    private Label errorL;
   private boolean isInBank = false;
    private final DatabaseR database = DatabaseR.getInstance();
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
        int userId = database.getUserId(loginL.getText());
        String token = JWToken.generateToken(userId);
        DatabaseAuthorizationAccount.saveTokenToDatabase(userId, token);
    }

    private void monitorToken(){
        int userId = database.getUserId(loginL.getText());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            scheduler.scheduleAtFixedRate(() -> {
                String token = DatabaseAuthorizationAccount.takeTokenDB(userId);
                if (token != null && JWToken.isExpiresToken(token)) {
                    logger.info("Токен истёк! Перенаправляем на другую сцену.");
                    Platform.runLater(() -> {
                        StageManager.switchScene("/org/example/bank/fxml/lobby.fxml");
                        ErrorDialog.showErrorDialog("");
                    });
                    scheduler.shutdown();
                }
            },0,10, TimeUnit.SECONDS);

    }

    private boolean validateLogin() {
        String login = loginL.getText();
        String password = passwordL.getText();

        String hashedPassword = DatabaseAuthorizationAccount.takeHashedPasswordDB(login);

        if (hashedPassword != null && BCrypt.checkpw(password, hashedPassword)) {
            return true;
        } else {
            return false;
        }

    }

    private void proceedToBank() {
        int userId = database.getUserId(loginL.getText());
        StageManager.switchScene(registration, "/org/example/bank/fxml/bank.fxml");
        logger.info("Переход на страницу банка. Пользователь ID: {}", userId);
        isInBank = true;
    }

    private void showLoginError() {
        errorL.setVisible(true);
        errorL.setText("Incorrect login or password");
        logger.warn("Неверный логин или пароль. Логин: {}", loginL.getText());
    }


}
