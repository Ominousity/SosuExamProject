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
                String StudentFName = rs.getString("StudentFName");
                String StudentLName = rs.getString("StudentLName");

                Citizen citizen = new Citizen(ID, FName, LName, Address, CPR, StudentFName, StudentLName);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public ArrayList<Citizen> getCitizensFromStudent(int StudentID) throws SQLException {
        ArrayList<Citizen> citizensInStudent = new ArrayList<>();

        try(Connection conn = connection.getConnection()) {

            String sql = "SELECT * FROM [SOSU_Eksammen].[dbo].[Citizen] INNER JOIN StuCit ON StuCit.CitizenID = Citizen.ID WHERE StudentID =(?);"; //sql command
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, StudentID);

            //Extract data from DB
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String FName = resultSet.getString("FName");
                    String LName = resultSet.getString("LName");
                    String Address = resultSet.getString("Address");
                    String CPR = resultSet.getString("CPR");
                    String StudentFName = resultSet.getString("StudentFName");
                    String StudentLName = resultSet.getString("StudentLName");

                    citizensInStudent.add(new Citizen(ID, FName, LName, Address, CPR, StudentFName, StudentLName));
                }
            }
        }catch (SQLException e){
                e.printStackTrace();

        }
        return citizensInStudent;
    }

    public ArrayList<Citizen> getAllCitizensStudent(Citizen citizen, Student student, int StudentID) throws SQLException {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT FName, LName, Email FROM Student WHERE StudentID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, StudentID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                int SchoolID = rs.getInt("SchoolID");

                Student student1 = new Student(ID, FName, LName, Email, Password, SchoolID);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    public void createCitizen(String FName, String LName, String dob, String Address, String CPR) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(FName,LName,DOB,Address,CPR) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Address);
                preparedStatement.setString(4, dob);
                preparedStatement.setString(5, CPR);
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
