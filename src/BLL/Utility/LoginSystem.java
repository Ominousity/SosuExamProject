package BLL.Utility;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import BLL.AdminManager;
import BLL.StudentManager;
import BLL.TeacherManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginSystem
{
    private HashMap<String, String> loginCreditials = new HashMap<>();
    private AdminManager adminManager;
    private StudentManager studentManager;
    private TeacherManager teacherManager;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Admin> admins;

    public LoginSystem() throws IOException
    {
        adminManager = new AdminManager();
        studentManager = new StudentManager();
        teacherManager = new TeacherManager();
        teachers = teacherManager.getAll();
        students = studentManager.getAll();
        admins = adminManager.getAllAdmins();
    }

    public boolean check(String username, String password){
        loginCreditials = arrayToHashMap(teachers, students, admins);
        String tempPass = loginCreditials.get(username);
        if (tempPass == password){
            return true;
        } else{
            return false;
        }
    }

    public HashMap<String, String> arrayToHashMap(ArrayList<Teacher> teachers, ArrayList<Student> students, ArrayList<Admin> admins){
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
    }
}
