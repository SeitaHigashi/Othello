package othello.player;

import othello.Othello;

import java.awt.event.MouseListener;

public abstract class Player implements MouseListener {

    protected Othello othello;

    protected int color;

    public Player(Othello othello, int color) {
        this.othello = othello;
        this.color = color;
    }

    public abstract void battle();

    public abstract void reset();
}
