package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox rememberMe;

    Stage stage;

    private ParseModel parseModel = ParseModel.getInstance();
    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;
    private int checked = 0;


    public LoginController() throws SQLException, IOException {
        sceneCreator = new SceneCreator();
        loginSystem = new LoginSystem();
        stage = new Stage();
        rememberMe = new CheckBox();
        autoLogin();
        usernameField = new TextField();
    }

    /**
     * check whether the credentials are correct and then changes the scene. if the remember me check box is checked it also saves the login
     * @throws IOException
     * @throws InterruptedException
     */
    public void handleLogin() throws IOException, InterruptedException
    {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())) {
            if (getSelected()) {
                loginSystem.rememberLogin(usernameField.getText(), loginSystem.getEncryptedPassword(passwordField.getText()));
                changeScene();
            } else {
                changeScene();
            }
        }
    }

    /**
     * checks whether the checkbox is checked
     * @return
     */
    public boolean getSelected() {
        if (checked == 0){
            checked++;
            return false;
        }else if (checked == 1){
            checked--;
            return  true;
        }
        return false;
    }

    /**
     * automatically logs in to the program
     */
    public void autoLogin() {
        Platform.runLater(() -> {
            try {
                if (!loginSystem.isFileEmpty()){
                    if (loginSystem.check(loginSystem.getRememberedLogin(1), loginSystem.getRememberedLogin(2))){
                        changeScene();
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * changes the scenes based on what user is attempting to login
     */
    public void changeScene(){
        if (parseModel.user.getUserType().contains("ADMIN")) {
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/AdminView.fxml", "UI/CSS/AdminViewStylesheet.css", this);
            stage.setScene(scene);
        } else if (parseModel.user.getUserType().contains("STUDENT") || parseModel.user.getUserType().contains("TEACHER")){
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }
}
