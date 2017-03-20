import javax.swing.*;
import java.awt.*;

class TilePanel extends JPanel {
	TilePanel(Tile tile) {
		add(drawBlack(), BorderLayout.WEST);
		add(drawBlack(), BorderLayout.NORTH);
		add(drawBlack(), BorderLayout.EAST);
		add(drawBlack(), BorderLayout.SOUTH);
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