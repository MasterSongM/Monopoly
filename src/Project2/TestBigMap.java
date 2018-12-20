package Project2;

import javax.swing.JFrame;

public class TestBigMap extends JFrame {
	public TestBigMap(Player x, Player y) {
		setTitle("Monopoly");
		add(new BigMap());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 999);
		setLocationRelativeTo(null);// Center the frame
		setVisible(false);
		// setResizable(false);
	}

}
