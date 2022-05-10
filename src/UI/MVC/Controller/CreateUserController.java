package UI.MVC.Controller;

import BLL.Utility.Encryptor;
import UI.MVC.Model.AdminModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
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
    private Encryptor encryptor;


    public CreateUserController() throws IOException {
       adminModel = new AdminModel();
       teacherModel = new TeacherModel();
       studentModel = new StudentModel();
       encryptor = new Encryptor();
    }

    public void handleCreateUser() throws SQLException, IOException {
        if (rbIsAdmin.isSelected()){
            tfFName.setDisable(true);
            tfLName.setDisable(true);
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            adminModel.createAdmin(email, encryptor.Encrypt(password));
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            studentModel.createStudent(fName, lName, email, encryptor.Encrypt(password));
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            String fName = tfFName.getText();
            String lName = tfLName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            teacherModel.createTeacher(fName, lName, email, encryptor.Encrypt(password));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
