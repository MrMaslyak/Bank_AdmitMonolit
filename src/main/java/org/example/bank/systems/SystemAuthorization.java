package org.example.bank.systems;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.controller.Bank;
import org.example.bank.database.DatabaseR;
import org.slf4j.Logger;

public class SystemAuthorization {


    private Button registration;
    private TextField loginL;
    private PasswordField passwordL;
    private Label  errorL;

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
            proceedToBank();
        } else {
            showLoginError();
        }
    }

    private boolean validateLogin() {
        return database.passBank(loginL.getText(), passwordL.getText());
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
