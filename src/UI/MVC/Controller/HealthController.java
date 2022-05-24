package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import DAL.SubCategoryDAO;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class HealthController implements Initializable{
    @FXML
    private Button backBtn;
    @FXML
    private GridPane catGridpane;
    @FXML
    private Button saveBtn;
    @FXML
    private Label lblVælg;
    @FXML
    private ComboBox cbSubCat;
    @FXML
    private Label lblCat;
    @FXML
    private TextArea taContent;
    @FXML
    private Label lblOBS;


    private int btnid = 0;
    private int y = 0;
    private int x = 0;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private ObservableList<SubCategory> subCategoryList;
    private List<Category> categoryList;
    private ArrayList<Category> funcCategory;
    private ArrayList<Button> buttons;
    private ParseModel parseModel = ParseModel.getInstance();

    public HealthController() throws IOException{
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryList = new ArrayList<>();
        funcCategory = new ArrayList<>();
        buttons = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getHealthCategories();
        for (Category category : funcCategory){
            addFuncButtons(category.getCatName());
        }
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

    public void getHealthCategories(){
        categoryList = categoryModel.getAllCategories(ParseModel.citizen.getID());
        for (Category category:categoryList) {
            if(category.isFuncHealth){
                funcCategory.add(category);
            }
        }
    }


    public void setItemsInvisible(int opacity, boolean isDisabled){
        lblCat.setOpacity(opacity);
        lblCat.setDisable(isDisabled);
        lblVælg.setOpacity(opacity);
        lblVælg.setDisable(isDisabled);
        cbSubCat.setOpacity(opacity);
        cbSubCat.setDisable(isDisabled);
        taContent.setOpacity(opacity);
        taContent.setDisable(isDisabled);
        saveBtn.setOpacity(opacity);
        saveBtn.setDisable(isDisabled);
    }

    public void addFuncButtons(String text){
        Button button = buttonCreator.createButtons(false, 100, 325, 0, 0, 0, 0, Pos.CENTER, "buttons-pressed", ""+btnid, text);
        catGridpane.add(button, x, y);
        button.setFont(Font.font(24));
        Paint paint = new Color(1,1,1, 1);
        button.setTextFill(paint);
        button.setStyle("-fx-background-color: #f19733");
        if (button != null){
            buttons.add(button);
        }
        button.setOnAction(e -> {
            try {
                lblCat.setText(button.getText());
                parseId(button.getId());
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #415d8a");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #f19733");
        });
        btnid++;
        y++;
    }

    public void parseId(String i) throws SQLException, IOException {

        Category category = funcCategory.get(Integer.parseInt(i));
        subCategoryList = subCategoryModel.getObservableSubCategories(category.getID());
        cbSubCat.setItems(subCategoryList);
        setItemsInvisible(100, false);
        lblOBS.setDisable(true);
        lblOBS.setOpacity(0);
    }


}
