package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DatabaseConnector connection;
    public UserDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM User";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int schoolID = rs.getInt("schoolID");
                boolean isAdmin = rs.getBoolean("isAdmin");
                boolean isStudent = rs.getBoolean("isStudent");
                boolean isTeacher = rs.getBoolean("isTeacher");

                User user = new User(ID, fName, lName, email, password, schoolID, isAdmin, isStudent, isTeacher);
                users.add(user);
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Student;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Student student = new Student(ID, FName, LName, Email, Password, SchoolID);
                students.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    public void createUser(String fName, String lName, String email, String password, int schoolID, boolean isAdmin, boolean isStudent, boolean isTeacher) throws SQLException
    {
        try(Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Admin(fName, lName, email, password, schoolID, isAdmin, isStudent, isTeacher) values(?,?,?,?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, lName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setInt(5, schoolID);
                preparedStatement.setBoolean(6, isAdmin);
                preparedStatement.setBoolean(7, isStudent);
                preparedStatement.setBoolean(8, isTeacher);

                preparedStatement.execute();
                preparedStatement.executeUpdate();

            } catch (SQLException e){
                e.getNextException();
            }
        }
    }

    public void updateUser(User user) throws SQLServerException {
        try(Connection conn = connection.getConnection()) {
            String sql = "UPDATE User SET fName=?, lName=?, email=?, password=?, schoolID=?, isAdmin=?, isStudent=?, isTeacher=? WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getFName());
            preparedStatement.setString(2,user.getLName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setInt(5,user.getSchoolID());
            preparedStatement.setBoolean(6,user.getIsAdmin());
            preparedStatement.setBoolean(7,user.getIsStudent());
            preparedStatement.setBoolean(8,user.getIsTeacher());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update User");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } ;
    }

    public void deleteUser() throws SQLServerException {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM User WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } ;
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

    /**
     * gets all students one citizen has
     * @param citizen the citizen that we are looking for students in
     * @return list of caregories for one movie
     */
    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException {
        List<Student> studentsInCitizen = new ArrayList<>();
        try(Connection conn = connection.getConnection()){
            String sql = "SELECT FName, LName FROM Student INNER JOIN StuCit ON StuCit.StudentID = Student.ID WHERE CitizenID =(?);"; //sql command
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizen.getID());

            //Extract data from DB
            if(preparedStatement.execute()){
                ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next()) {
                    String FName = resultSet.getString("FName");
                    String LName = resultSet.getString("LName");

                    studentsInCitizen.add(new Student(FName, LName));
                }
            }
        }

        return studentsInCitizen;
    }



    public void createStudent(String FName, String LName, String Email, String Password, int schoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Student(FName,LName, Email, Password, SchoolID) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, Password);
                preparedStatement.setInt(5, schoolID);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Student SET FName, LName, Email, Password";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, student.getFName());
                preparedStatement.setString(2, student.getLName());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setString(4, student.getPassword());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Student");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(int StudentID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM StuCit WHERE StudentID=?, DELETE FROM Student WHERE ID=?;";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            preparedStatement1.setInt(1,StudentID);
            preparedStatement1.setInt(2,StudentID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Teacher;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Teacher teacher = new Teacher(ID, FName, LName, Email, Password, SchoolID);
                teachers.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teachers;
    }

    public void createTeacher(String FName, String LName, String Email, String Password, int schoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Teacher(FName,LName, Email, Password, SchoolID) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, Password);
                preparedStatement.setInt(5, schoolID);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Teacher SET FName, LName, Email, Password";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, teacher.getFName());
                preparedStatement.setString(2, teacher.getLName());
                preparedStatement.setString(3, teacher.getEmail());
                preparedStatement.setString(4, teacher.getPassword());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Teacher");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTeacher(int TeacherID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM Teacher WHERE ID=?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            preparedStatement1.setInt(1,TeacherID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
