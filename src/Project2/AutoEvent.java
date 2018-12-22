package Project2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AutoEvent extends JPanel implements ActionListener, Runnable {
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
		if(land.getOwner() == null) { //无主土地

			int option = JOptionPane.showConfirmDialog(null, "这块地价值"+land.getBuyprice()+"\n侬要买地伐？");
			if(option == 0) {
				if(p.buyLand(land)) {
					JOptionPane.showMessageDialog(null, "你花费"+land.getBuyprice()+"购买了这块地！");
				}
				else {
					JOptionPane.showMessageDialog(null, "对不起，现金不足！");
				}
			}
		}
		else if(land.getOwner().getName()==p.getName()) { //自己的土地
			
			JOptionPane.showMessageDialog(null, "你在自己的一块土地上巡视了一圈！");
		}
		else{
			JOptionPane.showMessageDialog(null, "风过留痕，雁过拔毛，你过要交费：\n"+land.getOwner().getName()+" 收取了" 
			+ land.getPaidmoney() + "过路费~");
			if(!land.collect(p)) {
				//破产
			}
		}
	}


	public void run() {
		try {
			Thread.sleep(2000);
			setVisible(false); // 关闭窗口
			// System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
