package Project2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlayerPanel extends JLabel {
	JPanel playerPanel = new JPanel(new GridLayout(2, 2));
	JLabel avatar = new JLabel();//头像
	JLabel name = new JLabel();//名称
	JLabel cash = new JLabel();//现金
	JLabel more = new JLabel();//
	PlayerPanel(Player a, ImageIcon img){
		Font font = new Font("Times", Font.ITALIC, 20);
		playerPanel.add(avatar);
		avatar.setIcon(img);
		playerPanel.add(name);
		name.setText(a.getName());
		playerPanel.add(cash);
		cash.setText("现金:"+a.getCash());
		playerPanel.add(more);
		more.setText("地产数:"+0);
		name.setFont(font);
		name.setForeground(Color.RED);
		playerPanel.setBorder(new TitledBorder("玩家信息"));
	}
}
