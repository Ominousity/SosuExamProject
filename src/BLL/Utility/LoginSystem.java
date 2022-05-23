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
    private Thread thread;

    private File file = new File("Utilities/tools.txt");
    private ArrayList<User> users;
    private HashMap<String, User> hashMap;

    /**
     * Class helps login to the program.
     * @throws IOException
     * @throws SQLException
     */
    public LoginSystem() throws IOException, SQLException {
        hashMap = new HashMap<>();
        userManager = new UserManager();
        encryptor = new Encryptor();
        writer = new FileWriter(file, true);
        users = userManager.getAllUsers();
    }

    /**
     * The check method helps check if it's a user or a Teacher or an admin, then the program loggs the person
     * in on the view to them.
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public boolean check(String username, String password) throws IOException, InterruptedException
    {
        System.out.println(password);
        thread = new Thread(() -> userToHashMap(users));
        System.out.println(getClass().getName() + " Debug: " + "Trying to start: " + thread.getName() + " " + thread.getId());
        thread.start();
        if (thread.isInterrupted()){
            System.out.println(getClass().getName() + " Debug: " + "Thread was Interrupted" + thread.getStackTrace());
        }
        System.out.println(getClass().getName() + " Debug: " + "Thread started: " + thread.getState());
        thread.join();
        System.out.println(getClass().getName() + " Debug: " + "Thread: " + thread.getName() + " " + thread.getId() + " Has run successfully");
        if (hashMap.get(username) != null){
            User tempPass = hashMap.get(username);
            if (encryptor.check(password, tempPass.getPassword())){
                ParseModel.user = tempPass;
                return true;
            } else if (password.contains("isencrypted")){
                ParseModel.user = tempPass;
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * The method makes a HashMap of the users login.
     * @param users
     */
        public void userToHashMap (ArrayList <User> users) {
            System.out.println(getClass().getName() + " Debug: " + "Function running");
            for (User user : users) {
                hashMap.put(user.getEmail(), user);
            }
            System.out.println(getClass().getName() + " Debug: " + "Function Stopped");
        }

    /**
     * The method helps to remember the login for a person.
     * @param username
     * @param password
     * @throws IOException
     */
        public void rememberLogin (String username, String password) throws IOException {
            forgetLogin();
            writer.write(username + "\n" + password + "isencrypted");
            writer.close();
        }

    /**
     * The method helps if a person has forgot there login.
     */
    public void forgetLogin() throws IOException {
            writer.write("");
        }

    /**
     * The method helps to make the password to an encrypt.
     * @param password
     * @return
     * @throws IOException
     */
        public String getEncryptedPassword(String password) throws IOException {
            return encryptor.Encrypt(password);
        }

    /**
     * The method helps to remember the login with the linenumber.
     * @param lineNumber
     * @return
     * @throws IOException
     */
        public String getRememberedLogin(int lineNumber) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            String s = sb.toString();
            while ((line = reader.readLine()) != null) {
                if (i == 0 && lineNumber == 1) {
                    sb.append(line);
                    return line;

                } else if (i == 1 && lineNumber == 2) {
                    sb.append(line);
                    return line;
                }
                i++;
            }
            return null;
        }

    /**
     * The method checks if the file is empty.
     * @return
     */
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
