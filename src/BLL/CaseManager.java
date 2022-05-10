package BLL;

import BE.CitizenCase;
import DAL.CaseDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseManager {
    private CaseDAO caseDAO;

    public CaseManager() throws IOException
    {
        caseDAO = new CaseDAO();
    }

    public List<CitizenCase> getAllCases(int CitizenID){
        return caseDAO.getAllCases(CitizenID);
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
