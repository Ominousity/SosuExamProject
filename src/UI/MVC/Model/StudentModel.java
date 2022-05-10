package UI.MVC.Model;

import BE.Citizen;
import BE.Student;
import BLL.CitizenManager;
import BLL.StudentManager;
import BLL.Utility.Encryptor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel
{
    private StudentManager studentManager;
    private Encryptor encryptor;

    public StudentModel() throws IOException {
        studentManager = new StudentManager();
        encryptor = new Encryptor();
    }

    public void createStudent(String FName, String LName, String Email, String Password, int schoolID) throws IOException
    {
        studentManager.createStudent(FName, LName, Email, encryptor.Encrypt(Password), schoolID);
    }

    public void updateStudent(Student student) throws SQLException{
        studentManager.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        studentManager.deleteStudent(StudentID);
    }

    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException{
        return studentManager.getAllStudentsForOneCitizen(citizen);
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return studentManager.getAllStudents();
    }

}
