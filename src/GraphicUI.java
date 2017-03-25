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

	Board board;

	public GraphicUI(Board b) {
		board = b;

		callHomeScreen();
		// buildDefault(board);



	}

	void buildDefault(Board b) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Labyrinth");

		// Add the score Panel
		add(makeScorePanel(), BorderLayout.NORTH);

		// Add the board Panel
		add(makeBoardPanel(b), BorderLayout.WEST);

		JPanel rightOptionsPanel = new JPanel();
		rightOptionsPanel.setLayout(new GridLayout(2,1));
		rightOptionsPanel.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_WIDTH, 500));
		add(rightOptionsPanel, BorderLayout.EAST);

		JPanel tileInHandPanel = new JPanel();
		tileInHandPanel.setBorder(new EmptyBorder(80,50,0,50));
		tileInHandPanel.setBackground(Color.LIGHT_GRAY);
		tileInHandPanel.add(new TilePanel(b.tileInHand, 210));
		rightOptionsPanel.add(tileInHandPanel);

		JPanel tileOptionsPanel = new JPanel();
		tileOptionsPanel.setBorder(new EmptyBorder(60, 10, 0, 10));
		tileOptionsPanel.setBackground(Color.LIGHT_GRAY);
		Tile newTile = new Tile(b.tileInHand.north, b.tileInHand.south, b.tileInHand.east, b.tileInHand.west);

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		newTile.rotate(1);
		tileOptionsPanel.add(new TilePanel(newTile, 60));

		rightOptionsPanel.add(tileOptionsPanel);


		pack();

		setLocation(150,100);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
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

		JLabel helpLabel = new JLabel("Help");
		helpLabel.setForeground(Color.WHITE);
		helpLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				callHelpScreen();
			}
		});

		scorePanel.add(helpLabel, BorderLayout.PAGE_START);

		return scorePanel;
	}

	JPanel makeBoardPanel(Board b) {
		JPanel boardPanel = new JPanel(new GridLayout(7,7));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		boardPanel.setPreferredSize(new Dimension(BOARD_WIDTH, 100));

		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				boardPanel.add(new TilePanel(b.getTileAt(i,j)));
			}
		}

		return boardPanel;
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

	void callHomeScreen() {
		JFrame homeFrame = new JFrame();
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));

		JLabel startLabel = new JLabel("Play");
		startLabel.setFont(new Font("Sans-Serif", Font.BOLD, 48));
		startLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buildDefault(board);
				homeFrame.setVisible(false);
			}
		});

		wrapperPanel.add(startLabel);

		JLabel helpLabel = new JLabel("Help");
		helpLabel.setFont(new Font("Sans-Serif", Font.BOLD, 36));
		helpLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				callHelpScreen();
			}
		});

		wrapperPanel.add(helpLabel);
		wrapperPanel.setBorder(new EmptyBorder(100,100,100,100));

		homeFrame.add(wrapperPanel, BorderLayout.CENTER);
		homeFrame.pack();

		homeFrame.setLocation(100, 50);
		homeFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		homeFrame.setResizable(false);
		homeFrame.setVisible(true);
	}
}