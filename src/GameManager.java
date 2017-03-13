/**
	@Author SimonAnguish
*/

public class GameManager {

   private Player user, computer;
    
	public static void main(String[] args) {
      UserInterface ui = new UserInterface();
		Board board = new Board();
		ui.print(board.getTileAt(1,5).toString());
		board.getTileAt(1,5).rotate(1);
	}
}

// Testing out if Git works