package othello.player;

import othello.Othello;

import java.awt.event.MouseListener;

public abstract class Player implements MouseListener {
    protected Othello othello;
    public Player(Othello othello){
        this.othello = othello;
    }

    public abstract void battle();

    public abstract void reset();
}
