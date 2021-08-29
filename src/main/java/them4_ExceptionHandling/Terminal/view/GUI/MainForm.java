package them4_ExceptionHandling.Terminal.view.GUI;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    public MainForm(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        PinCodeInput pinCodeInput = new PinCodeInput();
        pinCodeInput.setSize(400, 200);
        pinCodeInput.setVisible(true);
    }

    public static void main(String[] args) {
        MainForm mainForm = new MainForm("Управление счетом");

    }
}
