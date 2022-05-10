package UI.MVC.Controller;

import UI.MVC.Model.CaseModel;
import UI.MVC.Model.ParseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CreateCaseController {

    @FXML
    private TextField caseNameTF;
    @FXML
    private TextArea caseTA;

    private CaseModel caseModel;

    public CreateCaseController() throws IOException {
        caseModel = new CaseModel();
    }

    public void handleCreateCase(ActionEvent actionEvent) throws SQLException {
        caseModel.createCase(caseNameTF.getText(), caseTA.getText(), "Åben", ParseModel.citizen.getID());
    }
}
