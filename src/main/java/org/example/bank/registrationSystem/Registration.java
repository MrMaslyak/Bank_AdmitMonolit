package org.example.bank.registrationSystem;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;
    public PasswordField passwordS;
    public TextField loginS, emailS;
    public Button accept;
    private boolean isEmailValid = true, isLoginValid = true, isPasswordValid = true;


    public void onLoginChanged() {
        String login = loginS.getText();

        if (isValidLogin(login)) {
            indicatorLogin.setStyle("-fx-fill: green");
            isLoginValid = true;
        } else {
            indicatorLogin.setStyle("-fx-fill: red");
            isLoginValid = false;
        }

    }

    private boolean isValidLogin(String login) {
        String loginRegex = "^[a-zA-Z0-9_]{3,20}$";

        Pattern pat = Pattern.compile(loginRegex);
        if (login == null)
            return false;
        return pat.matcher(login).matches();
    }


    public void onPasswordChanged() {
        String password = passwordS.getText();
        if (isValidPassword(password) && password.length() >= 8) {
            indicatorPassword.setStyle("-fx-fill: green");
            isPasswordValid = true;
        } else {
            indicatorPassword.setStyle("-fx-fill: red");
            isPasswordValid = false;
        }

    }

    private boolean isValidPassword(String password) {

        String passwordRegex = "^[a-zA-Z0-9]{8,20}$";

        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

    public void onEmailChanged() {
        String email = emailS.getText();

        if (isValidEmail(email)) {
            indicatorEmail.setStyle("-fx-fill: green");
            isEmailValid = true;
        } else {
            indicatorEmail.setStyle("-fx-fill: red");
            isEmailValid = false;
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bank/lobby.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            back.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onAccept() {
        onLoginChanged();
        onPasswordChanged();
        onEmailChanged();
        if (isEmailValid && isLoginValid && isPasswordValid) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bank/lobby.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                back.getScene().getWindow().hide();
//TODO add func how add in DB
                System.out.println(passwordS.getText());
                System.out.println(loginS.getText());
                System.out.println(emailS.getText());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
