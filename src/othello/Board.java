package othello;


import othello.utils.Arrow;
import othello.utils.Coordinate;

public class Board {
	private Disk[][] board = new Disk[8][8];

	private int turn = Disk.BLACK;

	public Board(){
		setDisk(new Coordinate(3, 4));
		setDisk(new Coordinate(3, 3));
		setDisk(new Coordinate(4, 3));
		setDisk(new Coordinate(4, 4));
	}

	public Disk setDisk(Coordinate coordinate){
		Disk newDisk = new Disk(coordinate, turn, this);
		board[coordinate.x][coordinate.y] = newDisk;
		for (Arrow arrow: Arrow.values()) {
			try{
				Coordinate shiftCoordinate = coordinate.shift(arrow);
				Disk arrowDisk = this.getDisk(shiftCoordinate);
				if(arrowDisk.state == newDisk.state)continue;
				arrowDisk.maybeTurn(arrow);
			}catch(Exception e){
				continue;
			}
		}
		turn *= -1;
		return newDisk;
	}

	public int getTurn(){
		return this.turn;
	}

	public Disk getDisk(Coordinate coordinate) {
		return board[coordinate.x][coordinate.y];
	}

	public void printBoard(){
		for(int i=0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[j][i] == null)
					System.out.print("　");
				else if(board[j][i].state == Disk.BLACK)
					System.out.print("黒");
				else
					System.out.print("白");
			}
			System.out.println("");
		}
	}
}
