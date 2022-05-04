package UI.MVC.Model;

import BE.Admin;
import BLL.AdminManager;
import BLL.Utility.Encryptor;
import DAL.AdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminModel
{
    private AdminManager adminManager;
    private Encryptor encryptor;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
        encryptor = new Encryptor();
    }

    public ArrayList<Admin> getAllAdmins(){
        return adminManager.getAllAdmins();
    }

    public void createAdmin(String email, String password) throws SQLException
    {
        adminManager.createAdmin(email, encryptor.Encrypt(password));
    }

    public void deleteAdmin(int adminID){
        adminManager.deleteAdmin(adminID);
    }

    public void updateAdmin(Admin admin){
        adminManager.updateAdmin(admin);
    }
}

