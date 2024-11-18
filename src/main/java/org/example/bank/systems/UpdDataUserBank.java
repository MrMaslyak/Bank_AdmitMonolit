package org.example.bank.systems;

import org.example.bank.database.DatabaseAccount;
import org.example.bank.database.DatabaseR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class UpdDataUserBank {

    private static final Logger logger = LoggerFactory.getLogger(UpdDataUserBank.class);
    private final DatabaseAccount databaseAccount;
    private int userId;

    public UpdDataUserBank(DatabaseAccount databaseAccount) {
        this.databaseAccount = databaseAccount;
    }

    public String getBalance() {
        String balance = String.valueOf(databaseAccount.getBalance(userId));
        logger.info("Balance retrieved successfully for user ID {}: {}", userId, balance);
        return balance;

    }

    public String getLogin(){
        String login = databaseAccount.getLogin(userId);
        logger.info("Login retrieved successfully for user ID {}: {}", userId, login);
        return login;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        logger.info("User ID set to {}", userId);
    }




}
