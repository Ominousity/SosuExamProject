package BLL;

import BE.GeneralInfo;
import DAL.GeneralInfoDAO;

public class GeneralInfoManager {
    private GeneralInfoDAO generalInfoDAO;

    public GeneralInfoManager(GeneralInfo generalInfo){
        generalInfoDAO.updateInfo(generalInfo);
    }
}
