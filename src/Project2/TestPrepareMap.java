package Project2;

import javax.swing.JFrame;

public class TestPrepareMap extends JFrame {
	public TestPrepareMap(Player x, Player y) {
		setTitle("Monopoly");
		add(new PrepareMap(x, y));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 440);
		System.out.println("TestPrepareMap Running");
		setLocationRelativeTo(null);// Center the frame
		setVisible(false);
	}
}
