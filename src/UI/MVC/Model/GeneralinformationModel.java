package UI.MVC.Model;

import BE.GeneralInfo;
import BLL.GeneralInfoManager;

import java.io.IOException;
import java.sql.SQLException;

public class GeneralinformationModel {

    GeneralInfoManager generalInfoManager;

    public GeneralinformationModel() throws IOException {
        generalInfoManager = new GeneralInfoManager();
    }

    public GeneralInfo getGeneralInfo(int CitizenID) {
        return generalInfoManager.getGeneralInfo(CitizenID);
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netvaerk, String helbredsoplysninger, String hjaelpemidler, String boligensIndretning, int citizenID){
        generalInfoManager.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netvaerk, helbredsoplysninger, hjaelpemidler, boligensIndretning, citizenID);
    }

    public void updateInfo(GeneralInfo generalInfo){
        generalInfoManager.updateInfo(generalInfo);
    }
}
