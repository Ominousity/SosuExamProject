package UI.MVC.Model;

import BE.GeneralInfo;
import BLL.GeneralInfoManager;

import java.sql.SQLException;

public class GeneralinformationModel {

    GeneralInfoManager generalInfoManager;

    public GeneralInfo getGeneralInfo(int CitizenID) throws SQLException {
        return generalInfoManager.getGeneralInfo(CitizenID);
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netvaerk, String helbredsoplysninger, String hjaelpemidler, String boligensIndretning){
        generalInfoManager.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netvaerk, helbredsoplysninger, hjaelpemidler, boligensIndretning);
    }

    public void updateInfo(GeneralInfo generalInfo){
        generalInfoManager.updateInfo(generalInfo);
    }
}
