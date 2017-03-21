import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class GraphicUI extends JFrame{

	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 600;
	final int BOARD_WIDTH = 600;

	public GraphicUI(Board b, Tile tileInHand) {
		buildDefault(b);

		pack();

		setLocation(150,100);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
	}

	void buildDefault(Board b) {
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
		JPanel tileInHandPanel = new JPanel();
		tileInHandPanel.setBackground(Color.LIGHT_GRAY);
		tileInHandPanel.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_WIDTH, 500));
		add(tileInHandPanel, BorderLayout.EAST);
	}
}