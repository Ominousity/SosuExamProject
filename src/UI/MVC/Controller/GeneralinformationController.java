package UI.MVC.Controller;

import BE.GeneralInfo;
import UI.MVC.Model.GeneralinformationModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GeneralinformationController implements Initializable {
    @FXML
    private Button ressourcerBtn;
    @FXML
    private Button mestringBtn;
    @FXML
    private Button motivationBtn;
    @FXML
    private Button rollerBtn;
    @FXML
    private Button vanerBtn;
    @FXML
    private Button uddannelseBtn;
    @FXML
    private Button livshistorieBtn;
    @FXML
    private Button netværkBtn;
    @FXML
    private Button helbredBtn;
    @FXML
    private TextArea textTA;
    @FXML
    private Button saveBtn;
    @FXML
    private Button backBtn;
    private SceneCreator sceneCreator;
    private ParseModel parseModel;
    private GeneralinformationModel generalinformationModel;
    String Ressourcer;
    String Mestring;
    String Motivation;
    String Roller;
    String Vaner;
    String Uddannelse;
    String Livshistorie;
    String Netværk;
    String Helbred;
    private int id = 0;


    public GeneralinformationController() {
        sceneCreator = new SceneCreator();
        parseModel = new ParseModel();
        generalinformationModel = new GeneralinformationModel();
    }
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/MainStylesheet.css",this);
        stage.setScene(scene);
    }

    public void handleSave(ActionEvent actionEvent) throws SQLException {
    switch (id){
        case 1:
            getGeneralInfo().setRessourcer(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 2:
            getGeneralInfo().setVaner(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 3:
            getGeneralInfo().setMestring(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 4:
            getGeneralInfo().setMotivation(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 5:
            getGeneralInfo().setRoller(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 6:
            getGeneralInfo().setUddannelseJob(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 7:
            getGeneralInfo().setLivshistorie(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 8:
            getGeneralInfo().setNetværk(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo());
            break;
        case 9:
            getGeneralInfo().setHelbredsoplysninger(textTA.getText());
            generalinformationModel.updateInfo(getGeneralInfo()); 
            break;
       }
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Info was saved", ButtonType.OK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public GeneralInfo getGeneralInfo() throws SQLException {
        GeneralInfo generalInfo = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID());
        Ressourcer =  generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getRessourcer();
        Vaner = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getVaner();
        Mestring = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getMestring();
        Motivation = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getMotivation();
        Roller = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getRoller();
        Uddannelse = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getUddannelseJob();
        Livshistorie = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getLivshistorie();
        Netværk = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getNetværk();
        Helbred = generalinformationModel.getGeneralInfo(ParseModel.citizen.getID()).getHelbredsoplysninger();
        return generalInfo;
    }

    public void handleRessourcer (ActionEvent actionEvent){
        textTA.setText(Ressourcer);
        id = 1;
    }

    public void handleVaner (ActionEvent actionEvent){
        textTA.setText(Vaner);
        id = 2;
    }

    public void handleMestring (ActionEvent actionEvent){
        textTA.setText(Mestring);
        id = 3;
    }

    public void handleMotivation (ActionEvent actionEvent){
        textTA.setText(Motivation);
        id = 4;
    }

    public void handleRoller (ActionEvent actionEvent){
        textTA.setText(Roller);
        id = 5;
    }

    public void handleUddannelse (ActionEvent actionEvent){
        textTA.setText(Uddannelse);
        id = 6;
    }

    public void handleLivshistorie (ActionEvent actionEvent){
        textTA.setText(Livshistorie);
        id = 7;
    }

    public void handleNetværk (ActionEvent actionEvent){
        textTA.setText(Netværk);
        id = 8;
    }

    public void handleHelbred (ActionEvent actionEvent){
        textTA.setText(Helbred);
        id = 9;
    }
}
