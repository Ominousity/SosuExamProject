package BLL;

import BE.Teacher;
import DAL.TeacherDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherManager
{
    TeacherDAO teacherDAO;
    public TeacherManager() throws IOException {
        teacherDAO = new TeacherDAO();
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException{
        return teacherDAO.getAllTeachers();
    }

    public void createTeacher(String FName, String LName, String Email, String Password, int schoolID){
        teacherDAO.createTeacher(FName, LName, Email, Password);
    }

    public void updateTeacher(Teacher teacher) throws SQLException{
        teacherDAO.updateTeacher(teacher);
    }

    public void deleteTeacher(int TeacherID) throws SQLException{
        teacherDAO.deleteTeacher(TeacherID);
    }

}
