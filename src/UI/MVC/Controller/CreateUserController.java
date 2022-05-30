package UI.MVC.Controller;

import BE.School;
import BE.User;
import UI.MVC.Model.*;
import UI.Utility.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private ComboBox<School> schoolCB;
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private RadioButton rbIsTeacher;
    @FXML
    private RadioButton rbIsAdmin;
    @FXML
    private RadioButton rbIsStudent;

    private UserModel userModel;
    private SchoolModel schoolModel;
    private SceneCreator sceneCreator;
    private ParseModel parseModel = ParseModel.getInstance();

    public CreateUserController() throws IOException {
       userModel = new UserModel();
       schoolModel = new SchoolModel();
       sceneCreator = new SceneCreator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            schoolCB.getItems().addAll(schoolModel.getSchool());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (parseModel.user != null && parseModel.isEditingUser) {
            tfFName.setText(parseModel.user.getFName());
            tfLName.setText(parseModel.user.getLName());
            tfEmail.setText(parseModel.user.getEmail());
            if (parseModel.user.getUserType().contains("ADMIN")){
                rbIsAdmin.setSelected(true);
            } else if (parseModel.user.getUserType().contains("TEACHER")) {
                rbIsTeacher.setSelected(true);
            }else {
                rbIsStudent.setSelected(true);
            }
        }

    }

    /**
     * calls the usermodel createuser or updateuser it depends if a user has been selected in the list.
     * @throws SQLException
     * @throws IOException
     */
    public void handleCreateUser() throws SQLException, IOException {
        String userType = "";
        if (rbIsStudent.isSelected()) {
            userType = "STUDENT";
        } else if (rbIsAdmin.isSelected()) {
            userType = "ADMIN";
        } else if (rbIsTeacher.isSelected()) {
            userType = "TEACHER";
        }

        if (!parseModel.isEditingUser) {
            userModel.createUser(tfFName.getText(), tfLName.getText(), tfEmail.getText(), tfPassword.getText(), userType, schoolCB.getSelectionModel().getSelectedItem().getSchoolID());
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Bruger oprettet succesfuldt", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        } else {
            userModel.updateUser(new User(parseModel.user.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), tfPassword.getText(), schoolCB.getSelectionModel().getSelectedItem().getSchoolID(), userType));
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Bruger opdateret succesfuldt", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * closes the window.
     * @param actionEvent
     */
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) rbIsAdmin.getScene().getWindow();
        stage.close();
    }
}
