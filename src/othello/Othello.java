package othello;

public class Othello {

	public View view;

	public Board board;

	public Othello(){
		this.board = new Board();

		this.view = new View(this.board);
	}

	public static void main(String[] args) {
		new Othello();
	}

}
