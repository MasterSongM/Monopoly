package Project2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AutoEvent extends JPanel implements ActionListener, Runnable {
	int conPlay = BigMap.conPlay;
	JLabel tipBuy = new JLabel();
	JLabel tipLevel = new JLabel();
	JLabel tipDo = new JLabel();
	static JButton btLandOK = new JButton("OK");
	static JButton btLandNO = new JButton("NO");
	public AutoEvent() {
	}
	
	public <CloseWindowIn5> AutoEvent(Square square, Player p) {
		setLayout(null);
	}
	
	public <CloseWindowIn5> AutoEvent(Land land, Player p) {//p.getLocation%10: 1-3 & 7-9 
		setLayout(null);
		if(land.getOwner() == null) { //��������

			int option = JOptionPane.showConfirmDialog(null, "���ؼ�ֵ"+land.getBuyprice()+"\nٯҪ��ط���");
			if(option == 0) {
				if(p.buyLand(land)) {
					JOptionPane.showMessageDialog(null, "�㻨��"+land.getBuyprice()+"���������أ�");
				}
				else {
					JOptionPane.showMessageDialog(null, "�Բ����ֽ��㣡");
				}
			}
		}
		else if(land.getOwner().getName()==p.getName()) { //�Լ�������
			
			JOptionPane.showMessageDialog(null, "�����Լ���һ��������Ѳ����һȦ��");
		}
		else{
			JOptionPane.showMessageDialog(null, "������ۣ������ë�����Ҫ���ѣ�\n"+land.getOwner().getName()+" ��ȡ��" 
			+ land.getPaidmoney() + "��·��~");
			if(!land.collect(p)) {
				//�Ʋ�
			}
		}
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
