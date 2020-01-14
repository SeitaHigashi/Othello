package othello;


import othello.utils.Arrow;
import othello.utils.Coordinate;

public class Disk {
	public static final int BLACK = 1;
	public static final int WHITE = -1;

	public int state;

	public final Coordinate coordinate;

	public final Board board;

	public Disk(Coordinate coordinate, int state, Board board){
		this.coordinate = coordinate;
		this.state = state;
		this.board = board;
	}

	public void turn(){
		this.state = - this.state;
	}
	public boolean maybeTurn(Arrow arrow){
		try{
			Coordinate shiftCoordinate = coordinate.shift(arrow);
			Disk nextDisk = this.board.getDisk(shiftCoordinate);
			if(nextDisk.state != this.state){
			    this.turn();
				return true;
			}
			Boolean canTurn = nextDisk.maybeTurn(arrow);
			if(canTurn)
				this.turn();
			return canTurn;
		}catch(Exception e){
			return false;
		}
	}
}
