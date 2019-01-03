package project2;

import javax.swing.JOptionPane;

public class LuckyCard extends Card{
	//以土地为作用对象，去到任意地方
	protected String name = "好运卡";
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

	protected int price = 200;

	@Override
	public boolean cardUse(Player p) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}

	@Override
	public boolean cardUse(Player p, Square s) {
		// TODO Auto-generated method stub
		
		if(this.usable) {
			p.setLocation(s.getId());
			return true;
		}
		else	return false;
	}
	
}
