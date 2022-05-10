package UI.MVC.Controller;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import BLL.Utility.Encryptor;
import UI.MVC.Model.AdminModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

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


    public EditUserController() throws IOException {
        adminModel = new AdminModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
        student = ParseModel.student;
        admin = ParseModel.admin;
        teacher = ParseModel.teacher;

    }

    public void updateUser() throws IOException, SQLException {
        if (rbIsAdmin.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            Admin tempAdmin = new Admin(admin.getId(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), admin.getSchoolId());
            adminModel.updateAdmin(tempAdmin);
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            Student tempStudent = new Student(student.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), student.getSchoolID());
            studentModel.updateStudent(tempStudent);
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            Teacher tempTeacher = new Teacher(teacher.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), teacher.getSchoolID());
            teacherModel.updateTeacher(tempTeacher);
        }

    }
}

