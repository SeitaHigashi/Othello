package othello.player;

import othello.Disk;
import othello.Othello;
import othello.exception.CantPutException;
import othello.utils.Coordinate;
import othello.view.DiskView;

import java.awt.event.MouseEvent;

public class Human extends Player{
    public Human(Othello othello) {
        super(othello);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DiskView diskView = (DiskView)e.getSource();
        try {
            Disk disk = this.othello.setDisk(diskView.coordinate);
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
        if(this.othello.board.canPut(diskView.coordinate, this.othello.getTurn())){
            diskView.mouseEntered();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        DiskView diskView = (DiskView)e.getSource();
        if(this.othello.board.canPut(diskView.coordinate, this.othello.getTurn())){
            diskView.mouseExited();
        }
    }
}
