package UI.MVC.Controller;

import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class CreateCitizenController {

    @FXML
    private ImageView citizenImg;
    @FXML
    private TextField fNameTextField;
    @FXML
    private TextField lNameTextField;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField socialSecTextField;
    @FXML
    private CheckBox isTemplate;
    @FXML
    private TextArea studentTextArea;
    @FXML
    private ChoiceBox chooseStudentCB;

    private String imgPath;

    private SceneCreator sceneCreator;
    private

    public CreateCitizenController() {
        sceneCreator = new SceneCreator();
    }

    public void handleChooseImg(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("image/" ));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            imgPath = ("image\\" + file.getName());
        }
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
