package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
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

    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;
    private Timer timer;
    private TimerTask timerTask;
    private int time = 9;
    private int time2 = 2;
    private double opacity = 100;
    private Image image;
    private Thread thread;


    public LoginController() throws SQLException, IOException {
        sceneCreator = new SceneCreator();
        loginSystem = new LoginSystem();
        stage = new Stage();
        image = new Image("UI/Images/SOSO.gif");
        rememberMe = new CheckBox();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gifImage = new ImageView(image);
        gifImage.setOpacity(100);
        fadeIntro();
    }

    public void handleLogin() throws IOException, SQLException {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())) {
            if (!rememberMe.isSelected()) {
                loginSystem.rememberLogin(usernameField.getText(), loginSystem.getEncryptedPassword(passwordField.getText()));
                changeScene();
            } else {
                changeScene();
            }
        }
    }

    public void autoLogin() throws IOException {
        if (!loginSystem.isFileEmpty()){
            if (loginSystem.check(loginSystem.getRememberedLogin(1), loginSystem.getRememberedLogin(2))){
                changeScene();
            }
        }
    }

    public void changeScene(){
        if (ParseModel.isAdmin) {
            stage = (Stage) usernameField.getScene().getWindow();
            sceneCreator.createStage(sceneCreator.createScene("../View/AdminView.fxml", "UI/CSS/MainStylesheet.css", this), "Program", false);
            stage.close();
        } else if (ParseModel.isTeacher) {
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/TeacherStylesheet.css", this);
            stage.setScene(scene);
        } else {
            stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
            stage.setScene(scene);
        }
    }

    private void fadeIntro(){
        timer = new Timer();
        System.out.println("hello");

        timerTask = new TimerTask() {
            @Override
            public void run() {
                time -= 1;
                System.out.println(time);
                if(!(time >= 0)){
                    System.out.println("work");
                    if (time2 != 0){
                        System.out.println("yes");
                        opacity -= 50;
                        Platform.runLater(() -> gifImage.setOpacity(opacity));
                        time2 -= 1;
                    } else {
                        System.out.println("only one");
                        Platform.runLater(() -> gifImage.toBack());
                        timer.cancel();
                    }
                }
            }
        };
        //sets how many times the progressbar should update the progress.
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
}
