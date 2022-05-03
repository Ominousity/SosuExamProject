package UI.Utility;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneCreator
{
    private double xOffset = 0;
    private double yOffset = 0;

    /***
     * A function
     * @param fxmlPlace
     * @param undecorated
     */
    public void createScene(String fxmlPlace, String styleSheet, boolean undecorated, Object caller)
    {
        try {
            System.out.println(fxmlPlace);
            Parent root = FXMLLoader.load(caller.getClass().getResource(fxmlPlace));
            root.getStylesheets().add("file:UI/CSS-Files/main.css");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // if undecorated is true then there needs to be a way to move the window around.
            if (undecorated){
                stage.initStyle(StageStyle.UNDECORATED);
                //when the mouse button has been pressed it remembers the position of it has been pressed for the window.
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                //when the mouse is dragged it moves the scene around with the position of the mouse in mind.
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    }
                });
            }
            stage.show();
            System.out.println(stage + " Loaded and the scene : " + scene + "has loaded");
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happened : Error:0x009");
            alert.showAndWait();
        }
    }
}
