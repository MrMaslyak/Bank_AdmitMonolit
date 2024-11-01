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

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;
    public PasswordField passwordS;
    public TextField loginS, emailS;
    public Button accept;
    private SystemR systemR;


    public void initialize() {
      systemR = new SystemR(indicatorEmail, indicatorLogin, indicatorPassword, passwordS, loginS, emailS);
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
