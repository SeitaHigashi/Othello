package othello.player;

import othello.Board;

import java.awt.event.MouseListener;

public abstract class Player implements MouseListener {
    protected Board board;
    public Player(Board board){
        this.board = board;
    }
}
