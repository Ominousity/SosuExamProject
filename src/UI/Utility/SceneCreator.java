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


    /**
     * funktion that creates and returns a JavaFX Scene sorrounded by a try catch to get nearly every error.
     * @param fxmlPlace
     * @param styleSheet
     * @param caller
     * @return
     */
    public Scene createScene(String fxmlPlace, String styleSheet, Object caller)
    {
        try {
            System.out.println(fxmlPlace);
            Parent root = FXMLLoader.load(caller.getClass().getResource(fxmlPlace));
            root.getStylesheets().add(styleSheet);
            Scene scene = new Scene(root);
            System.out.println(scene + "has loaded");
            return scene;
        } catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "" + e, ButtonType.OK);
            alert.setHeaderText("Ohh no an Error happened : Error:0x009");
            alert.showAndWait();
        }
        return null;
    }

    /**
     * creates a stage with the given scene. if the boolean undecorated is true, windows standart buttons
     * will be gone and the window can be draged around with the mouse when clicked and holed everywhere
     * @param scene
     * @param stageTitle
     * @param undecorated
     */
    public void createStage(Scene scene, String stageTitle, boolean undecorated){
        Stage stage = new Stage();
        System.out.println(scene);
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
        stage.setScene(scene);
        stage.show();
    }

    /**
     * creates an alert with the given type and buttontype.
     * @param type
     * @param headerText
     * @param context
     * @param buttonType
     * @return
     */
    public Alert popupBox(Alert.AlertType type, String headerText, String context, ButtonType buttonType){
        Alert alert = new Alert(type, context, buttonType);
        alert.setHeaderText(headerText);
        return alert;
    }
}
