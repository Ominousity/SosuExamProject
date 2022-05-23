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

import java.io.IOException;
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
    private GeneralinformationModel generalinformationModel;
    private  ParseModel parseModel = ParseModel.getInstance();
    private String Ressourcer;
    private String Mestring;
    private String Motivation;
    private String Roller;
    private String Vaner;
    private String Uddannelse;
    private String Livshistorie;
    private String Netværk;
    private String Helbred;
    private int id = 0;


    public GeneralinformationController() throws IOException {
        sceneCreator = new SceneCreator();
        generalinformationModel = new GeneralinformationModel();
    }

    /**
     * closes the current fxml and opens the CitizenView
     * @param actionEvent
     */
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css",this);
        stage.setScene(scene);
    }

    /**
     * Saves the information put in by the user
     * @param actionEvent
     * @throws SQLException
     */
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
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Info was saved", ButtonType.OK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * gets the general information that's belonging to the specific citizen
     * @return
     * @throws SQLException
     */
    public GeneralInfo getGeneralInfo() throws SQLException {
        GeneralInfo generalInfo = generalinformationModel.getGeneralInfo(parseModel.citizen.getID());
        Ressourcer =  generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getRessourcer();
        Vaner = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getVaner();
        Mestring = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getMestring();
        Motivation = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getMotivation();
        Roller = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getRoller();
        Uddannelse = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getUddannelseJob();
        Livshistorie = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getLivshistorie();
        Netværk = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getNetværk();
        Helbred = generalinformationModel.getGeneralInfo(parseModel.citizen.getID()).getHelbredsoplysninger();
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
