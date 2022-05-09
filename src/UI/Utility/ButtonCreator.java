package UI.Utility;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonCreator extends Button
{
    private final int transtionTime = 100;
    private final float transtionScaleSize = 0.98f;
    private final float transtionenReset = 1f;

    public Button createButtons(boolean Animations, double Height, double Width, int margin1, int margin2, int margin3 ,int margin4, Pos pos, String css, String id){
        Button button = new Button();
        if(true){
            if (Animations){
                animation(button);
            }
            button.setMinHeight(Height);
            button.setMinWidth(Width);
            button.setPadding(new Insets(margin1, margin2, margin3, margin4));
            button.setAlignment(pos);
            button.getStyleClass().add(css);
            button.setId(id);
        } else {
            new Exception("There needs to be at least 4 posstionens");
        }
        return button;
    }

    private void animation(Button button){
        ScaleTransition scaleTransitionIN = new ScaleTransition();
        scaleTransitionIN.setDuration(Duration.millis(transtionTime));
        scaleTransitionIN.setNode(button);
        scaleTransitionIN.setToY(transtionScaleSize);
        scaleTransitionIN.setToX(transtionScaleSize);
        ScaleTransition scaleTransitionOUT = new ScaleTransition();
        scaleTransitionOUT.setDuration(Duration.millis(transtionTime));
        scaleTransitionOUT.setNode(button);
        scaleTransitionOUT.setToY(transtionenReset);
        scaleTransitionOUT.setToX(transtionenReset);
        button.setOnMouseEntered(e -> scaleTransitionIN.playFromStart());
        button.setOnMouseExited(e -> scaleTransitionOUT.playFromStart());
    }
}
