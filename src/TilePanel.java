import javax.swing.*;
import java.awt.*;

class TilePanel extends JPanel {
	int tileSize = 30;
	TilePanel(Tile tile) {
		setLayout(new GridLayout(3,3));
		setSize(new Dimension(tileSize, tileSize));

		drawTile(tile);
	}

	TilePanel(Tile tile, int newTileSize) {
		tileSize = newTileSize;
		setLayout(new GridLayout(3,3));
		setSize(new Dimension(tileSize, tileSize));

		drawTile(tile);
	}

	void drawTile(Tile tile) {
		add(drawWhite());
		if (tile.north) add(drawBlack());
		else add(drawWhite());
		add(drawWhite());

		if (tile.west) add(drawBlack());
		else add(drawWhite());

		add(drawBlack());

		if (tile.east) add(drawBlack());
		else add(drawWhite());

		add(drawWhite());
		if (tile.south) add(drawBlack());
		else add(drawWhite());
		add(drawWhite());
	}

	JPanel drawWhite() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(tileSize/3,tileSize/3));
		return panel;
	}

	JPanel drawBlack() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(tileSize/3,tileSize/3));
		return panel;
	}
}