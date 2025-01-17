package org.example.bank.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseBank {

    private static volatile DatabaseBank instance;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseBank.class);

    public DatabaseBank() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            logger.error("Connection failure during initialization.", e);
        }

    }

    public static DatabaseBank getInstance() {
        if (instance == null) {
            synchronized (DatabaseBank.class) {
                if (instance == null) {
                    instance = new DatabaseBank();
                    logger.info("DatabaseR instance created.");
                }
            }
        }
        return instance;
    }




    public static void updateBalanceUserDB(int userId, double balance) {
        String query = "UPDATE bank_user_balance SET balance_usd = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Баланс пользователя с user_id " + userId + " обновлен.");
            } else {
                logger.info("Пользователь с user_id " + userId + " не найден.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при обновлении баланса", e);
        }
    }
}
