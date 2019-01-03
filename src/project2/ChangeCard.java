package project2;

import javax.swing.JOptionPane;

public class ChangeCard extends Card{
	//以到达的对手空白土地作为对象，和自己的一块空白地强制交换
	protected String name = "交换卡";
	protected int price = 300;

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
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}

	@Override
	public boolean cardUse(Player p, Square s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}
	
	public boolean cardUse(Player p, Land by, Land it) {
		// TODO Auto-generated method stub
		if(this.usable) {
			Player op = it.getOwner();
			it.setOwner(p);
			by.setOwner(op);
			this.setUsable(false);
			return true;
		}
		else	return false;
	}
	
}
