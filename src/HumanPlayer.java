/**
 * HumanPlayer class
 * Handles gameplay for human user
 */
public class HumanPlayer extends Player {
    
    public int[] location = {6,6};
    
    /**
    * Constructor
    */
    public HumanPlayer() {

    }
    
    /**
     * The takeTurn method
     * @param gm GameManager
     * @param board Board
     */
    @Override
    public void takeTurn(GameManager gm,TilePanel handTile,TilePanel[][] boardPanels){
         System.out.println("*********************************");
         System.out.println("Your Hand: " + getHand());
         System.out.println("You reached your goal: " + getTopCard());
         removeTopCard();
         System.out.println("Your score: " + getScore());
         
         if ( gm.checkForWinner() ){
            gm.callWinScreen();
         }
        
    }
}
