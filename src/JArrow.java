import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

class JArrow extends JLabel {
	JArrow(ImageIcon img, int row, int col, String direction) {
		super.setIcon(img);
		System.out.println("New JArrow added at " + row + ", " + col + " " + direction);
		super.setHorizontalAlignment(JLabel.CENTER);
	}
}