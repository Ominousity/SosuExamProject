package DAL;

import BE.GeneralInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneralInfoDAO
{
    private DatabaseConnector connection;
    public GeneralInfoDAO(){
        connection = new DatabaseConnector();
    }

    public GeneralInfo getGeneralInfo(int CitizenID){
        try(Connection conn = connection.getConnection())
        {
            String sql = "SELECT * FROM GeneralInfo WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, CitizenID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String mestring = rs.getString("Mestring");
                String motivation = rs.getString("Motivation");
                String Ressourcer = rs.getString("Ressourcer");
            }
            GeneralInfo generalInfo = new GeneralInfo();
        }
    }
}
