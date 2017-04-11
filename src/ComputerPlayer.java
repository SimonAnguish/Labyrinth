/**
 * ComputerPlayer Class
 * Extends the Player Class and is the counterpart of the HumanPlayer class
 * Handles gameplay for the computer
 */
public class ComputerPlayer extends Player{
    UserInterface ui;
    
    /**
     * Constructor that takes the local UI object
     * @param ui UserInterface
     */
    public ComputerPlayer(UserInterface ui) {
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
         System.out.println("Computers Hand: " + getHand());
    }    
}
