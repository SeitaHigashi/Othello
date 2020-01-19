package othello.player;

import othello.Board;
import othello.Disk;
import othello.exception.CantPutException;
import othello.view.DiskView;

import java.awt.event.MouseEvent;

public class Human extends Player{
    public Human(Board board) {
        super(board);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DiskView diskView = (DiskView)e.getSource();
        try {
            Disk disk = this.board.setDisk(diskView.coordinate);
            diskView.setDisk(disk);
        } catch (CantPutException ex) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        DiskView diskView = (DiskView)e.getSource();
        if(this.board.canPut(diskView.coordinate, this.board.getTurn())){
            diskView.mouseEntered();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        DiskView diskView = (DiskView)e.getSource();
        if(this.board.canPut(diskView.coordinate, this.board.getTurn())){
            diskView.mouseExited();
        }
    }
}
