package UI.MVC.Model;

import BE.CitizenCase;
import BLL.CaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseModel {
    private CaseManager caseManager;

    private ObservableList<CitizenCase> caseList;

    public CaseModel() throws IOException {
        caseManager = new CaseManager();

        caseList = FXCollections.observableArrayList();
    }

    public ObservableList<CitizenCase> getAllCases(int CitizenID){
        caseList.clear();
        caseList.addAll(caseManager.getAllCases(CitizenID));
        return caseList;
    }

    public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException {
        caseManager.createCase(caseName, caseContents, caseStatus, citizenID);
    }

    public void updateCase(CitizenCase citizenCase){
        caseManager.updateCase(citizenCase);
    }

    public void deleteCase(int citizenID){
        caseManager.deleteCase(citizenID);
    }
}
