package UI.MVC.Controller;

import BE.Category;
import UI.MVC.Model.CategoryModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
    private int x = 0;
    private int y = 0;
    private List<Category> catList;

    public HealthController() throws IOException {
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
        getCategories();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void addButtons(String text) {

        Button button = buttonCreator.createButtons(false, getHeight(), 385, 0, 0, 0, 0, Pos.CENTER, "buttons", "1", text);
        GridPane.add(button, 0, y);
        y++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : catList){
            addButtons(cat.getCatName());
        }
    }

    public int getHeight(){
        AtomicInteger height = new AtomicInteger();
        Platform.runLater(() -> {
            height.set((int) backBtn.getScene().getWindow().getHeight() * catList.size());
            System.out.println(height);
        });
        return height.get();
    }

    public void getCategories(){
        catList = categoryModel.getAllCategories(10);
    }
}
