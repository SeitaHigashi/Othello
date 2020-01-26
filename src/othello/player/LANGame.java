package othello.player;

import othello.Disk;
import othello.Othello;
import othello.exception.CantPutException;
import othello.player.lanGame.LANGameMessage;
import othello.player.lanGame.LANGameSetting;
import othello.utils.Coordinate;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LANGame extends Player implements Runnable{
    private Socket socket;

    private String serverAddress = "localhost";

    public LANGame(Othello othello, int color) {
        super(othello, color);
        if(color == Disk.BLACK){
            Thread thread = new Thread(this);
            thread.start();
            try {
                JOptionPane.showMessageDialog(
                        this.othello,
                        "IPアドレス:"+InetAddress.getLocalHost().getHostAddress(),
                        "IPアドレス情報",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        else{
            new LANGameSetting(this);
        }
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
                    this.socket = new Socket(this.serverAddress, 8888);
                    System.out.println("connect server");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this.othello, "サーバと接続できませんでした");
                    new LANGameSetting(this);
                }
                break;
        }
    }

    public void setServerAddress(String address){
        this.serverAddress = address;
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
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(LANGameMessage.RESET);
        } catch (IOException e) {
        }
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

    @Override
    public void run() {
        init();
        try {
            while (true) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream((this.socket.getInputStream()));
                    Coordinate coordinate = (Coordinate) objectInputStream.readObject();
                    System.out.println("Recieve:" + coordinate.toString());
                    if (commandHelper(coordinate))
                        return;
                    Disk disk = this.othello.setDisk(coordinate);
                    this.othello.getDiskView(coordinate).setDisk(disk);
                    this.othello.update();
                    this.othello.nextTurn();
                    Thread.sleep(1000);
                } catch (CantPutException e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.othello, "接続が切れました");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.othello, "不明なエラーが発生しました");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean commandHelper(Coordinate coordinate){
        if(coordinate.equals(LANGameMessage.RESET)){
            try {
                this.socket.close();
                System.out.printf("Close");
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.othello.reset();
            return true;
        }
        else
            return false;
    }
}
