package DAL;

import BE.Admin;
import BE.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO
{
    private DatabaseConnector connection;
    public AdminDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

    public ArrayList<Admin> getAllAdmin(){
        ArrayList<Admin> admins = new ArrayList<>();

        try(Connection conn = connection.getConnection())
        {
            String sql = "SELECT * FROM Admin;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int schoolID = rs.getInt("SchoolID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                boolean isAdmin = rs.getBoolean("IsAdmin");

                Admin admin = new Admin(ID, Email, Password, schoolID, isAdmin);
                admins.add(admin);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return admins;
    }

    public void createAdmin(String Email, String Password) throws SQLException
    {
        try(Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Admin(Email, Password) values(?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, Email);
                preparedStatement.setString(2, Password);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables){
                throwables.getNextException();
            }
        }
    }

    public void updateAdmin(Admin admin){
        try(Connection conn = connection.getConnection()){
            String sql = "UPDATE Admin SET Email=?, Password=? WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update Student");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteAdmin(int adminID){
        try(Connection conn = connection.getConnection())
        {
            String sql1 = "DELETE FROM Admin WHERE ID=?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,adminID);
            preparedStatement1.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
