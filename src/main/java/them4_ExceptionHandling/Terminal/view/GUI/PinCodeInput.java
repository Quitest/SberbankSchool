package them4_ExceptionHandling.Terminal.view.GUI;

import them4_ExceptionHandling.Terminal.PinValidator;
import them4_ExceptionHandling.Terminal.controller.TerminalImpl;
import them4_ExceptionHandling.Terminal.model.TerminalServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class PinCodeInput extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField pinCodeField;
    private JLabel labelInfo;
    private JLabel labelTitle;
    private int enteredPin;
    private final TerminalImpl terminal;



    public PinCodeInput() {
        terminal = new TerminalImpl(new TerminalServer(), new PinValidator());
        setContentPane(contentPane);
//        setModal(true);
        setModalityType(ModalityType.DOCUMENT_MODAL);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pinCodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                labelInfo.setText(e.getKeyChar() + "");
                try {
                    enteredPin = enteredPin * 10 + PinValidator.charValidate(e.getKeyChar() + "");
//                    System.out.println("Pin="+enteredPin);
                } catch (NumberFormatException exception) {
                    //удаляем последний введеный символ
                    char[] pin = pinCodeField.getPassword();
                    pin = Arrays.copyOfRange(pin, 0, pin.length - 1);
                    pinCodeField.setText("");
                    pinCodeField.setText(new String(pin));
//                    labelInfo.setText(exception.getMessage());
                    Arrays.fill(pin, '0'); //элемент безопасности
                }
            }
        });
    }

    public static void main(String[] args) {
        PinCodeInput dialog = new PinCodeInput();
//        dialog.pack();
        dialog.setSize(400, 200);
        dialog.setVisible(true);

        System.exit(0);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onOK() {
        // add your code here
        if (terminal.loginToTerminalServer(enteredPin)) {
//            labelInfo.setText("Pin верный");
            MainForm mainForm = new MainForm("Управление счетом");
            mainForm.setSize(400, 200);
            mainForm.setVisible(true);
        } else {
            labelInfo.setText("Pin неверный");
        }

        //элементы безопасности
        pinCodeField.setText("");
        enteredPin = 0;

        dispose();
    }
}
