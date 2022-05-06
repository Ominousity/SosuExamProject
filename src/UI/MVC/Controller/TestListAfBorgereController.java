package UI.MVC.Controller;

import BE.Category;
import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import BLL.StudentManager;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestListAfBorgereController {

    CitizenManager citizenManager;
    CitizenModel citizenModel;

    ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();

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
