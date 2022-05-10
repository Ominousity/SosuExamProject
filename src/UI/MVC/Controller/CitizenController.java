package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import UI.MVC.Model.CaseModel;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private Citizen citizen;

    public CitizenController() throws IOException
    {

        caseModel = new CaseModel();
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
    }

    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleGoToHealthState(ActionEvent actionEvent){
        sceneCreator.createStage(sceneCreator.createScene("../View/HealthView.fxml", "UI/CSS/MainStylesheet.css", this), "Helbred",  false);
    }

    public void handleGoToFunctionState(ActionEvent actionEvent){
        sceneCreator.createStage(sceneCreator.createScene("../View/Funktionsevne.fxml", "UI/CSS/MainStylesheet.css", this), "Funktion",  false);
    }

    public void handleGoToGeneralInfo (ActionEvent actionEvent){
        sceneCreator.createStage(sceneCreator.createScene("../View/Generalinformation.fxml", "UI/CSS/MainStylesheet.css", this), "GeneralInfo",  false);
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        tcCases.setCellValueFactory(new PropertyValueFactory<String, String>("tvCases"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<String, String>("tvStatus"));
        try {
            ObservableList<Object> cases = FXCollections.observableArrayList(caseModel.getAllCases(citizen.getID()));
            tvCases.setItems(cases);
        }catch (Exception e){
            e.printStackTrace();
        }

        fNameLbl.setText(ParseModel.citizen.getFName());
        lNameLbl.setText(ParseModel.citizen.getLName());
        socialSecLbl.setText(ParseModel.citizen.getCPR());


    }

    public void handleAddCase(ActionEvent actionEvent) {
    }

    public void handleRemoveCase(ActionEvent actionEvent) {
    }
}
