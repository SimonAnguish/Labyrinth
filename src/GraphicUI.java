import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.List;
/**
 * This is the sturcture of graphic user interface
 * @author Simon build the structure and alomost all of the staffs,
 * Sean worked on the makeNext button, Yifan added some logic staff into
 * arrow listeners.
 *
 */
class GraphicUI extends JFrame{

	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 650;
	final int BOARD_WIDTH = 470;
	int oldCol = -1;
	int oldRow = -1;
	Board board;

	boolean canInsertTile = true;
   
   GameManager gm = new GameManager();
   
	TilePanel boardPanels[][] = new TilePanel[7][7];
	
	TilePanel handTile;
	JPanel scorePanel = new JPanel(new BorderLayout());
   JPanel nextPanel = new JPanel(new BorderLayout());
   JLabel skipLabel = new JLabel("Skip Turn", SwingConstants.CENTER);
	
	JLabel playerScore = new JLabel("Player: " + gm.user.getScore());
	JLabel computerScore = new JLabel("Computer: " + gm.computer.getScore());
	JLabel currentTreasureGoal = new JLabel("", SwingConstants.CENTER);
	
	public GraphicUI() {
		board = gm.board;
      
		callHomeScreen();
	}

	void buildDefault(Board b) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Labyrinth");

		// Add the score Panel
		add(makeScorePanel(), BorderLayout.NORTH);

		// Add the board Panel
		add(makeBoardPanel(b), BorderLayout.WEST);
      
      // Add the next button panel
      	add(makeNextButton(), BorderLayout.SOUTH);

		JPanel rightOptionsPanel = new JPanel();
		rightOptionsPanel.setLayout(new GridLayout(2,1));
		rightOptionsPanel.setPreferredSize(new Dimension(FRAME_WIDTH - FRAME_HEIGHT + 50, 500));
		rightOptionsPanel.setBorder(new EmptyBorder(80, 100, 80, 125));
		
		handTile = new TilePanel(b.tileInHand, 60);
		
		rightOptionsPanel.add(handTile);

		JPanel tileOptionsPanel = new JPanel();
		
		JLabel rotateLabel = new JLabel(new ImageIcon("../docs/rotate-clockwise.png"));

		rotateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handTile.rotateTile();
			}
		});
      
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
		
		scorePanel.setBackground(Color.DARK_GRAY);
		scorePanel.setPreferredSize(new Dimension(BOARD_WIDTH, 50));
		scorePanel.setBorder(new EmptyBorder(5, 10, 5, 10));

		playerScore.setFont(new Font("Serif", Font.BOLD, 20));
		currentTreasureGoal.setFont(new Font("Serif", Font.BOLD, 20));
		computerScore.setFont(new Font("Serif", Font.BOLD, 20));

		playerScore.setForeground(Color.WHITE);
		currentTreasureGoal.setForeground(Color.WHITE);
		computerScore.setForeground(Color.WHITE);

		scorePanel.add(playerScore, BorderLayout.LINE_START);
		scorePanel.add(currentTreasureGoal, BorderLayout.CENTER);
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
   
	JPanel makeNextButton() {
		nextPanel.setBackground(Color.DARK_GRAY);
		nextPanel.setPreferredSize(new Dimension(BOARD_WIDTH, 50));
		nextPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

		skipLabel.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		skipLabel.setForeground(Color.WHITE);
		skipLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!canInsertTile) {
					gm.computer.takeTurn(gm, boardPanels);
					handTile.setTile(board.tileInHand);
					updateScorePanel();
					paintBoard();

					canInsertTile = true;
					skipLabel.setVisible(false);
				}
			}
		});

		skipLabel.setVisible(false);

		nextPanel.add(skipLabel, BorderLayout.PAGE_START);
      
		return nextPanel;
	}
	JPanel makeBoardPanel(Board b) {
		JPanel boardWrapper = new JPanel(new BorderLayout());
		boardWrapper.setPreferredSize(new Dimension(FRAME_HEIGHT-100, FRAME_HEIGHT));
		
		JPanel boardPanel = new JPanel(new GridLayout(7,7));
		boardPanel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_WIDTH));
		
		JPanel northPanel = new JPanel(new GridLayout(1,3));
		northPanel.setBorder(new EmptyBorder(0, 130, 0, 70));
		
		JPanel southPanel = new JPanel(new GridLayout(1,3));
		southPanel.setBorder(new EmptyBorder(0, 130, 0, 70));
		
		JPanel eastPanel = new JPanel(new GridLayout(3,1));
		eastPanel.setBorder(new EmptyBorder(40, 0, 40, 0));

		JPanel westPanel = new JPanel(new GridLayout(3,1));
		westPanel.setBorder(new EmptyBorder(40, 0, 40, 0));
		
		JArrow northArrow_1 = new JArrow("down", 0, 1);
		northPanel.add(northArrow_1);
		JArrow northArrow_2 = new JArrow("down", 0, 3);
		northPanel.add(northArrow_2);
		JArrow northArrow_3 = new JArrow("down", 0, 5);
		northPanel.add(northArrow_3);

		addArrowActionListeners(northArrow_1);
		addArrowActionListeners(northArrow_2);
		addArrowActionListeners(northArrow_3);
		
		JArrow southArrow_1 = new JArrow("up", 6, 1);
		southPanel.add(southArrow_1);
		JArrow southArrow_2 = new JArrow("up", 6, 3);
		southPanel.add(southArrow_2);
		JArrow southArrow_3 = new JArrow("up", 6, 5);
		southPanel.add(southArrow_3);

		addArrowActionListeners(southArrow_1);
		addArrowActionListeners(southArrow_2);
		addArrowActionListeners(southArrow_3);

		JArrow eastArrow_1 = new JArrow("left", 1, 6);
		eastPanel.add(eastArrow_1);
		JArrow eastArrow_2 = new JArrow("left", 3, 6);
		eastPanel.add(eastArrow_2);
		JArrow eastArrow_3 = new JArrow("left", 5, 6);
		eastPanel.add(eastArrow_3);
		
		addArrowActionListeners(eastArrow_1);
		addArrowActionListeners(eastArrow_2);
		addArrowActionListeners(eastArrow_3);

		JArrow westArrow_1 = new JArrow("right", 1, 0);
		westPanel.add(westArrow_1);
		JArrow westArrow_2 = new JArrow("right", 3, 0);
		westPanel.add(westArrow_2);
		JArrow westArrow_3 = new JArrow("right", 5, 0);
		westPanel.add(westArrow_3);

		addArrowActionListeners(westArrow_1);
		addArrowActionListeners(westArrow_2);
		addArrowActionListeners(westArrow_3);
		
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				boardPanels[i][j] = new TilePanel(b.getTileAt(i,j), i, j);
				boardPanel.add(boardPanels[i][j]);
				
				addTileActionListeners(boardPanels[i][j]);

				if (i%2==0 && j%2==0) {
					boardPanels[i][j].makeImmovable();
				}
			}
		}

		boardPanels[0][0].setPlayer(gm.computer);
		boardPanels[6][6].setPlayer(gm.user);
		
		boardWrapper.add(northPanel, BorderLayout.NORTH);
		boardWrapper.add(southPanel, BorderLayout.SOUTH);
		boardWrapper.add(eastPanel, BorderLayout.EAST);
		boardWrapper.add(westPanel, BorderLayout.WEST);

		boardWrapper.add(boardPanel, BorderLayout.CENTER);
		
		return boardWrapper;
	}
	
	void addTileActionListeners(TilePanel tile) {
		tile.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (!canInsertTile) {
					int[] userLocation = gm.user.location;
					if (gm.canPlayerMove(boardPanels[userLocation[0]][userLocation[1]], tile)) {
						gm.movePlayerTo(gm.user, tile);
						userLocation = gm.user.location;
                  paintBoard();
						gm.computer.takeTurn(gm, boardPanels);
						handTile.setTile(board.tileInHand);

						paintBoard();
						updateScorePanel();
						canInsertTile = true;
						skipLabel.setVisible(false);
					}
					
				}
			}
		});
	}
	
	void addArrowActionListeners(JArrow arrow) {
		arrow.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (canInsertTile) {
                    if(!(arrow.getRow() == gm.computer.randomRow && gm.computer.randomCol == 0 && arrow.getCol() ==6)
                    		&&!(arrow.getRow() == gm.computer.randomRow && gm.computer.randomCol == 6 && arrow.getCol() ==0)
                    		&&!(arrow.getCol() == gm.computer.randomCol && gm.computer.randomRow == 0 && arrow.getRow() ==6)
                    		&&!(arrow.getCol() == gm.computer.randomCol && gm.computer.randomRow == 6 && arrow.getRow() ==0)){
                    	board.insertTile(arrow.getDir(), arrow.getRow(), arrow.getCol());
    					handTile.setTile(board.tileInHand);
    					oldCol = arrow.getCol();
    					oldRow = arrow.getRow();
    					updateScorePanel();
    					paintBoard();
    					canInsertTile = false;
    					skipLabel.setVisible(true);
                    
                    }
						
				}
			}
		});
	}

	
	void paintBoard() {
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				boardPanels[i][j].setTile(board.getTileAt(i,j));
			}
		}
      updateScorePanel();
	}
   
   // Function to update the score panel
   void updateScorePanel(){
   		playerScore.setText("Player: " + gm.user.getScore());
		computerScore.setText("Computer: " + gm.computer.getScore());
		currentTreasureGoal.setText("Current Goal: " + gm.user.getTopCard().toString());
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
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.WHITE);
		optionsPanel.setPreferredSize(new Dimension(300,500));
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
		optionsPanel.setBorder(new EmptyBorder(50,20,50,20));

		BackgroundPanel wrapperPanel = new BackgroundPanel("../docs/eichenwalde-pixel.jpg");
		
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));
		wrapperPanel.setBorder(new EmptyBorder(100,100,100,600));

		JLabel startLabel = new JLabel("Play");
		startLabel.setForeground(Color.DARK_GRAY);
		startLabel.setFont(new Font("Sans-Serif", Font.BOLD, 48));
		startLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buildDefault(board);
				homeFrame.setVisible(false);
            	play();
			}
		});

		optionsPanel.add(startLabel);

		JLabel helpLabel = new JLabel("Help");
		helpLabel.setForeground(Color.DARK_GRAY);
		helpLabel.setFont(new Font("Sans-Serif", Font.BOLD, 36));
		helpLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				callHelpScreen();
			}
		});

		optionsPanel.add(helpLabel);

		wrapperPanel.add(optionsPanel);

		homeFrame.add(wrapperPanel, BorderLayout.CENTER);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.pack();

		homeFrame.setLocation(100, 50);
		homeFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		homeFrame.setResizable(false);
		homeFrame.setVisible(true);
	}
   
   // call the game loop of the gameManager
   public void play(){
	    gm.run();
		currentTreasureGoal.setText("Current Goal: " + gm.user.getTopCard().toString());
   }
}