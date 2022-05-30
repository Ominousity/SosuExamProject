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

    /**
     * createButton creates a JavaFX button with the given parameters.
     * @param Animations
     * @param Height
     * @param Width
     * @param margin1
     * @param margin2
     * @param margin3
     * @param margin4
     * @param pos
     * @param css
     * @param id
     * @param text
     * @return JavaFX button
     */
    public Button createButtons(boolean Animations, double Height, double Width, int margin1, int margin2, int margin3, int margin4,
                                Pos pos, String css, String id, String text){
        Button button = new Button(text);
        if (Animations){
            animation(button);
        }
        button.setMinHeight(Height);
        button.setMinWidth(Width);
        button.setPadding(new Insets(margin1, margin2, margin3, margin4));
        button.setAlignment(pos);
        button.getStyleClass().add(css);
        button.setId(id);
        return button;
    }

    /**
     * Animation gives the given JavaFX button animation with a scale transition that scales the button with the transtionScaleSize.
     * @param button
     */
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
