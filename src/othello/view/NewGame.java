package othello.view;

import othello.Disk;
import othello.Othello;
import othello.player.DemoAI;
import othello.player.Human;
import othello.player.LANGame;
import othello.player.Player;

import javax.swing.*;
import java.awt.*;

public class NewGame extends JFrame {
    private Othello othello;

    public NewGame(Othello othello){
        this.othello = othello;
        setSize(200, 200);
        setLayout(new GridLayout(4,2));

        add(new Label("黒"));
        JComboBox jComboBox = new JComboBox();
        jComboBox.addItem(new Human(othello, Disk.BLACK));
        jComboBox.addItem(new DemoAI(othello, Disk.BLACK));
        jComboBox.addItem(new LANGame(othello, Disk.BLACK));

        add(jComboBox);

        setVisible(true);
    }
}
