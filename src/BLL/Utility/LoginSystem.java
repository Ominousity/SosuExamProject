package BLL.Utility;

import BE.User;
import BLL.UserManager;
import UI.MVC.Model.ParseModel;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginSystem {
    private HashMap<String, String> loginCreditials = new HashMap<>();
    private HashMap<String, String> userCreditials = new HashMap<>();
    private HashMap<String, String> studentCreditials = new HashMap<>();
    private UserManager userManager;
    private Encryptor encryptor;

    private FileWriter writer;

    private File file = new File("Utilities/tools.txt");
    private ArrayList<User> users;

    /**
     * Class helps login to the program.
     * @throws IOException
     * @throws SQLException
     */
    public LoginSystem() throws IOException, SQLException {
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
    public boolean check(String username, String password) throws IOException {
        userCreditials = userToHashMap(users);

        if (userCreditials.get(username) != null) {
            String tempPass = userCreditials.get(username);
            if (encryptor.check(password, tempPass)) {
                ParseModel.isTeacher = true;
                ParseModel.isStudent = false;
                ParseModel.isAdmin = false;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * The method makes a HashMap of the users login.
     * @param users
     * @return
     */
        public HashMap<String, String> userToHashMap (ArrayList <User> users) {
            HashMap<String, String> hashMap = new HashMap<>();

            for (User user : users) {
                hashMap.put(user.getEmail(), user.getPassword());
            }
            System.out.println(hashMap);
            return hashMap;
        }

    /**
     * The method helps to remember the login for a person.
     * @param username
     * @param password
     * @throws IOException
     */
        public void rememberLogin (String username, String password) throws IOException {
            forgetLogin();
            writer.write(username + "\n" + password);
            writer.close();
            System.out.println(username + "\n" + password);
        }

    /**
     * The method helps if a person has forgot there login.
     * @throws IOException
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
