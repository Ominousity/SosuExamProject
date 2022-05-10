package UI.MVC.Controller;

import UI.MVC.Model.CaseModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CreateCaseController {

    @FXML
    private TextField caseNameTF;
    @FXML
    private TextArea caseTA;

    private CaseModel caseModel;
    private SceneCreator sceneCreator;

    public CreateCaseController() throws IOException {
        caseModel = new CaseModel();
        sceneCreator = new SceneCreator();
    }

    public void handleCreateCase(ActionEvent actionEvent) throws SQLException {
        caseModel.createCase(caseNameTF.getText(), caseTA.getText(), "Åben", ParseModel.citizen.getID());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Citizen was created", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) caseTA.getScene().getWindow();
            stage.close();
        }
    }
}
