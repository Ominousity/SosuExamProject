package UI.MVC.Model;

import BE.*;

public class ParseModel
{
    private static ParseModel parseModel;

    public static Citizen citizen;
    public static Student student;
    public static Category category;
    public static User user;
    public static SubCategory subCategory;
    public static GeneralInfo generalInfo;
    public static boolean isStudent = false;
    public static boolean isTeacher = false;
    public static boolean isAdmin = false;

    private ParseModel(){

    }

    public static ParseModel getParseModel(){
        return parseModel;
    }
}
