package UI.MVC.Controller;

import BE.School;
import UI.MVC.Model.SchoolModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class CreateSchoolController {
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField tfSchoolName;
    private SchoolModel schoolModel;

    public void CreateCitizenController() throws IOException {
        schoolModel = new SchoolModel();
        tfSchoolName = new TextField();
        cancelBtn = new Button();
    }

    public void handleCreateSchool(){
        schoolModel.createSchool(tfSchoolName.getText());
    }

    public void handleCancel(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
