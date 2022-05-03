package BLL;

import BE.Teacher;
import DAL.TeacherDAO;

import java.sql.SQLException;
import java.util.List;

public class TeacherManager
{
    TeacherDAO teacherDAO;

    public List<Teacher> getAllTeachers(int SchoolID) throws SQLException{
        return teacherDAO.getAllTeachers(SchoolID);
    }

    public void createTeacher(String FName, String LName, String Email, String Password){
        teacherDAO.createTeacher(FName, LName, Email, Password);
    }

    public void updateTeacher(Teacher teacher) throws SQLException{
        teacherDAO.updateTeacher(teacher);
    }

    public void deleteTeacher(int TeacherID) throws SQLException{
        teacherDAO.deleteTeacher(TeacherID);
    }

}
