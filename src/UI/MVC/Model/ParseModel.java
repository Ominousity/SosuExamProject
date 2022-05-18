package UI.MVC.Model;

import BE.*;

public class ParseModel
{
    private static ParseModel instance = null;

    public static Citizen citizen = null;
    public static User user = null;

    public static ParseModel getInstance(){
        if (instance == null){
            instance = new ParseModel();
        }
        return instance;
    }

}
