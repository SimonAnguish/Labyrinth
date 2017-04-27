import javax.swing.*;
import java.awt.*;
import java.util.Random;

class TilePanel extends JComponent {
	int bSize = 20;
	public Tile tile = new Tile(true, true, true, true, new Treasure(0));
	
	boolean staticTile = false;
	
	int[] tileLocation = new int[2];
	Color[][] backgroundMatrix = new Color[15][15];
	Color[][] pathMatrix = new Color[5][5];
	
	boolean hasPlayer = false;
	boolean hasComputer = false;
	JLabel treasureLabel = new JLabel("", SwingConstants.CENTER);
	
	TilePanel(Tile tile, int x, int y) {
		setSize(new Dimension(bSize, bSize));
		this.tile = tile;
		
		tileLocation[0] = x;
		tileLocation[1] = y;
		if (tile.treasure.getValue() != 0){
			treasureLabel.setText(tile.treasure.toString());
			treasureLabel.setSize(bSize, bSize);
        	add(treasureLabel);
     	}
		generateRandomBackground();
	}
	
	private void generateRandomBackground() {
		int startingGreen = 150;
		if (staticTile) {
			startingGreen = 20;
		}
		Random rand = new Random();
		for (int i=0;i<backgroundMatrix.length;i++) {
			for (int j=0;j<backgroundMatrix.length;j++) {
				backgroundMatrix[i][j] = new Color(22,startingGreen+rand.nextInt(80),14);
			}
		}
		int randGray;
		for (int i=0;i<pathMatrix.length;i++) {
			for (int j=0;j<pathMatrix.length;j++) {
				randGray = rand.nextInt(80);
				pathMatrix[i][j] = new Color(130+randGray, 130+randGray, 130+randGray);
			}
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (tile.treasure.getValue() != 0){
			treasureLabel.setVisible(true);
		} else {
			treasureLabel.setVisible(false);
		}
      
		int tileSize = bSize*3;
		for (int i=0;i<backgroundMatrix.length;i++) {
			for (int j=0;j<backgroundMatrix.length;j++) {
				g2.setColor(backgroundMatrix[i][j]);
				g2.fillRect(i*(bSize/5),j*(bSize/5),bSize/5,bSize/5);
			}
		}
		
		for (int i=0;i<pathMatrix.length;i++) {
			for (int j=0;j<pathMatrix.length;j++) {
				Random rand = new Random();
				g2.setColor(pathMatrix[i][j]);
				g2.fillRect(bSize+i*(bSize/5),bSize+j*(bSize/5),bSize/5,bSize/5);
			}
		}
		
		if (tile.playerOnTile instanceof HumanPlayer) {
			g2.setColor(Color.BLUE);
			g2.fillOval(bSize,bSize,bSize,bSize);
			g2.setColor(Color.BLACK);
		} else if (tile.playerOnTile instanceof ComputerPlayer) {
			g2.setColor(Color.RED);
			g2.fillOval(bSize,bSize,bSize,bSize);
			g2.setColor(Color.BLACK);
		}

		if (tile.north) {
			for (int i=0;i<pathMatrix.length;i++) {
				for (int j=0;j<pathMatrix.length;j++) {
					Random rand = new Random();
					g2.setColor(pathMatrix[i][j]);
					g2.fillRect(bSize+i*bSize/5,j*bSize/5,bSize/5,bSize/5);
				}
			}
//			g2.fillRect(bSize,0,bSize,bSize);
		}

		if (tile.east) {
			for (int i=0;i<pathMatrix.length;i++) {
				for (int j=0;j<pathMatrix.length;j++) {
					Random rand = new Random();
					g2.setColor(pathMatrix[i][j]);
					g2.fillRect(2*bSize+i*bSize/5,bSize+j*bSize/5,bSize/5,bSize/5);
				}
			}
//			g2.fillRect(2*bSize,bSize,bSize,bSize);
		}

		if (tile.south) {
			for (int i=0;i<pathMatrix.length;i++) {
				for (int j=0;j<pathMatrix.length;j++) {
					Random rand = new Random();
					g2.setColor(pathMatrix[i][j]);
					g2.fillRect(bSize+i*bSize/5,2*bSize+j*bSize/5,bSize/5,bSize/5);
				}
			}
//			g2.fillRect(bSize,2*bSize,bSize,bSize);
		}

		if (tile.west) {
			for (int i=0;i<pathMatrix.length;i++) {
				for (int j=0;j<pathMatrix.length;j++) {
					Random rand = new Random();
					g2.setColor(pathMatrix[i][j]);
					g2.fillRect(i*bSize/5,bSize+j*bSize/5,bSize/5,bSize/5);
				}
			}
//			g2.fillRect(0,bSize,bSize,bSize);
		}
	}
	
	public void printTileString() {
		System.out.println(tile.toString());
	}
	
	public void rotateTile() {
		this.tile.rotate(1);
		repaint();
	}

	public void setTile(Tile newTile) {
		this.tile = newTile;
		repaint();
	}

	public void setUser() {
		this.hasPlayer = !this.hasPlayer;
		repaint();
	}

	public void setComputer() {
		this.hasComputer = !this.hasComputer;
		repaint();
	}
   
   public void removeTreasure() {
		this.tile.treasure = null;
		repaint();
	}
	
	public void setPlayer(Player p) {
		tile.addPlayer(p);
		p.location = getTileLocation();
		repaint();
	}

	public void makeImmovable() {
		staticTile = true;
		repaint();
	}
	
	public int[] getTileLocation() {
		return tileLocation;
	}

	TilePanel(Tile tile, int newTileSize) {
		bSize = newTileSize;
		setSize(new Dimension(bSize, bSize));

		this.tile = tile;
		
		if (tile.treasure.getValue() != 0){
			treasureLabel.setText(tile.treasure.toString());
			treasureLabel.setSize(bSize, bSize);
        	add(treasureLabel);
     	}
		generateRandomBackground();
	}
}