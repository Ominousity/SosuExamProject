package BLL;

import BE.Citizen;
import BE.Student;
import DAL.CitizenDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {
    private CitizenDAO citizenDAO;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
    }

    public List<Citizen> getAllCitizensSchool(int schoolID) {
        return citizenDAO.getAllCitizensSchool(schoolID);
    }

    public List<Citizen> getAllCitizensStudent(int studentID) {
        return citizenDAO.getAllCitizensSchool(studentID);
    }

    public List<Citizen> getTemplateCitizens(){
        return citizenDAO.getTemplateCitizens();
    }

    public Citizen createCitizen(String fName, String lName, String dob, String address, String sex, boolean isTemplate, int schoolID){
        return citizenDAO.createCitizen(fName, lName, dob, address, sex, isTemplate, schoolID);
    }

    public void bindCitizenToStudent(int citizenID, int studentID) throws SQLException {
        citizenDAO.createCitizenToStudent(citizenID, studentID);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        citizenDAO.removeCitizenFromStudent(citizen,student);
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }
}
