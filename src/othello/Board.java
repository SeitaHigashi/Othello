package othello;


import othello.utils.Coordinate;

public class Board {
	private Disk[][] board = new Disk[8][8];

	public Board(){
	}

	public void setDisk(Coordinate coordinate){
		board[coordinate.x][coordinate.y] = new Disk(coordinate);
	}

	public Disk getDisk(Coordinate coordinate) {
		return board[coordinate.x][coordinate.y];
	}
}
