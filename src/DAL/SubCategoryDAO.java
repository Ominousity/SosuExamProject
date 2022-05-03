package DAL;

import BE.Category;
import BE.School;
import BE.Student;
import BE.SubCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryDAO
{
    private DatabaseConnector connection;

    public List<SubCategory> getSubCategories(int SubCategoryID) throws SQLException {
        ArrayList<SubCategory> subCategories = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM SubCategory WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, SubCategoryID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String SubCatName = rs.getString("SubCatName");
                String SubCatContents = rs.getString("SubCatContents");
                int CategoryID = rs.getInt("CategoryID");

                SubCategory subCategory = new SubCategory(ID, SubCatName, SubCatContents, CategoryID);
                subCategories.add(subCategory);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return subCategories;
    }

    public void createSubCategory(String SubCatName, String SubCatContents, int CategoryID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO SubCategory(SubCatName,SubCatContents, CategoryID) VALUES (?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, SubCatName);
                preparedStatement.setString(2, SubCatContents);
                preparedStatement.setInt(3, CategoryID);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE SubCategory SET SubCatName, SubCatContents, CategoryID";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, subCategory.getSubCatName());
                preparedStatement.setString(2, subCategory.getSubCatContents());
                preparedStatement.setInt(3, subCategory.getCategoryID());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update SubCategory");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM SubCategory WHERE ID=?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            preparedStatement1.setInt(1,SubCategoryID);

            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
