package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;

public class FunktionsevneController {

    private SceneCreator sceneCreator;

    public FunktionsevneController() {
        sceneCreator = new SceneCreator();
    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleSave(ActionEvent actionEvent) {
    }
}
