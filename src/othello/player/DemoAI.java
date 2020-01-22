package othello.player;

import othello.Othello;
import othello.exception.CantPutException;
import othello.utils.Coordinate;

import java.awt.event.MouseEvent;

public class DemoAI extends Player {
    public DemoAI(Othello othello, int color) {
        super(othello, color);
    }

    @Override
    public void battle() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Coordinate coordinate = new Coordinate(j, i);
                if(this.othello.board.canPut(coordinate, this.othello.getTurn())){
                    try {
                        this.othello.setDisk(coordinate);
                        this.othello.update();
                        this.othello.nextTurn();
                        return;
                    } catch (CantPutException e) {
                    }
                }
            }
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
