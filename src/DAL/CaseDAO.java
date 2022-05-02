package DAL;

import BE.CitizenCase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO
{
    private DatabaseConnector connection;
    public CaseDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

    public List<CitizenCase> getAllCases(int CitizenID){
        ArrayList<CitizenCase> citizenCases = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM CitizenCase WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, CitizenID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String content = rs.getString("CitizenCaseContent");

                CitizenCase citizenCaseTemp = new CitizenCase(content);
                citizenCases.add(citizenCaseTemp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return citizenCases;
    }

    public void createCase(String Content) throws SQLException
    {
        try(Connection conn = connection.getConnection())
        {
            String sql = "INSERT INTO CitizenCase(Content) values(?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                preparedStatement.setString(1, CitizenCaseContent);
            } catch (SQLException e){
                e.getNextException();
            }
        }
    }

    public void updateCase(CitizenCase citizenCase){
        try(Connection conn = connection.getConnection())
        {
            String sql = "UPDATE CitizenCase SET CitizenCaseContent=? WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, citizenCase.getContent());
            preparedStatement.setInt(2, citizenCase.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update Case");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCitizenCase(int citizenCaseID){
        try(Connection conn = connection.getConnection())
        {
            String sql = "DELETE FROM CitizenCase WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}