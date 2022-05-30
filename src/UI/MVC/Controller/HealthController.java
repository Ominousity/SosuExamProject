package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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
    private ComboBox<SubCategory> cbSubCat;
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
            addHealthButtons(category.getCatName());
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

    /**
     * saves the current infomationen froms the textfields
     * @param actionEvent
     * @throws SQLException
     */
    public void handleSave(ActionEvent actionEvent) throws SQLException {
        SubCategory subCat = cbSubCat.getSelectionModel().getSelectedItem();
        subCat.setSubCatContents(taContent.getText());
        subCategoryModel.updateSubCategory(subCat);
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Info was saved", ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * gets all the caregories
     */
    public void getHealthCategories(){
        categoryList = categoryModel.getAllCategories(ParseModel.citizen.getID());
        for (Category category:categoryList) {
            if(category.isFuncHealth){
                funcCategory.add(category);
            }
        }
    }

    /**
     * sets all items to the given opacity
     * @param opacity
     * @param isDisabled
     */
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

    /**
     * adds buttons acounting to how many categories there is, gives them animation and sets the on action method
     * @param text
     */
    public void addHealthButtons(String text){
        Button button = buttonCreator.createButtons(false, 100, 325, 0, 0, 0, 0, Pos.CENTER, "he-buttons", ""+btnid, text);
        catGridpane.add(button, x, y);
        button.setFont(Font.font(24));
        Paint paint = new Color(1,1,1, 1);
        button.setTextFill(paint);
        if (button != null){
            buttons.add(button);
        }
        button.setOnAction(e -> {
            try {
                for (Button butt : buttons)
                {
                    if (butt == button){
                        button.getStyleClass().add("he-buttons-clicked");
                    } else {
                        butt.getStyleClass().remove("he-buttons-clicked");
                    }
                }
                lblCat.setText(button.getText());
                parseId(button.getId());
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
        button.setOnMouseEntered(event -> button.getStyleClass().add("he-buttons-hover"));
        button.setOnMouseExited(event -> button.getStyleClass().remove("he-buttons-hover"));
        btnid++;
        y++;
    }

    /**
     * method which get called when a generated button is pressed
     * @param i
     * @throws SQLException
     * @throws IOException
     */
    public void parseId(String i) throws SQLException, IOException {

        Category category = funcCategory.get(Integer.parseInt(i));
        subCategoryList = subCategoryModel.getObservableSubCategories(category.getID());
        cbSubCat.setItems(subCategoryList);
        setItemsInvisible(100, false);
        lblOBS.setDisable(true);
        lblOBS.setOpacity(0);

    }

    /**
     * sets the text area from the corresponding subcategory's contents
     */
    public void handleFillContent() {
        if (cbSubCat.getSelectionModel().getSelectedItem() != null) {
            taContent.setText(cbSubCat.getSelectionModel().getSelectedItem().getSubCatContents());
        }else{
            //Do nothing
        }
    }
}
