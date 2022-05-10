package BLL;

import BE.Admin;
import DAL.AdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminManager {
    private AdminDAO adminDAO;

    public AdminManager() throws IOException {
        adminDAO = new AdminDAO();
    }

    public ArrayList<Admin> getAllAdmins(){
       return adminDAO.getAllAdmin();
    }

    public void createAdmin(String email, String password, int schoolID) throws SQLException
    {
        adminDAO.createAdmin(email, password, schoolID);
    }

    public void deleteAdmin(int adminID){
        adminDAO.deleteAdmin(adminID);
    }

    public void updateAdmin(Admin admin){
        adminDAO.updateAdmin(admin);
    }
}
