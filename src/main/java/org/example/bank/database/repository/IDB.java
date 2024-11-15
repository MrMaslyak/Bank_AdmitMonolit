package org.example.bank.database.repository;

import org.example.bank.database.DatabaseAccount;

import java.sql.Connection;

public interface IDB {

    static DatabaseAccount getInstance() {
        return null;
    }
   Connection getConnection() throws Exception;


}
