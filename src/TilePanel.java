import javax.swing.*;
import java.awt.*;

class TilePanel extends JPanel {
	TilePanel(Tile tile) {
		setLayout(new GridLayout(3,3));
		setSize(new Dimension(30, 30));
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
		panel.setPreferredSize(new Dimension(10,10));
		return panel;
	}

	JPanel drawBlack() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(10,10));
		return panel;
	}
}