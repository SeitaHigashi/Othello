package othello;

import othello.utils.Coordinate;

public class Othello {

	public View view;

	public Board board;

	public Othello(){
		this.board = new Board();


		this.view = new View(this.board);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new Othello();
	}

}
