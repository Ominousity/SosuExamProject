package DAL;

import BE.School;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO
{
    private DatabaseConnector connection = DatabaseConnector.getInstance();

    public SchoolDAO() throws IOException {
    }

    /**
     * The method helps to make a list of Schools in the datebase.
     * @return
     */

    public List<School> getSchool() {
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

    /**
     * The method creates a School in the database.
     * @param SchoolName
     */
    public void createSchool(String SchoolName) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Citizen(SchoolName) VALUES (?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(2, SchoolName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method updates a School in the database.
     * @param school
     * @throws SQLException
     */
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

    /**
     * The method deletes a School form the database
     * @param SchoolID
     */
    public void deleteSchool(int SchoolID) {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM School WHERE ID = ?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,SchoolID);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
