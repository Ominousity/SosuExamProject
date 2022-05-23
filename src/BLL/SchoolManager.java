package BLL;

import BE.School;
import DAL.SchoolDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SchoolManager {

    SchoolDAO schoolDAO;

    public SchoolManager() throws IOException {
        schoolDAO = new SchoolDAO();
    }

    public List<School> getSchool() {
        return schoolDAO.getSchool();
    }

    public void createSchool(String SchoolName){
        schoolDAO.createSchool(SchoolName);
    }

    public void updateSchool(School school) throws SQLException{
        schoolDAO.updateSchool(school);
    }

    public void deleteSchool(int SchoolID) {
        schoolDAO.deleteSchool(SchoolID);
    }

}
