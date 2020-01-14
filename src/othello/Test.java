package othello;

import othello.utils.Coordinate;

public class Test {
    public static void main(String[] args){
        Board board = new Board();

        board.setDisk(new Coordinate(3, 3), Disk.WHITE);
        board.setDisk(new Coordinate(4, 4), Disk.WHITE);
        board.setDisk(new Coordinate(3, 4), Disk.BLACK);
        board.setDisk(new Coordinate(4, 3), Disk.BLACK);

        board.printBoard();

        board.setDisk(new Coordinate(5, 3), Disk.WHITE);
        board.printBoard();

        board.setDisk(new Coordinate(5, 2), Disk.BLACK);
        board.printBoard();
    }
}
