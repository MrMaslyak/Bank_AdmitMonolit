package org.example.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseAuth {


    public static void saveTokenToDatabase(int userId, String token) {
        String query = "UPDATE bank_user_auth_token SET token = ?, created_at = CURRENT_TIMESTAMP  WHERE user_id = ?";
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




}
