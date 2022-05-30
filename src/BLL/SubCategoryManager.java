package BLL;

import BE.SubCategory;
import DAL.SubCategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubCategoryManager {
    SubCategoryDAO subCategoryDAO;

    public SubCategoryManager() throws IOException {
        subCategoryDAO = new SubCategoryDAO();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param SubCategoryID
     * @return
     */
    public List<SubCategory> getSubCategories(int SubCategoryID) {
        return subCategoryDAO.getSubCategories(SubCategoryID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param subCatName
     * @param subCatContents
     * @param categoryID
     */
    public void createSubCategory(String subCatName, String subCatContents, int categoryID) {
        subCategoryDAO.createSubCategory(subCatName, subCatContents, categoryID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param subCategory
     * @throws SQLException
     */
    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryDAO.updateSubCategory(subCategory);
    }
}
