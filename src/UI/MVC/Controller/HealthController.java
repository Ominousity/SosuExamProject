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
import javafx.scene.Node;
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
import java.util.*;

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
    private int btnNumber = 0;
    private List<SubCategory> subcatlist;
    private ArrayList<TextArea> textAreas;
    private ArrayList<Label> labels;

    public HealthController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        getCategories();
        subcatlist = new ArrayList();
        GridPane = new GridPane();
        textAreas = new ArrayList<>();
        labels = new ArrayList<>();
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
        Button button = buttonCreator.createButtons(true, 600/catList.size(), 357, 0, 0, 0, 0, Pos.CENTER, "buttons", ""+ btnNumber, text);
        GridPane.add(button, y, x);
        button.setOnAction(e ->
        {
            try
            {
                parseIDToInt(button.getId());
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        btnNumber++;
        x++;
        y++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : catList){
            addButtons(cat.getCatName());
        }
    }


    public void getCategories(){
        catList = categoryModel.getAllCategories(ParseModel.citizen.getID());
    }

    public void createSubCats(int id) throws SQLException {
        Label label = new Label();
        TextArea textArea = new TextArea();
        textArea.setMaxSize(200, 50);
        textArea.setPrefHeight(50);
        textArea.setPrefWidth(200);
        textArea.setMinHeight(50);
        textArea.setMinWidth(200);
        textArea.setPadding(new Insets(0, 0, 0, 0));
        textArea.setText(subCategoryModel.getSubCategories(id).get(subnumbers).getSubCatContents());
        label.setText(subCategoryModel.getSubCategories(id).get(subnumbers).getSubCatName());
        label.setPadding(new Insets(0, 0, 100, 0));
        textArea.setEditable(true);
        textArea.setId(""+subnumbers);
        label.setId(""+subnumbers);
        gridPane2.add(textArea, c, v);
        gridPane2.add(label, c, v);
        labels.add(label);
        textAreas.add(textArea);
        v++;
        subnumbers++;
        System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[32mCreated: \u001B[37m" + "\u001B[0m" + textArea + " and " + label + " to: " + gridPane2);
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
        for (Button button : buttons)
        {
            button.setOpacity(0);
            button.setDisable(true);
        }
    }

    private void parseIDToInt(String i) throws SQLException
    {
        Category category = categoryModel.getAllCategories(ParseModel.citizen.getID()).get(Integer.parseInt(i));
        System.out.println(ParseModel.citizen.getID());
        System.out.println(i);
        subcatlist = subCategoryModel.getSubCategories(category.getID());
        for (TextArea textArea : textAreas)
        {
            subCategoryModel.updateSubCategory(new SubCategory(subcatlist.get(Integer.parseInt(textArea.getId())).getSubCatID(), subcatlist.get(Integer.parseInt(textArea.getId())).getSubCatName(),textArea.getText()));
            System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[35mRemoving: \u001B[37m" + "\u001B[0m" + textArea + " From: " + textArea.getParent());
            gridPane2.getChildren().remove(textArea);
        }
        for (Label label : labels)
        {
            System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[35mRemoving: \u001B[37m" + "\u001B[0m" + label + " From: " + label.getParent());
            gridPane2.getChildren().remove(label);
        }
        c = 0;
        v = 0;
        subnumbers = 0;
        System.gc();
        for (SubCategory subCategory : subcatlist)
        {
            createSubCats(category.getID());
        }
    }


    private void showUnderContent(SubCategory subCategory){
        taHealthInfo.setText(subCategory.getSubCatContents());
    }
}
