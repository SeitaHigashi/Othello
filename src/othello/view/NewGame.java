package othello.view;

import othello.Disk;
import othello.Othello;
import othello.player.DemoAI;
import othello.player.Human;
import othello.player.LANGame;
import othello.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame extends JFrame implements ActionListener {
    private Othello othello;

    private JComboBox blackBox;

    private JComboBox whiteBox;

    private JButton startButton;

    public NewGame(Othello othello) {
        this.othello = othello;
        this.blackBox = new JComboBox();
        this.whiteBox = new JComboBox();
        this.startButton = new JButton("Start");
        this.startButton.addActionListener(this);

        initFrame();

        setVisible(true);
    }

    private void initFrame() {
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        initComboBox();

        add(new Label("黒", JLabel.CENTER));
        add(this.blackBox);
        add(new Label("白", JLabel.CENTER));
        add(this.whiteBox);
        add(new JPanel());
        add(this.startButton);

    }

    private void initComboBox() {
        String[] select = {"プレイヤー", "BOT", "LAN対戦者"};
        for (String str : select) {
            this.blackBox.addItem(str);
            this.whiteBox.addItem(str);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player blackPlayer;
        switch (this.blackBox.getSelectedIndex()) {
            case 0:
                blackPlayer = new Human(othello, Disk.BLACK);
                break;
            case 1:
                blackPlayer = new DemoAI(othello, Disk.BLACK);
                break;
            case 2:
                blackPlayer = new LANGame(othello, Disk.BLACK);
                break;
            default:
                return;
        }
        Player whitePlayer;
        switch (this.whiteBox.getSelectedIndex()) {
            case 0:
                whitePlayer = new Human(othello, Disk.WHITE);
                break;
            case 1:
                whitePlayer = new DemoAI(othello, Disk.WHITE);
                break;
            case 2:
                whitePlayer = new LANGame(othello, Disk.WHITE);
                break;
            default:
                return;
        }
        othello.setBlackPlayer(blackPlayer);
        othello.setWhitePlayer(whitePlayer);
        othello.start();
        setVisible(false);
    }
}
