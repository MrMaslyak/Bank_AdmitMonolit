package org.example.bank.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseGetter;


public class Bank {

    @FXML
    private Label balanceLabel, user;
    private String userLogin;

    public Bank() {
    }

    public Bank(String userLogin) {
        this.userLogin = userLogin;
    }

    public void initialize() {
        int userId = DatabaseGetter.getUserId(userLogin);
        System.out.println(userId);
        String balance = DatabaseGetter.getBalanceUserDB(userId);
        balanceLabel = new Label();
        Platform.runLater(() -> balanceLabel.setText(balance));
    }
}

