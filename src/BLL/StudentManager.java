package BLL;

import BE.Category;
import BE.Citizen;
import BE.Student;
import DAL.DatabaseConnector;
import DAL.StudentDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager
{
    StudentDAO studentDAO;


    private String citizens;
    public StudentManager(String citizens) {
        this.citizens = citizens;
    }

    public ArrayList<Citizen> getAllStudents() throws SQLException{
        return studentDAO.getAllStudents();
    }

    public void createStudent(String FName, String LName, String Email, String Password){
        studentDAO.createStudent(FName, LName, Email, Password);
    }

    public void updateStudent(Student student) throws SQLException{
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        studentDAO.deleteStudent(StudentID);
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        studentDAO.removeCitizenFromStudent(citizen,student);
    }

    public void createCitizenToStudent(Citizen citizen, Student student) throws SQLException {
        studentDAO.createCitizenToStudent(citizen,student);
    }

    public List<Citizen> getCitizenToStudent(int StudentID) throws SQLException {
    return studentDAO.getCitizenToStudent(StudentID);}

    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException {
    return studentDAO.getAllStudentsForOneCitizen(citizen);}

    public void setCitizens(String FName, String LName) {
        if(this.citizens.equals("")){
            this.citizens = citizens;
        }
        else{
            this.citizens = this.citizens + ", " + citizens;
        }
    }
    public void removeCategories(){
        this.citizens = "";
    }

}
