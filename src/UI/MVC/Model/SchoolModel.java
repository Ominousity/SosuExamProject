package UI.MVC.Model;

import BE.School;
import BLL.SchoolManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class SchoolModel {

    private ObservableList schoolList;

    SchoolManager schoolManager;

    public SchoolModel() throws IOException {
        schoolManager = new SchoolManager();
        schoolList = FXCollections.observableArrayList();
    }

    public ObservableList<School> getSchool() throws SQLException {
        schoolList.clear();
        schoolList.addAll(schoolManager.getSchool());
        return schoolList;
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
