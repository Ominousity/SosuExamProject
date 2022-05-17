package DAL;

import BE.SubCategory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryDAO
{
    private DatabaseConnector connection;

    public SubCategoryDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

    /**
     * The method makes a list of all the SubCategories in the database.
     * @param SubCategoryID
     * @return
     * @throws SQLException
     */
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

                SubCategory subCategory = new SubCategory(ID, SubCatName, SubCatContents);
                subCategories.add(subCategory);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return subCategories;
    }

    /**
     * The method create a SubCategory in the database.
     * @param SubCatName
     * @param SubCatContents
     */
    public void createSubCategory(String SubCatName, String SubCatContents) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO SubCategory(SubCatName,SubCatContents, CategoryID) VALUES (?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, SubCatName);
                preparedStatement.setString(2, SubCatContents);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method updates the SubCategory in the database.
     * @param subCategory
     * @throws SQLException
     */
    public void updateSubCategory(SubCategory subCategory) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE SubCategory SET SubCatName, SubCatContents";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, subCategory.getSubCatName());
                preparedStatement.setString(2, subCategory.getSubCatContents());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update SubCategory");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method deletes a SubCategory in the database.
     * @param SubCategoryID
     * @throws SQLException
     */
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
