package othello;

import othello.utils.Coordinate;
import othello.view.DiskView;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame implements Runnable, MouseListener {

    public Board board;

    public DiskView[][] diskViews = new DiskView[8][8];

    public View(Board board){
        this.board = board;
        init();
    }

    private void init(){
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setSize(400, 400);
        setTitle("Othello");
        setLayout(new BorderLayout());
        add("Center", initOthelloBoard());


        setVisible(true);
    }

    private JPanel initOthelloBoard(){
        JPanel othelloBoard = new JPanel();
        othelloBoard.setLayout(new GridLayout( 8, 8));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                diskViews[j][i] = new DiskView(new Coordinate(j, i));
                diskViews[j][i].addMouseListener(this);
                othelloBoard.add(diskViews[j][i]);
                Disk disk = this.board.getDisk(new Coordinate(j, i));
                if(disk != null)
                    diskViews[j][i].setDisk(disk);
            }
        }
        return othelloBoard;
    }

    public void update(){
        for (DiskView[] diskViews:diskViews) {
            for (DiskView diskView:diskViews){
                try{
                    diskView.update();
                }catch (NullPointerException e){
                    continue;
                }
            }
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DiskView diskView = (DiskView) e.getSource();
        Disk disk = this.board.setDisk(diskView.coordinate);
        diskView.setDisk(disk);
        update();
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
