package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import UI.MVC.Model.CitizenModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TextArea taCasetext;
    private TableColumn tvCases;
    private Label fNameLbl;
    private Label lNameLbl;
    private Label dobLbl;
    private Label socialSecLbl;
    private ImageView citizenImg;
    private Button genInfoBtn;

    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;

    public CitizenController() throws IOException
    {
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
    }

    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {

    }
}
