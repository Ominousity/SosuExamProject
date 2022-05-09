package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class GeneralinformationController {

    private Button ressourcerBtn;
    private Button mestringBtn;
    private Button motivationBtn;
    private Button rollerBtn;
    private Button vanerBtn;
    private Button uddannelseBtn;
    private Button livshistorieBtn;
    private Button netværkBtn;
    private Button helbredBtn;
    private TextArea textTA;
    private Button saveBtn;
    private Button backBtn;
    private SceneCreator sceneCreator;

    public GeneralinformationController() {
        sceneCreator = new SceneCreator();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleSave(ActionEvent actionEvent) {
    }
}
