package org.example.bank.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseGetter;

public class Bank {

    @FXML
    private Label balanceLabel, user;

    private String userLogin;

    public Bank() {}

    public Bank(String userLogin) {
        this.userLogin = userLogin;
    }

    @FXML
    public void initialize() {
        if (userLogin != null) {
            start();
        }
    }

    public void start() {

        int userId = DatabaseGetter.getUserId(userLogin);
        String balance = DatabaseGetter.getBalanceUserDB(userId);

        System.out.println("User in bank: " + userLogin);
        System.out.println("Id user in bank: " + userId);
        System.out.println("Balance user: " + balance);

        Platform.runLater(() -> {
            balanceLabel = new Label(balance);
            user = new Label(userLogin);
        });
    }
}
