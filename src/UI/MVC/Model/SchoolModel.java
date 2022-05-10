package UI.MVC.Model;

import BE.School;
import BLL.SchoolManager;

import java.sql.SQLException;
import java.util.List;

public class SchoolModel {

    SchoolManager schoolManager;

    public List<School> getSchool() throws SQLException {
        return schoolManager.getSchool();
    }

    public void createSchool(int SchoolID, String SchoolName){
        schoolManager.createSchool(SchoolID, SchoolName);
    }

    public void updateSchool(School school) throws SQLException{
        schoolManager.updateSchool(school);
    }

    public void deleteSchool(int SchoolID, int StudentID, int CategoryID, int CitizenID,int GeneralinfoID) throws SQLException{
        schoolManager.deleteSchool(SchoolID, StudentID, CategoryID, CitizenID, GeneralinfoID);
    }

}
