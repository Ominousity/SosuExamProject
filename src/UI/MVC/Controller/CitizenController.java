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

    public TableColumn tcFName;
    public TableColumn tcLName;
    public TableColumn tcAddress;
    public TableColumn tcCPR;
    public TableView tvCitizens;
    ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();

    CitizenModel citizenModel;
    CitizenManager citizenManager;

    private SceneCreator sceneCreator;

    public CitizenController() {
        sceneCreator = new SceneCreator();
    }
    public void handleBack(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void fillTableview() throws SQLException {
        tcFName.setCellValueFactory(new PropertyValueFactory<Citizen, String>("FName"));
        tcLName.setCellValueFactory(new PropertyValueFactory<Citizen, Float>("LName"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Citizen, Float>("imdbRating"));
        tcCPR.setCellValueFactory(new PropertyValueFactory<Citizen, String>("categories"));
        tvCitizens.setItems(allCitizens);
    }

    public void assignStudentsIntoCitizen(Citizen citizen, Student student, int StudentID) throws SQLException {
        ArrayList<Citizen> citizens = citizenModel.getAllCitizensStudent(citizen ,student ,StudentID);
        for (Citizen citizen2 : citizens) {
            citizenManager.setCitizens(student.getFName(), student.getLName());
        }
    }

    /**
     * creates and return observablelist from list off all citizens.
     *
     * @return an observablelist of all citizens.
     */
    public ObservableList<Citizen> getCitizens(Citizen citizen, Student student, int StudentID) throws SQLException {
        allCitizens.clear();
        allCitizens.addAll(citizenModel.getAllCitizensStudent(citizen, student, StudentID));
        assignStudentsIntoCitizen(citizen, student, StudentID);
        return allCitizens;
    }

}
