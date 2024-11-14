package org.example.bank.database;

import org.example.bank.controller.Registration;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccount {


    private static DatabaseAccount dataBase;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "LaLa27418182";
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DatabaseR.class);


    public DatabaseAccount() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Connected to the PostgreSQL server successfully.");
            connection.close();
        } catch (Exception e) {
            logger.error("Connection failure.", e);
        }
    }

    public static DatabaseAccount getInstance() {
        if (dataBase == null) {
            dataBase = new DatabaseAccount();
            logger.info("DatabaseAccount instance created.");
        }
        else {
            logger.error("DatabaseAccount instance already exists.");
        }
        return dataBase;
    }

    public BigDecimal getUserBalance(String login) {
        String query = "SELECT balance FROM bankUsers WHERE login = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBigDecimal("balance");
            }
        } catch (Exception e) {
            logger.error("Ошибка при извлечении баланса пользователя.", e);
        }
        return BigDecimal.ZERO;
    }

}
