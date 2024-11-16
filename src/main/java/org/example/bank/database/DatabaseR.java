package org.example.bank.database;

import org.example.bank.database.repository.IDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseR implements IDB {

    private static volatile DatabaseR instance;
    private static volatile DatabaseAccount dbAcc;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "LaLa27418182";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseR.class);

    private DatabaseR() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            logger.info("Connected to the PostgreSQL server successfully.");
            dbAcc = new DatabaseAccount();
        } catch (Exception e) {
            logger.error("Connection failure during initialization.", e);
        }
    }

    public static DatabaseR getInstance() {
        if (instance == null) {
            synchronized (DatabaseR.class) {
                if (instance == null) {
                    instance = new DatabaseR();
                    logger.info("DatabaseR instance created.");
                }
            }
        }
        return instance;
    }

    public boolean passBank(String login, String password) {
        String query = "SELECT * FROM bankUsers WHERE login = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            boolean exists = resultSet.next();

            if (exists) {
                logger.info("User authenticated successfully.");
            } else {
                logger.warn("Invalid login or password.");
            }
            return exists;
        } catch (Exception e) {
            logger.error("Error during user authentication.", e);
            return false;
        }
    }


    public void addUser(String login, String password, String email) {
        String query = "INSERT INTO bankUsers (login, password, email) VALUES (?, ?, ?) " +
                "ON CONFLICT (login) DO UPDATE SET password = EXCLUDED.password, email = EXCLUDED.email";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();

            int userId = getUserId(login);
            if (userId == -1) {
                logger.error("Failed to add account: user ID not found for login '{}'", login);
                return;
            }
            dbAcc.addAccount(userId);

            logger.info("User added or updated successfully.");
        } catch (Exception e) {
            logger.error("Error during user addition or update.", e);
        }
    }


    private int getUserId(String login) {
        String query = "SELECT user_id FROM bankusers WHERE login = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }
        } catch (Exception e) {
            logger.error("Error fetching user ID.", e);
        }
        return -1;
    }


    public boolean availableLogin(String login) {
        return isFieldExists("SELECT 1 FROM bankUsers WHERE login = ?", login);
    }

    public boolean availableEmail(String email) {
        return isFieldExists("SELECT 1 FROM bankUsers WHERE email = ?", email);
    }

    private boolean isFieldExists(String query, String fieldValue) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, fieldValue);
            ResultSet resultSet = statement.executeQuery();
            boolean exists = resultSet.next();

            if (exists) {
                logger.warn("{} already exists in the database.", fieldValue);
            } else {
                logger.info("{} is available for use.", fieldValue);
            }
            return exists;
        } catch (Exception e) {
            logger.error("Error checking field existence.", e);
            return false;
        }
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
