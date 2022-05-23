package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class LoginController implements Initializable
{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView gifImage;

    @FXML
    private CheckBox rememberMe;
    Stage stage;

    private ParseModel parseModel = ParseModel.getInstance();
    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;
    private double opacity = 100;
    private Image image;
    private Thread thread;


    public LoginController() throws SQLException, IOException {
        sceneCreator = new SceneCreator();
        loginSystem = new LoginSystem();
        stage = new Stage();
        rememberMe = new CheckBox();
        autoLogin();
        image = new Image("UI/Images/LoginBack.png");
        gifImage = new ImageView(image);
        usernameField = new TextField();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gifImage.setImage(image);
        gifImage.setOpacity(10);
        gifImage.setScaleX(1.5);
        gifImage.setScaleY(1.5);
    }

    public void handleLogin() throws IOException, InterruptedException
    {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())) {
            if (!rememberMe.isSelected()) {
                loginSystem.rememberLogin(usernameField.getText(), loginSystem.getEncryptedPassword(passwordField.getText()));
                changeScene();
            } else {
                changeScene();
            }
        }
    }

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

    public void changeScene(){
        if (parseModel.user.getUserType().contains("ADMIN")) {
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/AdminView.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        } else if (parseModel.user.getUserType().contains("TEACHER")) {
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/TeacherStylesheet.css", this);
            stage.setScene(scene);
        }else if (parseModel.user.getUserType().contains("STUDENT")){
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }

    private void fadeIntro(){
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(gifImage);
        scaleTransition.setDuration(new Duration(100));
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.playFromStart();
    }
}
