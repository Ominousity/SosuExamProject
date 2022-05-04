package UI.MVC.Model;

import BE.Citizen;
import BLL.CitizenManager;
import DAL.CitizenDAO;

import java.sql.SQLException;

public class CitizenModel
{
    private CitizenManager citizenManager;
    public CitizenModel(){
        citizenManager = new CitizenManager();
    }

    public void createCitizen(String FName, String LName,String Address,String CPR){
        citizenManager.createCitizen(FName, LName, Address, CPR);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenManager.updateCitizen(citizen);
    }

    public void deleteCitizen(int citizenID){
        citizenManager.deleteCitizen(citizenID);
    }

    public void duplicateCitizen(int citizenID){
        //TODO Lav senere når vi har bedre overblik over hvordan ting skal gøres
    }
}

