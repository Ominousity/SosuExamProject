package BLL.Utility;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import BLL.AdminManager;
import BLL.StudentManager;
import BLL.TeacherManager;
import UI.MVC.Model.ParseModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginSystem
{
    private HashMap<String, String> loginCreditials = new HashMap<>();
    private HashMap<String, String> teacherCreditials = new HashMap<>();
    private HashMap<String, String> studentCreditials = new HashMap<>();
    private HashMap<String, String> adminCreditials = new HashMap<>();
    private AdminManager adminManager;
    private StudentManager studentManager;
    private TeacherManager teacherManager;
    private Encryptor encryptor;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Admin> admins;

    public LoginSystem() throws IOException, SQLException
    {
        adminManager = new AdminManager();
        studentManager = new StudentManager();
        teacherManager = new TeacherManager();
        encryptor = new Encryptor();
        teachers = teacherManager.getAllTeachers();
        students = studentManager.getAllStudents();
        admins = adminManager.getAllAdmins();
    }

    public boolean check(String username, String password) throws IOException
    {
        //loginCreditials = arrayToHashMap(teachers, students, admins);
        teacherCreditials = teacherToHashMap(teachers);
        studentCreditials = studentToHashMap(students);
        adminCreditials = adminToHashMap(admins);
        if (teacherCreditials.get(username) != null){
            String tempPass = teacherCreditials.get(username);
            if (encryptor.check(password, tempPass)){
                ParseModel.isTeacher = true;
                ParseModel.isStudent = false;
                ParseModel.isAdmin = false;
                return true;
            } else{
                return false;
            }
        } else if(studentCreditials.get(username) != null){
            String tempPass = studentCreditials.get(username);
            if (encryptor.check(password, tempPass)){
                ParseModel.isTeacher = false;
                ParseModel.isStudent = true;
                ParseModel.isAdmin = false;
                return true;
            } else{
                return false;
            }

        } else if(adminCreditials.get(username) != null){
            String tempPass = adminCreditials.get(username);
            if (encryptor.check(password, tempPass)){
                ParseModel.isTeacher = false;
                ParseModel.isStudent = false;
                ParseModel.isAdmin = true;
                return true;
            } else{
                return false;
            }
        }
        return false;
    }

    /*public HashMap<String, String> arrayToHashMap(ArrayList<Teacher> teachers, ArrayList<Student> students, ArrayList<Admin> admins){
        HashMap<String, String> hashMap = new HashMap<>();

        for (Teacher teacher : teachers){
            hashMap.put(teacher.getEmail(), teacher.getPassword());
        }
        for (Student student : students){
            hashMap.put(student.getEmail(), student.getPassword());
        }
        for (Admin admin : admins){
            hashMap.put(admin.getEmail(), admin.getPassword());
        }

        return hashMap;
    }*/
    public HashMap<String, String> teacherToHashMap(ArrayList<Teacher> teachers){
        HashMap<String, String> hashMap = new HashMap<>();

        for (Teacher teacher : teachers){
            hashMap.put(teacher.getEmail(), teacher.getPassword());
        }
        return hashMap;
    }
    public HashMap<String, String> studentToHashMap(ArrayList<Student> students){
        HashMap<String, String> hashMap = new HashMap<>();

        for (Student student : students){
            hashMap.put(student.getEmail(), student.getPassword());
        }

        return hashMap;
    }
    public HashMap<String, String> adminToHashMap(ArrayList<Admin> admins){
        HashMap<String, String> hashMap = new HashMap<>();

        for (Admin admin : admins){
            hashMap.put(admin.getEmail(), admin.getPassword());
        }

        return hashMap;
    }
}
