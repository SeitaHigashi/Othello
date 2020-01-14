package othello;


import othello.utils.Coordinate;

public class Disk {
	public static final int BLACK = 1;
	public static final int WHITE = -1;

	public int state;

	public final Coordinate coordinate;

	public Disk(Coordinate coordinate,int state){
		this.coordinate = coordinate;
		this.state = state;
	}

	public void turn(){
		this.state = - this.state;
	}
	public void maybeTurn(){

	}
}
