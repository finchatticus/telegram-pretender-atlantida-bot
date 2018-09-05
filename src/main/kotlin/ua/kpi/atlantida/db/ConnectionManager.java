package ua.kpi.atlantida.db;

import org.telegram.telegrambots.meta.logging.BotLogger;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vlad on 07.09.17.
 */
class ConnectionManager {

    private static final String TAG = ConnectionManager.class.getSimpleName();

    //TODO: read from properties
    private static final String DB_NAME = "pretender.db";
    private static final String URL = "jdbc:sqlite:/Users/android/Documents/" + DB_NAME;

    private static ConnectionManager instance;

    private AtomicInteger openCounter = new AtomicInteger();
    private Connection currentConnection;

    private ConnectionManager() {
    }

    static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    synchronized Connection openConnection() {
        BotLogger.info(TAG, "Open connection START: " + openCounter);
        if (openCounter.incrementAndGet() == 1) {
            try {
                DriverManager.registerDriver(new org.sqlite.JDBC());
                currentConnection = DriverManager.getConnection(URL);
                if (currentConnection != null) {
                    BotLogger.info(TAG, "Connection != null");
                }
            } catch (SQLException e) {
                BotLogger.error(TAG, e);
            }
        }
        BotLogger.info(TAG, "Open connection END: " + openCounter);
        return currentConnection;
    }

    synchronized void closeConnection() {
        BotLogger.info(TAG, "Close connection START: " + openCounter);
        if (openCounter.decrementAndGet() == 0) {
            // Closing database
            try {
                currentConnection.close();
            } catch (SQLException e) {
                BotLogger.error(TAG, e);
            }
        }
        BotLogger.info(TAG, "Close connection END: " + openCounter);
    }

}