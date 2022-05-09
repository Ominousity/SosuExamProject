package UI.MVC.Controller;

import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.ButtonCreator;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
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
    private int i = 0;
    private int j = 0;

    public HealthController(){
        sceneCreator = new SceneCreator();
        buttonCreator = new ButtonCreator();
        categoryModel = new CategoryModel();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void addButtons() {
        if (!(i == 3 && j == 1)) {
            Button button = buttonCreator.createButtons(false, 100, 100, 0, 0, 0, 0, Pos.CENTER, "buttons", "1");
            GridPane.add(button, i, j);
            i++;
            if(i == 3){
                j++;
                i = 0;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) throws SQLException{
        for (int k = 0; k > categoryModel.getAllCategories(ParseModel.citizen.getID()).size(); k++){
            addButtons();
        }

        BorderPane mainPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu categoryMenu = new Menu("SubCategory");
        MenuItem fillSubcategory = new MenuItem(categoryModel.getSubCategories();
    }
}
