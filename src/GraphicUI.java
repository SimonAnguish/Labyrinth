import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class GraphicUI extends JFrame{

	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 600;
	final int BOARD_WIDTH = 600;

	public GraphicUI(Board b, Tile tileInHand) {
		buildDefault(b, tileInHand);

		pack();

		setLocation(150,100);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
	}

	void buildDefault(Board b, Tile tileInHand) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Labyrinth");

		// Add the big boardPanel
		JPanel boardPanel = new JPanel(new GridLayout(7,7));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		boardPanel.setPreferredSize(new Dimension(BOARD_WIDTH, 100));
		add(boardPanel, BorderLayout.WEST);

		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				boardPanel.add(new TilePanel(b.getTileAt(i,j)));
			}
		}

		JPanel rightOptionsPanel = new JPanel();
		rightOptionsPanel.setLayout(new GridLayout(2,1));
		rightOptionsPanel.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_WIDTH, 500));
		add(rightOptionsPanel, BorderLayout.EAST);

		JPanel tileInHandPanel = new JPanel();
		tileInHandPanel.setBorder(new EmptyBorder(80,50,0,50));
		tileInHandPanel.setBackground(Color.LIGHT_GRAY);
		tileInHandPanel.add(new TilePanel(tileInHand, 210));
		rightOptionsPanel.add(tileInHandPanel);

		JPanel tileOptionsPanel = new JPanel();
		tileOptionsPanel.setBorder(new EmptyBorder(60, 10, 0, 10));
		tileOptionsPanel.setBackground(Color.LIGHT_GRAY);
		Tile newTile = new Tile(tileInHand.north, tileInHand.south, tileInHand.east, tileInHand.west);

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		rightOptionsPanel.add(tileOptionsPanel);
	}
}