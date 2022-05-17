package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable {

    public Button backBtn;
    private SceneCreator sceneCreator;

    public FunktionsevneController() {
        sceneCreator = new SceneCreator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void handleSave(ActionEvent actionEvent) {
    }
}
