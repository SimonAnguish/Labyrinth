/**
   @Author SeanKates
 * GameManager handles the gameplay of Labyrinth
 */

import java.util.*;

public class GameManager {

   // Initialize some variables for the UI, and the human + computer players
   private UserInterface ui;
   private Player user, computer;
   private Board b;
   private GraphicUI gui;
   
   // Constructor for GameManager that starts the game
   public GameManager() {

      // Initialize board, gui,
      b = new Board();
      gui = new GraphicUI(b);
      
      // Create a UI to print stuff and take input
      ui = new UserInterface();
      ui.print("Welcome to Labyrinth, made for CS 205 by Yifan, Simon, and Sean");
      
      // Loop to manage the game options, allows for help, play and exit
      while (true) {
         
         // Get input from the user
         String[] starting_option_arr = {"help", "play", "exit"};
         LinkedList<String> starting_options = new LinkedList<>(Arrays.asList(starting_option_arr));
         String starting_choice = ui.prompt("What do you want to do? (help, play, or exit): ", starting_options);
         
         // Depending on user input, we either help, play, or exit the game
         if (starting_choice.equals(starting_options.get(0))) {
            ui.print("----------------------------------------------------------");
            ui.print("We should probably have a help page for usability.");
            ui.print("----------------------------------------------------------" + "\n");
         } else if (starting_choice.equals(starting_options.get(1))) {
            ui.print("Lets play Labyrinth.");
            
            // Create the instances of user and computer
            user = new HumanPlayer(ui);
            computer = new ComputerPlayer(ui);
            
            // Deal hands to the user and the computer
            dealHands(user, computer);
            
            // Initialize the board
            Board board = new Board();
            
            // Initialize a boolean to determine turns
            boolean player_turn = true;
            
            // While loop that is true to switch between player and computer turns
            while (true) {
               
               // If player_turn = true, then the player takes a turn
               // else, computer takes a turn
               if (player_turn) {
                  ui.print("\n"+"**************************************************"+"\n");
                  ui.print("Players Turn");
                  
                  // takeTurn function that takes this(which is the gameManager) and the board
                  user.takeTurn(this, board);
                  
                  System.out.println("Hand: " + user.getHand());
                  System.out.println("Goal: " + user.getTopCard());
                  user.removeTopCard();
                  
                  // update the boolean to reflect that it is now the computers turn
                  player_turn = false;
                  
               } else {
                  ui.print("\n"+"**************************************************"+"\n");
                  ui.print("Computers Turn:");
                  
                  // takeTurn function that takes this(which is the gameManager) and the board
                  computer.takeTurn(this, board);
                  
                  System.out.println(computer.getHand());
                  
                  // update the boolean to reflect that it is now the players turn
                  player_turn = true;
               }
               
               /* Checks to see if either players hands are empty, if either are empty,
                * print the score and break from the game loop
                */
               if (user.isHandEmpty() || computer.isHandEmpty()){
                  System.out.println("**************************************************");
                  System.out.println("User Score: " + user.score);
                  System.out.println("Computer Score: " + computer.score);
                  if (user.score > computer.score) {
                     System.out.println("User won!");
                  }else{
                     System.out.println("Computer won!");
                  }
                  break;
               }
            }   
         } else if (starting_choice.equals(starting_options.get(2))) {
            break;
         }
      }
   }
   
   /**
    * dealHands method deals out hands to two players
    * @param user the user player
    * @param computer the computer player
    */
   public void dealHands(Player user, Player computer){
      int index;
      List<Integer> deck = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
      Random rand = new Random();
      while (!deck.isEmpty()){
         // Randomly add card to users hand and remove it from the deck
         index = rand.nextInt(deck.size());
         user.addCard(deck.get(index));
         deck.remove(index);
         
         // Randomly add card to computers hand and remove it from the deck
         index = rand.nextInt(deck.size());
         computer.addCard(deck.get(index));
         deck.remove(index);
      }
   }
   
   /**
   * creates a GameManager class to kick off the game
   */
	public static void main(String[] args) {
      new GameManager();
	}
}