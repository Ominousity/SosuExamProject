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


    public LoginSystem() throws IOException, SQLException
    {
        userManager = new UserManager();
        loginManager = new LoginManager();
        encryptor = new Encryptor();
        file = new File("Utilities/tools.txt");
        writer = new PrintWriter(file);
        users = userManager.getAllUsers();
    }

    public boolean check(String username, String password) throws IOException, SQLException {
        userCreditials = userToHashMap(users);

        if (userCreditials.get(username) != null){
            String tempPass = userCreditials.get(username);
            if (encryptor.check(password, tempPass)){
                ParseModel.isTeacher = true;
                ParseModel.isStudent = false;
                ParseModel.isAdmin = false;
                ParseModel.user = loginManager.getTeacher(username);
                return true;
            } else{
                return false;
            }
        return false;
    }

    public HashMap<String, String> userToHashMap(ArrayList<User> users){
        HashMap<String, String> hashMap = new HashMap<>();

        for (User user : users){
            hashMap.put(user.getEmail(), user.getPassword());
        }
        return hashMap;
    }

    public void rememberLogin(String username, String password) {
        writer.print(username + "\n" + password);
    }

    public void forgetLogin(){
        writer.print("");
    }

    public String getEncryptedPassword(String password) throws IOException {
        return encryptor.Encrypt(password);
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
