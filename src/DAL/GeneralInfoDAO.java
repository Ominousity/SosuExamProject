package DAL;

import BE.GeneralInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralInfoDAO {
    private DatabaseConnector connection;

    public GeneralInfoDAO() throws IOException {
        connection = new DatabaseConnector();
    }

    public GeneralInfo getGeneralInfo(int CitizenID) throws SQLException {
        try (Connection conn = connection.getConnection()) {
            String sql = "SELECT * FROM GeneralInfo WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, CitizenID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mestring = rs.getString("Mestring");
                String motivation = rs.getString("Motivation");
                String ressourcer = rs.getString("Ressourcer");
            }
            GeneralInfo generalInfo = new GeneralInfo("Mestring","Motivstion","Ressourcer");
        }

        return getGeneralInfo(1);
    }
}

