/**
   @Author SeanKates&&Simon&&Yifan
 * GameManager handles the gameplay of Labyrinth
 */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.List;

public class GameManager extends JFrame {
   // Initialize some variables for the human + computer players, and gui
   public Player user, computer;
   private Deck deck;
   public Board board;
   
   final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 650;
	final int BOARD_WIDTH = 470;
   
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
      dealHands(user, computer);
   }
   
   /**
    * check if there exists a path to the destination
    * @param curPanel This is the place where player is
    * @param desPanel This is the place that the player wants to go
    * @param board
    * @return if there exists a path to the destination,then it returns true, otherwise returns false
    */
   public boolean canPlayerMove(TilePanel curPanel,TilePanel desPanel) {
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
   		   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.NORTH)){
   			   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   			   bfsQueue.add(tilePanel);
   			   visited[tileX][tileY] = true;
   		   }
   			   
   	   }
   	   if(preTilePanel.tileLocation[0]<6){
   		   tileX = preTilePanel.tileLocation[0]+1;
   		   tileY = preTilePanel.tileLocation[1];
   		   neiTile = board.getTileAt(tileX, tileY);
   		   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.SOUTH)){
   			   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   			   bfsQueue.add(tilePanel);
   			   visited[tileX][tileY] = true;
   		   }
   	   }
   	   if(preTilePanel.tileLocation[1]>0){
   		   tileX = preTilePanel.tileLocation[0];
   		   tileY = preTilePanel.tileLocation[1]-1;
   		   neiTile = board.getTileAt(tileX, tileY);
   		   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.WEST)){
   			   TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
   			   bfsQueue.add(tilePanel);
   			   visited[tileX][tileY] = true;
   		   }
   			   
   	   }
   	   if(preTilePanel.tileLocation[1]<6){
   		   tileX = preTilePanel.tileLocation[0];
   		   tileY = preTilePanel.tileLocation[1]+1;
   		   neiTile = board.getTileAt(tileX, tileY);
   		   if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.EAST)){
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
    */
   public void moveCurrentPlayerTo(TilePanel curPanel,TilePanel desPanel) {
      if(canPlayerMove(curPanel, desPanel))
        desPanel.setPlayer(user);
      else
        System.out.println("You could not move to that place.");
   }
   
   public void movePlayerTo(Player p, TilePanel moveHere) {
      board.getTileAt(p.location[0],p.location[1]).clearPlayers();
      moveHere.setPlayer(p);
      System.out.println(p.getTopCard().getValue());
      System.out.println(moveHere.tile.treasure.getValue());
      if (p.getTopCard().getValue() == moveHere.tile.treasure.getValue()){
         moveHere.tile.treasure.setValue(0);
         p.removeTopCard();
      }
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
   
   // Rough version of a win screen
   // With the gameloop logic I couldnt get this to work in GraphicUI
   // Need to quit the game when there is a winner too
   void callWinScreen() {
		JFrame winFrame = new JFrame();
		JPanel wrapperPanel = new JPanel();
		
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));
		wrapperPanel.setBorder(new EmptyBorder(100,100,100,100));

		JLabel winLabel = new JLabel("We Have A Winner!");
		winLabel.setFont(new Font("Sans-Serif", Font.BOLD, 48));
      
      JLabel userScoreLabel = new JLabel("User Score: " + user.getScore());
		userScoreLabel.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      
      JLabel computerScoreLabel = new JLabel("Computer Score: " + computer.getScore());
		computerScoreLabel.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      
      // There has got to be a better way to do this
      JButton exit = new JButton("Exit");
      ActionListener al=new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         System.exit(0);
         }
      };
      exit.addActionListener(al);
      
      wrapperPanel.add(exit);
		wrapperPanel.add(winLabel);
      wrapperPanel.add(userScoreLabel);
      wrapperPanel.add(computerScoreLabel);
      
		winFrame.add(wrapperPanel, BorderLayout.CENTER);
		winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winFrame.pack();

		winFrame.setLocation(100, 50);
		winFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		winFrame.setResizable(false);
		winFrame.setVisible(true);
	}
}