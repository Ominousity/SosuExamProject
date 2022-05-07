package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;

public class CitizenController {

    private SceneCreator sceneCreator;

    public CitizenController() {
        sceneCreator = new SceneCreator();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }
}
