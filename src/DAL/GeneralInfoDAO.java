package DAL;

import BE.GeneralInfo;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralInfoDAO {
    private DatabaseConnector connection = DatabaseConnector.getInstance();

    public GeneralInfoDAO() throws IOException {
    }

    /**
     * The method makes generalInfo in the database.
     * @param CitizenID
     * @return
     */
    public GeneralInfo getGeneralInfo(int CitizenID) {
        GeneralInfo generalInfo = new GeneralInfo(0,"","","","","","","","","","","");
        try (Connection conn = connection.getConnection()) {
            String sql = "SELECT * FROM GeneralInfo WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, CitizenID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String mestring = rs.getString("Mestring");
                String motivation = rs.getString("Motivation");
                String ressourcer = rs.getString("Ressourcer");
                String roller = rs.getString("Roller");
                String vaner = rs.getString("Vaner");
                String uddanelseJob = rs.getString("UddanelseJob");
                String livshistorie = rs.getString("Livshistorie");
                String netværk = rs.getString("Netværk");
                String helbredsoplysninger = rs.getString("Helbredsoplysninger");
                String hjælpemidler = rs.getString("Hjælpemidler");
                String boligensIndretning = rs.getString("BoligensIndretning");
                generalInfo = new GeneralInfo(id, mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livshistorie, netværk, helbredsoplysninger, hjælpemidler, boligensIndretning);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return generalInfo;
    }

    /**
     * The method create a GeneralInfo in the database.
     * @param mestring
     * @param motivation
     * @param ressourcer
     * @param roller
     * @param vaner
     * @param uddanelseJob
     * @param livhistorie
     * @param netvaerk
     * @param helbredsoplysninger
     * @param hjaelpemidler
     * @param boligensIndretning
     */
    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netvaerk, String helbredsoplysninger, String hjaelpemidler, String boligensIndretning, int citizenID){
        try(Connection conn = connection.getConnection())
        {
            String sql = "INSERT INTO GeneralInfo(Mestring, Motivation, Ressourcer, Roller, Vaner, UddanelseJob, Livshistorie, Netværk, Helbredsoplysninger, Hjælpemidler, BoligensIndretning, CitizenID)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?);";
            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                preparedStatement.setString(1, mestring);
                preparedStatement.setString(2, motivation);
                preparedStatement.setString(3, ressourcer);
                preparedStatement.setString(4, roller);
                preparedStatement.setString(5, vaner);
                preparedStatement.setString(6, uddanelseJob);
                preparedStatement.setString(7, livhistorie);
                preparedStatement.setString(8, netvaerk);
                preparedStatement.setString(9, helbredsoplysninger);
                preparedStatement.setString(10, hjaelpemidler);
                preparedStatement.setString(11, boligensIndretning);
                preparedStatement.setInt(12, citizenID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * The method updates the info in the database.
     * @param generalInfo
     */
    public void updateInfo(GeneralInfo generalInfo) {
        try(Connection conn = connection.getConnection())
        {
            String sql = "UPDATE GeneralInfo SET Mestring=?, Motivation=?, Ressourcer=?, Roller=?, Vaner=?, UddanelseJob=?, Livhistorie=?, Netværk=?, Helbredsoplysninger=?, Hjælpemidler=?, BoligensIndretning=? WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, generalInfo.getMestring());
            preparedStatement.setString(2, generalInfo.getMotivation());
            preparedStatement.setString(3, generalInfo.getRessourcer());
            preparedStatement.setString(4, generalInfo.getRoller());
            preparedStatement.setString(5, generalInfo.getVaner());
            preparedStatement.setString(6, generalInfo.getUddannelseJob());
            preparedStatement.setString(7, generalInfo.getLivshistorie());
            preparedStatement.setString(8, generalInfo.getNetværk());
            preparedStatement.setString(9, generalInfo.getHelbredsoplysninger());
            preparedStatement.setString(10, generalInfo.getHjælpemidler());
            preparedStatement.setString(11, generalInfo.getBoligIndretning());
            preparedStatement.setInt(12, generalInfo.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update general Information");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

