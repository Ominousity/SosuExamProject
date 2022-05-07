package BLL.Utility;
import BE.Citizen;
import BE.Student;
import BE.Teacher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class DataCacher{

    public void writeStudent(List<Student> studentList) throws IOException {
        FileWriter writer = new FileWriter("Utilities/Cache/CachedStudents.txt");
        for (Student student : studentList) {
            writer.write( student.getID() + "," + student.getFName() + "," + student.getLName() + "," + student.getEmail() + "," + student.getPassword() + "," + student.getSchoolID());
        }
        writer.close();
    }

    public void writeCitizen(List<Citizen> citizenList) throws IOException {
        FileWriter writer = new FileWriter("Utilities/Cache/CachedCitizens.txt");
        for (Citizen citizen : citizenList) {
            writer.write( citizen.getID() + "," + citizen.getFName() + "," + citizen.getLName() + "," + citizen.getAddress() + "," + citizen.getCPR());
        }
        writer.close();
    }

    public void read(){

    }
}
