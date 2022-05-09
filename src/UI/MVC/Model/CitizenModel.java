package UI.MVC.Model;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import DAL.CitizenDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenModel
{
    private ObservableList<Citizen> citizenList;

    private CitizenManager citizenManager;

    public CitizenModel(){
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

    public void createCitizen(String FName, String LName, String dob,String Address,String CPR){
        citizenManager.createCitizen(FName, LName, dob, Address, CPR);
    }

    public void createCitizenToStudent(Citizen citizen, Student student) throws SQLException {
        citizenManager.createCitizenToStudent(citizen,student);
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

    public void duplicateCitizen(int citizenID){
        //TODO Lav senere når vi har bedre overblik over hvordan ting skal gøres
    }



}

