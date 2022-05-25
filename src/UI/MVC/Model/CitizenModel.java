package UI.MVC.Model;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenModel
{
    private ObservableList<Citizen> citizenList;

    private CitizenManager citizenManager;

    public CitizenModel() throws IOException {
        citizenList = FXCollections.observableArrayList();
        citizenManager = new CitizenManager();
    }

    public ObservableList<Citizen> getAllCitizensSchool(int schoolID) throws SQLException {
        citizenList.clear();
        citizenList.addAll(citizenManager.getAllCitizensSchool(schoolID));
        return citizenList;
    }

    public ObservableList<Citizen> getAllCitizensStudent(int studentID) throws SQLException{
        citizenList.clear();
        citizenList.addAll(citizenManager.getAllCitizensStudent(studentID));
        return citizenList;
    }

    public ObservableList<Citizen> getTemplateCitizens() {
        citizenList.clear();
        citizenList.addAll(citizenManager.getTemplateCitizens());
        return citizenList;
    }

    public Citizen createCitizen(String fName, String lName, int age, boolean isTemplate, int schoolID){
        return citizenManager.createCitizen(fName, lName, age, isTemplate, schoolID);
    }

    public void createCitizenToStudent(int citizenID, int studentID) throws SQLException {
        citizenManager.bindCitizenToStudent(citizenID, studentID);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenManager.updateCitizen(citizen);
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException{
        citizenManager.removeCitizenFromStudent(citizen, student);
    }

    public void deleteCitizen(int citizenID){
        citizenManager.deleteCitizen(citizenID);
    }

    public Citizen duplicateCitizen(Citizen citizen, int schoolID){
        return citizenManager.dublicateCitizen(citizen, schoolID);
    }



}

