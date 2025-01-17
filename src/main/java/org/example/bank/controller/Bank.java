package org.example.bank.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseBank;
import org.example.bank.database.DatabaseGetter;
import org.example.bank.systems.JWToken;

public class Bank {


    @FXML
    private Label balanceLabel, user;
    public Button btnAddBalance;
    private String userLogin;
    private int userId;
    private DatabaseBank databaseBank;

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        updateUserInfo();
    }

    @FXML
    public void initialize() {
        databaseBank = DatabaseBank.getInstance();
    }

    private void updateUserInfo() {
        if (userLogin == null) return;

        userId = DatabaseGetter.getUserId(userLogin);
        String balance = DatabaseGetter.getBalanceUserDB(userId);

        Platform.runLater(() -> {
            balanceLabel.setText(balance);
            user.setText(userLogin);
        });
    }

    public void addMoney() {
        String token = DatabaseGetter.getTokenDB(userId);
        if (JWToken.verifyToken(token)) {
            Platform.runLater(() -> {
                double balance = Double.parseDouble(balanceLabel.getText()) + 100;
                System.out.println(balance);
                balanceLabel.setText(String.format("%.2f", balance).replace(",", "."));
                databaseBank.updateBalanceUserDB(userId, balance);
            });
        }
    }

}
