package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable {

    @FXML
    private GridPane gridPaneFunc;
    @FXML
    private Button backBtn;
    @FXML
    private ComboBox cbNuværendeTilstand;
    @FXML
    private ComboBox cbBorgerVudering;
    @FXML
    private ComboBox cbGoal;
    @FXML
    private ComboBox cbOmsorg;
    @FXML
    private ComboBox cbPraktiskeOpgaver;
    @FXML
    private ComboBox cbMobilitet;
    @FXML
    private ComboBox cbMentaleFunktioner;
    @FXML
    private ComboBox cbSamfundsliv;
    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private int btnid = 0;
    private int x = 0;
    private int y = 0;
    private ArrayList<ComboBox> comboxs;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private List<Category> categoryList;
    private ObservableList<Category> funcCategory;
    private List<SubCategory> funcsubcatlist;
    private ObservableList<SubCategory> subCategoryList;


    public FunktionsevneController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        funcCategory = FXCollections.observableArrayList();
        subCategoryList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFuncCategories();
        for (Category category : funcCategory){
            addFuncButtons(category.getCatName());
        }
        System.out.println(funcCategory);
    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void getFuncCategories(){
        categoryList = categoryModel.getAllCategories(ParseModel.citizen.getID());
        for (Category category:categoryList) {
            if(!category.isFuncHealth){
                funcCategory.add(category);
            }
        }
    }


    public void getCategoryFromButton(){

    }

    public void setItemsToCombobox() throws SQLException {
        cbSamfundsliv.setItems(subCategoryList);
    }

    public void addFuncButtons(String text){
        Button button = buttonCreator.createButtons(true, 100, 320, 0, 0, 0, 0, Pos.CENTER, "buttons", ""+btnid, text);
        gridPaneFunc.add(button, x, y);
        button.setOnAction(e -> {
            try {
                parseId(button.getId());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnid++;
        x++;
        y++;
    }

    public void parseId(String i) throws SQLException {
        Category category = categoryModel.getAllCategories(ParseModel.citizen.getID()).get(Integer.parseInt(i));
        subCategoryList = subCategoryModel.getObservableSubCategories(category.getID());
        cbSamfundsliv.setItems(subCategoryList);
    }
}
