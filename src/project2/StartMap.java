package project2;

import java.awt.*;

import javax.swing.*;

import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.*;

public class StartMap extends JFrame {
	PrepareMap a = new PrepareMap(2);
	// TestBigMap a = new TestBigMap(new Player(),new Player());
	private ImageIcon startImage = new ImageIcon("icons/start/index.jpg");
	private ImageIcon btImage1 = new ImageIcon("icons/start/bt1.jpg");
	private ImageIcon btImage2 = new ImageIcon("icons/start/bt2.jpg");
	private ImageIcon btImage3 = new ImageIcon("icons/start/bt3.jpg");
	JLabel image1 = new JLabel(startImage);
	// Start new game
	JButton bt1 = new JButton(btImage1);
	// End the game
	JButton bt2 = new JButton(btImage2);
	// Continue the game
	JButton bt3 = new JButton(btImage3);

	public StartMap() {
		System.out.println("StartMap running");
		setLayout(null);
		// JButton image1 = new JButton(startImage);
		image1.add(bt1);
		image1.add(bt2);
		//image1.add(bt3);
		// image1.add(image1);

		bt1.setBounds(185, 160, 110, 30);
		bt2.setBounds(185, 222, 110, 30);
		bt3.setBounds(20, 245, 110, 30);
		image1.setBounds(0, 0, 480, 305);
		add(image1);

		// Create listener for the buttons
		ButtonListener listener = new ButtonListener();

		// Add listener to the buttons
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		bt3.addActionListener(listener);

	}

	public static void main(String[] args) {
		StartMap frame = new StartMap();
		frame.setTitle("����");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(486, 336);
		frame.setLocationRelativeTo(null);// Center the frame
		frame.setVisible(true);
		frame.setResizable(false);

	}

	class ButtonListener implements ActionListener {
		// TestPrepareMap a = new TestPrepareMap(ccc,ddd);
		public void actionPerformed(ActionEvent e) {
			// TestBigMap big = new TestBigMap(x,y);
			if (e.getSource() == bt1) {
				a.setVisible(true);
				setVisible(false);
			}
			if (e.getSource() == bt2) {

			}
		}
	}

}
