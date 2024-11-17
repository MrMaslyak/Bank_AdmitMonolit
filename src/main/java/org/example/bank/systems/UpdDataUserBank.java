package org.example.bank.systems;

import org.example.bank.database.DatabaseAccount;
import org.example.bank.database.DatabaseR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class UpdDataUserBank {

    private static final Logger logger = LoggerFactory.getLogger(UpdDataUserBank.class);
    private final DatabaseR databaseR;
    private final DatabaseAccount databaseAccount;
    private int userId;
    private BigDecimal balance;

    public UpdDataUserBank(DatabaseR databaseR, DatabaseAccount databaseAccount) {
        this.databaseR = databaseR;
        this.databaseAccount = databaseAccount;
    }

    public String getBalance() {
        try {
            balance = databaseAccount.getBalance(userId);
            logger.info("Balance retrieved successfully for user ID {}: {}", userId, balance);
        } catch (Exception e) {
            logger.error("Error during balance retrieval.", e);
        }
        return balance.toString();

    }

    public void setUserId(int userId) {
        this.userId = userId;
        logger.info("User ID set to {}", userId);
    }




}
