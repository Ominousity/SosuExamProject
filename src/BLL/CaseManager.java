package BLL;

import BE.CitizenCase;
import DAL.CaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseManager {
    private CaseDAO caseDAO;

    public CaseManager() throws IOException {
        caseDAO = new CaseDAO();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param CitizenID
     * @return
     */
    public List<CitizenCase> getAllCases(int CitizenID){
        return caseDAO.getAllCases(CitizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param caseName
     * @param caseContents
     * @param caseStatus
     * @param citizenID
     * @throws SQLException
     */
	public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException {
        caseDAO.createCase(caseName, caseContents, caseStatus, citizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizenCase
     */
    public void updateCase(CitizenCase citizenCase){
        caseDAO.updateCase(citizenCase);
    }
}
