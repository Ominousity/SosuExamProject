package BLL;

import BE.SubCategory;
import DAL.SubCategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubCategoryManager {
    SubCategoryDAO subCategoryDAO;

    public SubCategoryManager() throws IOException
    {
        subCategoryDAO = new SubCategoryDAO();
    }

    public List<SubCategory> getSubCategories(int SubCategoryID) {
        return subCategoryDAO.getSubCategories(SubCategoryID);
    }

    public void createSubCategory(String subCatName, String subCatContents, int categoryID){
        subCategoryDAO.createSubCategory(subCatName, subCatContents, categoryID);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryDAO.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) {
        subCategoryDAO.deleteSubCategory(SubCategoryID);
    }

}
