package UI.MVC.Model;

import BE.SubCategory;
import BLL.SubCategoryManager;

import java.sql.SQLException;
import java.util.List;

public class SubCategoryModel {

    SubCategoryManager subCategoryManager;

    public List<SubCategory> getSubCategories(int SubCategoryID) throws SQLException {
        return subCategoryManager.getSubCategories(SubCategoryID);
    }

    public void createSubCategory(String SubCatName, String SubCatContents){
        subCategoryManager.createSubCategory(SubCatName, SubCatContents);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryManager.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException{
        subCategoryManager.deleteSubCategory(SubCategoryID);
    }

}
