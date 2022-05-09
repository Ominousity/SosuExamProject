package BLL;

import BE.Citizen;
import BE.Student;
import DAL.CitizenDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CitizenManager {
    private CitizenDAO citizenDAO;

    private String citizens;

    public CitizenManager(){
        citizenDAO = new CitizenDAO();
    }

    public void createCitizen(String FName, String LName, String dob,String Address,String CPR){
        citizenDAO.createCitizen(FName, LName, dob, Address, CPR);
    }

    public void createCitizenToStudent(Citizen citizen, Student student) throws SQLException {
        citizenDAO.createCitizenToStudent(citizen,student);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    public ArrayList<Citizen> getAllCitizensStudent(Citizen citizen, Student student, int StudentID) throws SQLException{
        return citizenDAO.getAllCitizensStudent(citizen, student, StudentID);
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

    public void removeCitizens(){
        this.citizens = "";
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }

    public void duplicateCitizen(int citizenID){
        //TODO Lav senere når vi har bedre overblik over hvordan ting skal gøres
    }
}
