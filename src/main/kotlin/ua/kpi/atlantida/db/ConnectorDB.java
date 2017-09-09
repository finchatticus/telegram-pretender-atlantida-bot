package ua.kpi.atlantida.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vlad on 07.09.17.
 */
class ConnectorDB {
    //TODO: read from properties
    private static final String URL = "jdbc:sqlite:/home/vlad/telegram-bot/pretender.db";

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        Connection conn = DriverManager.getConnection(URL);
        return conn;
    }
}
