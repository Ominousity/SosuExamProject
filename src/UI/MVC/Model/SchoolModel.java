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

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @return
     * @throws SQLException
     */
    public ObservableList<School> getSchool() throws SQLException {
        schoolList.clear();
        schoolList.addAll(schoolManager.getSchool());
        return schoolList;
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param SchoolName
     */
    public void createSchool(String SchoolName){
        schoolManager.createSchool(SchoolName);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param school
     * @throws SQLException
     */
    public void updateSchool(School school) throws SQLException{
        schoolManager.updateSchool(school);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param SchoolID
     */
    public void deleteSchool(int SchoolID){
        schoolManager.deleteSchool(SchoolID);
    }

}
