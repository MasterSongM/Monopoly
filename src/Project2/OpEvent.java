package Project2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OpEvent extends JPanel implements ActionListener, Runnable {
	JLabel tipBuy = new JLabel();
	JLabel tipLevel = new JLabel();
	JLabel tipDo = new JLabel();
	static JButton btLandOK = new JButton("OK");
	static JButton btLandNO = new JButton("NO");
	public OpEvent() {
	}
	
	public <CloseWindowIn5> OpEvent(Square square, Player p) {
		setLayout(null);
		JOptionPane.showMessageDialog(null, "�Բ�������ֽ��㣬�����޷���������أ�");
	}
	
	public <CloseWindowIn5> OpEvent(Land land, Player p) {
		setLayout(null);
		
	}


	public void run() {
		try {
			Thread.sleep(2000);
			setVisible(false); // �رմ���
			// System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// ���
		// if (e.getSource() == btLandOK) {
		// if (p.getCash() < 1000) {
		// JOptionPane.showMessageDialog(null, "���������ȥȡ��Ǯ�ɣ����Ǯ�����ˡ���");
		// this.setVisible(false);
		// } else {
		// land[location].setOwner(p.getName());
		// p.setCash(-1000);
		// this.setVisible(false);
		// JOptionPane.showMessageDialog(null, "��ϲ������ɹ���");
		// }
		// }
		// // �����
		// if (e.getSource() == btLandNO) {
		// this.setVisible(false);
		// }

	}

}
