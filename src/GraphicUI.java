import javax.swing.*;
import java.awt.*;

class GraphicUI extends JFrame{
	public GraphicUI(Board b, Tile tileInHand) {
		buildDefault();

		pack();

		setLocation(150,100);
		setSize(1000,600);
		setResizable(false);
		setVisible(true);
	}

	void buildDefault() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		p.setPreferredSize(new Dimension(100, 100));

		add(p, BorderLayout.WEST);
	}
}