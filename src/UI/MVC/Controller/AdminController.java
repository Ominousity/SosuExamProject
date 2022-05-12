package UI.MVC.Controller;

import UI.MVC.Model.AdminModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.TeacherModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
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

    public AdminController() throws IOException {
        sceneCreator = new SceneCreator();
        adminModel = new AdminModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("FName"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("Lname"));
        schoolTC.setCellValueFactory(new PropertyValueFactory<String, String>("School"));
        emailTC.setCellValueFactory(new PropertyValueFactory<String , String>("Email"));
        pWordTC.setCellValueFactory(new PropertyValueFactory<String, String>("Password"));
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
        sceneCreator.createStage(sceneCreator.createScene("../View/Login.fxml", "UI/CSS/StudentStylesheet.css",this), "SOSU Logind", false);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateUserView.fxml", "UI/CSS/StudentStylesheet.css",this), "Opret Bruger", false);
    }

    public void handleRemove(ActionEvent actionEvent) throws SQLException
    {
        studentModel.deleteStudent(studentTB.getSelectionModel().getSelectedIndex());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Citizen was created", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void handleEdit(ActionEvent actionEvent) {
    }
}
