package org.example.bank.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DatabaseGetter {


    private static volatile DatabaseGetter instance;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseGetter.class);

    private DatabaseGetter() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            logger.error("Connection failure during initialization.", e);
        }

    }

    public static DatabaseGetter getInstance() {
        if (instance == null) {
            synchronized (DatabaseGetter.class) {
                if (instance == null) {
                    instance = new DatabaseGetter();
                    logger.info("DatabaseR instance created.");
                }
            }
        }
        return instance;
    }


    public static int getUserId(String login) {
        String query = "SELECT user_id FROM bankusers WHERE login = ?";
        try (Connection connection = DatabaseConnection.getConnection();
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


    public static String getHashedPasswordDB(String login) {
        String query = "SELECT password FROM bankusers WHERE login = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    return resultSet.getString("password");
                } else {
                    System.out.println("Пользователь с логином " + login + " не найден.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении хешированного пароля", e);
        }
        return null;
    }

    public static String getTokenDB(int userId) {
        String query = "SELECT token FROM bank_user_auth_token WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("token");
                } else {
                    System.out.println("Пользователь с user_id " + userId + " не найден.");
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении токена", e);
        }
    }

    public static String getBalanceUserDB(int userId) {
        String query = "SELECT balance_usd FROM bank_user_balance WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("balance_usd");
                } else {
                    System.out.println("Пользователь с user_id " + userId + " не найден.");
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении баланса", e);
        }
    }

    public static Date getTokenCreatedAt(String token) {
        String query = "SELECT created_at FROM bank_user_auth_token WHERE token = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, token);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getTimestamp("created_at");
                } else {
                    logger.info("Пользователь с таким token: " + token + " не найден.");
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении даты создания токена", e);
        }
    }



}
