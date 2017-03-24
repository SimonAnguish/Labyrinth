import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.List;

class GraphicUI extends JFrame{

	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 650;
	final int BOARD_WIDTH = 600;

	public GraphicUI(Board b, Tile tileInHand) {
		buildDefault(b, tileInHand);

		pack();

		setLocation(150,100);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);

		callHelpScreen();
	}

	void buildDefault(Board b, Tile tileInHand) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Labyrinth");

		add(makeScorePanel(), BorderLayout.NORTH);
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

	JPanel makeScorePanel() {
		JPanel scorePanel = new JPanel(new BorderLayout());
		scorePanel.setBackground(Color.DARK_GRAY);
		scorePanel.setPreferredSize(new Dimension(BOARD_WIDTH, 50));
		scorePanel.setBorder(new EmptyBorder(5, 10, 5, 10));

		JLabel playerScore = new JLabel("Player: 0");
		JLabel computerScore = new JLabel("Computer: 0");

		playerScore.setFont(new Font("Serif", Font.BOLD, 20));
		computerScore.setFont(new Font("Serif", Font.BOLD, 20));

		playerScore.setForeground(Color.WHITE);
		computerScore.setForeground(Color.WHITE);

		scorePanel.add(playerScore, BorderLayout.LINE_START);
		scorePanel.add(computerScore, BorderLayout.LINE_END);

		return scorePanel;
	}

	void callHelpScreen() {
		JFrame helpFrame = new JFrame();
		helpFrame.setTitle("Labyrinth: Help");

		// Build the text
		try {
			String allInstructions = "<html>";
			List<String> allLines = Files.readAllLines(
					Paths.get("../docs/instructions.txt"), 
					Charset.defaultCharset()
			);
			for (String line : allLines) {
				allInstructions += line + "<br>";
			}
			JLabel instructions = new JLabel(allInstructions + "</html>");
			instructions.setFont(new Font("Serif", Font.BOLD, 20));
			instructions.setBorder(new EmptyBorder(20,50,20,50));

			helpFrame.add(instructions, BorderLayout.CENTER);
		} catch (IOException e) {
			System.out.printf("Cannot find file");
		}
		
		helpFrame.pack();

		helpFrame.setLocation(200,150);
		helpFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		helpFrame.setResizable(false);
		helpFrame.setVisible(true);
	}
}