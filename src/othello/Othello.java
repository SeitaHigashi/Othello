package othello;

import othello.exception.CantPutException;
import othello.player.Player;
import othello.utils.Coordinate;
import othello.view.DiskView;
import othello.view.NewGame;
import othello.view.ResetButton;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Othello extends JFrame implements WindowListener {

    public Board board;

    public DiskView[][] diskViews = new DiskView[8][8];

    private JPanel othelloBoard;

    private int turn = Disk.BLACK;

    private Player blackPlayer;

    private Player whitePlayer;

    private Player nowPlayer;

    private Disk lastDisk;

    public Othello() {
        this.board = new Board();
        this.board.setOthello(this);
        init();
        new NewGame(this);
    }

    public static void main(String[] args) {
        new Othello();
    }

    private void init() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setSize(400, 400);
        setTitle("Othello");
        setLayout(new BorderLayout());
        add("South", new ResetButton(this));
        initOthelloBoard();

        setVisible(true);
        update();
    }

    private void initOthelloBoard() {
        JPanel othelloBoard = new JPanel();
        othelloBoard.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                diskViews[j][i] = new DiskView(new Coordinate(j, i));
                othelloBoard.add(diskViews[j][i]);
                Disk disk = this.board.getDisk(new Coordinate(j, i));
                if (disk != null)
                    diskViews[j][i].setDisk(disk);
            }
        }
        this.othelloBoard = othelloBoard;
        add("Center", othelloBoard);
    }

    public void setBlackPlayer(Player player) {
        this.blackPlayer = player;
    }

    public void setWhitePlayer(Player player) {
        this.whitePlayer = player;
    }

    public int getTurn() {
        return this.turn;
    }

    public Disk getLastDisk() {
        return this.lastDisk;
    }

    public Player getNowPlayer() {
        return this.nowPlayer;
    }

    public Player getNextPlayer() {
        return (this.nowPlayer == this.blackPlayer) ? this.whitePlayer : this.blackPlayer;
    }

    public DiskView getDiskView(Coordinate coordinate) {
        return this.diskViews[coordinate.x][coordinate.y];
    }

    public Disk setDisk(Coordinate coordinate) throws CantPutException {
        Disk disk = this.board.setDisk(coordinate, this.turn);
        getDiskView(coordinate).setDisk(disk);
        this.lastDisk = disk;
        return disk;
    }

    public void start() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                diskViews[j][i].addMouseListener(this.blackPlayer);
            }
        }
        this.nowPlayer = this.blackPlayer;
        this.blackPlayer.battle();
        this.update();
    }

    public void nextTurn() {
        turn *= -1;
        for (DiskView[] diskViews : this.diskViews) {
            for (DiskView diskView : diskViews) {
                diskView.removeMouseListener(getNowPlayer());
                diskView.addMouseListener(getNextPlayer());
            }
        }
        nowPlayer = getNextPlayer();
        boolean playerCanPut = this.board.canPut(this.turn);
        boolean enemyCanPut = this.board.canPut(-this.turn);
        if (!playerCanPut && !enemyCanPut)
            finish();
        else if (playerCanPut)
            nowPlayer.battle();
        else
            nextTurn();
        return;
    }

    public void update() {
        for (DiskView[] diskViews : diskViews) {
            for (DiskView diskView : diskViews) {
                try {
                    diskView.update();
                } catch (NullPointerException e) {
                    continue;
                }
            }
        }
    }

    public void reset() {
        this.turn = Disk.BLACK;
        this.board.reset();
        this.blackPlayer.reset();
        this.whitePlayer.reset();
        for (DiskView[] diskViews : diskViews) {
            for (DiskView diskView : diskViews) {
                diskView.removeDisk();
                try {
                    diskView.setDisk(this.board.getDisk(diskView.coordinate));
                } catch (NullPointerException e) {
                }
                diskView.removeMouseListener(nowPlayer);
            }
        }
        update();
        new NewGame(this);
    }

    public void finish() {
        int black = this.board.count(Disk.BLACK);
        int white = this.board.count(Disk.WHITE);
        String winner = (black == white) ? "引き分け" : (black < white) ? "白の勝ち" : "黒の勝ち";
        JOptionPane.showMessageDialog(
                this,
                "黒:" + black + "\n白:" + white,
                winner,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);
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
