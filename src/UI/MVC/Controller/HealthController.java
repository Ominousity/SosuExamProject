package UI.MVC.Controller;

import BE.Category;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private TilePane GridPane;
    @FXML
    private TilePane tilePane;
    @FXML
    private ScrollPane scrollPane;
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
    private int lastID = 0;
    private double height;
    private List<Category> catList;
    private int subnumbers = 0;
    private int btnNumber = 0;
    private List<SubCategory> subcatlist;
    private ArrayList<TextArea> textAreas;
    private ArrayList<Label> labels;
    private ArrayList<Button> buttons;

    public HealthController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        getCategories();
        subcatlist = new ArrayList();
        GridPane = new TilePane();
        textAreas = new ArrayList<>();
        labels = new ArrayList<>();
        scrollPane = new ScrollPane();
        tilePane = new TilePane();
        buttons = new ArrayList<>();
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
        Button button = buttonCreator.createButtons(false, 100, 290, 0, 0, 0, 0, Pos.CENTER, "buttons", ""+ btnNumber, text);
        button.setLayoutY(x);
        GridPane.getChildren().add(button);
        buttons.add(button);
        button.setOnAction(e ->
        {
            try
            {
                parseIDToInt(button.getId());
                for (Button butt : buttons)
                {
                    if (button == butt){
                        ScaleTransition scaleTransitionIN = new ScaleTransition();
                        scaleTransitionIN.setDuration(Duration.millis(1000));
                        scaleTransitionIN.setNode(button);
                        scaleTransitionIN.setToY(1);
                        scaleTransitionIN.setToX(1.1);
                        scaleTransitionIN.playFromStart();
                        button.setStyle("-fx-background-color: #6b8aa4");
                    } else {
                        ScaleTransition scaleTransitionIN = new ScaleTransition();
                        scaleTransitionIN.setDuration(Duration.millis(1000));
                        scaleTransitionIN.setNode(butt);
                        scaleTransitionIN.setToY(1);
                        scaleTransitionIN.setToX(1);
                        scaleTransitionIN.playFromStart();
                        butt.setStyle("-fx-background-color: #86b3d3");
                    }
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        btnNumber++;
        x += 100;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : catList){
            if (cat.isFuncHealth){
                addButtons(cat.getCatName());
            }
        }
    }


    public void getCategories(){
        catList = categoryModel.getAllCategories(ParseModel.citizen.getID());
    }

    public void createSubCats(int id) throws SQLException {
        Label label = new Label();
        TextArea textArea = new TextArea();
        textArea.setPadding(new Insets(0, 0, 0, 0));
        textArea.setText(subCategoryModel.getSubCategories(id).get(subnumbers).getSubCatContents());
        textArea.setPromptText("Skriv her: \n" + subCategoryModel.getSubCategories(id).get(subnumbers).getSubCatName());
        textArea.setMaxWidth(470);
        textArea.setFont(Font.font("Arial", 20));
        textArea.setLayoutY(v);
        label.setText(subCategoryModel.getSubCategories(id).get(subnumbers).getSubCatName());
        label.setPadding(new Insets(0, 0, 20, 0));
        label.setLayoutY(v);
        label.setFont(Font.font("Arial", 42));
        label.setWrapText(true);
        label.setMaxWidth(470);
        textArea.setEditable(true);
        textArea.setId(""+subnumbers);
        label.setId(""+subnumbers);
        tilePane.getChildren().add(label);
        tilePane.getChildren().add(textArea);
        labels.add(label);
        textAreas.add(textArea);
        v += 100;
        subnumbers++;
        System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[32mCreated: \u001B[37m" + "\u001B[0m" + textArea + " and " + label + " to: " + scrollPane);
    }


    private void deleteCategoriesButtons(ArrayList<Button> buttons){
        for (Button button : buttons)
        {
            button.setOpacity(0);
            button.setDisable(true);
        }
    }

    private void parseIDToInt(String i) throws SQLException, InterruptedException
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override public void run()
            {
                for (TextArea textArea : textAreas)
                {
                    try
                    {
                        subCategoryModel.updateSubCategory(new SubCategory(subcatlist.get(Integer.parseInt(textArea.getId())).getSubCatID(), subcatlist.get(Integer.parseInt(textArea.getId())).getSubCatName(),textArea.getText()));
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[35mRemoving: \u001B[37m" + "\u001B[0m" + textArea + " From: " + textArea.getParent());
                    tilePane.getChildren().remove(textArea);
                }
                textAreas.clear();
                for (Label label : labels)
                {
                    System.out.println(getClass().getName() + ": \u001B[33mDebug: " + "\u001B[35mRemoving: \u001B[37m" + "\u001B[0m" + label + " From: " + label.getParent());
                    tilePane.getChildren().remove(label);
                }
                labels.clear();
                Category category = categoryModel.getAllCategories(ParseModel.citizen.getID()).get(Integer.parseInt(i));
                try
                {
                    subcatlist = subCategoryModel.getSubCategories(category.getID());
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                v = 0;
                subnumbers = 0;
                System.gc();
                for (SubCategory subCategory : subcatlist)
                {
                    try
                    {
                        createSubCats(category.getID());
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        System.out.println("");
        thread.join();
        System.out.println("");
    }


    private void showUnderContent(SubCategory subCategory){
        taHealthInfo.setText(subCategory.getSubCatContents());
    }
}
