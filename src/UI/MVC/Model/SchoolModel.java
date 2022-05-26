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

    public void createSchool(String SchoolName){
        schoolManager.createSchool(SchoolName);
    }

    public void updateSchool(School school) throws SQLException{
        schoolManager.updateSchool(school);
    }

    public void deleteSchool(int SchoolID){
        schoolManager.deleteSchool(SchoolID);
    }

}
