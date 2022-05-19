package BLL;

import BE.*;
import DAL.CategoryDAO;
import DAL.CitizenDAO;
import DAL.GeneralInfoDAO;
import DAL.SubCategoryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {
    private CitizenDAO citizenDAO;
    private CategoryDAO categoryDAO;
    private SubCategoryDAO subCategoryDAO;
    private GeneralInfoDAO generalInfoDAO;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
        categoryDAO = new CategoryDAO();
        subCategoryDAO = new SubCategoryDAO();
        generalInfoDAO = new GeneralInfoDAO();
    }

    public List<Citizen> getAllCitizensSchool(int schoolID) {
        return citizenDAO.getAllCitizensSchool(schoolID);
    }

    public List<Citizen> getAllCitizensStudent(int studentID) {
        return citizenDAO.getAllCitizensSchool(studentID);
    }

    public List<Citizen> getTemplateCitizens(){
        return citizenDAO.getTemplateCitizens();
    }

    public Citizen createCitizen(String fName, String lName, String dob, String address, String sex, boolean isTemplate, int schoolID){
        return citizenDAO.createCitizen(fName, lName, dob, address, sex, isTemplate, schoolID);
    }

    public void bindCitizenToStudent(int citizenID, int studentID) throws SQLException {
        citizenDAO.createCitizenToStudent(citizenID, studentID);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    public void removeCitizenFromStudent(Citizen citizen, Student student) throws SQLException {
        citizenDAO.removeCitizenFromStudent(citizen,student);
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }

    public void dublicateCitizen(int citizenID){
        List<Citizen> citizenList = citizenDAO.getTemplateCitizens();
        for (Citizen citizen : citizenList) {
            if (citizen.getID() == citizenID){
                GeneralInfo generalInfo = generalInfoDAO.getGeneralInfo(citizenID);
                List<Category> categories = categoryDAO.getAllCategories(citizenID);
                Category tempCategory;
                List<SubCategory> subCategories;
                SubCategory tempSubCategory;

                generalInfoDAO.createGeneralInfo(generalInfo.getMestring(), generalInfo.getMotivation(), generalInfo.getRessourcer(), generalInfo.getRoller(), generalInfo.getVaner(), generalInfo.getUddannelseJob(), generalInfo.getLivshistorie(), generalInfo.getNetværk(), generalInfo.getHelbredsoplysninger(), generalInfo.getHjælpemidler(), generalInfo.getBoligIndretning(), citizenID);

                break;
            }
        }
    }
}
