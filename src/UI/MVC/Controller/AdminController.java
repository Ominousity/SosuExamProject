package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import BE.Teacher;
import UI.MVC.Model.AdminModel;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController {
    public TableColumn fNameTC;
    public TableColumn lNameTC;
    public TableColumn schoolTC;
    public TableColumn emailTC;
    public TableColumn pWordTC;
    public TableView studentTB;

    private AdminModel adminModel;
    private StudentModel studentModel;
    private TeacherModel teacherModel;

    private SceneCreator sceneCreator;

    public AdminController(){
        sceneCreator = new SceneCreator();
    }

    public void initialize(URL location, ResourceBundle resources) {
        fNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("Fornavn"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("Efternavn"));
        schoolTC.setCellValueFactory(new PropertyValueFactory<String, String>("Skole"));
        emailTC.setCellValueFactory(new PropertyValueFactory<String , String>("Email-Adresse"));
        pWordTC.setCellValueFactory(new PropertyValueFactory<String, String>("Kodeord"));
        try
        {
            ObservableList<Object> students = FXCollections.observableArrayList(teacherModel.getAllTeachers(), studentModel.getAllStudents(), adminModel.getAllAdmins());
            studentTB.setItems(students);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) throws SQLException {
        ObservableList<Object> students = FXCollections.observableArrayList(teacherModel.getAllTeachers(), studentModel.getAllStudents(), adminModel.getAllAdmins());
        studentTB.getItems().add(students);
    }

    public void handleRemove(ActionEvent actionEvent) {
    }

    public void handleEdit(ActionEvent actionEvent) {
    }
}
