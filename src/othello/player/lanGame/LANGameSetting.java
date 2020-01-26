package othello.player.lanGame;

import othello.player.LANGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LANGameSetting extends JFrame implements ActionListener {
    private LANGame lanGame;

    private JTextField jTextField;

    private JButton acceptButton;

    private static String lastAddress = "";

    public LANGameSetting(LANGame lanGame) {
        this.lanGame = lanGame;
        this.jTextField = new JTextField(LANGameSetting.lastAddress);
        this.acceptButton = new JButton("Accept");
        this.acceptButton.addActionListener(this);

        setSize(300, 100);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("相手IPアドレス:", JLabel.RIGHT));
        add(jTextField);
        add(new JPanel());
        add(acceptButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.lanGame.setServerAddress(jTextField.getText());
        LANGameSetting.lastAddress = jTextField.getText();
        new Thread(lanGame).start();
        setVisible(false);
    }
}
