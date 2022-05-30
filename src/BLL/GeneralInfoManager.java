package BLL;

import BE.GeneralInfo;
import DAL.GeneralInfoDAO;
import java.io.IOException;

public class GeneralInfoManager {
    private GeneralInfoDAO generalInfoDAO;

    public GeneralInfoManager() throws IOException {
        generalInfoDAO = new GeneralInfoDAO();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param CitizenID
     * @return
     */
    public GeneralInfo getGeneralInfo(int CitizenID) {
        return generalInfoDAO.getGeneralInfo(CitizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param mestring
     * @param motivation
     * @param ressourcer
     * @param roller
     * @param vaner
     * @param uddanelseJob
     * @param livhistorie
     * @param netvaerk
     * @param helbredsoplysninger
     * @param hjaelpemidler
     * @param boligensIndretning
     * @param citizenID
     */
    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netvaerk, String helbredsoplysninger, String hjaelpemidler, String boligensIndretning, int citizenID){
        generalInfoDAO.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netvaerk, helbredsoplysninger, hjaelpemidler, boligensIndretning, citizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param generalInfo
     */
    public void updateInfo(GeneralInfo generalInfo){
        generalInfoDAO.updateInfo(generalInfo);
    }

}
