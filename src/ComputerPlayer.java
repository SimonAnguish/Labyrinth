/**
 * ComputerPlayer Class
 * Extends the Player Class and is the counterpart of the HumanPlayer class
 * Handles gameplay for the computer
 */
public class ComputerPlayer extends Player{
    
    int location[] = {0,0};
    
    /**
     * Constructor that takes the local UI object
     * @param ui UserInterface
     */
    public ComputerPlayer() {
    
    }
    
    /**
     * The takeTurn method
     * @param gm GameManager
     * @param board Board
     */
    @Override
    public void takeTurn(GameManager gm){
         System.out.println("*********************************");
         System.out.println("Computers Hand: " + getHand());
         System.out.println("Computers score: " + getScore());
         // Check for a winner
         if (!gm.checkForWinner()){
            // Have the user take a turn if no winner
            gm.user.takeTurn(gm);
         }else{
            gm.callWinScreen();
         }
    }    
}
