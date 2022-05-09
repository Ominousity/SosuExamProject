package BLL;

import BE.Category;
import BE.SubCategory;
import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {
    private CategoryDAO categoryDAO;
    private SubCategoryDAO subCategoryDAO;

    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
        subCategoryDAO = new SubCategoryDAO();
    }

    public List<Category> getAllCategories(int citizenID) {
        return categoryDAO.getAllCategories(citizenID);
    }

    public void createCategory(String CatName, int citizenID){
        categoryDAO.createCategory(CatName, citizenID);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(int categoryID) throws SQLException{
        categoryDAO.deleteCategory(categoryID);
    }


}
