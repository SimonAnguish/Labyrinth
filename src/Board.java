import java.util.*;

public class Board {
	private static Tile[][] board = new Tile[7][7];

	public Board() {
		initBoard();
	}

	private static void initBoard() {
		board[0][0] = new Tile(false, true, true, false);
		board[0][2] = new Tile(false, true, true, true);
		board[0][4] = new Tile(false, true, true, true);
		board[0][6] = new Tile(false, true, false, true);

		board[2][0] = new Tile(true, true, true, false);
		board[2][2] = new Tile(true, true, true, false);
		board[2][4] = new Tile(false, true, true, true);
		board[2][6] = new Tile(true, true, false, true);

		board[4][0] = new Tile(true, true, true, false);
		board[4][2] = new Tile(true, false, true, true);
		board[4][4] = new Tile(true, true, false, true);
		board[4][6] = new Tile(true, true, false, true);

		board[6][0] = new Tile(true, false, true, false);
		board[6][2] = new Tile(true, false, true, true);
		board[6][4] = new Tile(true, false, true, true);
		board[6][6] = new Tile(true, false, false, true);

		List<Tile> tile_heap = generateCards();

		System.out.println(tile_heap.size());

		for (int i=0;i<7;i++) {
			if (i%2==1) {
				for (int j=0;j<7;j++) {
					board[i][j] = tile_heap.remove(tile_heap.size() - 1);
				}
			} else {
				for (int j=1;j<7;j+=2) {
					board[i][j] = tile_heap.remove(tile_heap.size() - 1);
				}
			}
		}

		System.out.println(tile_heap.size());
	}

	private static List generateCards() {
		List<Tile> tile_heap = new ArrayList<Tile>();
		// Add 'L' Shapes
		for (int i=0;i<16;i++) {
			tile_heap.add(new Tile(false, true, true, false));
		}

		// Add 'T' Shapes
		for (int i=0;i<6;i++) {
			tile_heap.add(new Tile(true, true, true, false));
		}

		// Add I' Shapes
		for (int i=0;i<12;i++) {
			tile_heap.add(new Tile(true, true, false, false));
		}

		Collections.shuffle(tile_heap);

		return tile_heap;
	}

	public Tile getTileAt(int row, int column) {
		return board[row][column];
	}
}