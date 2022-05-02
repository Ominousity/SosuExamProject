package DAL;

import BE.Category;
import BE.Citizen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO
{
    private DatabaseConnector connection;

    public List<Citizen> getAllCitizensTeacher(int SchoolID) throws SQLException {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Citizen WHERE SchoolID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, SchoolID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Address = rs.getString("Address");
                String CPR = rs.getString("CPR");

                Citizen citizen = new Citizen(ID, FName, LName, Address, CPR, SchoolID);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public List<Citizen> getAllCitizensStudent(int SchoolID) throws SQLException {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Citizen WHERE SchoolID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, SchoolID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Address = rs.getString("Address");
                String CPR = rs.getString("CPR");

                Citizen citizen = new Citizen(ID, FName, LName, Address, CPR, SchoolID);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public void createCitizen(String FName, String LName,String Address,String CPR,String SchoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(FName,LName,Address,CPR,SchoolID) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Address);
                preparedStatement.setString(4, CPR);
                preparedStatement.setString(5, SchoolID);
                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCitizen(Citizen citizen) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Citizen SET FName=?, LName=?, Address=?, CPR=?, SchoolID=?";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, citizen.getFName());
                preparedStatement.setString(2, citizen.getLName());
                preparedStatement.setString(3, citizen.getAddress());
                preparedStatement.setString(4, citizen.getCPR());
                preparedStatement.setInt(4, citizen.getSchoolID());
                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Citizen");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCitizen(int citizenID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM CitizenCase WHERE CitizenID=?";
            String sql2 = "DELETE FROM Citizen WHERE ID=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,citizenID);
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setInt(1,citizenID);
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
