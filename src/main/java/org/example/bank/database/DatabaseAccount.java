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
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "LaLa27418182";
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DatabaseAccount.class);


    private DatabaseAccount() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
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

    public BigDecimal getUserBalance(String login) {
        String query = "SELECT balance FROM bankUsers WHERE login = ?";
        try (Connection connection = getConnection();
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


    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
