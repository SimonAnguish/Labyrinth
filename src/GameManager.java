/**
   @Author SeanKates
 * GameManager handles the gameplay of Labyrinth
 */

import java.util.*;

public class GameManager {
	// Yifan was here
   // Initialize some variables for the human + computer players, and gui
   public Player user, computer;
   public Board board;
   private GraphicUI gui;
   private Deck deck;
   
   // Constructor for GameManager that starts the game
   public GameManager() {

      // Initialize board, deck
      board = new Board();
      deck = new Deck();

      System.out.println("Welcome to Labyrinth, made for CS 205 by Yifan, Simon, and Sean");
   }
   
   public void run() {
      // Depending on user input, we either help, play, or exit the game
      System.out.println("Lets play Labyrinth.");
      
      // Create the instances of user and computer
      user = new HumanPlayer();
      computer = new ComputerPlayer();
      
      // Shuffle the deck
      deck.shuffle();
      
      // Deal hands to the user and the computer
      dealHands(user, computer);
      
      // Initialize a boolean to determine turns
      boolean player_turn = true;
      
      // While loop that is true to switch between player and computer turns
      while (true) {
         //If player_turn = true, then the player takes a turn
         //else, computer takes a turn
         if (player_turn) {
            System.out.println("\n"+"**************************************************"+"\n");
            System.out.println("Players Turn");
           
            // takeTurn function that takes this(which is the gameManager) and the board
            user.takeTurn(this, board);
           
            // update the boolean to reflect that it is now the computers turn
            player_turn = false;
           
         }else {
            System.out.println("\n"+"**************************************************"+"\n");
            System.out.println("Computers Turn:");
           
            // takeTurn function that takes this(which is the gameManager) and the board
            computer.takeTurn(this, board);
           
            // update the boolean to reflect that it is now the players turn
            player_turn = true;
         }
        
         // checks for a winner every turn
         if(checkForWinner(user, computer)){
            break;
         }
      }
   }
   
   /**
    * checkForWinner method checks both players hands to see if someone has won
    * @param user the user player
    * @param computer the computer player
    * @return true or false whether there is a winner
    */
   public boolean checkForWinner(Player user, Player computer){
      if (user.isHandEmpty() || computer.isHandEmpty()){
         System.out.println("**************************************************");
         System.out.println("User Score: " + user.score);
         System.out.println("Computer Score: " + computer.score);
         if (user.score > computer.score) {
            System.out.println("User won!");
         }else{
            System.out.println("Computer won!");
         }
         return true;
      }else{
         return false;
      }
   }
   
   /**
    * dealHands method deals out hands to two players
    * @param user the user player
    * @param computer the computer player
    */
   public void dealHands(Player user, Player computer){
      while (!deck.isEmpty()){
         user.addTreasure(deck.dealTreasure());
         computer.addTreasure(deck.dealTreasure());
      }
   }
}