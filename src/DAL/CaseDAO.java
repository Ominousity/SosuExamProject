package DAL;

import BE.CitizenCase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO
{
    private DatabaseConnector connection = DatabaseConnector.getInstance();

    public CaseDAO() throws IOException {
    }

    /**
     * Makes an arraylist with all the cases, that is controlled by the CitizenID
     * @param CitizenID
     * @return
     */
    public List<CitizenCase> getAllCases(int CitizenID){
        ArrayList<CitizenCase> citizenCases = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM CitizenCase WHERE CitizenID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, CitizenID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("CaseName");
                String content = rs.getString("CaseContent");
                String status = rs.getString("CaseStatus");

                CitizenCase citizenCaseTemp = new CitizenCase(id, name, content, status);
                citizenCases.add(citizenCaseTemp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return citizenCases;
    }

    /**
     * Makes cases with the information shown below
     * @param caseName
     * @param caseContents
     * @param caseStatus
     * @param citizenID
     * @throws SQLException
     */
    public void createCase(String caseName , String caseContents, String caseStatus, int citizenID) throws SQLException
    {
        try(Connection conn = connection.getConnection()) {

            String sql = "INSERT INTO CitizenCase(CaseName, CaseContent, CaseStatus, CitizenID) VALUES(?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, caseName);
                preparedStatement.setString(2, caseContents);
                preparedStatement.setString(3, caseStatus);
                preparedStatement.setInt(4, citizenID);
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.out.println(e);
                e.getNextException();
            }
        }
    }

    /**
     * Updates the case
     * @param citizenCase
     */
    public void updateCase(CitizenCase citizenCase){
        try(Connection conn = connection.getConnection())
        {
            String sql = "UPDATE CitizenCase SET CaseName=?, CaseContent=?, CaseStatus=? WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, citizenCase.getName());
            preparedStatement.setString(2, citizenCase.getCaseContent());
            preparedStatement.setString(3, citizenCase.getStatus());
            preparedStatement.setInt(4, citizenCase.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update Case");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}