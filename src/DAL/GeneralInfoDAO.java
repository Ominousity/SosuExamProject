package DAL;

import BE.GeneralInfo;

import java.io.IOException;
import java.sql.*;
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
                String roller = rs.getString("Roller");
                String vaner = rs.getString("Vaner");
                String uddanelseJob = rs.getString("UddanelseJob");
                String livshistorie = rs.getString("Livshistorie");
                String netværk = rs.getString("Netværk");
                String helbredsoplysninger = rs.getString("Helbredsoplysninger");
                String hjælpemidler = rs.getString("Hjølpemidler");
                String boligensIndretning = rs.getString("BoligensIndretning");

                generalInfo = new GeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livshistorie, netværk, helbredsoplysninger, hjælpemidler, boligensIndretning);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return generalInfo;
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netværk, String helbresoplysninger, String hjælpemidler, String boligensIndretning){
        try(Connection conn = connection.getConnection())
        {
            String sql = "INSERT INTO GeneralInfo(Mestring, Motivation, Ressourcer, Roller, Vaner, UddanelseJob, Livshistorie, Netværk, Helbredsoplysninger, Hjælpemidler, BoligensIndretning)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?);";
            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                
            }
        }
        GeneralInfo generalInfo = new GeneralInfo("Mestring","Motivstion","Ressourcer");
    }

    public void updateInfo(GeneralInfo generalInfo) {
    }
}

