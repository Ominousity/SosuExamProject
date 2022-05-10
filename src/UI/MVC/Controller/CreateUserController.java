package UI.MVC.Controller;

import BE.School;
import UI.MVC.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public CreateUserController() throws IOException {
       adminModel = new AdminModel();
       teacherModel = new TeacherModel();
       studentModel = new StudentModel();
       schoolModel = new SchoolModel();
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
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            studentModel.createStudent(fName, lName, email, password, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            teacherModel.createTeacher(fName, lName, email,password, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
        }
    }
}
