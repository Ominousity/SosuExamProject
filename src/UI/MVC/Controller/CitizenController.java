package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import UI.MVC.Model.CitizenModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class CitizenController {

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
