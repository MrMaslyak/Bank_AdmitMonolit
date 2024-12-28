package org.example.bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;

import java.io.IOException;

public class App extends Application {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/lobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 666);
        stage.setTitle("Maslyak Bank");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        logger.info("Запуск приложения");

    }

    public static void main(String[] args) {
        launch();
    }
}