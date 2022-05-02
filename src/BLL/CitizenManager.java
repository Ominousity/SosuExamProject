package BLL;

import DAL.CitizenDAO;

public class CitizenManager {
    CitizenDAO citizenDAO;

    public CitizenManager(){
        citizenDAO = new CitizenDAO();
    }

    public void createCitizen(){
        citizenDAO.createCitizen();
    }

    public void updateCitizen(){
        citizenDAO.updateCitizen();
    }

    public void deleteCitizen(){
        citizenDAO.deleteCitizen();
    }
}
