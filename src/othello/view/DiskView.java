package othello.view;

import othello.Disk;
import othello.utils.Coordinate;

import javax.swing.*;
import java.awt.*;

public class DiskView extends JButton {

    private Disk disk;

    public final Coordinate coordinate;

    public DiskView(Coordinate coordinate){
        super();
        this.coordinate = coordinate;
        init();
    }

    private void init(){
        setBackground(Color.GREEN);
    }

    public void setDisk(Disk disk){
        this.disk = disk;
        setText("●");
        setForeground((this.disk.state == Disk.BLACK)?
                Color.BLACK:
                Color.WHITE);
    }

    public void update() throws NullPointerException{
        setForeground((this.disk.state == Disk.BLACK)?
                Color.BLACK:
                Color.WHITE);
    }
}
