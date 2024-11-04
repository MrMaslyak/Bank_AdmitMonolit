package org.example.bank.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.bank.database.DatabaseR;
import org.example.bank.systems.SystemRegistration;

import java.io.IOException;

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;
    public PasswordField passwordS;
    public TextField loginS, emailS;
    public Button accept;
    public Label errorMsg;
    private SystemRegistration systemRegistration;


    public void initialize() {

        DatabaseR.getInstance().setRegistration(this);
        systemRegistration = new SystemRegistration(indicatorEmail, indicatorLogin, indicatorPassword, passwordS, loginS, emailS, errorMsg);

    }

    public void showError(String message) {
        errorMsg.setText(message);
        errorMsg.setVisible(true);
    }


    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bank/fxml/lobby.fxml"));
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
        systemRegistration.onLoginChanged();
        systemRegistration.onPasswordChanged();
        systemRegistration.onEmailChanged();

        if (systemRegistration.isEmailValid() && systemRegistration.isLoginValid() && systemRegistration.isPasswordValid()) {


            DatabaseR.getInstance().addUser(loginS.getText(), passwordS.getText(), emailS.getText());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bank/fxml/lobby.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                back.getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
