package UI.MVC.Controller;

import BE.User;
import BLL.Utility.Encryptor;
import UI.MVC.Model.ParseModel;
import UI.MVC.Model.UserModel;
import UI.Utility.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EditUserController {
    @FXML
    private RadioButton rbIsAdmin;
    @FXML
    private RadioButton rbIsStudent;
    @FXML
    private RadioButton rbIsTeacher;
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;

    private UserModel userModel;
    private User user;
    private Encryptor encryptor;
    private SceneCreator sceneCreator;


    public EditUserController() throws IOException {
        userModel = new UserModel();
        user = ParseModel.user;
        sceneCreator = new SceneCreator();
    }

    /**
     * updates the information of the user
     * @throws IOException
     * @throws SQLException
     */
    public void updateUser() throws IOException, SQLException {
        if (rbIsAdmin.isSelected()){
            tfLName.setDisable(true);
            tfFName.setDisable(true);
            User tempUser = new User(user.getID(), user.getFName(), user.getLName(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), user.getSchoolID(), user.getUserType());
            userModel.updateUser(tempUser);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Success", "Admin var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsStudent.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            User tempUser = new User(user.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), user.getSchoolID(), user.getUserType());
            userModel.updateUser(tempUser);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Student var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }
        if (rbIsTeacher.isSelected()){
            tfLName.setDisable(false);
            tfFName.setDisable(false);
            User tempUser = new User(user.getID(), tfFName.getText(), tfLName.getText(), tfEmail.getText(), encryptor.Encrypt(tfPassword.getText()), user.getSchoolID(), user.getUserType());
            userModel.updateUser(tempUser);
            Alert alert = sceneCreator.popupBox(Alert.AlertType.CONFIRMATION, "Succes", "Teacher var opdateret", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Stage stage = (Stage) tfEmail.getScene().getWindow();
                stage.close();
            }
        }

    }
}

