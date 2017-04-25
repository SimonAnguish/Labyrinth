/**
   @Author SeanKates
 * Deck is the deck of treasures
 */
 
import java.util.Random;
public class Deck {
   
   // Initialize the number of treasures, and a few other variables
   public final static int CARDS_IN_DECK = 24;
   private Treasure[] deck;
   
   // The current amount of cards in deck
   private int ct;
   
   /**
    * Constructor that calls for a fresh deck (it will still need to be shuffled)
    */
   public Deck()
   {
      freshDeck();
      shuffle();
   }
   
   /**
    * freshDeck() method creates a new Deck at the specified size and fills it with Treasures
    */
   public void freshDeck()
   {
      deck = new Treasure[CARDS_IN_DECK];
      int top = 0;
      for(int i=1; i<=24; i++) {
         deck[top] = new Treasure(i);
         ct++;
         top++;
      }     
   }
   
   /** 
     * Remove and return the top Treasure on the Deck
     * @return A reference to a Treasure that was top on the Deck
     */
   public Treasure dealTreasure()
   {
      ct--;
      return deck[ct];
   }
   
   /** 
     * Randomize the order of Treasures in Deck
     */
   public void shuffle()
   {
      int randNum;
      Treasure temp;
      Random r = new Random();
      for (int i = 0; i < ct; i++)
      {
         randNum = r.nextInt(ct);
         temp = deck[i];
         deck[i]=deck[randNum];
         deck[randNum]=temp;
      }
   }
   
   /** 
     * Determine if Deck is empty
     * @return true if there are no more cards, false otherwise
     */
   public boolean isEmpty()
   {
      return (ct == 0);
   }
}

