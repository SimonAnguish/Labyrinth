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
    public HumanPlayer() {

    }
    
    /**
     * The takeTurn method
     * @param gm GameManager
     * @param board Board
     */
    @Override
    public void takeTurn(GameManager gm, Board board){
         System.out.println("Hand: " + getHand());
         System.out.println("Goal: " + getTopCard());
         removeTopCard();
    }

}
