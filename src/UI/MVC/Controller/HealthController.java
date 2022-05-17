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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HealthController implements Initializable{
    @FXML
    private javafx.scene.layout.GridPane GridPane;
    @FXML
    private Button backBtn;
    @FXML
    private TextArea taHealthInfo;
    private SceneCreator sceneCreator;
    private ButtonCreator buttonCreator;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private int x = 0;
    private int y = 0;
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
    }

    /**
     * closes the current fxml and opens the CitizenView fxml
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void addButtons(String text) {
        Button button = buttonCreator.createButtons(false, 600/catList.size(), 365, 0, 0, 0, 0, Pos.CENTER, "buttons", ""+y, text);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : catList){
            addButtons(cat.getCatName());
        }

        for (Category cat : catList){
            int subs = cat.getID();
            try {
                subcatlist = subCategoryModel.getSubCategories(subs);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (SubCategory subcat : subcatlist ) {
                try {
                    createSubCats(subcat.getSubCatName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void getCategories(){
        catList = categoryModel.getAllCategories(ParseModel.citizen.getID());
    }

    public void createSubCats(String subcatName) throws SQLException {
        Label label = new Label();
        TextArea textArea = new TextArea();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
    if(!(y == 2 && x == 1 )) {
        textArea.setMaxSize(50, 50);
        textArea.setPrefHeight(50);
        textArea.setPrefWidth(200);
        textArea.setPadding(new Insets(10, 10, 10, 10));
        label.setText(categoryModel.getAllCategories(ParseModel.citizen.getID()).get(subnumbers).getCatName());
        label.setPadding(new Insets(30, 30, 30, 30));
        vBox.getChildren().addAll(textArea, label);
        scrollPane.setContent(vBox);
        textArea.setId("taSub"+subnumbers);
        label.setId("lblSub"+subnumbers);
        y++;
        subnumbers++;
        if(y == 3){
            x++;
            y = 0;
        }
        }
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


    private void showUnderContent(SubCategory subCategory){
        taHealthInfo.setText(subCategory.getSubCatContents());
    }
}
