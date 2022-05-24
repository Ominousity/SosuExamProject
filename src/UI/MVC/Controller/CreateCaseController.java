package UI.MVC.Controller;

import BE.CitizenCase;
import UI.MVC.Model.CaseModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
public class CreateCaseController implements Initializable {
    public ComboBox<String> cbStatus;
    @FXML
    private TextField caseNameTF;
    @FXML
    private TextArea caseTA;

    private ObservableList statusList;
    private CaseModel caseModel;
    private SceneCreator sceneCreator;
    private  ParseModel parseModel = ParseModel.getInstance();

    public CreateCaseController() throws IOException {
        caseModel = new CaseModel();
        sceneCreator = new SceneCreator();
        cbStatus = new ComboBox<>();

        statusList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusList.add("Åben");
        statusList.add("Lukket");

        cbStatus.setItems(statusList);
        if (parseModel.cases != null) {
            caseNameTF.setText(parseModel.cases.getName());
            caseTA.setText(parseModel.cases.getCaseContent());
            cbStatus.getSelectionModel().select(parseModel.cases.getStatus());
        }
    }

    /**
     * creates a case by getting the information the user has entered
     * @param actionEvent
     * @throws SQLException
     */
    public void handleCreateCase(ActionEvent actionEvent) throws SQLException {
        if (parseModel.cases == null) {
            caseModel.createCase(caseNameTF.getText(), caseTA.getText(), cbStatus.getSelectionModel().getSelectedItem(), parseModel.citizen.getID());
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Case was created", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) caseTA.getScene().getWindow();
                stage.close();
            }
        }else if (parseModel.cases != null){
            caseModel.updateCase(new CitizenCase(parseModel.cases.getId(), caseNameTF.getText(), caseTA.getText(), cbStatus.getSelectionModel().getSelectedItem()));
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Case was created", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) caseTA.getScene().getWindow();
                stage.close();
            }
        }
    }
}
