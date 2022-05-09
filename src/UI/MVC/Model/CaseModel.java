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

    public void createCase(String caseContents) throws SQLException
    {
        caseManager.createCase(caseContents);
    }

    public void deleteCase(int citizenID){
        caseManager.deleteCase(citizenID);
    }

    public void updateCase(CitizenCase citizenCase){
        caseManager.updateCase(citizenCase);
    }
}
