package DAL;

import BE.Category;
import BE.Citizen;
import BE.Student;

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

                Citizen citizen = new Citizen(ID, FName, LName, Address, CPR);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public ArrayList<Citizen> getAllCitizensStudent(int StudentID) throws SQLException {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM StuCit WHERE StudentID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, StudentID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Address = rs.getString("Address");
                String CPR = rs.getString("CPR");

                Citizen citizen = new Citizen(ID, FName, LName, Address, CPR);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public void createCitizen(String FName, String LName,String Address,String CPR) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(FName,LName,Address,CPR) VALUES (?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Address);
                preparedStatement.setString(4, CPR);
                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCitizenToStudent(Citizen citizen, Student student) throws SQLException {

        try(Connection conn = connection.getConnection()) {
            String sql = "INSERT INTO StuCit VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, student.getID());
            preparedStatement.setInt(2, citizen.getID());
            preparedStatement.executeUpdate();
        }
    }

    public void updateCitizen(Citizen citizen) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Citizen SET FName=?, LName=?, Address=?, CPR=?;";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, citizen.getFName());
                preparedStatement.setString(2, citizen.getLName());
                preparedStatement.setString(3, citizen.getAddress());
                preparedStatement.setString(4, citizen.getCPR());
                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Citizen");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM StuCit WHERE CitizenID = (?) AND StudentID = (?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizen.getID());
            preparedStatement.setInt(2, student.getID());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteCitizen(int citizenID) {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM CitizenCase WHERE CitizenID=?," +
                          "DELETE FROM Citizen WHERE ID=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,citizenID);
            preparedStatement1.setInt(2,citizenID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
