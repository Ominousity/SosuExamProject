package BLL.Utility;

import BE.Student;
import BE.User;
import BLL.LoginManager;
import BLL.UserManager;
import UI.MVC.Model.ParseModel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginSystem
{
    private HashMap<String, String> loginCreditials = new HashMap<>();
    private HashMap<String, String> userCreditials = new HashMap<>();
    private HashMap<String, String> studentCreditials = new HashMap<>();
    private UserManager userManager;
    private LoginManager loginManager;
    private Encryptor encryptor;

    private PrintWriter writer;
    private ArrayList<User> users;
    private ArrayList<User> students;


    public LoginSystem() throws IOException, SQLException
    {
        userManager = new UserManager();
        loginManager = new LoginManager();
        encryptor = new Encryptor();
        writer = new PrintWriter("Utilities/tools.txt");
        users = userManager.getAllUsers();
        students = userManager.getAllStudents();
    }

    public boolean check(String username, String password) throws IOException, SQLException {
        //loginCreditials = arrayToHashMap(users, students);
        userCreditials = teacherToHashMap(users);
        studentCreditials = studentToHashMap(students);

        if (userCreditials.get(username) != null){
            String tempPass = userCreditials.get(username);
            if (encryptor.check(password, tempPass)){
                ParseModel.isTeacher = true;
                ParseModel.isStudent = false;
                ParseModel.isAdmin = false;
                ParseModel.teacher = loginManager.getTeacher(username);
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
                ParseModel.student = loginManager.getStudent(username);
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
    public HashMap<String, String> teacherToHashMap(ArrayList<User> teachers){
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

    public void rememberLogin(String username) {
        writer.print(username);
    }

    public void forgetLogin(){
        writer.print("");
    }
}
