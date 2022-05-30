package UI.MVC.Model;

import BE.Category;
import BLL.CategoryManager;
import BLL.SubCategoryManager;
import java.io.IOException;
import java.util.List;

public class CategoryModel
{
    private CategoryManager categoryManager;
    private SubCategoryManager subCategoryManager;

    public CategoryModel() throws IOException {
        categoryManager = new CategoryManager();
        subCategoryManager = new SubCategoryManager();
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizenID
     * @return
     */
    public List<Category> getAllCategories(int citizenID) {
        return categoryManager.getAllCategories(citizenID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param catName
     * @param isFuncHealth
     * @param citizenID
     * @return
     */
    public Category createCategory(String catName, boolean isFuncHealth, int citizenID) {
        return categoryManager.createCategory(catName, isFuncHealth, citizenID);
    }
}
