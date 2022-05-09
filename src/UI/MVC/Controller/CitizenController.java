package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import UI.MVC.Model.CitizenModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class CitizenController {
    private SceneCreator sceneCreator;

    public CitizenController() {
        sceneCreator = new SceneCreator();
    }

    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

}
