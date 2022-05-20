package UI.MVC.Model;

import BE.SubCategory;
import BLL.SubCategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubCategoryModel {

    private ObservableList subCatList;

    SubCategoryManager subCategoryManager;
    public SubCategoryModel() throws IOException {
        subCategoryManager = new SubCategoryManager();
        subCatList = FXCollections.observableArrayList();
    }

    public List<SubCategory> getSubCategories(int SubCategoryID) throws SQLException {
        return subCategoryManager.getSubCategories(SubCategoryID);
    }

    public ObservableList<SubCategory> getObservableSubCategories(int categoryID) throws SQLException {
        subCatList.clear();
        subCatList.addAll(subCategoryManager.getSubCategories(categoryID));
        return subCatList;
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
