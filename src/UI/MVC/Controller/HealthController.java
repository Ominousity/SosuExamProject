package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;

public class HealthController implements Initializable{
    public GridPane gridPane2;
    @FXML
    private GridPane GridPane;
    @FXML
    private Button backBtn;
    @FXML
    private TextArea taHealthInfo;
    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private  ParseModel parseModel = ParseModel.getInstance();
    private int x = 0;
    private int y = 0;
    private int c = 0;
    private int v = 0;
    private int j = 0;
    private double height;
    private List<Category> catList;
    private int subnumbers = 0;
    private List<SubCategory> subcatlist;

    public HealthController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        getCategories();
        subcatlist = new ArrayList();
        GridPane = new GridPane();
    }

    /**
     * closes the current fxml and opens the CitizenView fxml
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void addButtons(String text) {
        Button button = buttonCreator.createButtons(true, 600/catList.size(), 357, 0, 0, 0, 0, Pos.CENTER, "buttons", "1", text);
        GridPane.add(button, y, x);
        button.setOnAction(e -> parseIDToInt(button.getId()));
        x++;
        y++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : catList){
            addButtons(cat.getCatName());
            System.out.println("buttons here");

        }

        for (Category cat : catList) {
            int subs = cat.getID();
            try {
                subcatlist = subCategoryModel.getSubCategories(subs);
                System.out.println("sub here");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

            for (SubCategory subcat : subcatlist ) {
                try {
                    createSubCats(subcat.getSubCatName());
                    System.out.println("sub here 2");
                    System.out.println(subcat.getSubCatName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        System.out.println(subcatlist);
        }


    public void getCategories(){
        catList = categoryModel.getAllCategories(parseModel.citizen.getID());
    }

    public void createSubCats(String subcatName) throws SQLException {
        Label label = new Label();
        TextArea textArea = new TextArea();
        textArea.setMaxSize(200, 50);
        textArea.setPrefHeight(50);
        textArea.setPrefWidth(200);
        textArea.setMinHeight(50);
        textArea.setMinWidth(200);
        textArea.setPadding(new Insets(0, 0, 0, 0));
        label.setText(categoryModel.getAllCategories(parseModel.citizen.getID()).get(subnumbers).getCatName());
        label.setPadding(new Insets(0, 0, 100, 0));
        textArea.setEditable(true);
        textArea.setId("taSub"+subnumbers);
        label.setId("lblSub"+subnumbers);
        gridPane2.add(textArea, c, v);
        gridPane2.add(label, c, v);
        y++;
        subnumbers++;
    }



    /*
    private void underCatgoriesButtons(Button tbutton) throws SQLException
    {
        ArrayList<SubCategory> subCategories = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();
        subCategories = (ArrayList<SubCategory>) subCategoryModel.getSubCategories(Integer.parseInt(tbutton.getId()));
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(tbutton.getLayoutX() + 10);
        gridPane.setLayoutX(tbutton.getLayoutY());
        gridPane.setScaleX(100);
        gridPane.setScaleY(100);
        System.out.println("Create");
        for (SubCategory subCategory : subCategories)
        {
            System.out.println("HELLO?");
            Button button = buttonCreator.createButtons(false, 600/catList.size(), 365, 0, 0, 0, 0, Pos.CENTER, "buttons", ""+x, subCategory.getSubCatName());
            buttons.add(button);
            button.setOnAction(event -> showUnderContent(subCategory));
            button.setOnMouseExited(event -> deleteCategoriesButtons(buttons));
            gridPane.add(button, 0, x);
            x++;
        }
    }
 */
    private void deleteCategoriesButtons(ArrayList<Button> buttons){
        System.out.println("Deleted");
        for (Button button : buttons)
        {
            button.setOpacity(0);
            button.setDisable(true);
        }
    }

    private void parseIDToInt(String i){
        Category category = categoryModel.getAllCategories(ParseModel.citizen.getID()).get(Integer.parseInt(i));

    }


    private void showUnderContent(SubCategory subCategory){
        taHealthInfo.setText(subCategory.getSubCatContents());
    }
}
