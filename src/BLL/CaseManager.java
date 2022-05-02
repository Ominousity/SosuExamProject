package BLL;

import DAL.CaseDAO;

public class CaseManager {
    private CaseDAO caseDAO;

    public CaseManager(){
        caseDAO = new CaseDAO();
    }

    public void createCase(String caseContents){
        caseDAO.createCase(caseContents);
    }
}
