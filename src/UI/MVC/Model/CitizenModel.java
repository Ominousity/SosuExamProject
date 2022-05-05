package UI.MVC.Model;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import DAL.CitizenDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenModel
{
    private CitizenManager citizenManager;
    public CitizenModel(){
        citizenManager = new CitizenManager();
    }

    public ArrayList<Student> getAllCitizensStudent(Student student, int StudentID) throws SQLException{
        return citizenManager.getAllCitizensStudent(student, StudentID);
    }

    public void createCitizen(String FName, String LName,String Address,String CPR){
        citizenManager.createCitizen(FName, LName, Address, CPR);
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

    public ArrayList<Student> getAllStudents(Student student, int StudentID) throws SQLException {
        return citizenManager.getAllCitizensStudents(student, StudentID);
    }

}

