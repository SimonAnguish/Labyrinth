import java.util.LinkedList;

/**
 * Player superclass for both human and computer players
 */
public class Player {
    
    protected LinkedList<Integer> hand;
    protected int score = 0;
    
    /**
     * Constructor
     */
    public Player() {
         hand = new LinkedList<Integer>();
    }
    
    /**
    * The takeTurn method
    * @param gm GameManager
    * @param board Board
    */
    public void takeTurn(GameManager gm, Board board) {
        
    }    
    
    /**
     * The isHandEmpty method checks whether or not the players hand is empty
     * @return true or false
     */
    public boolean isHandEmpty() {
        if (!hand.isEmpty()) { 
            return false;
        }else {
            return true;
        }
    }
    
    /**
     * The getScore method returns the players score
     * @return score The players score
     */
    public int getScore(){
        return score;
    }
    
    /**
     * The getHand method returns the LinkedList hand
     * @return LinkedList The players hand
     */
    public LinkedList<Integer> getHand() {
        return hand;
    }
    
    /**
     * The addCard method adds a "card" to the LinkedList hand
     * @param c The int to be added to the players hand
     */
    public void addCard(int c) {
         hand.add(c);
    }
    
    /**
     * The removeTopCard method removes a the first card from the hand
     * Should be called when the user reaches a specific goal/destination
     * Updates the score of the user/computer when they remove the card
     */
    public void removeTopCard() {
        hand.remove();
        score++;
    }
    
    /**
     * The getTopCard method gets the first card from the hand
     * This will return the top card, which is the current goal to move to
     * @return goal The top card/goal to travel to.
     */
    public int getTopCard() {
        int goal = hand.getFirst();
        return goal;
    }
}
    
    
    
    
    

