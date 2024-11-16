package org.example.bank.systems;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.IOException;

public class StageManager {

    public static void switchScene(Button currentButton, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(StageManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            currentButton.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(StageManager.class);
            logger.error("Ошибка при смене сцены: " + fxmlPath, e);
        }
    }


}
