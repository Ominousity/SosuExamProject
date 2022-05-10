package DAL;

import BE.Citizen;
import BE.School;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO
{
    private DatabaseConnector connection;

    public SchoolDAO() throws IOException {
        connection = new DatabaseConnector();
    }

    public List<School> getSchool() throws SQLException {
        ArrayList<School> schools = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM School;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String SchoolName = rs.getString("SchoolName");

                School school = new School(ID, SchoolName);
                schools.add(school);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return schools;
    }

    public void createSchool(int SchoolID, String SchoolName) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(SchoolID,SchoolName) VALUES (?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, SchoolID);
                preparedStatement.setString(2, SchoolName);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSchool(School school) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE School SET SchoolName";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, school.getSchoolName());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update School");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSchool(int SchoolID, int StudentID, int CategoryID, int CitizenID,int GeneralinfoID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM Teacher WHERE SchoolID=?, DELETE FROM Student WHERE SchoolID=?, DELETE FROM Admin WHERE SchoolID=?, " +
                    "DELETE FROM StuCit WHERE StudentID=?, DELETE FROM SubCategory WHERE CategoryID=?, DELETE FROM Category WHERE CitizenID=?," +
                    "DELETE FROM CitizenCase WHERE CitizenID=?, DELETE FROM Generalinfo WHERE GeneralinfoID=?, DELETE FROM Citizen WHERE SchoolID=?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,SchoolID);
            preparedStatement1.setInt(2,SchoolID);
            preparedStatement1.setInt(3,SchoolID);
            preparedStatement1.setInt(4,StudentID);
            preparedStatement1.setInt(5,CategoryID);
            preparedStatement1.setInt(6,CitizenID);
            preparedStatement1.setInt(7,CitizenID);
            preparedStatement1.setInt(8,GeneralinfoID);
            preparedStatement1.setInt(9,SchoolID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
