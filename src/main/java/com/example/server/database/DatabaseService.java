package com.example.server.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {

    private static HikariDataSource dataSource;


    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:src/main/resources/com/example/server/database/database.db");

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void up(){
        SchemaInitializer builder = null;
        try {
            builder = new SchemaInitializer();
            builder.up();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
