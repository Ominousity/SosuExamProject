package UI.MVC.Model;

import BE.*;

/**
 * This class is used for transferring data between the different controllers
 */
public class ParseModel
{
    private static ParseModel instance = null;

    public static Citizen citizen;
    public static User user;
    public static boolean isEditingUser;
    public static CitizenCase cases;
    public static  School school;

    public static ParseModel getInstance(){
        if (instance == null){
            instance = new ParseModel();
        }
        return instance;
    }

}
