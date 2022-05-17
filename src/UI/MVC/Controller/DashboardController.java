package UI.MVC.Controller;
import BE.Citizen;
import BLL.Utility.LoginSystem;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public TableView tvCitizen;
    public TableColumn tcFornavn;
    public TableColumn tcEfternavn;
    public TableColumn tcDOB;
    public TableView tvCases;
    public TableColumn tcCaseName;
    public Button logOutBtn;
    public Label lblLogin;
    public Label lblBorgerNavn;
    Stage stage;

    private LoginSystem loginSystem;
    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;

    public DashboardController() throws IOException {
        tvCitizen = new TableView();
        tcFornavn = new TableColumn();
        tcEfternavn = new TableColumn();
        tcDOB = new TableColumn();

        citizenModel = new CitizenModel();
        sceneCreator = new SceneCreator();
        checkIdentity();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcFornavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("fName"));
        tcEfternavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("lName"));
        tcDOB.setCellValueFactory(new PropertyValueFactory<Citizen, String>("dob"));
        try
        {
            tvCitizen.setItems(citizenModel.getAllCitizensSchool(1));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * closes the current fxml and opens the login screen
     * @param actionEvent
     */
    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        loginSystem.forgetLogin();
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        sceneCreator.createScene("../View/Login.fxml", "UI/CSS/MainStylesheet.css",this);
        stage.close();
    }

    /**
     * checks what kind of user is logged in
     */
    public void checkIdentity(){
        if (ParseModel.isStudent) {
            Platform.runLater(() -> {

            });
        }
    }

    public void goToGeneralInfo(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("MVC/View/Generalinformation.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void goToHealthState(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/HealthView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void goToFunctionState(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("MVC/View/Funktionsevne.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    /**
     * creates a citizen
     * @param actionEvent
     */
    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }

    public void handleupdate(ActionEvent actionEvent) {
    }

    public void handleRemove(ActionEvent actionEvent) {
    }
}