/**
 * HumanPlayer class
 * Handles gameplay for human user
 */
public class HumanPlayer extends Player {
    UserInterface ui;
    
    /**
    * Constructor that takes the local UI object
    * @param ui UserInterface
    */
    public HumanPlayer(UserInterface ui) {
        this.ui = ui;
    }
    
    /**
     * The takeTurn method
     * @param gm GameManager
     * @param board Board
     */
    @Override
    public void takeTurn(GameManager gm, Board board){
         ui.print(board.getTileAt(1,5).toString());
		   board.getTileAt(1,5).rotate(1);
    }

}
