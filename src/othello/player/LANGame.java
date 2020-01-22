package othello.player;

import othello.Disk;
import othello.Othello;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LANGame extends Player {
    public LANGame(Othello othello, int color) {
        super(othello, color);
        init();
    }

    private void init(){
        if(this.color == Disk.BLACK){
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                Socket socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
        }
    }

    @Override
    public void battle() {

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
