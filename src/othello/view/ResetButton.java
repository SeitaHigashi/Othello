package othello.view;

import othello.Othello;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton implements ActionListener {
    private Othello othello;

    public ResetButton(Othello othello) {
        super("Reset(新規ゲーム)");
        this.othello = othello;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.othello.reset();
    }
}
