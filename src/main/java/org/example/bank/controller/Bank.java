package org.example.bank.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseGetter;

public class Bank {

    @FXML
    private Label balanceLabel, user;

    private String userLogin;

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        updateUserInfo();
    }

    @FXML
    public void initialize() {
    }

    private void updateUserInfo() {
        if (userLogin == null) return;

        int userId = DatabaseGetter.getUserId(userLogin);
        String balance = DatabaseGetter.getBalanceUserDB(userId);

        Platform.runLater(() -> {
            balanceLabel.setText(balance);
            user.setText(userLogin);
        });
    }
}
