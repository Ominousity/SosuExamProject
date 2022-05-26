package UI.MVC.Model;

import BE.Category;
import BE.SubCategory;
import BLL.CategoryManager;
import BLL.SubCategoryManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryModel
{
    private CategoryManager categoryManager;
    private SubCategoryManager subCategoryManager;

    public CategoryModel() throws IOException {
        categoryManager = new CategoryManager();
        subCategoryManager = new SubCategoryManager();
    }

    public List<Category> getAllCategories(int citizenID) {
        return categoryManager.getAllCategories(citizenID);
    }

    public Category createCategory(String catName, boolean isFuncHealth, int citizenID) {
        return categoryManager.createCategory(catName, isFuncHealth, citizenID);
    }
}
