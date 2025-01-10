package org.example.bank.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseAuthorizationAccount {



    public static void saveTokenToDatabase(int userId, String token) {
        String query = "UPDATE bank_user_auth_token SET token = ? WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сохранении токена в базу данных");
        }
    }

    public static String takeHashedPasswordDB(String login) {
        String query = "SELECT password FROM bankusers WHERE login = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    return resultSet.getString("password");
                } else {
                    throw new RuntimeException("Пользователь с логином " + login + " не найден.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении хешированного пароля", e);
        }
    }
}
