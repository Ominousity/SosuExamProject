package BLL;

import BE.SubCategory;
import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

import java.sql.SQLException;

public class CategoryManager {
    private CategoryDAO categoryDAO;
    private SubCategoryDAO subCategoryDAO;

    public CategoryManager(){
        categoryDAO = new CategoryDAO();
        subCategoryDAO = new SubCategoryDAO();
    }

    public void updateSubCategory(SubCategory subCatContents) throws SQLException {
        subCategoryDAO.updateSubCategory(subCatContents);
    }
}
