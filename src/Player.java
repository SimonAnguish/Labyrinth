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
     * The addCard method ands a card object to the LinkedList hand
     * @param c The Card object to be added to the players hand
     */
    public void addCard(int c) {
         hand.add(c);
    }
    
    /**
     * The removeCard method removes a card from the LinkedList hand and updates the score
     * @param c The Card object to be removed from players hand
     */
    public void removeCard(int c) {
        hand.remove(c);
        score++;
    }
}
    
    
    
    
    

