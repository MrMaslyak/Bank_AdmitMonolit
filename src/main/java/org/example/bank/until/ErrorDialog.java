package org.example.bank.until;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorDialog {

    public static void showErrorDialog(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(message);

        alert.getDialogPane().setMinWidth(125);
        alert.getDialogPane().setMinHeight(50);

        alert.getGraphic();
        alert.getButtonTypes().setAll(ButtonType.OK);

        alert.showAndWait();
    }
}
