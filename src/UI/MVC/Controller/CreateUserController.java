package UI.MVC.Controller;

import BE.School;
import UI.MVC.Model.*;
import BLL.Utility.Encryptor;
import UI.MVC.Model.AdminModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
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

    private TeacherModel teacherModel;
    private StudentModel studentModel;
    private AdminModel adminModel;
    private SchoolModel schoolModel;
    private SceneCreator sceneCreator;

    public CreateUserController() throws IOException {
       adminModel = new AdminModel();
       teacherModel = new TeacherModel();
       studentModel = new StudentModel();
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
        if (rbIsAdmin.isSelected()){
            tfFName.setDisable(true);
            tfLName.setDisable(true);
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            adminModel.createAdmin(email, password, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Admin var lavet", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            studentModel.createStudent(fName, lName, email, password, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Student var lavet", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            teacherModel.createTeacher(fName, lName, email,password, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Teacher var lavet", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
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
