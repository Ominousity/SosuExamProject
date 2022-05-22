package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import BLL.Utility.Quistenmarks;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable{

    public Button btnQuistenMarkSub;
    @FXML
    private ImageView ivImage;
    @FXML
    private Label lblNurværende;
    @FXML
    private Label lblBorgerVudering;
    @FXML
    private Label lblMål;
    @FXML
    private Label lblVudering1;
    @FXML
    private Label lblVudering2;
    @FXML
    private Label lblVudering3;
    @FXML
    private Label lblVælg;
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
        setItemsInvisible(0, true);
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


    public void setItemsInvisible(int opacity, boolean isDisabled){
        ivImage.setOpacity(opacity);
        ivImage.setDisable(isDisabled);
        lblBorgerVudering.setOpacity(opacity);
        lblBorgerVudering.setDisable(isDisabled);
        lblMål.setOpacity(opacity);
        lblMål.setDisable(isDisabled);
        lblNurværende.setOpacity(opacity);
        lblNurværende.setDisable(isDisabled);
        lblVudering1.setOpacity(opacity);
        lblVudering1.setDisable(isDisabled);
        lblVudering2.setOpacity(opacity);
        lblVudering2.setDisable(isDisabled);
        lblVudering3.setOpacity(opacity);
        lblVudering3.setDisable(isDisabled);
        cbBorgerVudering.setOpacity(opacity);
        cbBorgerVudering.setDisable(isDisabled);
        cbGoal.setOpacity(opacity);
        cbGoal.setDisable(isDisabled);
        cbNuværendeTilstand.setOpacity(opacity);
        cbNuværendeTilstand.setDisable(isDisabled);
        lblVælg.setOpacity(opacity);
        lblVælg.setDisable(isDisabled);
        cbSamfundsliv.setOpacity(opacity);
        cbSamfundsliv.setDisable(isDisabled);
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
        setItemsInvisible(100, false);
    }


    public void OnSubCatFunk(ActionEvent actionEvent) {
        Quistenmarks quistenmarks = btnQuistenMarkSub.setOnAction(JTextField);
    }
}
