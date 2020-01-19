package othello;


import othello.exception.CantPutException;
import othello.utils.Arrow;
import othello.utils.Coordinate;

public class Board {
	private Disk[][] board = new Disk[8][8];

	private int turn = Disk.BLACK;

	private View view = null;

	public Board(){
		init();
	}

	private void init(){
	    this.board = new Disk[8][8];
		this.board[3][3] = new Disk(new Coordinate(3, 3),Disk.WHITE, this);
		this.board[4][4] = new Disk(new Coordinate(4, 4),Disk.WHITE, this);
		this.board[3][4] = new Disk(new Coordinate(3, 4),Disk.BLACK, this);
		this.board[4][3] = new Disk(new Coordinate(4, 3),Disk.BLACK, this);
	}

	public void setView(View view){
		this.view = view;
	}

	public Disk setDisk(Coordinate coordinate) throws CantPutException {
		Disk newDisk = new Disk(coordinate, turn, this);
		if(!canPut(coordinate, turn))
			throw new CantPutException();
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
		this.view.update();
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
			System.out.println();
		}
	}

	public boolean canPut(Coordinate coordinate, int state){
		if(getDisk(coordinate) != null)
		    return false;
	    for(Arrow arrow:Arrow.values()){
	    	try{
	    		Coordinate shiftCoordinate = coordinate.shift(arrow);
	    		Disk disk = this.getDisk(shiftCoordinate);
	    		if(disk.state != state)
	    		    while(true){
	    		    	shiftCoordinate = shiftCoordinate.shift(arrow);
	    		    	disk = this.getDisk(shiftCoordinate);
	    		    	if(disk.state == state)
							return true;
					}
			}catch (Exception e){
	    	    continue;
			}
		}
		return false;
	}

	public void reset(){
		init();
	}

}
