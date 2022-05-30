package UI.MVC.Model;

import BE.Citizen;
import BLL.CitizenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;

public class CitizenModel
{
    private ObservableList<Citizen> citizenList;

    private CitizenManager citizenManager;

    public CitizenModel() throws IOException {
        citizenList = FXCollections.observableArrayList();
        citizenManager = new CitizenManager();
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param schoolID
     * @return
     */
    public ObservableList<Citizen> getAllCitizensSchool(int schoolID){
        citizenList.clear();
        citizenList.addAll(citizenManager.getAllCitizensSchool(schoolID));
        return citizenList;
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param studentID
     * @return
     */
    public ObservableList<Citizen> getAllCitizensStudent(int studentID){
        citizenList.clear();
        citizenList.addAll(citizenManager.getAllCitizensStudent(studentID));
        return citizenList;
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @return
     */
    public ObservableList<Citizen> getTemplateCitizens() {
        citizenList.clear();
        citizenList.addAll(citizenManager.getTemplateCitizens());
        return citizenList;
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param fName
     * @param lName
     * @param age
     * @param isTemplate
     * @param schoolID
     * @return
     */
    public Citizen createCitizen(String fName, String lName, int age, boolean isTemplate, int schoolID){
        return citizenManager.createCitizen(fName, lName, age, isTemplate, schoolID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizenID
     * @param studentID
     * @throws SQLException
     */
    public void createCitizenToStudent(int citizenID, int studentID) throws SQLException {
        citizenManager.bindCitizenToStudent(citizenID, studentID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizen
     * @throws SQLException
     */
    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenManager.updateCitizen(citizen);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizenID
     */
    public void deleteCitizen(int citizenID){
        citizenManager.deleteCitizen(citizenID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizen
     * @param schoolID
     * @return
     */
    public Citizen duplicateCitizen(Citizen citizen, int schoolID){
        return citizenManager.dublicateCitizen(citizen, schoolID);
    }



}

