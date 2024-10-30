package org.example.bank;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {

    public Button back;
    public Circle indicatorEmail, indicatorLogin, indicatorPassword;





    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lobby.fxml"));
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
