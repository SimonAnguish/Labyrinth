import java.awt.*;
import javax.swing.*;
/**
 * This is used to set up the background
 * @author Simon Anguish
 *
 */

class BackgroundPanel extends JPanel {
	private Image img;

	public BackgroundPanel(String url) {
		this(new ImageIcon(url).getImage());
	}

	public BackgroundPanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}