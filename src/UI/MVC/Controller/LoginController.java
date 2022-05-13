package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class LoginController
{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView gifImage;

    Stage stage;

    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;
    private Timer timer;
    private TimerTask timerTask;


    public LoginController() throws SQLException, IOException {
        sceneCreator = new SceneCreator();
        loginSystem = new LoginSystem();
        stage = new Stage();
    }

    public void handleLogin() throws IOException, SQLException {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())){
            if (ParseModel.isAdmin){
                ParseModel.isAdmin = true;
                ParseModel.isStudent = false;
                ParseModel.isTeacher = false;
                stage = (Stage) usernameField.getScene().getWindow();
                sceneCreator.createStage(sceneCreator.createScene("../View/AdminView.fxml", "UI/CSS/MainStylesheet.css", this), "Program", false);
                stage.close();
            }else{
                stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = sceneCreator.createScene("../View/DashboardView.fxml", "UI/CSS/MainStylesheet.css", this);
                stage.setScene(scene);
            }
        }
    }

    private void fadeIntro(){
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                int time = 8;
                int time2 = 2;

                if(time >= 0){

                }
            }
        };
        //sets how many times the progressbar should update the progress.
        timer.scheduleAtFixedRate(timerTask, 100, 100);
    }

}
