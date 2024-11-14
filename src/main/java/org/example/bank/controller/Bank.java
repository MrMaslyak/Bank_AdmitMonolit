package org.example.bank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.math.BigDecimal;

public class Bank {
    @FXML
    private BigDecimal balance;
    @FXML
    private Label balanceLabel;


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
        updateBalanceLabel();
    }

    private void updateBalanceLabel() {
        if (balanceLabel != null) {
            balanceLabel.setText(balance.toString());
        }
    }
}
