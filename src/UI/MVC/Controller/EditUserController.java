package UI.MVC.Controller;

import BLL.Utility.Encryptor;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EditUserController {
    @FXML
    private RadioButton rbIsAdmin;
    @FXML
    private RadioButton rbIsStudent;
    @FXML
    private RadioButton rbIsTeacher;
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;

    private AdminModel adminModel;
    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private Admin admin;
    private Student student;
    private Teacher teacher;
    private Encryptor encryptor;
    private SceneCreator sceneCreator;


    public EditUserController() throws IOException {
        adminModel = new AdminModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
        student = ParseModel.student;
        admin = ParseModel.admin;
        teacher = ParseModel.teacher;
        sceneCreator = new SceneCreator();
    }

    public void updateUser() throws IOException, SQLException {
        if (rbIsAdmin.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            Admin tempAdmin = new Admin(admin.getId(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), admin.getSchoolId());
            adminModel.updateAdmin(tempAdmin);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Admin var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            Student tempStudent = new Student(student.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), student.getSchoolID());
            studentModel.updateStudent(tempStudent);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Student var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            Teacher tempTeacher = new Teacher(teacher.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), teacher.getSchoolID());
            teacherModel.updateTeacher(tempTeacher);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Teacher var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }

    }
}

