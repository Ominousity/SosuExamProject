package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FunktionsevneController implements Initializable {

    private SceneCreator sceneCreator;

    public FunktionsevneController() {
        sceneCreator = new SceneCreator();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Borger", false);
    }
    public void handleSave(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
