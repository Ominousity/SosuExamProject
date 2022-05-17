package BLL.Utility;

import BE.User;
import BLL.UserManager;
import UI.MVC.Model.ParseModel;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginSystem {

    private UserManager userManager;
    private Encryptor encryptor;

    private FileWriter writer;

    private File file = new File("Utilities/tools.txt");
    private ArrayList<User> users;
    private HashMap<String, User> hashMap;


    public LoginSystem() throws IOException, SQLException {
        hashMap = new HashMap<>();
        userManager = new UserManager();
        encryptor = new Encryptor();
        writer = new FileWriter(file, true);
        users = userManager.getAllUsers();
    }

    public boolean check(String username, String password) throws IOException {
        userToHashMap(users);
        if (hashMap.get(username) != null){
            User tempPass = hashMap.get(username);
            if (encryptor.check(password, tempPass.getPassword())){
                ParseModel.user = tempPass;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

        public void userToHashMap (ArrayList <User> users) {

            for (User user : users) {
                hashMap.put(user.getEmail(), user);
            }
        }

        public void rememberLogin (String username, String password) throws IOException {
            forgetLogin();
            writer.write(username + "\n" + password);
            writer.close();
            System.out.println(username + "\n" + password);
        }

        public void forgetLogin() throws IOException {
            writer.write("");
        }

        public String getEncryptedPassword(String password) throws IOException {
            return encryptor.Encrypt(password);
        }

        public String getRememberedLogin(int lineNumber) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();

            if (line != null) {
                for (int i = 0; i < 2; i++) {

                    if (i == 0 && lineNumber == 1) {
                        sb.append(line);
                        return sb.toString();

                    } else if (i == 1 && lineNumber == 2) {
                        sb.append(line);
                        return sb.toString();
                    }
                }
            }
            return null;
        }

        public boolean isFileEmpty () {
            if (file.length() == 0) {
                return true;
                //file is empty!
            } else {
                return false;
                //file is not empty!
            }
        }

}
