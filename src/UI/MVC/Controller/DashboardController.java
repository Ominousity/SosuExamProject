package UI.MVC.Controller;
import BE.Citizen;
import BE.CitizenCase;
import BLL.Utility.LoginSystem;
import UI.MVC.Model.CaseModel;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public TableView<Citizen> tvCitizen;
    public TableColumn tcFornavn;
    public TableColumn tcEfternavn;
    public TableColumn tcDOB;
    public TableView<CitizenCase> tvCases;
    public TableColumn tcCaseName;
    public Button logOutBtn;
    public Label lblLogin;
    public Label lblBorgerNavn;
    public TextArea caseText;
    public TableColumn tcCaseStatus;
    public Label promptText;
    private Stage stage;

    private LoginSystem loginSystem;
    private CaseModel caseModel;
    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private  ParseModel parseModel = ParseModel.getInstance();

    public DashboardController() throws IOException, SQLException {
        tvCitizen = new TableView();
        tcFornavn = new TableColumn();
        tcEfternavn = new TableColumn();
        tcDOB = new TableColumn();
        caseText = new TextArea();
        tvCases = new TableView<>();
        tcCaseName = new TableColumn<>();
        tcCaseStatus = new TableColumn<>();
        promptText = new Label();

        loginSystem = new LoginSystem();
        citizenModel = new CitizenModel();
        sceneCreator = new SceneCreator();
        caseModel = new CaseModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcFornavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("FName"));
        tcEfternavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("LName"));
        tcDOB.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Dob"));
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

        tcCaseName.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Name"));
        tcCaseStatus.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Status"));
    }

    /**
     * closes the current fxml and opens the login screen
     * @param actionEvent
     */
    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        loginSystem.forgetLogin();
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        stage.setScene(sceneCreator.createScene("../View/Login.fxml", "UI/CSS/MainStylesheet.css",this));
    }

    public void goToGeneralInfo(){
        if (parseModel.citizen == null){
            Alert alert = sceneCreator.popupBox(Alert.AlertType.WARNING, "Husk at vælge en borger først", "programmet kan ikke finde dataene fra borger", ButtonType.OK);
            alert.showAndWait();
        }else {
            stage = (Stage) tvCitizen.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/Generalinformation.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }

    public void goToHealthState(){
        if (parseModel.citizen == null){
            Alert alert = sceneCreator.popupBox(Alert.AlertType.WARNING, "Husk at vælge en borger først", "programmet kan ikke finde dataene fra borger", ButtonType.OK);
            alert.showAndWait();
        }else {
            stage = (Stage) tvCitizen.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/HealthView.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }

    public void goToFunctionState(){
        if (parseModel.citizen == null){
            Alert alert = sceneCreator.popupBox(Alert.AlertType.WARNING, "Husk at vælge en borger først", "programmet kan ikke finde dataene fra borger", ButtonType.OK);
            alert.showAndWait();
        }else {
            stage = (Stage) tvCitizen.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/Funktionsevne.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }

    public void getSelectedCitizen(MouseEvent mouseEvent) {
            parseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();

            if (tvCitizen.getSelectionModel().getSelectedItem() != null){
                tvCases.setItems(caseModel.getAllCases(parseModel.citizen.getID()));
            }
    }

    public void getSelectedCase(MouseEvent mouseEvent) {
        if (tvCases.getSelectionModel().getSelectedItem() != null){
            caseText.setText(tvCases.getSelectionModel().getSelectedItem().getCaseContent());
            promptText.setDisable(true);
            promptText.setOpacity(0);
        }
    }

    /**
     * creates a citizen
     * @param actionEvent
     */
    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }

    public void handleupdate(ActionEvent actionEvent) {
        ParseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }

    public void handleRemove(ActionEvent actionEvent) {
        citizenModel.deleteCitizen(tvCitizen.getSelectionModel().getSelectedItem().getID());
    }
}