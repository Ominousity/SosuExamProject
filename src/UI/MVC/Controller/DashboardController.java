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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Label lblUsertype;
    public Button btnOpret;
    public Button btnOpdate;
    public Button btnDelete;
    public Button btnGe;
    public Button btnFu;
    public Button btnHe;
    public Button btnCase;

    private LoginSystem loginSystem;
    private CaseModel caseModel;
    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private ParseModel parseModel = ParseModel.getInstance();

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
        lblLogin = new Label();
        lblUsertype = new Label();
        loginSystem = new LoginSystem();
        citizenModel = new CitizenModel();
        sceneCreator = new SceneCreator();
        caseModel = new CaseModel();
        btnOpret = new Button();
        btnDelete = new Button();
        btnOpdate = new Button();
        btnGe = new Button();
        btnFu = new Button();
        btnHe = new Button();
        btnCase = new Button();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGe.setOnMouseEntered(event -> btnGe.getStyleClass().add("genBut-Hover"));
        btnGe.setOnMouseExited(event -> btnGe.getStyleClass().remove("genBut-Hover"));
        btnHe.setOnMouseEntered(event -> btnHe.getStyleClass().add("heBut-Hover"));
        btnHe.setOnMouseExited(event -> btnHe.getStyleClass().remove("heBut-Hover"));
        btnFu.setOnMouseEntered(event -> btnFu.getStyleClass().add("fuBut-Hover"));
        btnFu.setOnMouseExited(event -> btnFu.getStyleClass().remove("fuBut-Hover"));
        btnOpdate.setOnMouseEntered(event -> btnOpdate.getStyleClass().add("teBut-Hover"));
        btnOpdate.setOnMouseExited(event -> btnOpdate.getStyleClass().remove("teBut-Hover"));
        btnOpret.setOnMouseEntered(event -> btnOpret.getStyleClass().add("teBut-Hover"));
        btnOpret.setOnMouseExited(event -> btnOpret.getStyleClass().remove("teBut-Hover"));
        btnDelete.setOnMouseEntered(event -> btnDelete.getStyleClass().add("teBut-Hover"));
        btnDelete.setOnMouseExited(event -> btnDelete.getStyleClass().remove("teBut-Hover"));
        btnCase.setOnMouseEntered(event -> btnCase.getStyleClass().add("teBut-Hover"));
        btnCase.setOnMouseExited(event -> btnCase.getStyleClass().remove("teBut-Hover"));

        tcFornavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("FName"));
        tcEfternavn.setCellValueFactory(new PropertyValueFactory<Citizen, String>("LName"));
        tcDOB.setCellValueFactory(new PropertyValueFactory<Citizen, Integer>("age"));
        if (parseModel.user.getUserType().contains("STUDENT")){
            try {
                btnOpdate.setOpacity(0);
                btnOpret.setOpacity(0);
                btnDelete.setOpacity(0);
                btnCase.setOpacity(0);
                btnDelete.setDisable(true);
                btnOpret.setDisable(true);
                btnOpdate.setDisable(true);
                btnCase.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                tvCitizen.setItems(citizenModel.getAllCitizensSchool(parseModel.user.getSchoolID()));
                System.out.println(citizenModel.getAllCitizensStudent(parseModel.user.getID()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        tcCaseName.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Name"));
        tcCaseStatus.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Status"));
        caseText.setEditable(false);
        lblLogin.setText(ParseModel.user.getEmail());
        lblUsertype.setText(ParseModel.user.getUserType());
        tvCases.setPlaceholder(new Label("OBS HUSK AT VÆLGE EN BORGER FØRST!"));
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
    public void handleAddCase(ActionEvent actionEvent) {
        if (tvCases.getSelectionModel().getSelectedItem() != null) {
            parseModel.cases = tvCases.getSelectionModel().getSelectedItem();
            parseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateCaseView.fxml", "UI/CSS/MainStylesheet.css", this), "Create Case", false);
        }else{
            parseModel.cases = null;
            sceneCreator.createStage(sceneCreator.createScene("../View/CreateCaseView.fxml", "UI/CSS/MainStylesheet.css", this), "Create Case", false);
        }
    }

    public void handleAdd(){
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Case", false);
        tvCitizen.refresh();
    }
    public void handleupdate(ActionEvent actionEvent) {
        ParseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }

    public void handleRemove(ActionEvent actionEvent) {
        citizenModel.deleteCitizen(tvCitizen.getSelectionModel().getSelectedItem().getID());
    }
}