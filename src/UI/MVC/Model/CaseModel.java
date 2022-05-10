package UI.MVC.Model;

import BE.CitizenCase;
import BLL.CaseManager;
import DAL.CaseDAO;

import java.io.IOException;
import java.sql.SQLException;

public class CaseModel {
    private CaseManager caseManager;

    public CaseModel() throws IOException {
        caseManager = new CaseManager();
    }

    public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException
    {
        caseManager.createCase(caseName, caseContents, caseStatus, citizenID);
    }

    public void deleteCase(int citizenID){
        caseManager.deleteCase(citizenID);
    }

    public void updateCase(CitizenCase citizenCase){
        caseManager.updateCase(citizenCase);
    }
}
