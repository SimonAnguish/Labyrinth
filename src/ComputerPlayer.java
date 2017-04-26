/**
 * ComputerPlayer Class
 * Extends the Player Class and is the counterpart of the HumanPlayer class
 * Handles gameplay for the computer
 */
import java.util.*;

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
    public void takeTurn(GameManager gm, TilePanel[][] boardPanels){
      System.out.println("*********************************");
      System.out.println("Computers Hand: " + getHand());
      System.out.println("Computers score: " + getScore());
 		int rotateNo;
 		TilePanel curPanel = null;
 		TilePanel desPanel = null;
 		rotateNo = (int)Math.random()*3+1;
 		for(int i = 0;i<rotateNo;i++){
 			gm.board.tileInHand.rotate(1);
 		}
 		System.out.println("The tile in hand rotated "+rotateNo +"times.");
 		int randomDir;
 		int randomCol;
 		int randomRow;
 		randomDir = (int)Math.random()*4;
 		switch(randomDir){
 		case 0:
 			randomCol = (int)Math.random()*5+1;
 			if(randomCol % 2 ==0 )
 				randomCol = randomCol+1;
 			gm.board.insertTile("down", 0, randomCol);
 			
 			break;
 		case 1:
 			randomCol = (int)Math.random()*5+1;
 			if(randomCol % 2 ==0 )
 				randomCol = randomCol+1;
 			gm.board.insertTile("up", 6, randomCol);
 			break;
 		case 2:
 			randomRow = (int)Math.random()*5+1;
 			if(randomRow % 2 ==0 )
 				randomRow = randomRow+1;
 			gm.board.insertTile("right", randomRow, 0);
 			break;
 		case 3:
 			randomRow = (int)Math.random()*5+1;
 			if(randomRow % 2 ==0 )
 				randomRow = randomRow+1;
 			gm.board.insertTile("left", randomRow, 6);
 			break;
 		}
 		int[] playerLocation = gm.computer.location;
 		curPanel = boardPanels[playerLocation[0]][playerLocation[1]];
 		for(int i=0;i<boardPanels.length;i++){
 			for(int j=0;j<boardPanels[i].length;j++){
 				if(boardPanels[i][j].tile.treasure.getValue() == getTopCard().getValue()){
 					desPanel = boardPanels[i][j];
               System.out.println("Computers destination: " + boardPanels[i][j].tile.treasure.toString());
 				}

 			}
 		}
      
      moveComputerPlayerTo( curPanel, desPanel, gm );
      
      // Check for a winner
      if (!gm.checkForWinner()){
         // Have the user take a turn if no winner
         gm.user.takeTurn(gm, boardPanels);
      }else{
         gm.callWinScreen();
      }
    }
    
    public void moveComputerPlayerTo(TilePanel curPanel,TilePanel desPanel,GameManager gm){
		Board board = gm.board;
      boolean[][] visited = new boolean[7][7];
		visited[curPanel.tileLocation[0]][curPanel.tileLocation[1]] = true;
		LinkedList<TilePanel> bfsQueue = new LinkedList<TilePanel>();
		LinkedList<TilePanel> visitedSequence = new LinkedList<TilePanel>();
		Tile neiTile;
		int tileX;
		int tileY;
		bfsQueue.add(curPanel);
		visitedSequence.add(curPanel);
		while(bfsQueue.size()>0){
			TilePanel preTilePanel = bfsQueue.poll();
			if(preTilePanel.tileLocation[0]>0){
				tileX = preTilePanel.tileLocation[0]-1;
				tileY = preTilePanel.tileLocation[1];
				neiTile = board.getTileAt(tileX, tileY);
				if(!visited[tileX][tileY]&&preTilePanel.tile.pathExists(neiTile,Direction.NORTH)){
					TilePanel tilePanel = new TilePanel(neiTile,tileX,tileY);
					bfsQueue.add(tilePanel);
					visitedSequence.add(tilePanel);
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
					visitedSequence.add(tilePanel);
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
					visitedSequence.add(tilePanel);
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
					visitedSequence.add(tilePanel);
					visited[tileX][tileY] = true;
				}
			}

		}
      

   		if( visited[desPanel.tileLocation[0]][desPanel.tileLocation[1]] ){
            gm.movePlayerTo(gm.computer, desPanel);
         }else {
            if (visitedSequence.pollLast() != null){
   			   gm.movePlayerTo(gm.computer, visitedSequence.pollLast());
            }
   		}
	}	  
}
