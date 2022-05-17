package UI.MVC.Model;

import BE.*;

public class ParseModel
{
    private static ParseModel instance = null;

    public static Citizen citizen;
    public static Student student;
    public static Category category;
    public static User user;
    public static SubCategory subCategory;
    public static GeneralInfo generalInfo;

    public static ParseModel getInstance(){
        if (instance == null){
            instance = new ParseModel();
        }
        return instance;
    }

}
