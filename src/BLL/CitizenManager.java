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

    /**
     * This class passes data between the bll layer and the dal layer
     * @param schoolID
     * @return
     */
    public List<Citizen> getAllCitizensSchool(int schoolID) {
        return citizenDAO.getAllCitizensSchool(schoolID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param studentID
     * @return
     */
    public List<Citizen> getAllCitizensStudent(int studentID) {
        return citizenDAO.getCitizensFromStudent(studentID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @return
     */
    public List<Citizen> getTemplateCitizens(){
        return citizenDAO.getTemplateCitizens();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param fName
     * @param lName
     * @param age
     * @param isTemplate
     * @param schoolID
     * @return
     */
    public Citizen createCitizen(String fName, String lName, int age, boolean isTemplate, int schoolID){
        return citizenDAO.createCitizen(fName, lName, age, isTemplate, schoolID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizenID
     * @param studentID
     * @throws SQLException
     */
    public void bindCitizenToStudent(int citizenID, int studentID) throws SQLException {
        citizenDAO.createCitizenToStudent(citizenID, studentID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizen
     * @throws SQLException
     */
    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizenID
     */
    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }

    /**
     * this method creates a new citizen from a template citizen
     * @param templateCitizen
     * @param schoolID
     * @return
     */
    public Citizen dublicateCitizen(Citizen templateCitizen, int schoolID){
        List<Citizen> citizenList = citizenDAO.getTemplateCitizens();
        Citizen citizen = citizenDAO.createCitizen(templateCitizen.getFName(), templateCitizen.getLName(), templateCitizen.getAge(), false, schoolID);

        for (Citizen cit : citizenList) {
            if (cit.getID() == templateCitizen.getID()){
                GeneralInfo generalInfo = generalInfoDAO.getGeneralInfo(templateCitizen.getID());
                List<Category> categories = categoryDAO.getAllCategories(templateCitizen.getID());
                Category tempCategory;
                List<SubCategory> subCategories;

                generalInfoDAO.createGeneralInfo(generalInfo.getMestring(), generalInfo.getMotivation(), generalInfo.getRessourcer(), generalInfo.getRoller(), generalInfo.getVaner(), generalInfo.getUddannelseJob(), generalInfo.getLivshistorie(), generalInfo.getNetværk(), generalInfo.getHelbredsoplysninger(), generalInfo.getHjælpemidler(), generalInfo.getBoligIndretning(), citizen.getID());

                for (Category cat: categories) {
                    tempCategory = categoryDAO.createCategory(cat.getCatName(), cat.getIsFuncHealth(), citizen.getID());
                    subCategories = subCategoryDAO.getSubCategories(cat.getID());
                    for (SubCategory subCat : subCategories) {
                        subCategoryDAO.createSubCategory(subCat.getSubCatName(), subCat.getSubCatContents(), tempCategory.getID());
                    }
                }
                break;
            }
        }
        return citizen;
    }
}
