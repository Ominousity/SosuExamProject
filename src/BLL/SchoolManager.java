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

    /**
     * This class passes data between the bll layer and the dal layer
     * @return
     */
    public List<School> getSchool() {
        return schoolDAO.getSchool();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param SchoolName
     */
    public void createSchool(String SchoolName){
        schoolDAO.createSchool(SchoolName);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param school
     * @throws SQLException
     */
    public void updateSchool(School school) throws SQLException{
        schoolDAO.updateSchool(school);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param SchoolID
     */
    public void deleteSchool(int SchoolID) {
        schoolDAO.deleteSchool(SchoolID);
    }

}
