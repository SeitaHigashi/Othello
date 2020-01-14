package othello;


import othello.utils.Arrow;
import othello.utils.Coordinate;

public class Board {
	private Disk[][] board = new Disk[8][8];

	public Board(){
	}

	public void setDisk(Coordinate coordinate, int state){
		Disk newDisk = new Disk(coordinate, state, this);
		board[coordinate.x][coordinate.y] = newDisk;
		for (Arrow arrow: Arrow.values()) {
			Coordinate shiftCoordinate = coordinate.shift(arrow);
			Disk arrowDisk = this.getDisk(shiftCoordinate);
			if(arrowDisk == null)continue;
			if(arrowDisk.state == newDisk.state)continue;
			arrowDisk.maybeTurn(arrow);
		}
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
