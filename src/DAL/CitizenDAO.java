package DAL;

import BE.Citizen;
import BE.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO
{
    private DatabaseConnector connection = DatabaseConnector.getInstance();

    public CitizenDAO() throws IOException {
    }

    /**
     * Makes an arraylist with all the citizens, that is controlled by the SchoolID
     * @param schoolID
     * @return
     */
    public List<Citizen> getAllCitizensSchool(int schoolID) {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Citizen WHERE SchoolID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, schoolID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                String sex = rs.getString("Sex");
                boolean isTemplate = rs.getBoolean("IsTemplate");

                Citizen citizen = new Citizen(ID, fName, lName, address, dob, sex, isTemplate);
                citizens.add(citizen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return citizens;
    }

    /**
     * Makes an arraylist with all the students who are putted on a specific citizen, that is controlled by the StudentID
     * @param StudentID
     * @return
     */
    public ArrayList<Citizen> getCitizensFromStudent(int StudentID) {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()) {

            String sql = "SELECT * FROM [SOSU_Eksammen].[dbo].[Citizen] INNER JOIN StuCit ON StuCit.CitizenID = Citizen.ID WHERE StudentID =(?);"; //sql command
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, StudentID);

            //Extract data from DB
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String fName = resultSet.getString("FName");
                    String lName = resultSet.getString("LName");
                    String address = resultSet.getString("Address");
                    String dob = resultSet.getString("DOB");
                    String sex = resultSet.getString("Sex");
                    boolean isTemplate = resultSet.getBoolean("IsTemplate");

                    Citizen citizen = new Citizen(ID, fName, lName, address, dob, sex, isTemplate);
                    citizens.add(citizen);
                }
            }
        }catch (SQLException e){
                e.printStackTrace();

        }
        return citizens;
    }

    public ArrayList<Citizen> getTemplateCitizens() {
        ArrayList<Citizen> citizens = new ArrayList<>();

        try(Connection conn = connection.getConnection()) {

            String sql = "SELECT * FROM Citizen WHERE isTemplate = 1;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Extract data from DB
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String fName = resultSet.getString("FName");
                    String lName = resultSet.getString("LName");
                    String address = resultSet.getString("Address");
                    String dob = resultSet.getString("DOB");
                    String sex = resultSet.getString("Sex");
                    boolean isTemplate = resultSet.getBoolean("IsTemplate");

                    Citizen citizen = new Citizen(ID, fName, lName, address, dob, sex, isTemplate);
                    citizens.add(citizen);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return citizens;
    }

    /**
     * Creates a Citizen with the information shown below
     * @param fName
     * @param lName
     * @param dob
     * @param address
     * @param sex
     * @param schoolID
     * @return
     */
    public Citizen createCitizen(String fName, String lName, String dob, String address, String sex, boolean isTemplate, int schoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(FName,LName,DOB,Address,Sex,IsTemplate,SchoolID) VALUES (?,?,?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, lName);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, dob);
                preparedStatement.setString(5, sex);
                preparedStatement.setBoolean(6, isTemplate);
                preparedStatement.setInt(7, schoolID);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                int ID = 0;
                if(rs.next()){
                    ID = rs.getInt(1);
                }

                Citizen citizen = new Citizen(ID, fName, lName, address, dob, sex, isTemplate);
                return citizen;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Makes a student available to a specific citizen
     * @param citizenID
     * @param studentID
     * @throws SQLException
     */
    public void createCitizenToStudent(int citizenID, int studentID) throws SQLException {

        try(Connection conn = connection.getConnection()) {
            String sql = "INSERT INTO StuCit VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, citizenID);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Updates the Citizen
     * @param citizen
     * @throws SQLException
     */
    public void updateCitizen(Citizen citizen) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Citizen SET FName=?, LName=?, Address=?, CPR=?;";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, citizen.getFName());
                preparedStatement.setString(2, citizen.getLName());
                preparedStatement.setString(3, citizen.getAddress());
                preparedStatement.setString(4, citizen.getSex());
                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Citizen");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes a student from a specific Citizen
     * @param citizen
     * @param student
     * @throws SQLException
     */
    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM StuCit WHERE CitizenID = (?) AND StudentID = (?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizen.getID());
            preparedStatement.setInt(2, student.getID());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Deletes a Citizen
     * @param citizenID
     */
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
