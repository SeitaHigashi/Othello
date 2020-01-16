package othello;

import othello.exception.CantPutException;
import othello.utils.Coordinate;

public class Test {
    public static void main(String[] args){
        Board board = new Board();

        board.printBoard();

        try {
            board.setDisk(new Coordinate(5, 4));
            board.printBoard();

            board.setDisk(new Coordinate(5, 3));
            board.printBoard();

            board.setDisk(new Coordinate(3, 2));
            board.printBoard();

            board.setDisk(new Coordinate(2, 3));
            board.printBoard();
        } catch (CantPutException e) {
            e.printStackTrace();
        }

    }
}
