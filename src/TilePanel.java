import javax.swing.*;
import java.awt.*;

class TilePanel extends JComponent {
	int bSize = 20;
	public Tile tile = new Tile(true, true, true, true);
	TilePanel(Tile tile) {
		setSize(new Dimension(bSize, bSize));
		this.tile = tile;

		this.tile.linkTilePanel(this);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);

		g2.fillRect(bSize,bSize,bSize,bSize);
		if (tile.treasure != null) add(new JLabel(tile.treasure.toString()));

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

	TilePanel(Tile tile, int newTileSize) {
		bSize = newTileSize;
		
		setSize(new Dimension(bSize, bSize));

		this.tile = tile;

		this.tile.linkTilePanel(this);
	}
}