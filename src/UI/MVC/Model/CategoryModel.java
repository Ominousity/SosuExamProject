package UI.MVC.Model;

import BE.Category;
import BE.SubCategory;
import BLL.CategoryManager;
import BLL.SubCategoryManager;
import DAL.CategoryDAO;
import DAL.SubCategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel
{
    private CategoryManager categoryManager;
    private SubCategoryManager subCategoryManager;

    public CategoryModel(){
        categoryManager = new CategoryManager();
        subCategoryManager = new SubCategoryManager();
    }

    public List<Category> getAllCategories(int citizenID) throws SQLException {
        return categoryManager.getAllCategories(citizenID);
    }

    public void createCategory(String CatName, String CitizenID){
        categoryManager.createCategory(CatName, CitizenID);
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

    public void createSubCategory(String SubCatName, String SubCatContents){
        subCategoryManager.createSubCategory(SubCatName, SubCatContents);
    }

    public void updateSubCategory(SubCategory subCategory) throws SQLException{
        subCategoryManager.updateSubCategory(subCategory);
    }

    public void deleteSubCategory(int SubCategoryID) throws SQLException{
        subCategoryManager.deleteSubCategory(SubCategoryID);
    }

}
