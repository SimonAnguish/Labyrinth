import javax.swing.*;
import java.awt.*;

class TilePanel extends JComponent {
	int bSize = 20;
	public Tile tile = new Tile(true, true, true, true);
	
	boolean staticTile = false;
	
	int[] tileLocation = new int[2];
	
	boolean hasPlayer = false;
	boolean hasComputer = false;
   Treasure treasure = null;
	
	TilePanel(Tile tile, int x, int y) {
		setSize(new Dimension(bSize, bSize));
		this.tile = tile;
		
		tileLocation[0] = x;
		tileLocation[1] = y;

//		this.tile.linkTilePanel(this);
      this.treasure = tile.treasureOnTile;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (tile.treasureOnTile != null){
         System.out.println(tile.treasureOnTile.toString());
         add(new JLabel(tile.treasureOnTile.toString()));
      }
		if (staticTile) {
			g2.setColor(Color.LIGHT_GRAY);
			
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					g2.fillRect(i*bSize,j*bSize,bSize,bSize);
				}
			}
		}
		
		if (tile.playerOnTile instanceof HumanPlayer) {
			g2.setColor(Color.GREEN);
			g2.fillRect(bSize,bSize,bSize,bSize);
			g2.setColor(Color.BLACK);
		} else if (tile.playerOnTile instanceof ComputerPlayer) {
			g2.setColor(Color.RED);
			g2.fillRect(bSize,bSize,bSize,bSize);
			g2.setColor(Color.BLACK);
		} else {
			g2.setColor(Color.BLACK);
			g2.fillRect(bSize,bSize,bSize,bSize);
		}

		if (tile.north) {
			g2.fillRect(bSize,0,bSize,bSize);
		}

		if (tile.east) {
			g2.fillRect(2*bSize,bSize,bSize,bSize);
		}

		if (tile.south) {
			g2.fillRect(bSize,2*bSize,bSize,bSize);
		}

		if (tile.west) {
			g2.fillRect(0,bSize,bSize,bSize);
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
		this.treasure = null;
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
	}
}