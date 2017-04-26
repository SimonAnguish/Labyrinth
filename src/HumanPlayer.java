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
    public void takeTurn(GameManager gm, TilePanel[][] boardPanels){
         System.out.println("*********************************");
         System.out.println("Your Hand: " + getHand());
         
         if ( gm.checkForWinner() ){
            gm.callWinScreen();
         }
        
    }
}
