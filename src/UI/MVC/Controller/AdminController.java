package UI.MVC.Controller;

import BE.School;
import BE.User;
import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.SchoolModel;
import UI.MVC.Model.UserModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TableColumn fNameTC;
    @FXML
    private TableColumn lNameTC;
    @FXML
    private TableColumn emailTC;
    @FXML
    private TableColumn userTypeTC;
    @FXML
    private TableView<User> tvUser;
    @FXML
    private TableView<School> tvSchool;
    @FXML
    private TableColumn tcSchoolName;
    @FXML
    private Button btnSchoolRemove;
    @FXML
    private Button btnSchool;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button exitBtn;

    private LoginSystem loginSystem;
    private SceneCreator sceneCreator;
    private UserModel userModel;
    private SchoolModel schoolModel;
    private ParseModel parseModel = ParseModel.getInstance();

    public AdminController() throws IOException, SQLException {
        sceneCreator = new SceneCreator();
        userModel = new UserModel();
        schoolModel = new SchoolModel();
        loginSystem = new LoginSystem();

        btnSchoolRemove = new Button();
        btnSchool = new Button();
        createBtn = new Button();
        deleteBtn = new Button();
        exitBtn = new Button();
    }

    /**
     * sets the value of the cells in the admin fxml
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitBtn.setOnMouseEntered(event -> exitBtn.getStyleClass().add("but-Hover"));
        exitBtn.setOnMouseExited(event -> exitBtn.getStyleClass().remove("but-Hover"));
        createBtn.setOnMouseEntered(event -> createBtn.getStyleClass().add("but-Hover"));
        createBtn.setOnMouseExited(event -> createBtn.getStyleClass().remove("but-Hover"));
        deleteBtn.setOnMouseEntered(event -> deleteBtn.getStyleClass().add("but-Hover"));
        deleteBtn.setOnMouseExited(event -> deleteBtn.getStyleClass().remove("but-Hover"));
        btnSchool.setOnMouseEntered(event -> btnSchool.getStyleClass().add("but-Hover"));
        btnSchool.setOnMouseExited(event -> btnSchool.getStyleClass().remove("but-Hover"));
        btnSchoolRemove.setOnMouseEntered(event -> btnSchoolRemove.getStyleClass().add("but-Hover"));
        btnSchoolRemove.setOnMouseExited(event -> btnSchoolRemove.getStyleClass().remove("but-Hover"));

        fNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("FName"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<String, String>("LName"));
        emailTC.setCellValueFactory(new PropertyValueFactory<String , String>("Email"));
        userTypeTC.setCellValueFactory(new PropertyValueFactory<String, String>("UserType"));
        try {
            ObservableList<User> users = FXCollections.observableArrayList(userModel.getAllUsers());
            tvUser.setItems(users);
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
    public void handleLogOut(ActionEvent actionEvent) throws FileNotFoundException {
        loginSystem.forgetLogin();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        sceneCreator.createStage(sceneCreator.createScene("../View/Login.fxml", "UI/CSS/MainStylesheet.css",this), "SOSU Logind", false);
        stage.close();
    }

    /**
     * opens and handles the CreateUser fxml
     */
    public void handleAdd() {
        if (tvUser.getSelectionModel().getSelectedItem() == null) {
            parseModel.user = null;
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateUserView.fxml", "UI/CSS/MainStylesheet.css",this), "Opret Bruger", false);
        }else if (tvUser.getSelectionModel().getSelectedItem() != null){
            parseModel.isEditingUser = true;
            parseModel.user = tvUser.getSelectionModel().getSelectedItem();
            System.out.println(parseModel.isEditingUser + "  " + parseModel.user.getFName());
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateUserView.fxml", "UI/CSS/MainStylesheet.css",this), "Opret Bruger", false);
        }
    }

    /**
     * removes an admin
     */
    public void handleRemove() {
        userModel.deleteUser(tvUser.getSelectionModel().getSelectedItem().getID());
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", tvUser.getSelectionModel().getSelectedItem().getUserType() + " " + parseModel.user.getFName() + "blev slettet", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * removes a school when a button is pressed
     */
    public void handleRemoveSchool() {
        schoolModel.deleteSchool(tvSchool.getSelectionModel().getSelectedItem().getSchoolID());
    }

    /**
     * Opens a new stage when a button is pressed
     */
    public void handleGoToCreateSchool(){
        if (tvSchool.getSelectionModel().getSelectedItem() == null) {
            parseModel.school = null;
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateSchoolView.fxml", "UI/CSS/MainStylesheet.css", this), "Opret Skole", false);
        }else if (tvSchool.getSelectionModel().getSelectedItem() != null){
            parseModel.school = tvSchool.getSelectionModel().getSelectedItem();
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateSchoolView.fxml", "UI/CSS/MainStylesheet.css", this), "Opret Skole", false);
        }
    }

    /**
     * Shows the users from the chosen school
     * @throws SQLException
     */
    public void showStudentsFromSchool() throws SQLException {
        if (tvSchool.getSelectionModel().getSelectedItem() != null){
            tvUser.setItems(userModel.getAllUsersFromSchool(tvSchool.getSelectionModel().getSelectedItem().getSchoolID()));
        }
    }
}
