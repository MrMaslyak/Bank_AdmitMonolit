package org.example.bank.controller;

import javafx.scene.control.*;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;
import org.example.bank.database.DatabaseR;
import org.example.bank.systems.StageManager;
import org.example.bank.systems.SystemRegistration;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;
    public PasswordField passwordS;
    public TextField loginS, emailS;
    public Button accept;
    public Label errorMsg;
    private SystemRegistration systemRegistration;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Registration.class);

    public void initialize() {
        Tooltip.install(indicatorEmail, new Tooltip("Email має бути у форматі example@gmail.com"));
        Tooltip.install(indicatorLogin, new Tooltip("Login має бути написаний тільки латиницею" + "\n" + "Login має бути від 4 символів"));
        Tooltip.install(indicatorPassword, new Tooltip("Password повинен містити від 8 до 20 символів" + "\n" + "Password не повинен містити спеціальних символів"));
        systemRegistration = new SystemRegistration(indicatorEmail, indicatorLogin, indicatorPassword, passwordS, loginS, emailS, errorMsg);
    }

    public void showError(String message) {
        logger.error("Ошибка: {}", message);
        errorMsg.setText(message);
        errorMsg.setVisible(true);
    }

    public void clearError() {
        errorMsg.setText("");
        errorMsg.setVisible(false);
    }

    public void back() {
        StageManager.switchScene(back, "/org/example/bank/fxml/lobby.fxml");
        logger.info("Переход на страницу лобби");
    }

    public void onAccept() {

        systemRegistration.onLoginChanged();
        systemRegistration.onPasswordChanged();
        systemRegistration.onEmailChanged();

        logger.info("Проверка валидности данных...");

        if (systemRegistration.isEmailValid() && systemRegistration.isLoginValid() && systemRegistration.isPasswordValid()) {
            logger.info("Данные валидны. Начало регистрации.");


            String hashedPassword = BCrypt.hashpw(passwordS.getText(), BCrypt.gensalt(12));


            boolean isEmailExists = DatabaseR.getInstance().availableEmail(emailS.getText());
            boolean isLoginExists = DatabaseR.getInstance().availableLogin(loginS.getText());

            if (!isEmailExists && !isLoginExists) {
                DatabaseR.getInstance().addUser(loginS.getText(), hashedPassword, emailS.getText());
                clearError();
                logger.info("Регистрация успешна. Переход на главную страницу.");
                StageManager.switchScene(accept, "/org/example/bank/fxml/lobby.fxml");
            } else {
                if (isLoginExists) {
                    showError("Пользователь с таким логином уже существует.");
                } else {
                    showError("Пользователь с таким email уже существует.");
                }
            }
        } else {
            logger.warn("Данные не валидны. Регистрация отклонена.");
            showError("Проверьте правильность введенных данных.");
        }
    }
}
