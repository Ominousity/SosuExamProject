package BLL.Utility;

import BE.Student;
import BE.User;
import BLL.LoginManager;
import BLL.UserManager;
import UI.MVC.Model.ParseModel;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class LoginSystem
{
    private HashMap<String, String> loginCreditials = new HashMap<>();
    private HashMap<String, String> userCreditials = new HashMap<>();
    private HashMap<String, String> studentCreditials = new HashMap<>();
    private UserManager userManager;
    private LoginManager loginManager;
    private Encryptor encryptor;

    private PrintWriter writer;

    private File file;
    private ArrayList<User> users;
    private ArrayList<User> students;


    public LoginSystem() throws IOException, SQLException
    {
        userManager = new UserManager();
        loginManager = new LoginManager();
        encryptor = new Encryptor();
        file = new File("Utilities/tools.txt");
        writer = new PrintWriter(file);
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

    public void rememberLogin(String username, String password) {
        writer.print(username + "\n" + password);
    }

    public void forgetLogin(){
        writer.print("");
    }

    public String getRememberedLogin(int lineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();
        
        if (line != null){
            for (int i = 0; i < 2; i++) {

                if (i == 0 && lineNumber == 1){
                    sb.append(line);
                    return sb.toString();

                } else if (i == 1 && lineNumber == 2) {
                    sb.append(line);
                    return sb.toString();
                }
            }
        }
    }

    public boolean isFileEmpty(){
        if (file.length() == 0){
            return true;
            //file is empty!
        }else{
            return false;
            //file is not empty!
        }
    }
}
