import java.util.*;

/**
	@Author SimonAnguish
*/

public class Board {
	private static Tile[][] board = new Tile[7][7];
	public Tile tileInHand;

	/**
		Board
		Constructs a randomly generated board
	*/
	public Board() {
		initBoard();
	}

	/**
		initBoard
		Places the required tiles on the board, before generating a
		random placement of the remaining tiles. Then places the last tile in the stack
		into the hand for the player to use.
	*/
	private void initBoard() {
		board[0][0] = new Tile(false, true, true, false,new Treasure(0));
		board[0][2] = new Tile(false, true, true, true,new Treasure(1));
		board[0][4] = new Tile(false, true, true, true,new Treasure(2));
		board[0][6] = new Tile(false, true, false, true,new Treasure(0));

		board[2][0] = new Tile(true, true, true, false,new Treasure(3));
		board[2][2] = new Tile(true, true, true, false,new Treasure(4));
		board[2][4] = new Tile(false, true, true, true,new Treasure(5));
		board[2][6] = new Tile(true, true, false, true,new Treasure(6));

		board[4][0] = new Tile(true, true, true, false,new Treasure(7));
		board[4][2] = new Tile(true, false, true, true,new Treasure(8));
		board[4][4] = new Tile(true, true, false, true,new Treasure(9));
		board[4][6] = new Tile(true, true, false, true,new Treasure(10));

		board[6][0] = new Tile(true, false, true, false,new Treasure(0));
		board[6][2] = new Tile(true, false, true, true,new Treasure(11));
		board[6][4] = new Tile(true, false, true, true,new Treasure(12));
		board[6][6] = new Tile(true, false, false, true,new Treasure(0));

		List<Tile> tileHeap = generateTiles();

		// Places the shuffled tiles onto the board in the free spaces
		for (int i=0;i<7;i++) {
			// This if is for every other row, where all of the spaces are empty
			// Else is for where we have every other space already filled in by the required tiles
			if (i%2==1) {
				for (int j=0;j<7;j++) {
					board[i][j] = tileHeap.remove(tileHeap.size() - 1);
				}
			} else {
				for (int j=1;j<7;j+=2) {
					board[i][j] = tileHeap.remove(tileHeap.size() - 1);
				}
			}
		}

		tileInHand = tileHeap.remove(0);
      
	}

	/**
		generateTiles
		Generates the appropriate number of L, T, and I Shape tiles
		@return List A shuffled collection of tiles
	*/
	private static List generateTiles() {
		List<Tile> tileHeap = new ArrayList<Tile>();
		// Add 'L' Shapes
		for (int i=0;i<16;i++) {
			if(i<6){
				tileHeap.add(new Tile(false, true, true, false,new Treasure(i+13)));
			}
			else
				tileHeap.add(new Tile(false, true, true, false,new Treasure(0)));
			
		}

		// Add 'T' Shapes
		for (int i=0;i<6;i++) {
			tileHeap.add(new Tile(true, true, true, false,new Treasure(19+i)));
		}

		// Add I' Shapes
		for (int i=0;i<12;i++) {
			tileHeap.add(new Tile(true, true, false, false,new Treasure(0)));
		}

		Random rand = new Random();

		for (Tile tileToRotate : tileHeap) {
			tileToRotate.rotate(rand.nextInt(4));
		}

		Collections.shuffle(tileHeap);

		return tileHeap;
	}

	/**
		getTileAt
		Gives the Tile at the appropriate row and column index
		@param row The index of the row for the tile wanted
		@param column The index of the column for the tile wanted
		@return Tile The tile at the specified index
	*/
	public Tile getTileAt(int row, int column) {
		return board[row][column];
	}
	
	public void insertTile(String direction, int row, int column){

		Tile newTileInHand = null;
		switch(direction){
		case "down": 
			newTileInHand = getTileAt(6,column);
			for(int t=6;t>0;t--){
				board[t][column] = getTileAt(t-1,column);
			}
			board[0][column] = tileInHand;
			break;
		case "up":
			newTileInHand = getTileAt(0,column);
			for(int t=0;t<6;t++){
				board[t][column] = getTileAt(t+1,column);
			}
			board[6][column] = tileInHand;
			break;
		case "left":
			newTileInHand = getTileAt(row,0);
			for(int t=0;t<6;t++){
				board[row][t] = getTileAt(row,t+1);
			}
			board[row][6] = tileInHand;
			break;
		case "right":
			newTileInHand = getTileAt(row,6);
			for(int t=6;t>0;t--){
				board[row][t] = getTileAt(row,t-1);
			}
			board[row][0] = tileInHand;
			break;
		}


		this.tileInHand = newTileInHand;
	}
}