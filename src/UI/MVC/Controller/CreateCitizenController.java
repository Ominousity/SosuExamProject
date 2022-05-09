package UI.MVC.Controller;

import BE.Category;
import BE.Citizen;
import BE.Student;
import BE.SubCategory;
import UI.MVC.Model.CategoryModel;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.StudentModel;
import UI.MVC.Model.SubCategoryModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateCitizenController implements Initializable
{
    @FXML
    private TableView<Citizen> citizenTB;
    @FXML
    private TableColumn<Citizen, String> fNameTC;
    @FXML
    private TableColumn<Citizen, String> lNameTC;
    @FXML
    private ImageView citizenImg;
    @FXML
    private TextField fNameTextField;
    @FXML
    private TextField lNameTextField;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField socialSecTextField;
    @FXML
    private CheckBox isTemplate;
    @FXML
    private TextArea studentTextArea;
    @FXML
    private ChoiceBox chooseStudentCB;

    private String imgPath;

    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private StudentModel studentModel;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;

    public CreateCitizenController() throws IOException
    {
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
        studentModel = new StudentModel();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        citizenTB = new TableView();
        fNameTC = new TableColumn();
        lNameTC = new TableColumn();
        fNameTextField = new TextField();
        lNameTextField = new TextField();
        dobDatePicker = new DatePicker();
        adressTextField = new TextField();
        socialSecTextField = new TextField();
        isTemplate = new CheckBox();
        studentTextArea = new TextArea();
        chooseStudentCB = new ChoiceBox();
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        citizenTB.setPlaceholder(new Label("Ingen borgere var fundet i databasen"));

        fNameTC.setCellValueFactory(new PropertyValueFactory<>("FName"));
        lNameTC.setCellValueFactory(new PropertyValueFactory<>("LName"));
        try
        {
            ObservableList<Student> students = FXCollections.observableArrayList(studentModel.getAllStudents());
            chooseStudentCB.setItems(students);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void handleChooseImg(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("image/" ));
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            imgPath = ("image\\" + file.getName());
            citizenImg.setImage(new Image(file.toURI().toString()));
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) citizenImg.getScene().getWindow();
        stage.close();
    }

    public void handleCreate(ActionEvent actionEvent) {
        citizenModel.createCitizen(fNameTextField.getText(), lNameTextField.getText(), String.valueOf(dobDatePicker.getValue()), adressTextField.getText(), socialSecTextField.getText());
        ArrayList<String> categoryName = new ArrayList<>();
        categoryName.add("Funktionsniveau");
        categoryName.add("Bevægeapparat");
        categoryName.add("Ernæring");
        categoryName.add("Hud og slimhinder");
        categoryName.add("Kommunikation");
        categoryName.add("Psykosociale forhold");
        categoryName.add("Respiration og cirkulation");
        categoryName.add("Seksualitet");
        categoryName.add("Smerter og sanseindtryk");
        categoryName.add("Søvn og hvile");
        categoryName.add("Viden og udvikling");
        categoryName.add("Udskillelse af affaldsstoffer");
        for (String cat : categoryName)
        {
            categoryModel.createCategory(cat, );
        }
    }

    public void handleCancelTemplate(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleCreateTemplate(ActionEvent actionEvent) {
    }
}
