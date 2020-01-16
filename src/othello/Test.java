package othello;

import othello.utils.Coordinate;

public class Test {
    public static void main(String[] args){
        Board board = new Board();

        board.setDisk(new Coordinate(3, 4));
        board.setDisk(new Coordinate(3, 3));
        board.setDisk(new Coordinate(4, 3));
        board.setDisk(new Coordinate(4, 4));

        board.printBoard();

        board.setDisk(new Coordinate(5, 4));
        board.printBoard();

        board.setDisk(new Coordinate(5, 3));
        board.printBoard();

        board.setDisk(new Coordinate(3, 2));
        board.printBoard();

        board.setDisk(new Coordinate(2, 3));
        board.printBoard();

    }
}
