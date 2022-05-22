package BLL.Utility;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionListener;

public class Quistenmarks extends Frame implements ActionListener {
    public Button B;
    private TextField tf;
    private Frame f;
    public void OnSubCatFunk(ActionEvent actionEvent){

        f = new Frame();
        B = new Button("?");
        tf = new TextField(10);
        f.add(B);
        f.add(tf);
        B.addActionListener(this);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        String str = e.getActionCommand();
        tf.setText(str);
    }
}
