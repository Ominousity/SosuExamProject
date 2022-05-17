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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public TableView<Citizen> tvCitizen;
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
    private  ParseModel parseModel = ParseModel.getInstance();

    public DashboardController() throws IOException {
        tvCitizen = new TableView();
        tcFornavn = new TableColumn();
        tcEfternavn = new TableColumn();
        tcDOB = new TableColumn();

        citizenModel = new CitizenModel();
        sceneCreator = new SceneCreator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcFornavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("FName"));
        tcEfternavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("LName"));
        tcDOB.setCellValueFactory(new PropertyValueFactory<Citizen, String>("DOB"));
        if (parseModel.user.getUserType().contains("STUDENT")){
            try {
                tvCitizen.setItems(citizenModel.getAllCitizensStudent(parseModel.user.getID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println(parseModel.user.getSchoolID());
                tvCitizen.setItems(citizenModel.getAllCitizensSchool(parseModel.user.getSchoolID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void goToGeneralInfo(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/Generalinformation.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void goToHealthState(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/HealthView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void goToFunctionState(){
        stage = (Stage) tvCitizen.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/Funktionsevne.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    public void getSelectedItem(MouseEvent mouseEvent) {
        parseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();
        System.out.println(parseModel.citizen.getFName());
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