package othello;

import othello.exception.CantPutException;
import othello.utils.Coordinate;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();

        board.printBoard();

        try {
            board.setDisk(new Coordinate(5, 4), Disk.BLACK);
            board.printBoard();

            board.setDisk(new Coordinate(5, 3), Disk.WHITE);
            board.printBoard();

            board.setDisk(new Coordinate(3, 2), Disk.BLACK);
            board.printBoard();

            board.setDisk(new Coordinate(2, 3), Disk.WHITE);
            board.printBoard();

            board.reset();
            board.printBoard();

        } catch (CantPutException e) {
            e.printStackTrace();
        }

    }
}
