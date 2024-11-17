package org.example.bank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseAccount;
import org.example.bank.database.DatabaseR;
import org.example.bank.systems.UpdDataUserBank;
import org.slf4j.Logger;
import java.math.BigDecimal;

public class Bank {
    @FXML
    private Label balanceLabel;
    private UpdDataUserBank bankDataUser;

    public void initialize() {
        bankDataUser = new UpdDataUserBank(DatabaseR.getInstance(), DatabaseAccount.getInstance());
    }

    public void updateUI(String balance) {
        balanceLabel.setText(balance);
    }



}
