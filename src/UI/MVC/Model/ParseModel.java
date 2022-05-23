package UI.MVC.Model;

import BE.*;

public class ParseModel
{
    private static ParseModel instance = null;

    public static Citizen citizen;
    public static User user;
    public static CitizenCase cases;
    public static ParseModel getInstance(){
        if (instance == null){
            instance = new ParseModel();
        }
        return instance;
    }

}
