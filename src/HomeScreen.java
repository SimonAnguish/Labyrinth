import java.util.Random;
import javax.swing.*;
import java.awt.*;

class HomeScreen extends JComponent {
	final int FRAME_HEIGHT = 650;
	final int FRAME_WIDTH = 1000;
	final int BLOCK_SIZE = 10;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Random rand = new Random();

		for (int i=0;i<FRAME_WIDTH/BLOCK_SIZE;i++) {
			for (int j=0;j<FRAME_HEIGHT/BLOCK_SIZE;j++) {
				g2.setColor(new Color(22,130+rand.nextInt(80),14));
				g2.fillRect(i*BLOCK_SIZE, j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
			}
		}

		System.out.printf("Drawn\n");
	}

	public HomeScreen() {}
}