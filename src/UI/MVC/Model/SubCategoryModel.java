package UI.MVC.Model;

import BE.SubCategory;
import BLL.SubCategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;

public class SubCategoryModel {

    private ObservableList subCatList;

    SubCategoryManager subCategoryManager;
    public SubCategoryModel() throws IOException {
        subCategoryManager = new SubCategoryManager();
        subCatList = FXCollections.observableArrayList();
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param categoryID
     * @return
     */
    public ObservableList<SubCategory> getObservableSubCategories(int categoryID) {
        subCatList.clear();
        subCatList.addAll(subCategoryManager.getSubCategories(categoryID));
        return subCatList;
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param subCatName
     * @param subCatContents
     * @param categoryID
     */
    public void createSubCategory(String subCatName, String subCatContents, int categoryID){
        subCategoryManager.createSubCategory(subCatName, subCatContents, categoryID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param subCategory
     * @throws SQLException
     */
    public void updateSubCategory(SubCategory subCategory) throws SQLException {
        subCategoryManager.updateSubCategory(subCategory);
    }
}
