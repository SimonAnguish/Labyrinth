/**
   @Author SeanKates&&Simon&&Yifan
 * GameManager handles the gameplay of Labyrinth
 */

import java.util.*;

public class GameManager {
	// Yifan was here
   // Initialize some variables for the human + computer players, and gui
   public Player user, computer;
   private Deck deck;
   public Board board;
   
   // Initialize a boolean to determine turns
   public boolean player_turn;
   
   // Constructor for GameManager that starts the game
   public GameManager() {

      // Initialize deck, board, user, and computer, gui
      deck = new Deck();
      board = new Board();
      user = new HumanPlayer();
      computer = new ComputerPlayer();
      
      System.out.println("Welcome to Labyrinth, made for CS 205 by Yifan, Simon, and Sean");
   }
   
   public void run() {
      // Shuffle the deck and deal the hands
      deck.shuffle();
      dealHands(user, computer);
      
      // Initialize player_turn as true so the human starts
      player_turn = true;
   }
   /**
       * check if there exists a path to the destination
       * @param curPanel This is the place where player is
       * @param desPanel This is the place that the player wants to go
       * @param board
       * @return if there exists a path to the destination,then it returns true, otherwise returns false
       */
   public boolean canPlayerMove(TilePanel curPanel,TilePanel desPanel,Board board) {
   	   boolean[][] visited = new boolean[7][7];
   	   visited[curPanel.tileLocation[0]][curPanel.tileLocation[1]] = true;
   	   LinkedList<TilePanel> bfsQueue = new LinkedList<TilePanel>();
   	   Tile neiTile;
   	   int tileX;
   	   int tileY;
   	   bfsQueue.add(curPanel);
   	   while(bfsQueue.size()>0){
   		   TilePanel preTilePanel = bfsQueue.poll();
   		   if(preTilePanel.tileLocation[0]>0){
   			   tileX = preTilePanel.tileLocation[0]-1;
   			   tileY = preTilePanel.tileLocation[1];
   			   neiTile = board.getTileAt(tileX, tileY);
   			   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.WEST)){
   				   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   				   bfsQueue.add(tilePanel);
   				   visited[tileX][tileY] = true;
   			   }
					   
   		   }
   		   if(preTilePanel.tileLocation[0]<6){
   			   tileX = preTilePanel.tileLocation[0]+1;
   			   tileY = preTilePanel.tileLocation[1];
   			   neiTile = board.getTileAt(tileX, tileY);
   			   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.EAST)){
   				   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   				   bfsQueue.add(tilePanel);
   				   visited[tileX][tileY] = true;
   			   }
   		   }
   		   if(preTilePanel.tileLocation[1]>0){
   			   tileX = preTilePanel.tileLocation[0];
   			   tileY = preTilePanel.tileLocation[1]-1;
   			   neiTile = board.getTileAt(tileX, tileY);
   			   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.NORTH)){
   				   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   				   bfsQueue.add(tilePanel);
   				   visited[tileX][tileY] = true;
   			   }
					   
   		   }
   		   if(preTilePanel.tileLocation[1]<6){
   			   tileX = preTilePanel.tileLocation[0];
   			   tileY = preTilePanel.tileLocation[1]+1;
   			   neiTile = board.getTileAt(tileX, tileY);
   			   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.SOUTH)){
   				   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   				   bfsQueue.add(tilePanel);
   				   visited[tileX][tileY] = true;
   			   }
   		   }
		   
   	   }
   	   return visited[desPanel.tileLocation[0]][desPanel.tileLocation[1]];
      }
	  /**
	      * if there exists a path to the destination, move the player to destination
	      * @param curPanel This is the place where player is
	      * @param desPanel This is the place the player wants to go
	      * @param board
	      */
      public void moveCurrentPlayerTo(TilePanel curPanel,TilePanel desPanel,Board board) {
         if(canPlayerMove(curPanel, desPanel, board))
       	  desPanel.setPlayer();
         else
       	  System.out.println("You could not move to that place.");
      }
   
   
   /**
    * checkForWinner method checks both players hands to see if someone has won
    * @param user the user player
    * @param computer the computer player
    * @return true or false whether there is a winner
    */
   public boolean checkForWinner(){
      // once we determine the winner we are going to want to display that on the GUI and exit
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