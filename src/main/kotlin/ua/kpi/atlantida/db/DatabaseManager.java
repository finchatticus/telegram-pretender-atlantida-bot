package ua.kpi.atlantida.db;

import ua.kpi.atlantida.model.Pretender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vlad on 07.09.17.
 */
public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getSimpleName();
    private static final String SELECT_PRETENDER = "SELECT * FROM pretender WHERE chat_id = ?";
    private static final String INSERT_PRETENDER = "INSERT INTO pretender(chat_id, name, level, faculty, my_swimming_level, phone, email, profile, motivation, marketing, timestamp)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_PRETENDER = "UPDATE pretender SET name=?, level=?, faculty=?, my_swimming_level=?, phone=?, email=?, profile=?, motivation=?, marketing=?" +
            " WHERE chat_id = ?";
    private static final String DELETE_PRETENDER = "DELETE pretender WHERE chat_id = ?";

    private static volatile DatabaseManager instance;
    private static volatile ConnectionManager connectionManager;

    private DatabaseManager() {
        connectionManager = ConnectionManager.getInstance();
        final int currentVersion = getDbVersion();
//        BotLogger.info(TAG, "Current db version: " + currentVersion);
        if (currentVersion < CreationStrings.VERSION) {
            recreateTable(currentVersion);
        }
    }

    public static DatabaseManager getInstance() {
        final DatabaseManager currentInstance;
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

    private int getDbVersion() {
        try {
            final Statement statement = connectionManager.openConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery(CreationStrings.GET_CURRENT_VERSION);
            final int version = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            connectionManager.closeConnection();
            return version;
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        }
        return 0;
    }

    private void recreateTable(int currentVersion) {
        try {
            if (currentVersion == 0) {
                createNewTables();
            }
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        }
    }

    private int createNewTables() throws SQLException {
        final Statement statement = connectionManager.openConnection().createStatement();
        statement.executeUpdate(CreationStrings.CREATE_PRETENDER_TABLE);
        statement.close();
        connectionManager.closeConnection();
        return CreationStrings.VERSION;
    }

    public Pretender getPretenderById(long chatId) {
        Pretender pretender = null;
        try {
            final PreparedStatement ps = connectionManager.openConnection().prepareStatement(SELECT_PRETENDER);
            ps.setLong(1, chatId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pretender = new Pretender();
                pretender.setChatId(rs.getLong("chat_id"));
                pretender.setName(rs.getString("name"));
                pretender.setLevel(rs.getString("level"));
                pretender.setFaculty(rs.getString("faculty"));
                pretender.setMySwimmingRating(rs.getString("my_swimming_level"));
                pretender.setPhone(rs.getString("phone"));
                pretender.setEmail(rs.getString("email"));
                pretender.setProfile(rs.getString("profile"));
                pretender.setMotivation(rs.getString("motivation"));
                pretender.setMarketing(rs.getString("marketing"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        } finally {
            connectionManager.closeConnection();
        }
        return pretender;
    }

    public void insertPretender(Pretender pretender) {
        try {
            final PreparedStatement ps = connectionManager.openConnection().prepareStatement(INSERT_PRETENDER);
            ps.setLong(1, pretender.getChatId());
            ps.setString(2, pretender.getName());
            ps.setString(3, pretender.getLevel());
            ps.setString(4, pretender.getFaculty());
            ps.setString(5, pretender.getMySwimmingRating());
            ps.setString(6, pretender.getPhone());
            ps.setString(7, pretender.getEmail());
            ps.setString(8, pretender.getProfile());
            ps.setString(9, pretender.getMotivation());
            ps.setString(10, pretender.getMarketing());
            ps.setInt(11, (int) (System.currentTimeMillis() / 1000L));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    public void updatePretender(Pretender pretender) {
        try {
            final PreparedStatement ps = connectionManager.openConnection().prepareStatement(UPDATE_PRETENDER);
            ps.setString(1, pretender.getName());
            ps.setString(2, pretender.getLevel());
            ps.setString(3, pretender.getFaculty());
            ps.setString(4, pretender.getMySwimmingRating());
            ps.setString(5, pretender.getPhone());
            ps.setString(6, pretender.getEmail());
            ps.setString(7, pretender.getProfile());
            ps.setString(8, pretender.getMotivation());
            ps.setString(9, pretender.getMarketing());
            ps.setLong(10, pretender.getChatId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    public void deletePretender(Pretender pretender) {
        try {
            final PreparedStatement ps = connectionManager.openConnection().prepareStatement(DELETE_PRETENDER);
            ps.setLong(1, pretender.getChatId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
//            BotLogger.error(TAG, e);
        } finally {
            connectionManager.closeConnection();
        }
    }

}
