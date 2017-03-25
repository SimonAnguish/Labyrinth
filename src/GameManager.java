/**
	@Author SeanKates
 * GameManager handles the gameplay of Labyrinth
 */

import java.util.LinkedList;
import java.util.Arrays;

public class GameManager {
   
   private GraphicUI gui;

   // Initialize some variables for the UI, and the human + computer players
   private UserInterface ui;
   private Player user, computer;
   
   // Constructor for GameManager that starts the game
   public GameManager() {

      // ADDING GUI

      Board b = new Board();

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
                  
                  // update the boolean to reflect that it is now the computers turn
                  player_turn = false;
                  
               } else {
                  ui.print("\n"+"**************************************************"+"\n");
                  ui.print("Computers Turn:");
                  
                  // takeTurn function that takes this(which is the gameManager) and the board
                  computer.takeTurn(this, board);
                  
                  // update the boolean to reflect that it is now the players turn
                  player_turn = true;
               }
               
               // Need to have an if statement at the end of every turn to determine
               // if the game is over
               /* if (objective is reached)
               *     break
               */
               String[] answers = {"yes", "no"};
               LinkedList<String> keep_going  = new LinkedList<>(Arrays.asList(answers));
               String choice = ui.prompt("Do you want to keep going? (yes, no): ", keep_going);
               
               if (choice.equals(keep_going.get(1))) {
                  break;
               }
            }   
         } else if (starting_choice.equals(starting_options.get(2))) {
            break;
         }
      }
   }
   
   /**
   * creates a GameManager class to kick off the game
   */
	public static void main(String[] args) {
      new GameManager();
	}
}