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


    public GeneralinformationController() {
        ressourcerBtn = new Button();
        mestringBtn = new Button();
        motivationBtn = new Button();
        rollerBtn = new Button();
        vanerBtn = new Button();
        uddannelseBtn = new Button();
        livshistorieBtn = new Button();
        netværkBtn = new Button();
        helbredBtn = new Button();
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
        ressourcerBtn.setOnMouseEntered(event -> {
            ressourcerBtn.getStyleClass().add("gen-buttons-hover");
        });
        vanerBtn.setOnMouseEntered(event -> {
            vanerBtn.getStyleClass().add("gen-buttons-hover");
        });
        mestringBtn.setOnMouseEntered(event -> {
            mestringBtn.getStyleClass().add("gen-buttons-hover");
        });
        motivationBtn.setOnMouseEntered(event -> {
            motivationBtn.getStyleClass().add("gen-buttons-hover");
        });
        rollerBtn.setOnMouseEntered(event -> {
            rollerBtn.getStyleClass().add("gen-buttons-hover");
        });
        uddannelseBtn.setOnMouseEntered(event -> {
            uddannelseBtn.getStyleClass().add("gen-buttons-hover");
        });
        livshistorieBtn.setOnMouseEntered(event -> {
            livshistorieBtn.getStyleClass().add("gen-buttons-hover");
        });
        netværkBtn.setOnMouseEntered(event -> {
            netværkBtn.getStyleClass().add("gen-buttons-hover");
        });
        helbredBtn.setOnMouseEntered(event -> {
            helbredBtn.getStyleClass().add("gen-buttons-hover");
        });

        ressourcerBtn.setOnMouseExited(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-hover");
        });
        vanerBtn.setOnMouseExited(event -> {
            vanerBtn.getStyleClass().remove("gen-buttons-hover");
        });
        mestringBtn.setOnMouseExited(event -> {
            mestringBtn.getStyleClass().remove("gen-buttons-hover");
        });
        motivationBtn.setOnMouseExited(event -> {
            motivationBtn.getStyleClass().remove("gen-buttons-hover");
        });
        rollerBtn.setOnMouseExited(event -> {
            rollerBtn.getStyleClass().remove("gen-buttons-hover");
        });
        uddannelseBtn.setOnMouseExited(event -> {
            uddannelseBtn.getStyleClass().remove("gen-buttons-hover");
        });
        livshistorieBtn.setOnMouseExited(event -> {
            livshistorieBtn.getStyleClass().remove("gen-buttons-hover");
        });
        netværkBtn.setOnMouseExited(event -> {
            netværkBtn.getStyleClass().remove("gen-buttons-hover");
        });
        helbredBtn.setOnMouseExited(event -> {
            helbredBtn.getStyleClass().remove("gen-buttons-hover");
        });
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
        ressourcerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().add("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Ressourcer);
        id = 1;
    }

    public void handleVaner (ActionEvent actionEvent){
        vanerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().add("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Vaner);
        id = 2;
    }

    public void handleMestring (ActionEvent actionEvent){
        mestringBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().add("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Mestring);
        id = 3;
    }

    public void handleMotivation (ActionEvent actionEvent){
        motivationBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().add("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Motivation);
        id = 4;
    }

    public void handleRoller (ActionEvent actionEvent){
        rollerBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().add("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Roller);
        id = 5;
    }

    public void handleUddannelse (ActionEvent actionEvent){
        uddannelseBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().add("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Uddannelse);
        id = 6;
    }

    public void handleLivshistorie (ActionEvent actionEvent){
        livshistorieBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().add("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Livshistorie);
        id = 7;
    }

    public void handleNetværk (ActionEvent actionEvent){
        netværkBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().add("gen-buttons-clicked");
            helbredBtn.getStyleClass().remove("gen-buttons-clicked");
        });
        textTA.setText(Netværk);
        id = 8;
    }

    public void handleHelbred (ActionEvent actionEvent){
        helbredBtn.setOnMouseClicked(event -> {
            ressourcerBtn.getStyleClass().remove("gen-buttons-clicked");
            vanerBtn.getStyleClass().remove("gen-buttons-clicked");
            mestringBtn.getStyleClass().remove("gen-buttons-clicked");
            motivationBtn.getStyleClass().remove("gen-buttons-clicked");
            rollerBtn.getStyleClass().remove("gen-buttons-clicked");
            uddannelseBtn.getStyleClass().remove("gen-buttons-clicked");
            livshistorieBtn.getStyleClass().remove("gen-buttons-clicked");
            netværkBtn.getStyleClass().remove("gen-buttons-clicked");
            helbredBtn.getStyleClass().add("gen-buttons-clicked");
        });
        textTA.setText(Helbred);
        id = 9;
    }
}
