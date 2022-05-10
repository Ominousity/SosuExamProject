package BLL;

import BE.GeneralInfo;
import DAL.GeneralInfoDAO;

import java.sql.SQLException;

public class GeneralInfoManager {
    private GeneralInfoDAO generalInfoDAO;

    public GeneralInfo getGeneralInfo(int CitizenID) throws SQLException{
        return generalInfoDAO.getGeneralInfo(CitizenID);
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netværk, String helbredsoplysninger, String hjælpemidler, String boligensIndretning){
        generalInfoDAO.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netværk, helbredsoplysninger, hjælpemidler, boligensIndretning);
    }

    public void updateInfo(GeneralInfo generalInfo){
        generalInfoDAO.updateInfo(generalInfo);
    }

}
