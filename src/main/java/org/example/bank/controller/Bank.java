package org.example.bank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.systems.UpdDataUserBank;

public class Bank {

    @FXML
    private Label balanceLabel, user;
    private UpdDataUserBank bankDataUser;

    public void initialize() {
    }

    public void updateUI() {
        if (bankDataUser != null) {
            balanceLabel.setText(bankDataUser.getBalance());
            user.setText(bankDataUser.getLogin());
        } else {
            balanceLabel.setText("No data");
            user.setText("No data");
        }
    }

    public void setBankDataUser(UpdDataUserBank bankDataUser) {
        this.bankDataUser = bankDataUser;
        updateUI();
    }
}
