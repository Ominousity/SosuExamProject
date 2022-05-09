package BLL;

import BE.Citizen;
import BE.School;
import BE.Student;
import DAL.CitizenDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenManager {
    private CitizenDAO citizenDAO;

    private String citizens;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
    }

    public List<Citizen> getAllCitizensSchool(int schoolID) throws SQLException {
        return citizenDAO.getAllCitizensSchool(schoolID);
    }

    public List<Citizen> getAllCitizensStudent(int studentID) throws SQLException {
        return citizenDAO.getAllCitizensSchool(studentID);
    }

    public Citizen createCitizen(String FName, String LName, String dob, String Address, String CPR, int schoolID){
        return citizenDAO.createCitizen(FName, LName, dob, Address, CPR, schoolID);
    }

    public void createCitizenToStudent(Citizen citizen, Student student) throws SQLException {
        citizenDAO.createCitizenToStudent(citizen,student);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        citizenDAO.removeCitizenFromStudent(citizen,student);
    }

    public void setCitizens(String FName, String LName) {
        if(this.citizens.equals("")){
            this.citizens = citizens;
        }
        else{
            this.citizens = this.citizens + ", " + citizens;
        }
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }
}
