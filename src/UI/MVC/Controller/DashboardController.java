package UI.MVC.Controller;
import BE.Citizen;
import BLL.CitizenManager;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TableView<Citizen> citizenTV;
    @FXML
    private TableColumn fNameTC;
    @FXML
    private TableColumn lNameTC;
    @FXML
    private TableColumn dobTC;

    @FXML
    private Button addBtn;
    @FXML
    private Pane seperator;


    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;

    public DashboardController() throws IOException {
        citizenTV = new TableView();
        fNameTC = new TableColumn();
        lNameTC = new TableColumn();
        dobTC = new TableColumn();
        

        citizenModel = new CitizenModel();
        sceneCreator = new SceneCreator();
        checkIdentity();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fNameTC.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Fornavn"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Efternavn"));
        dobTC.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Fødselsdato"));

        try
        {
            citizenTV.setItems(citizenModel.getAllCitizensStudent(ParseModel.student.getID()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) addBtn.getScene().getWindow();
        sceneCreator.createScene("../View/Login.fxml","UI/CSS/MainStylesheet.css",this);
        stage.close();
    }

    public void handleAdd(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CreateCitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Create Citizen", false);
    }
    
    public void checkIdentity(){
        if (ParseModel.student != null) {
            Platform.runLater(() -> {
                addBtn.setDisable(true);
                addBtn.setOpacity(0);
                seperator.setOpacity(0);
            });
        }
    }
}