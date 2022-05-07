package UI.MVC.Controller;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
public class DashboardController {
    private SceneCreator sceneCreator;

    public DashboardController(){
        sceneCreator = new SceneCreator();
    }
    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createScene("UI/MVC/View/Login.fxml","UI/CSS/MainStylesheet.css",this);
        stage.close();
    }
    public void handleAdd(ActionEvent actionEvent) {
    }
}