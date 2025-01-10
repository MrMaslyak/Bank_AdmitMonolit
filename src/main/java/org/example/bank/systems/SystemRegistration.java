package org.example.bank.systems;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import org.example.bank.database.DatabaseR;

import java.util.regex.Pattern;

public class SystemRegistration {

    private boolean isEmailValid, isLoginValid, isPasswordValid;
    private final Circle indicatorEmail, indicatorLogin, indicatorPassword;
    private final PasswordField passwordS;
    private final TextField loginS, emailS;
    private final Label errorMsg;

    public SystemRegistration(Circle indicatorEmail, Circle indicatorLogin, Circle indicatorPassword,
                              PasswordField passwordS, TextField loginS, TextField emailS, Label errorMsg) {
        this.indicatorEmail = indicatorEmail;
        this.indicatorLogin = indicatorLogin;
        this.indicatorPassword = indicatorPassword;
        this.passwordS = passwordS;
        this.loginS = loginS;
        this.emailS = emailS;
        this.errorMsg = errorMsg;
    }

    public void onLoginChanged() {
        String login = loginS.getText();
        if (login == null || login.isEmpty()) {
            updateIndicator(indicatorLogin, false);
            setLoginValid(false);
            return;
        }

        boolean isAvailable = false;
        try {
            isAvailable = DatabaseR.getInstance().availableLogin(login);
        } catch (Exception e) {
            errorMsg.setText("Ошибка проверки логина.");
            errorMsg.setVisible(true);
        }

        boolean isValid = isValidLogin(login) && !isAvailable;
        updateIndicator(indicatorLogin, isValid);
        setLoginValid(isValid);
    }

    private boolean isValidLogin(String login) {
        String loginRegex = "^[a-zA-Z0-9_]{3,20}$";
        return Pattern.matches(loginRegex, login);
    }

    public void onPasswordChanged() {
        String password = passwordS.getText();
        boolean isValid = password != null && isValidPassword(password) && password.length() >= 8  && password.length() <= 72;
        updateIndicator(indicatorPassword, isValid);
        setPasswordValid(isValid);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^[a-zA-Z0-9]{8,20}$";
        return Pattern.matches(passwordRegex, password);
    }

    public void onEmailChanged() {
        String email = emailS.getText();
        if (email == null || email.isEmpty()) {
            updateIndicator(indicatorEmail, false);
            setEmailValid(false);
            return;
        }

        boolean isAvailable = false;
        try {
            isAvailable = DatabaseR.getInstance().availableEmail(email);
        } catch (Exception e) {
            errorMsg.setText("Ошибка проверки email.");
            errorMsg.setVisible(true);
        }

        boolean isValid = isValidEmail(email) && !isAvailable;
        updateIndicator(indicatorEmail, isValid);
        setEmailValid(isValid);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

    private void updateIndicator(Circle indicator, boolean isValid) {
        indicator.setStyle("-fx-fill: " + (isValid ? "green" : "red"));
    }

    public boolean isEmailValid() {
        return isEmailValid;
    }

    public boolean isLoginValid() {
        return isLoginValid;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public void setEmailValid(boolean emailValid) {
        isEmailValid = emailValid;
    }

    public void setLoginValid(boolean loginValid) {
        isLoginValid = loginValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        isPasswordValid = passwordValid;
    }
}
