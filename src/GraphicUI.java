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
	
	GameManager gm = new GameManager();

	public GraphicUI() {
		board = gm.board;

		callHomeScreen();
		gm.run();
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
		rightOptionsPanel.setBackground(Color.LIGHT_GRAY);
		rightOptionsPanel.setBorder(new EmptyBorder(80, 100, 80, 125));
		
		TilePanel handTile = new TilePanel(b.tileInHand, 50);
		
		rightOptionsPanel.add(handTile);

		JPanel tileOptionsPanel = new JPanel();
		tileOptionsPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel rotateLabel = new JLabel(new ImageIcon("../docs/rotate-clockwise.png"));

		rotateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handTile.rotateTile();
			}
		});
		
//		upLabel.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				handTile.rotateTile();
//			}
//		});
		
		tileOptionsPanel.add(rotateLabel, BorderLayout.SOUTH);
		rightOptionsPanel.add(tileOptionsPanel);
		
		add(rightOptionsPanel, BorderLayout.EAST);


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
		JPanel boardPanel = new JPanel(new GridLayout(9,9));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		boardPanel.setPreferredSize(new Dimension(BOARD_WIDTH, 100));
		
		JLabel upLabel = new JLabel(new ImageIcon("../docs/up_triangle_button.png"));
		JLabel downLabel = new JLabel(new ImageIcon("../docs/down_triangle_button.png"));
		JLabel leftLabel = new JLabel(new ImageIcon("../docs/left_triangle_button.png"));
		JLabel rightLabel = new JLabel(new ImageIcon("../docs/right_triangle_button.png"));

		for (int i=0;i<7;i++) {
			boardPanel.add(downLabel);
			for (int j=0;j<7;j++) {
				boardPanel.add(leftLabel);
				boardPanel.add(new TilePanel(b.getTileAt(i,j)));
				boardPanel.add(rightLabel);
			}
			boardPanel.add(upLabel);
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
		wrapperPanel.setBorder(new EmptyBorder(100,100,100,100));

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

		homeFrame.add(wrapperPanel, BorderLayout.CENTER);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.pack();

		homeFrame.setLocation(100, 50);
		homeFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		homeFrame.setResizable(false);
		homeFrame.setVisible(true);
	}
}