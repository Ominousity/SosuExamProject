package BLL;

import BE.Category;
import DAL.CategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {
    private CategoryDAO categoryDAO;

    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories(int citizenID) {
        return categoryDAO.getAllCategories(citizenID);
    }

    public Category createCategory(String catName, boolean isFuncHealth, String catColor, int citizenID){
        return categoryDAO.createCategory(catName, isFuncHealth, catColor, citizenID);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(int categoryID) throws SQLException{
        categoryDAO.deleteCategory(categoryID);
    }
}
