package org.example.bank.systems;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.controller.Bank;
import org.example.bank.database.DatabaseAuthorizationAccount;
import org.example.bank.database.DatabaseConnection;
import org.example.bank.database.DatabaseR;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAuthorization {


    private Button registration;
    private TextField loginL;
    private PasswordField passwordL;
    private Label errorL;

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
        } else {
            showLoginError();
        }
    }

    private void saveToken() {
        int userId = database.getUserId(loginL.getText());
        String token = GenerationJWT.generateToken(userId);
        DatabaseAuthorizationAccount.saveTokenToDatabase(userId, token);
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
    }

    private void showLoginError() {
        errorL.setVisible(true);
        errorL.setText("Incorrect login or password");
        logger.warn("Неверный логин или пароль. Логин: {}", loginL.getText());
    }





}
