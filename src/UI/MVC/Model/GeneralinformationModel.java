package UI.MVC.Model;

import BE.GeneralInfo;
import BLL.GeneralInfoManager;

import java.sql.SQLException;

public class GeneralinformationModel {

    GeneralInfoManager generalInfoManager;

    public GeneralInfo getGeneralInfo(int CitizenID) throws SQLException {
        return generalInfoManager.getGeneralInfo(CitizenID);
    }

    public void createGeneralInfo(String mestring, String motivation, String ressourcer, String roller, String vaner, String uddanelseJob, String livhistorie, String netværk, String helbresoplysninger, String hjælpemidler, String boligensIndretning){
        generalInfoManager.createGeneralInfo(mestring, motivation, ressourcer, roller, vaner, uddanelseJob, livhistorie, netværk, helbresoplysninger, hjælpemidler, boligensIndretning);
    }

    public void updateInfo(GeneralInfo generalInfo){
        generalInfoManager.updateInfo(generalInfo);
    }
}
