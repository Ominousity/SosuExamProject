package UI.MVC.Controller;

import BE.Citizen;
import BE.Student;
import UI.MVC.Model.*;
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

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private boolean isFuncHealth;
    
    private SceneCreator sceneCreator;
    private CitizenModel citizenModel;
    private UserModel userModel;
    private CategoryModel categoryModel;
    private SubCategoryModel subCategoryModel;
    private GeneralinformationModel generalinformationModel;

    public CreateCitizenController() throws IOException
    {
        sceneCreator = new SceneCreator();
        citizenModel = new CitizenModel();
        userModel = new UserModel();
        categoryModel = new CategoryModel();
        subCategoryModel = new SubCategoryModel();
        generalinformationModel = new GeneralinformationModel();
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
        try {
            ObservableList<Student> students = FXCollections.observableArrayList(userModel.getAllStudents());
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

    /**
     * closes the stage
     * @param actionEvent
     */
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) citizenImg.getScene().getWindow();
        stage.close();
    }

    /**
     * creates a citizen by getting all the information the user has entered
     * @param actionEvent
     */

    
    public void handleCreate(ActionEvent actionEvent) throws IOException {
        Citizen citizen = citizenModel.createCitizen(fNameTextField.getText(), lNameTextField.getText(), adressTextField.getText(), String.valueOf(dobDatePicker.getValue()), socialSecTextField.getText(), 1);
        //createCategories(citizen.getID());
        generalinformationModel.createGeneralInfo("","","","","","","","","","","");
        
        Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Citizen er oprettet", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) citizenImg.getScene().getWindow();
            stage.close();
        }
    }
    
    public void createCategories(int citizenID) throws IOException {
        List categoryNameList = new ArrayList();
        FileReader funcReader = new FileReader("Utilities/FunktionsevneTilstandCat.txt");
        FileReader healthReader = new FileReader("Utilities/HelbredsTilstandCat.txt");
        int fileLength;

        for (int i = 0; i < getLengthOfFile(funcReader); i++) {
            
        }
    }

    public int getLengthOfFile(FileReader fileReader) throws IOException {
        BufferedReader reader = new BufferedReader(fileReader);
        int lines = 0;
        while (reader.readLine() != null){
            lines++;
        }
        reader.close();
        return lines;
    }

    /**
     * closes the stage
     * @param actionEvent
     */
    public void handleCancelTemplate(ActionEvent actionEvent) {
        sceneCreator.createStage(sceneCreator.createScene("../View/CitizenView.fxml", "UI/CSS/MainStylesheet.css",this), "Borger", false);
    }


    public void handleCreateTemplate(ActionEvent actionEvent) {
    }
}
