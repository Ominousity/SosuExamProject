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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable {
    @FXML
    private Button saveBtn;
    @FXML
    private Label lblOBS;
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
    private ComboBox<SubCategory> cbSubCat;
    @FXML
    private Label lblCat;

    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private int btnid = 0;
    private int x = 0;
    private int y = 0;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private List<Category> categoryList;
    private ObservableList<Category> funcCategory;
    private ObservableList<SubCategory> subCategoryList;
    private ObservableList<Integer> vudering;
    private ParseModel parseModel = ParseModel.getInstance();
    private ArrayList<Button> buttons;

    public FunktionsevneController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        funcCategory = FXCollections.observableArrayList();
        subCategoryList = FXCollections.observableArrayList();
        vudering = FXCollections.observableArrayList();
        vudering.add(0);
        vudering.add(1);
        vudering.add(2);
        vudering.add(3);
        vudering.add(4);
        vudering.add(9);
        buttons = new ArrayList<>();
        lblCat = new Label();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFuncCategories();
        for (Category category : funcCategory){
            addFuncButtons(category.getCatName());
        }
        setItemsInvisible(0, true);
        cbNuværendeTilstand.setItems(vudering);
        cbGoal.setItems(vudering);
        cbBorgerVudering.setItems(vudering);
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
     * saves the current infomation from the textfields.
     * @param actionEvent
     * @throws SQLException
     */
    public void handleSave(ActionEvent actionEvent) throws SQLException {
        SubCategory subCat = cbSubCat.getSelectionModel().getSelectedItem();
        subCat.setSubCatContents(cbNuværendeTilstand.getSelectionModel().getSelectedItem().toString() +
                                 cbBorgerVudering.getSelectionModel().getSelectedItem().toString() +
                                 cbGoal.getSelectionModel().getSelectedItem().toString());
        subCategoryModel.updateSubCategory(subCat);
    }

    /**
     * fills the comboboxes with the infomation from the database
     * @param actionEvent
     */
    public void handleFillContent(ActionEvent actionEvent) {
        if (cbSubCat.getSelectionModel().getSelectedItem() != null) {
            SubCategory subCategory = cbSubCat.getSelectionModel().getSelectedItem();
            String contents = subCategory.getSubCatContents();
            if (contents.isEmpty()){
                cbNuværendeTilstand.getSelectionModel().select(null);
                cbBorgerVudering.getSelectionModel().select(null);
                cbGoal.getSelectionModel().select(null);
            }else {
                char[] ch = new char[contents.length()];
                for (int place = 0; place < contents.length(); place++) {
                    ch[place] = contents.charAt(place);
                }
                System.out.println(ch.length);
                int i = 1;
                for (char c : ch) {
                    checkWhatComboBox(i, c);
                    i++;
                }
            }
        }else{
            //Do nothing
        }
    }

    /**
     * sets the comboboxes.
     * @param i
     * @param value
     */
    public void checkWhatComboBox(int i, char value){
        if (i == 1) {
            cbNuværendeTilstand.getSelectionModel().select(vudering.get(Integer.parseInt(value + "")));
        }else if (i == 2) {
            cbBorgerVudering.getSelectionModel().select(vudering.get(Integer.parseInt(value + "")));
        }else {
            cbGoal.getSelectionModel().select(vudering.get(Integer.parseInt(value + "")));
        }
    }

    /**
     * gets all the categories.
     */
    public void getFuncCategories(){
        categoryList = categoryModel.getAllCategories(ParseModel.citizen.getID());
        for (Category category:categoryList) {
            if(!category.isFuncHealth){
                funcCategory.add(category);
            }
        }
    }

    /**
     * sets everthing to 0 opacity
     * @param opacity
     * @param isDisabled
     */
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
        cbSubCat.setOpacity(opacity);
        cbSubCat.setDisable(isDisabled);
        saveBtn.setOpacity(opacity);
        saveBtn.setDisable(isDisabled);
        lblCat.setOpacity(opacity);
        lblCat.setDisable(isDisabled);
    }

    /**
     * buttons created on the amount of categories there is.
     * @param text
     */
    public void addFuncButtons(String text){
        Button button = buttonCreator.createButtons(false, 100, 325, 0, 0, 0, 0, Pos.CENTER, "fu-buttons", ""+btnid, text);
        gridPaneFunc.add(button, x, y);
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
                        button.getStyleClass().add("fu-buttons-clicked");
                    } else {
                        butt.getStyleClass().remove("fu-buttons-clicked");
                    }
                }
                lblCat.setText(button.getText());
                parseId(button.getId());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button.setOnMouseEntered(event -> button.getStyleClass().add("fu-buttons-hover"));
        button.setOnMouseExited(event -> button.getStyleClass().remove("fu-buttons-hover"));
        btnid++;
        y++;
    }

    /**
     * gets the subcategories from the category.
     * @param i
     * @throws SQLException
     */
    public void parseId(String i) throws SQLException {
        Category category = funcCategory.get(Integer.parseInt(i));
        subCategoryList = subCategoryModel.getObservableSubCategories(category.getID());
        cbSubCat.setItems(subCategoryList);
        setItemsInvisible(100, false);
        lblOBS.setDisable(true);
        lblOBS.setOpacity(0);
    }
}
