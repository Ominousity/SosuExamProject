package DAL;

import BE.Category;
import BE.SubCategory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryDAO
{
    private DatabaseConnector connection = DatabaseConnector.getInstance();

    public SubCategoryDAO() throws IOException {
    }

    /**
     * The method makes a list of all the SubCategories in the database.
     * @param categoryID
     * @return
     */
    public List<SubCategory> getSubCategories(int categoryID) {
        ArrayList<SubCategory> subCategories = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM SubCategory WHERE CategoryID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, categoryID);

            if (preparedStatement.execute()) {
                ResultSet rs = preparedStatement.getResultSet();
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String subCatName = rs.getString("SubCatName");
                    String subCatContents = rs.getString("SubCatContents");

                    SubCategory subCategory = new SubCategory(ID, subCatName, subCatContents);
                    subCategories.add(subCategory);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return subCategories;
    }

    /**
     * The method create a SubCategory in the database.
     * @param subCatName
     * @param subCatContents
     */
    public void createSubCategory(String subCatName, String subCatContents, int categoryID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO SubCategory(SubCatName,SubCatContents,CategoryID) VALUES (?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, subCatName);
                preparedStatement.setString(2, subCatContents);
                preparedStatement.setInt(3, categoryID);
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
            String sqlStatement = "UPDATE SubCategory SET SubCatContents=? WHERE ID=?";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, subCategory.getSubCatContents());
                preparedStatement.setInt(2, subCategory.getSubCatID());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update SubCategory");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
