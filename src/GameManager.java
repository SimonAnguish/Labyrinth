/**
	@Author SimonAnguish
*/

public class GameManager {
	public static void main(String[] args) {
		Board board = new Board();
		System.out.printf("%s", board.getTileAt(1,5).toString());
		board.getTileAt(1,5).rotate(1);
	}
}

// Testing out if Git works