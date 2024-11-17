package org.example.bank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import java.math.BigDecimal;

public class Bank {
    @FXML
    private BigDecimal balance;
    @FXML
    private Label balanceLabel;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Bank.class);

    public void initialize() {
        logger.info("Bank page initialized");
        balanceLabel.setText("Balance: " + balance);
    }
}
