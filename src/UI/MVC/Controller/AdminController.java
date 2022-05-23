package UI.MVC.Controller;

import BE.School;
import BE.User;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SchoolModel;
import UI.MVC.Model.UserModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public TableColumn fNameTC;
    public TableColumn lNameTC;
    public TableColumn emailTC;
    public TableColumn userTypeTC;
    public TableView<User> studentTB;
    public TableView<School> tvSchool;
    public TableColumn tcSchoolName;

    private SceneCreator sceneCreator;
    private UserModel userModel;
    private SchoolModel schoolModel;
    private ParseModel parseModel = ParseModel.getInstance();

    public AdminController() throws IOException {
        sceneCreator = new SceneCreator();
        userModel = new UserModel();
        schoolModel = new SchoolModel();
    }

    /**
     * sets the value of the cells in the admin fxml
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("FName"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("LName"));
        emailTC.setCellValueFactory(new PropertyValueFactory<String , String>("Email"));
        userTypeTC.setCellValueFactory(new PropertyValueFactory<String, String>("UserType"));
        try {
            ObservableList<User> users = FXCollections.observableArrayList(userModel.getAllUsers());
            studentTB.setItems(users);
        } catch (Exception e){
            e.printStackTrace();
        }

        tcSchoolName.setCellValueFactory(new PropertyValueFactory<String, String>("SchoolName"));
        try {
            ObservableList<School> schools = FXCollections.observableArrayList(schoolModel.getSchool());
            tvSchool.setItems(schools);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * close the present view and opens the previous
     * @param actionEvent
     */
    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createStage(sceneCreator.createScene("../View/Login.fxml", "UI/CSS/MainStylesheet.css",this), "SOSU Logind", false);
        stage.close();
    }

    /**
     * opens and handles the CreateUser fxml
     * @param actionEvent
     */
    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateUserView.fxml", "UI/CSS/MainStylesheet.css",this), "Opret Bruger", false);
    }

    /**
     * removes an admin
     * @param actionEvent
     */
    public void handleRemove(ActionEvent actionEvent) {
        userModel.deleteUser(studentTB.getSelectionModel().getSelectedItem().getID());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", parseModel.user.getUserType() + " " + parseModel.user.getFName() + "blev slettet", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void handleEdit(ActionEvent actionEvent) {
    }

    public void handleGoToCreateSchool(){
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateSchoolView.fxml", "UI/CSS/MainStylesheet.css", this), "Opret Skole", false);
    }

    public void showStudentsFromSchool(MouseEvent mouseEvent) throws SQLException {
        if (tvSchool.getSelectionModel().getSelectedItem() != null){
            studentTB.setItems(userModel.getAllUsersFromSchool(tvSchool.getSelectionModel().getSelectedItem().getSchoolID()));
        }
    }
}
