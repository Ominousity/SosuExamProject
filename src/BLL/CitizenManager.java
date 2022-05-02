package BLL;

import BE.Citizen;
import DAL.CitizenDAO;

public class CitizenManager {
    private CitizenDAO citizenDAO;

    public CitizenManager(){
        citizenDAO = new CitizenDAO();
    }

    public void createCitizen(String FName, String LName,String Address,String CPR){
        citizenDAO.createCitizen(FName, LName, Address, CPR);
    }

    public void updateCitizen(Citizen citizen){
        citizenDAO.updateCitizen(citizen);
    }

    public void deleteCitizen(int citizenID){
        citizenDAO.deleteCitizen(citizenID);
    }

    public void duplicateCitizen(int citizenID){
        citizenDAO.duplicateCitizen(citizenID);
    }
}
