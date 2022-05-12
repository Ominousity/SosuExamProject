package UI.MVC.Controller;

import BE.User;
import UI.MVC.Model.UserModel;
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

    private SceneCreator sceneCreator;
    private UserModel userModel;

    public AdminController() throws IOException {
        sceneCreator = new SceneCreator();
        userModel = new UserModel();

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
            ObservableList<User> users = FXCollections.observableArrayList(userModel.getAllUsers());
            studentTB.setItems(users);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createStage(sceneCreator.createScene("../View/Login.fxml", "UI/CSS/MainStylesheet.css",this), "SOSU Logind", false);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateUserView.fxml", "UI/CSS/MainStylesheet.css",this), "Opret Bruger", false);
    }

    public void handleRemove(ActionEvent actionEvent) throws SQLException
    {
        userModel.deleteUser(studentTB.getSelectionModel().getSelectedIndex());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Citizen was created", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void handleEdit(ActionEvent actionEvent) {
    }
}
