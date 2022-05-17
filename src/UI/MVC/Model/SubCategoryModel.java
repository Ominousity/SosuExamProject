package UI.MVC.Model;

import BE.SubCategory;
import BLL.SubCategoryManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubCategoryModel {

    SubCategoryManager subCategoryManager;
    public SubCategoryModel() throws IOException
    {
        subCategoryManager = new SubCategoryManager();
    }

    public List<SubCategory> getSubCategories(int SubCategoryID) throws SQLException {
        return subCategoryManager.getSubCategories(SubCategoryID);
    }

    public void createSubCategory(String subCatName, String subCatContents, int categoryID){
        subCategoryManager.createSubCategory(subCatName, subCatContents, categoryID);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryManager.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException{
        subCategoryManager.deleteSubCategory(SubCategoryID);
    }

}
