package UI.MVC.Model;

import BE.GeneralInfo;
import BLL.GeneralInfoManager;
import java.io.IOException;

public class GeneralinformationModel {

    GeneralInfoManager generalInfoManager;

    public GeneralinformationModel() throws IOException {
        generalInfoManager = new GeneralInfoManager();
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param CitizenID
     * @return
     */
    public GeneralInfo getGeneralInfo(int CitizenID) {
        return generalInfoManager.getGeneralInfo(CitizenID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
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
        generalInfoManager.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netvaerk, helbredsoplysninger, hjaelpemidler, boligensIndretning, citizenID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param generalInfo
     */
    public void updateInfo(GeneralInfo generalInfo){
        generalInfoManager.updateInfo(generalInfo);
    }
}
