import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JArrow extends JLabel {
	private int row;
	private int col;
	private String dir;
	
	JArrow(String dir, int row, int col) {
		super.setIcon(new ImageIcon("../docs/" + dir.toLowerCase() + "_triangle_button.png"));
		this.row = row;
		this.col = col;
		this.dir = dir;
	}

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
	
	public String getDir() {
		return dir;
	}
}