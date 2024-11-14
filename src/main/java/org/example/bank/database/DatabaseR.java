package org.example.bank.database;

import javafx.application.Platform;
import org.example.bank.controller.Registration;
import org.example.bank.database.repository.IDB;
import org.slf4j.Logger;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class DatabaseR implements IDB {


    private static DatabaseR dataBase;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "LaLa27418182";
    private Registration register;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DatabaseR.class);

    public DatabaseR() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Connected to the PostgreSQL server successfully.");
            connection.close();
        } catch (Exception e) {
            logger.error("Connection failure.", e);
        }
    }

    public static DatabaseR getInstance() {
        if (dataBase == null) {
            dataBase = new DatabaseR();
            logger.info("DatabaseR instance created.");
        }
        else {
            logger.error("DatabaseR instance already exists.");
        }
        return dataBase;
    }




    public boolean passBank(String login, String password){

        String query = "SELECT * FROM bankUsers WHERE login = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Пользователь найден в базе.");
                return true;
            } else {
                logger.warn("Пользователь не найден в базе.");
                return false;
            }
        } catch (Exception e) {
            logger.error("Ошибка при поиске пользователя.", e);
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void addUser(String login, String password, String email){

        String query = "INSERT INTO bankUsers (login, password, email) VALUES (?, ?, ?) " +
                "ON CONFLICT (login) DO UPDATE SET password = EXCLUDED.password, email = EXCLUDED.email";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
            logger.info("Пользователь добавлен или обновлен успешно.");

        } catch (Exception e) {
            logger.error("Ошибка при добавлении пользователя.", e);
            e.printStackTrace();
        }
    }

    public boolean availableLogin(String login) {
        String query = "SELECT * FROM bankUsers WHERE login = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.warn("Пользователь существует в базе.");
                Platform.runLater(() -> register.showError("User with this login already exists"));
                return true;
            } else {
                logger.info("Пользователь не найден в базе.");
                return false;
            }
        } catch (Exception e) {
            logger.error("Ошибка при поиске пользователя.", e);
            e.printStackTrace();
        }
        return false;
    }


    public boolean availableEmail(String email) {
        String query = "SELECT * FROM bankUsers WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.warn("Email такой существует в базе.");
                Platform.runLater(() -> register.showError("This Email already exists"));
                return true;
            } else {
                logger.info("Email не найден в базе.");
                return false;
            }
        } catch (Exception e) {
            logger.error("Ошибка при поиске пользователя.", e);
            e.printStackTrace();
        }
        return false;
    }


    public void setRegistration(Registration registration) {
        this.register = registration;
    }

}
