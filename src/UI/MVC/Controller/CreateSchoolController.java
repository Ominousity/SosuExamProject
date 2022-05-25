package UI.MVC.Controller;

import BE.School;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SchoolModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CreateSchoolController implements Initializable {
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField tfSchoolName;
    private SchoolModel schoolModel;
    private ParseModel parseModel = ParseModel.getInstance();

    public CreateSchoolController() throws IOException {
        schoolModel = new SchoolModel();
        tfSchoolName = new TextField();
        cancelBtn = new Button();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (parseModel.school != null){
            tfSchoolName.setText(parseModel.school.getSchoolName());
        }
    }

    public void handleCreateSchool() throws SQLException {
        if (parseModel.school != null){
            schoolModel.updateSchool(new School(parseModel.school.getSchoolID(), tfSchoolName.getText()));
        }else {
            schoolModel.createSchool(tfSchoolName.getText());
        }
    }

    public void handleCancel(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
