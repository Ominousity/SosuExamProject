package UI.MVC.Controller;

import BE.Category;
import BE.Citizen;
import BE.Student;
import BLL.StudentManager;
import UI.MVC.Model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TestListAfBorgereController {

    StudentManager studentManager;
    StudentModel studentModel;


    ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();

    public void assignStudentsIntoCitizen() throws SQLException {
        for (Citizen citizen : allCitizens) {
            studentManager.removeCategories();
            List<Student> students = studentModel.getAllStudentsForOneCitizen(citizen);
            for (Student student : students) {
                studentManager.setCitizens(student.getFName(), student.getLName());
            }
        }
    }


    /**
     * creates and return observablelist from list off all citizens.
     *
     * @return an observablelist of all citizens.
     */
    public ObservableList<Citizen> getCitizens() throws SQLException {
        allCitizens.clear();
        allCitizens.addAll(studentManager.getAllStudents());
        asignCategoriesIntoMovies();
        return allMovies;
    }
}
