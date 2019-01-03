package project2;

import javax.swing.JOptionPane;

public class PassCard extends Card{
	//监狱通行证
	protected String name = "通行卡";
	protected int price = 100;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean cardUse(Player p) {
		// TODO Auto-generated method stub
		if(this.usable && p.getJailDays() > 0) {
			p.setJailDays(0);
			this.setUsable(false);
			return true;
		}
		else {
			if(!this.usable) {//没有拥有卡片
				JOptionPane.showMessageDialog(null, "想出去？可惜你现在没有通行卡！");
			}
			else {//不在监狱里
				JOptionPane.showMessageDialog(null, "你不满足通行卡的使用条件！");
			}
			return false;
		}
	}

	@Override
	public boolean cardUse(Player p, Square s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}
}
