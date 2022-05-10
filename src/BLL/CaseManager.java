package BLL;

import BE.CitizenCase;
import DAL.CaseDAO;

import java.io.IOException;
import java.sql.SQLException;

public class CaseManager {
    private CaseDAO caseDAO;

    public CaseManager() throws IOException
    {
        caseDAO = new CaseDAO();
    }

    public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException
    {
        caseDAO.createCase(caseName, caseContents, caseStatus, citizenID);
    }

    public void deleteCase(int citizenID){
        caseDAO.deleteCitizenCase(citizenID);
    }

    public void updateCase(CitizenCase citizenCase){
        caseDAO.updateCase(citizenCase);
    }
}
