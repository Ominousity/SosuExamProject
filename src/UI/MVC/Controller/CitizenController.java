package UI.MVC.Controller;

import UI.MVC.Model.CaseModel;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CitizenController implements Initializable
{
    public TableColumn tcStatus;
    public TableView tvCases;
    @FXML
    private Button helbredBtn;
    @FXML
    private Button funktionBtn;
    @FXML
    private TextArea taCasetext;
    @FXML
    private TableColumn tcCases;
    @FXML
    private Label fNameLbl;
    @FXML
    private Label lNameLbl;
    @FXML
    private Label dobLbl;
    @FXML
    private Label socialSecLbl;
    @FXML
    private ImageView citizenImg;
    @FXML
    private Button genInfoBtn;

    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private CaseModel caseModel;

    public CitizenController() throws IOException
    {
        caseModel = new CaseModel();
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
    }

    //close the present view and opens the previous
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) fNameLbl.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    //close the present view and opens the HealthView fxml
    public void handleGoToHealthState(ActionEvent actionEvent){
        Stage stage = (Stage) fNameLbl.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/HealthView.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    //close the present view and opens the Funktionsevne fxml
    public void handleGoToFunctionState(ActionEvent actionEvent){
        Stage stage = (Stage) fNameLbl.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/Funktionsevne.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    //close the present view and opens the GeneralInformation fxml
    public void handleGoToGeneralInfo (ActionEvent actionEvent){
        Stage stage = (Stage) fNameLbl.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/Generalinformation.fxml", "UI/CSS/MainStylesheet.css", this);
        stage.setScene(scene);
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        tcCases.setCellValueFactory(new PropertyValueFactory<String, String>("tvCases"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<String, String>("tvStatus"));
        try {
            ObservableList<Object> cases = FXCollections.observableArrayList(caseModel.getAllCases(ParseModel.citizen.getID()));
            tvCases.setItems(cases);
        }catch (Exception e){
            e.printStackTrace();
        }

        fNameLbl.setText(ParseModel.citizen.getFName());
        lNameLbl.setText(ParseModel.citizen.getLName());
        socialSecLbl.setText(ParseModel.citizen.getCPR());


    }

    //close the present view and opens the HealthView
    public void handleAddCase(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCaseView.fxml", "UI/CSS/MainStylesheet.css", this), "GeneralInfo",  false);
    }

    public void handleRemoveCase(ActionEvent actionEvent) {
    }
}
