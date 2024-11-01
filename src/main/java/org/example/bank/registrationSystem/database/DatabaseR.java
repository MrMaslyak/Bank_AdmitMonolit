package org.example.bank.registrationSystem.database;

import org.example.bank.registrationSystem.database.repository.IDB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseR   implements IDB {



        private static DatabaseR dataBase;
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "LaLa27418182";

        public DatabaseR() {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connected to the PostgreSQL server successfully.");


                connection.close();

            } catch (Exception e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }

        public static DatabaseR getInstance() {
            if (dataBase == null) {
                dataBase = new DatabaseR();
            }
            return dataBase;
        }

    @Override
    public void save_music(String author, String name, String duration, File musicFile) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void delete(int musicId) {

    }

    @Override
    public File getMusicFromDatabase(int musicId) {
        return null;
    }

    @Override
    public int getMusicIdByName(String name) {
        return 0;
    }
}
