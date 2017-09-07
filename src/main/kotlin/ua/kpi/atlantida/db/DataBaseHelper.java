package ua.kpi.atlantida.db;

import ua.kpi.atlantida.model.Pretender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by vlad on 07.09.17.
 */
public class DataBaseHelper {

    private final static String INSERT_PRETENDER =
            "INSERT INTO pretender(name, level, faculty, swimming, swimming_level, phone, email, profile, motivation, marketing) " +
                    " VALUES(?,?,?,?,?,?,?,?,?,?)";

    private Connection connect;

    public DataBaseHelper() throws SQLException {
        connect = ConnectorDB.getConnection();
    }

    public boolean insertPretender(Pretender pretender) {
        System.out.println("insert");
        boolean flag = false;
        final PreparedStatement ps = getPrepearedStatement();
        try {
            ps.setString(1, pretender.getName());
            ps.setString(2, pretender.getLevel());
            ps.setString(3, pretender.getFaculty());
            ps.setString(4, pretender.getSwimming());
            ps.setString(5, pretender.getSwimmingLevel());
            ps.setString(6, pretender.getPhone());
            ps.setString(7, pretender.getEmail());
            ps.setString(8, pretender.getProfile());
            ps.setString(9, pretender.getMotivation());
            ps.setString(10, pretender.getMarketing());
            ps.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        System.out.println("insert flag " + flag);
        return flag;
    }

    private void closeStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private PreparedStatement getPrepearedStatement() {
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(INSERT_PRETENDER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

}
