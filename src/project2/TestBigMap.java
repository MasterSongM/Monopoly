package project2;

import javax.swing.JFrame;

public class TestBigMap extends JFrame {
	public TestBigMap(int pNum) {
		setTitle("Monopoly");
		add(new BigMap(pNum));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 1010);
		setLocationRelativeTo(null);// Center the frame
		setVisible(false);
		// setResizable(false);
	}

}
