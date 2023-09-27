package com.example.server.database;

import com.example.server.threads.Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    private Connection connection;
    public SchemaInitializer() throws SQLException {
       this.connection =  DatabaseService.getConnection();
    }

    public  void up() throws SQLException {
        createUsersTable();
    }

    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(100), type VARCHAR(10), password VARCHAR(100));");
//        createFirstUser();
    }

    public void createFirstUser() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO users (name, email, password, type) VALUES ('Admin', 'admin@example.com', '0192023A7BBD73250516F069DF18B500', 'admin')");
        connection.close();
    }

}
