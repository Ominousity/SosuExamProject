package DAL;

import BE.Citizen;
import BE.User;
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
                int age = rs.getInt("Age");
                boolean isTemplate = rs.getBoolean("IsTemplate");

                Citizen citizen = new Citizen(ID, fName, lName, age, isTemplate);
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

            String sql = "SELECT * FROM Citizen c INNER JOIN UserCitizen uc ON uc.CitizenID = c.ID WHERE uc.UserID = ?;"; //sql command
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, StudentID);

            //Extract data from DB
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String fName = resultSet.getString("FName");
                    String lName = resultSet.getString("LName");
                    int age = resultSet.getInt("Age");
                    boolean isTemplate = resultSet.getBoolean("IsTemplate");

                    Citizen citizen = new Citizen(ID, fName, lName, age, isTemplate);
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
                    int age = resultSet.getInt("Age");
                    boolean isTemplate = resultSet.getBoolean("IsTemplate");

                    Citizen citizen = new Citizen(ID, fName, lName, age, isTemplate);
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
     * @param age
     * @param schoolID
     * @return
     */
    public Citizen createCitizen(String fName, String lName, int age, boolean isTemplate, int schoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(FName,LName,Age,IsTemplate,SchoolID) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, lName);
                preparedStatement.setInt(3, age);
                preparedStatement.setBoolean(4, isTemplate);
                preparedStatement.setInt(5, schoolID);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                int ID = 0;
                if(rs.next()){
                    ID = rs.getInt(1);
                }

                Citizen citizen = new Citizen(ID, fName, lName, age, isTemplate);
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
            String sql = "INSERT INTO UserCitizen VALUES (?,?);";
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
            String sqlStatement = "UPDATE Citizen SET FName=?, LName=?, Age=? WHERE ID =?";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, citizen.getFName());
                preparedStatement.setString(2, citizen.getLName());
                preparedStatement.setInt(3, citizen.getAge());
                preparedStatement.setInt(4, citizen.getID());

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
     * @param user
     * @throws SQLException
     */
    public void removeCitizenFromStudent(Citizen citizen, User user) throws SQLException {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM UserCitizen WHERE CitizenID=? AND StudentID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizen.getID());
            preparedStatement.setInt(2, user.getID());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Deletes a Citizen
     * @param citizenID
     */
    public void deleteCitizen(int citizenID) {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM Citizen WHERE ID=?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,citizenID);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
