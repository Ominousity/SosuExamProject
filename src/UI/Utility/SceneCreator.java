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
     * @return
     */
    public Scene createScene(String fxmlPlace, String styleSheet, Object caller)
    {
        try {
            System.out.println(fxmlPlace);
            Parent root = FXMLLoader.load(caller.getClass().getResource(fxmlPlace));
            root.getStylesheets().add("file:" + styleSheet);
            Scene scene = new Scene(root);
            System.out.println(scene + "has loaded");
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happened : Error:0x009");
            alert.showAndWait();
        }
        return null;
    }

    public void createStage(Scene scene, String stageTitle, boolean undecorated){
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(stageTitle);
        if (undecorated){
            stage.initStyle(StageStyle.UNDECORATED);
            //when the mouse button has been pressed it remembers the position of it has been pressed for the window.
            scene.getRoot().setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            //when the mouse is dragged it moves the scene around with the position of the mouse in mind.
            scene.getRoot().setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
        }
        stage.show();
    }

    public void popupBox(Alert.AlertType type, String headerText, String context, ButtonType buttonType){
        Alert alert = new Alert(type, context, buttonType);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
