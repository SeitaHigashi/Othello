package othello;

import othello.exception.CantPutException;
import othello.utils.Coordinate;
import othello.view.DiskView;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class View extends JFrame implements Runnable, MouseListener, WindowListener {

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
        update();
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
        try{
            Disk disk = this.board.setDisk(diskView.coordinate);
            diskView.setDisk(disk);
            update();
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
        DiskView diskView = (DiskView) e.getSource();
        if(this.board.canPut(diskView.coordinate, board.getTurn())){
            diskView.mouseEntered();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        DiskView diskView = (DiskView) e.getSource();
        if(this.board.canPut(diskView.coordinate, board.getTurn())){
            diskView.mouseExited();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(1);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
