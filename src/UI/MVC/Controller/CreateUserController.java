package UI.MVC.Controller;

import BE.School;
import UI.MVC.Model.*;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private ComboBox<School> schoolCB;
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private RadioButton rbIsTeacher;
    @FXML
    private RadioButton rbIsAdmin;
    @FXML
    private RadioButton rbIsStudent;

    private UserModel userModel;
    private SchoolModel schoolModel;
    private SceneCreator sceneCreator;

    public CreateUserController() throws IOException {
       userModel = new UserModel();
       schoolModel = new SchoolModel();
       sceneCreator = new SceneCreator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            schoolCB.getItems().addAll(schoolModel.getSchool());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleCreateUser() throws SQLException, IOException {


        String fName = tfFName.getText();
        String lName = tfLName.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String userType = "";

        if(rbIsStudent.isSelected()){
            userType = "STUDENT";
        }

        else if(rbIsAdmin.isSelected()){
            userType = "ADMIN";
        }
        else if(rbIsTeacher.isSelected()){
            userType = "TEACHER";
        }

        userModel.createUser(fName, lName, email, password, userType, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Student oprettet succesfuldt", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) tfEmail.getScene().getWindow();
            stage.close();
        }


    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    public void handleIsTeacher(ActionEvent actionEvent) {
    }

    public void handleIsAdmin(ActionEvent actionEvent) {
    }

    public void handleIsStudent(ActionEvent actionEvent) {
    }
}
