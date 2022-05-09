package DAL;

import BE.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO
{
    private DatabaseConnector connection;

    public CategoryDAO() throws IOException {
        connection = new DatabaseConnector();
    }

    public List<Category> getAllCategories(int citizenID) {
        ArrayList<Category> categories = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Category WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, citizenID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String catName = rs.getString("CatName");

                Category category = new Category(catName, ID);
                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Creates a Category
     *
     * @param CatName The name of Category
     * @param citizenID The ID of the Citizen
     * @return the coordinator object
     */
    public void createCategory(String CatName, int citizenID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Category(CatName,CitizenID) VALUES (?, ?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, CatName);
                preparedStatement.setInt(2, citizenID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Category SET CatName=?;";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, category.getCatName());
                preparedStatement.setInt(2, category.getID());
                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Category");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCategory(int categoryID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM SubCategory WHERE CategoryID=?;";
            String sql2 = "DELETE FROM Category WHERE ID=?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,categoryID);
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setInt(1,categoryID);
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
