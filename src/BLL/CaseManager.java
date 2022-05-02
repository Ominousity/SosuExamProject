package BLL;

import DAL.CaseDAO;

public class CaseManager {
    CaseDAO caseDAO;

    public CaseManager(){
        caseDAO = new CaseDAO();
    }

    public void createCase(String caseContents){
        caseDAO.createCase(caseContents);
    }
}
