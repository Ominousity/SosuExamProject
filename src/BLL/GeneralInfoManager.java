package BLL;

import BE.GeneralInfo;
import DAL.GeneralInfoDAO;

import java.io.IOException;
import java.sql.SQLException;

public class GeneralInfoManager {
    private GeneralInfoDAO generalInfoDAO;

    public GeneralInfoManager() throws IOException {
        generalInfoDAO = new GeneralInfoDAO();
    }
    public GeneralInfo getGeneralInfo(int CitizenID) {
        return generalInfoDAO.getGeneralInfo(CitizenID);
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netvaerk, String helbredsoplysninger, String hjaelpemidler, String boligensIndretning, int citizenID){
        generalInfoDAO.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netvaerk, helbredsoplysninger, hjaelpemidler, boligensIndretning, citizenID);
    }

    public void updateInfo(GeneralInfo generalInfo){
        generalInfoDAO.updateInfo(generalInfo);
    }

}
