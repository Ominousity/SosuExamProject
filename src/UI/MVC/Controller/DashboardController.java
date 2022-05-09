package UI.MVC.Controller;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class DashboardController {
    @FXML
    private Button logOutBtn;

    private SceneCreator sceneCreator;

    public DashboardController(){
        sceneCreator = new SceneCreator();
    }
    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        sceneCreator.createScene("UI/MVC/View/Login.fxml","UI/CSS/MainStylesheet.css",this);
        stage.close();
    }
    public void handleAdd(ActionEvent actionEvent) {
    }
}