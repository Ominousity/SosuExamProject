package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController {
SceneCreator sceneCreator = new SceneCreator();

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        //sceneCreator.createScene("Login.fxml","",false,this);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) {
    }

    public void handleRemove(ActionEvent actionEvent) {
    }

    public void handleEdit(ActionEvent actionEvent) {
    }
}
