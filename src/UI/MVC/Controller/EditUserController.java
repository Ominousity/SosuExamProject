package UI.MVC.Controller;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import UI.MVC.Model.AdminModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditUserController {
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


    public EditUserController() throws IOException {
        adminModel = new AdminModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
        student = ParseModel.student;
        admin = ParseModel.admin;
        teacher = ParseModel.teacher;

    }

    public void updateUser(){
        
    }
}

