package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;

public class CreateCitizenController {

    private SceneCreator sceneCreator;

    public CreateCitizenController() {
        sceneCreator = new SceneCreator();
    }
    public void handleChooseImg(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleCreate(ActionEvent actionEvent) {
    }

    public void handleCancelTemplate(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleCreateTemplate(ActionEvent actionEvent) {
    }
}
