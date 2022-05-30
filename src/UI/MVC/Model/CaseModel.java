package UI.MVC.Model;

import BE.CitizenCase;
import BLL.CaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;

public class CaseModel {
    private CaseManager caseManager;

    private ObservableList<CitizenCase> caseList;

    public CaseModel() throws IOException {
        caseManager = new CaseManager();
        caseList = FXCollections.observableArrayList();
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param CitizenID
     * @return
     */
    public ObservableList<CitizenCase> getAllCases(int CitizenID){
        caseList.clear();
        caseList.addAll(caseManager.getAllCases(CitizenID));
        return caseList;
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param caseName
     * @param caseContents
     * @param caseStatus
     * @param citizenID
     * @throws SQLException
     */
    public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException {
        caseManager.createCase(caseName, caseContents, caseStatus, citizenID);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param citizenCase
     */
    public void updateCase(CitizenCase citizenCase){
        caseManager.updateCase(citizenCase);
    }
}
