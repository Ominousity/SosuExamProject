package UI.MVC.Controller;
import BE.Citizen;
import BE.CitizenCase;
import BLL.Utility.LoginSystem;
import UI.MVC.Model.CaseModel;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TableView<Citizen> tvCitizen;
    @FXML
    private TableColumn tcFornavn;
    @FXML
    private TableColumn tcEfternavn;
    @FXML
    private TableColumn tcDOB;
    @FXML
    private TableView<CitizenCase> tvCases;
    @FXML
    private TableColumn tcCaseName;
    @FXML
    private Button logOutBtn;
    @FXML
    private Label lblLogin;
    @FXML
    private TextArea caseText;
    @FXML
    private TableColumn tcCaseStatus;
    @FXML
    private Label promptText;
    @FXML
    private Label lblUsertype;
    @FXML
    private Button btnOpret;
    @FXML
    private Button btnOpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnGe;
    @FXML
    private Button btnFu;
    @FXML
    private Button btnHe;
    @FXML
    private Button btnCase;

    private Stage stage;
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
                tvCitizen.setItems(citizenModel.getAllCitizensStudent(parseModel.user.getID()));
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

    /**
     * changes the scene to generalinfomation if a citizen has been selected.
     */
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

    /**
     * changes the scene to healthstate if a citizen has been selected.
     */
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

    /**
     * changes the scene to functionstate if a citizen has been selected.
     */
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

    /**
     * when a citizen has been selected all casses for that citizen will come.
     * @param mouseEvent
     */
    public void getSelectedCitizen(MouseEvent mouseEvent) {
            parseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();

            if (tvCitizen.getSelectionModel().getSelectedItem() != null){
                tvCases.setItems(caseModel.getAllCases(parseModel.citizen.getID()));
            }
    }

    /**
     * when a case has been selected the cases text will come.
     * @param mouseEvent
     */
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

    /**
     * changes the scene to citizenCreate.
     */
    public void handleAdd(){
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Case", false);
        tvCitizen.refresh();
    }

    /**
     * changes the scene to citizenCreate and sets the parsemodel citizen to the citizen marked in the citizen list.
     * @param actionEvent
     */
    public void handleupdate(ActionEvent actionEvent) {
        ParseModel.citizen = tvCitizen.getSelectionModel().getSelectedItem();
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }

    /**
     * removes the selected citizen.
     * @param actionEvent
     */
    public void handleRemove(ActionEvent actionEvent) {
        citizenModel.deleteCitizen(tvCitizen.getSelectionModel().getSelectedItem().getID());
    }
}