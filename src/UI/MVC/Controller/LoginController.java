package UI.MVC.Controller;

import BLL.Utility.LoginSystem;
import UI.Utility.SceneCreator;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController
{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private SceneCreator sceneCreator;
    private LoginSystem loginSystem;

    public LoginController()
    {

    }

    public void login() throws IOException
    {
        if (loginSystem.check(usernameField.getText(), passwordField.getText())){
            sceneCreator.createStage(sceneCreator.createScene("UI/MVC/View/DashboardView.fxml","UI/CSS/MainStylesheet.css",this), "Dashboard", false);
        }
    }
}
