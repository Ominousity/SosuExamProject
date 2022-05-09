package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.MVC.Model.ParseModel;
import UI.Utility.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
public class LoginController
{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;

    public LoginController() throws SQLException, IOException {
        sceneCreator = new SceneCreator();
        loginSystem = new LoginSystem();
    }

    public void handleLogin() throws IOException
    {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())){
            if (ParseModel.isAdmin){
                sceneCreator.createStage(sceneCreator.createScene("../View/AdminView.fxml","UI/CSS/MainStylesheet.css",this), "Admin", false);
            }else{
                sceneCreator.createStage(sceneCreator.createScene("../View/DashboardView.fxml","UI/CSS/MainStylesheet.css",this), "Dashboard", false);
            }
        }
    }
}
