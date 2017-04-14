import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JArrow extends JLabel implements MouseListener {
	private int row;
	private int col;

	JArrow(ImageIcon img, int row, int col) {
		super.setIcon(img);
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}