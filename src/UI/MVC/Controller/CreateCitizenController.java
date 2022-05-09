package UI.MVC.Controller;

import BE.Student;
import UI.MVC.Model.CitizenModel;
import UI.MVC.Model.StudentModel;
import UI.Utility.SceneCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateCitizenController implements Initializable
{

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

    public CreateCitizenController() throws IOException
    {
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
        studentModel = new StudentModel();
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        fNameTextField = new TextField();
        lNameTextField = new TextField();
        dobDatePicker = new DatePicker();
        adressTextField = new TextField();
        socialSecTextField = new TextField();
        isTemplate = new CheckBox();
        studentTextArea = new TextArea();
        chooseStudentCB = new ChoiceBox();
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
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleCreate(ActionEvent actionEvent) {
        citizenModel.createCitizen(fNameTextField.getText(), lNameTextField.getText(), String.valueOf(dobDatePicker.getValue()), adressTextField.getText(), socialSecTextField.getText());
    }

    public void handleCancelTemplate(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml","UI/CSS/MainStylesheet.css",this), "Borger", false);
    }

    public void handleCreateTemplate(ActionEvent actionEvent) {
    }
}
