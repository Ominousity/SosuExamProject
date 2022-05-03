package BLL;

import BE.Category;
import BE.SubCategory;
import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager {
    private CategoryDAO categoryDAO;
    private SubCategoryDAO subCategoryDAO;

    public CategoryManager(){
        categoryDAO = new CategoryDAO();
        subCategoryDAO = new SubCategoryDAO();
    }

    public List<Category> getAllCategories(int citizenID) throws SQLException{
        return categoryDAO.getAllCategories(citizenID);
    }

    public void createCategory(String CatName, String CitizenID){
        categoryDAO.createCategory(CatName, CitizenID);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(int categoryID) throws SQLException{
        categoryDAO.deleteCategory(categoryID);
    }


}
