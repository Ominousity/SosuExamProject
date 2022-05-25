package UI.MVC.Controller;

import BE.Category;
import BE.Citizen;
import BE.Student;
import UI.MVC.Model.*;
import UI.Utility.SceneCreator;
import com.sun.jdi.ArrayReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateCitizenController implements Initializable
{
    public TextField fNameTextField;
    public TextField lNameTextField;
    public TableView<Citizen> tvCitizen;
    public ComboBox<Student> chooseStudentCB;
    public TableColumn tcFName;
    public TableColumn tcLName;
    public Button cancelBtn;
    public CheckBox isTemplate;
    public Button addBtn;
    public ComboBox<Student> chooseTempStudent;
    public ListView studentsListView;
    public ListView templateStudentsListView;
    public TextField ageTextField;

    private ObservableList<Student> students;
    private ObservableList<Student> templateStudents;

    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private UserModel userModel;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private GeneralinformationModel generalinformationModel;
    private ParseModel parseModel = ParseModel.getInstance();

    public CreateCitizenController() throws IOException {
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
        userModel = new UserModel();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        generalinformationModel = new GeneralinformationModel();

        students = FXCollections.observableArrayList();
        templateStudents = FXCollections.observableArrayList();

        fNameTextField = new TextField();
        lNameTextField = new TextField();
        ageTextField = new TextField();
        tvCitizen = new TableView<>();
        chooseStudentCB = new ComboBox<>();
        tcFName = new TableColumn<>();
        tcLName = new TableColumn<>();
        cancelBtn = new Button();
        isTemplate = new CheckBox();
        studentsListView = new ListView<>();
        addBtn = new Button();
        chooseTempStudent = new ComboBox<>();
        templateStudentsListView = new ListView<>();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (parseModel.citizen != null){
            fNameTextField.setText(parseModel.citizen.getFName());
            fNameTextField.setText(parseModel.citizen.getLName());
            fNameTextField.setText(parseModel.citizen.getAge() + "");
        }

        try {
            chooseStudentCB.setItems(userModel.getAllStudentsFromSchool(parseModel.user.getSchoolID()));
            chooseTempStudent.setItems(userModel.getAllStudentsFromSchool(parseModel.user.getSchoolID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tvCitizen.setPlaceholder(new Label("Ingen borgere var fundet i databasen"));
        tcFName.setCellValueFactory(new PropertyValueFactory<String, String>("FName"));
        tcLName.setCellValueFactory(new PropertyValueFactory<String, String>("LName"));
        try{
            ObservableList<Citizen> citizens = FXCollections.observableArrayList(citizenModel.getTemplateCitizens());
            tvCitizen.setItems(citizens);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * closes the stage
     * @param actionEvent
     */
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void isTemplate(Citizen citizen) throws SQLException {
        if (citizen.isTemplate()){

            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Citizen er oprettet", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
            }
        }else{
            bindStudentToCitizen(citizen.getID());

            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Citizen er oprettet", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * creates a citizen by getting all the information the user has entered
     * @param actionEvent
     */
    public void handleCreate(ActionEvent actionEvent) throws IOException, SQLException {
        if (parseModel.citizen == null) {
            Citizen citizen = citizenModel.createCitizen(fNameTextField.getText(), lNameTextField.getText(), Integer.parseInt(ageTextField.getText()), isTemplate.isSelected(), parseModel.user.getSchoolID());
            createCategories(citizen.getID());
            generalinformationModel.createGeneralInfo("", "", "", "", "", "", "", "", "", "", "", citizen.getID());

            isTemplate(citizen);
        } else {
            citizenModel.updateCitizen(new Citizen(parseModel.citizen.getID(), fNameTextField.getText(), lNameTextField.getText(), Integer.parseInt(ageTextField.getText()), isTemplate.isSelected()));
            parseModel.citizen = null;
        }

    }
    
    public void createCategories(int citizenID) throws IOException {
        BufferedReader funcBR = new BufferedReader(new FileReader("Utilities/FunktionsevneTilstandCat.txt"));
        BufferedReader healthBR = new BufferedReader(new FileReader("Utilities/HelbredsTilstandCat.txt"));
        Category tempCategory = new Category(0,"", false);
        String line;

        while ((line = funcBR.readLine()) != null)   {
            if (line.contains(";")){
                line = line.replace(";","".repeat(line.length()));
                if (line.contains("Egenomsorg")){
                    tempCategory = categoryModel.createCategory(line, false,  citizenID);
                }else if (line.contains("Praktiske Opgaver")){
                    tempCategory = categoryModel.createCategory(line, false,  citizenID);
                }else if (line.contains("Mobilitet")){
                    tempCategory = categoryModel.createCategory(line, false,  citizenID);
                }else if (line.contains("Mentale Funktioner")){
                    tempCategory = categoryModel.createCategory(line, false,  citizenID);
                }else if (line.contains("Samfundsliv")){
                    tempCategory = categoryModel.createCategory(line, false,  citizenID);
                }
            }else {
                subCategoryModel.createSubCategory(line, "", tempCategory.getID());
            }
        }
        funcBR.close();

        while ((line = healthBR.readLine()) != null)   {
            if (line.contains(";")){
                line = line.replace(";","".repeat(line.length()));
                tempCategory = categoryModel.createCategory(line, true, citizenID);
            }else {
                subCategoryModel.createSubCategory(line, "", tempCategory.getID());
            }
        }
        healthBR.close();
    }

    public void bindStudentToCitizen(int citizenID) throws SQLException {
        for (Student student : students) {
            citizenModel.createCitizenToStudent(citizenID, student.getID());
        }
    }

    public void addStudent(){
        students.add(chooseStudentCB.getSelectionModel().getSelectedItem());
        studentsListView.setItems(students);
    }

    public void addTemplateStudent(){
        templateStudents.add(chooseTempStudent.getSelectionModel().getSelectedItem());
        templateStudentsListView.setItems(templateStudents);
    }

    public void handleIsTemplate(ActionEvent actionEvent) {
        if (isTemplate.isSelected()){
            chooseStudentCB.setDisable(true);
            studentsListView.setDisable(true);
            addBtn.setDisable(true);
        }else{
            chooseStudentCB.setDisable(false);
            studentsListView.setDisable(false);
            addBtn.setDisable(false);
        }
    }

    public void handleCreateFromTemplate(ActionEvent actionEvent) throws SQLException {
        if (tvCitizen.getSelectionModel().getSelectedItem() == null && templateStudents.isEmpty()){
            Alert alert = sceneCreator.popupBox(Alert.AlertType.WARNING, "Husk at vælge en borger først", "programmet kan ikke finde dataene fra borger", ButtonType.OK);
            alert.showAndWait();
        }else{
            Citizen citizen = citizenModel.duplicateCitizen(tvCitizen.getSelectionModel().getSelectedItem(), parseModel.user.getSchoolID());

            for (Student student : templateStudents) {
                citizenModel.createCitizenToStudent(citizen.getID(), student.getID());
            }
        }
    }
}
