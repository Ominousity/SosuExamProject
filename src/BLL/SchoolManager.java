package BLL;

import BE.School;
import DAL.SchoolDAO;

import java.sql.SQLException;
import java.util.List;

public class SchoolManager {

    SchoolDAO schoolDAO;

    public List<School> getSchool(int SchoolID) throws SQLException{
        return schoolDAO.getSchool(SchoolID);
    }

    public void createSchool(int SchoolID, String SchoolName){
        schoolDAO.createSchool(SchoolID, SchoolName);
    }

    public void updateSchool(School school) throws SQLException{
        schoolDAO.updateSchool(school);
    }

    public void deleteSchool(int SchoolID, int StudentID, int CategoryID, int CitizenID,int GeneralinfoID) throws SQLException{
        schoolDAO.deleteSchool(SchoolID, StudentID, CategoryID, CitizenID, GeneralinfoID);
    }

}