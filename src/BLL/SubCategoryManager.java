package BLL;

import BE.SubCategory;
import DAL.SubCategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class SubCategoryManager {
    SubCategoryDAO subCategoryDAO;

    public List<SubCategory> getSubCategories(int SubCategoryID) throws SQLException{
        return subCategoryDAO.getSubCategories(SubCategoryID);
    }

    public void createSubCategory(String SubCatName, String SubCatContents){
        subCategoryDAO.createSubCategory(SubCatName, SubCatContents);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryDAO.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException{
        subCategoryDAO.deleteSubCategory(SubCategoryID);
    }

}
