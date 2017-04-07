import javax.swing.*;
import java.awt.*;

class TilePanel extends JComponent {
	int tileSize = 30;
	Tile tile = new Tile(true, true, true, true);
	TilePanel(Tile thisTile) {
		setSize(new Dimension(tileSize, tileSize));

		tile = thisTile;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);

		g2.fillRect(20,20,20,20);

		if (tile.north) {
			g2.fillRect(20,0,20,20);
		}

		if (tile.east) {
			g2.fillRect(40,20,20,20);
		}

		if (tile.south) {
			g2.fillRect(20,40,20,20);
		}

		if (tile.west) {
			g2.fillRect(0,20,20,20);
		}
	}

	TilePanel(Tile newTile, int newTileSize) {
		tileSize = newTileSize;
		setSize(new Dimension(tileSize, tileSize));

		tile = newTile;
	}
}