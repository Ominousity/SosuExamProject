package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;

public class FunktionsevneController {

    private SceneCreator sceneCreator;

    public FunktionsevneController() {
        sceneCreator = new SceneCreator();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/StudentStylesheet.css",this), "Borger", false);
    }
    public void handleSave(ActionEvent actionEvent) {
    }
}
