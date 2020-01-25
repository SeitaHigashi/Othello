package othello.player;

import othello.Disk;
import othello.Othello;
import othello.exception.CantPutException;
import othello.utils.Coordinate;
import othello.view.DiskView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LANGame extends Player implements Runnable{
    private Socket socket;

    public LANGame(Othello othello, int color) {
        super(othello, color);
        Thread thread = new Thread(this);
        thread.start();
    }

    private void init(){
        switch (this.color){
            case Disk.BLACK:
                try {
                    ServerSocket serverSocket = new ServerSocket(8888);
                    this.socket = serverSocket.accept();
                    System.out.println("connect clinet");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Disk.WHITE:
                try {
                    this.socket = new Socket("localhost", 8888);
                    System.out.println("connect server");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void battle() {
        try {
            Disk disk = this.othello.getLastDisk();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(disk.coordinate);
            System.out.println("Send:"+disk.coordinate.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        DiskView diskView = (DiskView)e.getSource();
//        try{
//            Disk disk = this.othello.setDisk(diskView.coordinate);
//            diskView.setDisk(disk);
//            this.othello.update();
//            this.othello.nextTurn();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
//            objectOutputStream.writeObject(disk.coordinate);
//            System.out.println("Send:"+disk.coordinate.toString());
//        } catch (CantPutException ex){
//        } catch (IOException ex) {
//            System.out.println("could not send");
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        DiskView diskView = (DiskView)e.getSource();
//        if(this.othello.board.canPut(diskView.coordinate, this.othello.getTurn())){
//            diskView.mouseEntered();
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        DiskView diskView = (DiskView)e.getSource();
//        if(this.othello.board.canPut(diskView.coordinate, this.othello.getTurn())){
//            diskView.mouseExited();
//        }

    }

    @Override
    public void run() {
        init();
        while(true){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream((this.socket.getInputStream()));
                Coordinate coordinate = (Coordinate)objectInputStream.readObject();
                System.out.println("Recieve:"+coordinate.toString());
                //if(this.othello.getTurn() != this.color)
                    //continue;
                Disk disk = this.othello.setDisk(coordinate);
                this.othello.getDiskView(coordinate).setDisk(disk);
                this.othello.update();
                this.othello.nextTurn();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (CantPutException e) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
