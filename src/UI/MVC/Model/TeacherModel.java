package UI.MVC.Model;

import BE.Teacher;
import BLL.TeacherManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherModel
{
    private TeacherManager teacherManager;
    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        return teacherManager.getAllTeachers();
    }

    public void createTeacher(String FName, String LName, String Email, String Password){
        teacherManager.createTeacher(FName, LName, Email, Password);
    }

    public void updateTeacher(Teacher teacher) throws SQLException{
        teacherManager.updateTeacher(teacher);
    }

    public void deleteTeacher(int TeacherID) throws SQLException{
        teacherManager.deleteTeacher(TeacherID);
    }
}
