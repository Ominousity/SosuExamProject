package BLL;

import BE.SubCategory;
import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

public class CategoryManager {
    private CategoryDAO categoryDAO;
    private SubCategoryDAO subCategoryDAO;

    public CategoryManager(){
        categoryDAO = new CategoryDAO();
        subCategoryDAO = new SubCategoryDAO();
    }

    public void updateSubCategory(String subCatContents){
        subCategoryDAO.updateSubCategory(subCatContents);
    }
}
