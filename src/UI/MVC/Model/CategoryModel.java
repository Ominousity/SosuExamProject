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

    public Category createCategory(String CatName, boolean isFuncHealth, int citizenID){
        return categoryManager.createCategory(CatName, isFuncHealth, citizenID);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryManager.updateCategory(category);
    }

    public void deleteCategory(int categoryID) throws SQLException{
        categoryManager.deleteCategory(categoryID);
    }

    public List<SubCategory> getSubCategories(int CategoryID) throws SQLException{
        return subCategoryManager.getSubCategories(CategoryID);
    }

    public void createSubCategory(String subCatName, String subCatContents, int categoryID){
        subCategoryManager.createSubCategory(subCatName, subCatContents, categoryID);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryManager.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException{
        subCategoryManager.deleteSubCategory(SubCategoryID);
    }

}
