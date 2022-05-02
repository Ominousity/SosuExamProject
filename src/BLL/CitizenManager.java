package BLL;

import BE.Citizen;
import DAL.CitizenDAO;

import java.sql.SQLException;

public class CitizenManager {
    private CitizenDAO citizenDAO;

    public CitizenManager(){
        citizenDAO = new CitizenDAO();
    }

    public void createCitizen(String FName, String LName,String Address,String CPR){
        citizenDAO.createCitizen(FName, LName, Address, CPR);
    }

    public void updateCitizen(Citizen citizen) throws SQLException {
        citizenDAO.updateCitizen(citizen);
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }

    public void duplicateCitizen(int citizenID){
        //TODO Lav senere når vi har bedre overblik over hvordan ting skal gøres
    }
}
