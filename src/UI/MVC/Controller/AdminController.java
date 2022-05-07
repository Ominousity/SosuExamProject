package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController {
    private SceneCreator sceneCreator;

    public AdminController(){
        sceneCreator = new SceneCreator();
    }
    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleRemove(ActionEvent actionEvent) {
    }

    public void handleEdit(ActionEvent actionEvent) {
    }
}
