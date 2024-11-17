package org.example.bank.database;

import org.example.bank.database.repository.IDB;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccount implements IDB {


    private static volatile DatabaseAccount instance;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DatabaseAccount.class);


    DatabaseAccount() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            logger.error("Connection failure during initialization.", e);
        }
    }

    public static DatabaseAccount getInstance() {
        if (instance == null) {
            synchronized (DatabaseR.class) {
                if (instance == null) {
                    instance = new DatabaseAccount();
                    logger.info("DatabaseAccount instance created.");
                }
            }
        }
        return instance;
    }

    public void addAccount(int userId) {
        String query = "INSERT INTO bankaccounts (user_id, balance, credit_limit, amount_usd, amount_eur, amount_uan) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.setBigDecimal(2, BigDecimal.ZERO);
            statement.setBigDecimal(3, BigDecimal.ZERO);
            statement.setBigDecimal(4, BigDecimal.ZERO);
            statement.setBigDecimal(5, BigDecimal.ZERO);
            statement.setBigDecimal(6, BigDecimal.ZERO);

            statement.executeUpdate();

            logger.info("Account added successfully for user ID: {}", userId);
        } catch (Exception e) {
            logger.error("Error during account addition.", e);
        }
    }

    public BigDecimal getBalance(int userId) {
        String query = "SELECT balance FROM bankaccounts WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    logger.info("Balance retrieved successfully for user ID {}: {}", userId, balance);
                    return balance;
                } else {
                    logger.warn("No account found for user ID: {}", userId);
                }
            }
        } catch (Exception e) {
            logger.error("Error during balance retrieval.", e);
        }
        return BigDecimal.ZERO;
    }







}
