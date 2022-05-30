package BLL;

import BE.Category;
import DAL.CategoryDAO;
import java.io.IOException;
import java.util.List;

public class CategoryManager {
    private CategoryDAO categoryDAO;

    public CategoryManager() throws IOException {
        categoryDAO = new CategoryDAO();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizenID
     * @return
     */
    public List<Category> getAllCategories(int citizenID) {
        return categoryDAO.getAllCategories(citizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param catName
     * @param isFuncHealth
     * @param citizenID
     * @return
     */
    public Category createCategory(String catName, boolean isFuncHealth, int citizenID){
        return categoryDAO.createCategory(catName, isFuncHealth, citizenID);
    }
}
