package UI.MVC.Model;

import BE.CitizenCase;
import BLL.CaseManager;
import DAL.CaseDAO;

import java.io.IOException;
import java.sql.SQLException;

public class CaseModel {
    private CaseManager caseDAO;

    public CaseModel() throws IOException
    {
        caseManager = new CaseManager();
    }

    public void createCase(String caseContents) throws SQLException
    {
        caseDAO.createCase(caseContents);
    }

    public void deleteCase(int citizenID){
        caseDAO.deleteCitizenCase(citizenID);
    }

    public void updateCase(CitizenCase citizenCase){
        caseDAO.updateCase(citizenCase);
    }
}
