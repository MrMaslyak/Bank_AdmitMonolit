package org.example.bank.registrationSystem;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.bank.registrationSystem.database.DatabaseR;

import java.io.IOException;

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;
    public PasswordField passwordS;
    public TextField loginS, emailS;
    public Button accept;
    public Label errorMsg;
    private SystemR systemR;


    public void initialize() {

        DatabaseR.getInstance().setRegistration(this);
        systemR = new SystemR(indicatorEmail, indicatorLogin, indicatorPassword, passwordS, loginS, emailS, errorMsg);

    }

    public void showError(String message) {
        errorMsg.setText(message);
        errorMsg.setVisible(true);
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
        systemR.onLoginChanged();
        systemR.onPasswordChanged();
        systemR.onEmailChanged();

        if (systemR.isEmailValid() && systemR.isLoginValid() && systemR.isPasswordValid()) {


            DatabaseR.getInstance().addUser(loginS.getText(), passwordS.getText(), emailS.getText());

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
    }

}
