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
        setText("");
        setBackground(Color.GREEN);
        setMargin(new Insets(0, 0, 0, 0));
    }

    public void setDisk(Disk disk) throws NullPointerException{
        if(disk == null)throw new NullPointerException();
        this.disk = disk;
        setText("●");
        setForeground((this.disk.state == Disk.BLACK)?
                Color.BLACK:
                Color.WHITE);
    }

    public void removeDisk(){
        this.disk = null;
        init();
    }

    public void update() throws NullPointerException{
        setFont(new Font("Arial", Font.PLAIN, (int)(getHeight()*1.5)));
        setForeground((this.disk.state == Disk.BLACK)?
                Color.BLACK:
                Color.WHITE);
    }

    public void mouseEntered(){
        setText("●");
        setForeground(Color.ORANGE);
    }

    public void mouseExited(){
        setText("");
    }

}
