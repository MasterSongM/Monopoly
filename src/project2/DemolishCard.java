package project2;

import java.util.List;

import javax.swing.JOptionPane;

public class DemolishCard extends Card{
	//以到达的对手土地作为对象，相连的三块地建筑降一级
	protected String name = "拆除卡";
	protected int price = 200;

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
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}

	@Override
	public boolean cardUse(Player p, Square s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "啊哦，你的道具卡用错对象了！");
		return false;
	}
	
	public boolean cardUse(Player p, Land land) {
		// TODO Auto-generated method stub
		if(this.usable) {
			List<Land> list = land.getNeighbour();
			if(list.get(0).getHouseLevel() <= 0 && list.get(1).getHouseLevel() <= 0 && list.get(2).getHouseLevel() <= 0) {
				JOptionPane.showMessageDialog(null, "这里无房可拆！");
				return false;
			}
			else {
				for(int i = 0; i < 3; i++) {
					int level = list.get(i).getHouseLevel();
					if(level > 0) {
						list.get(i).setHouseLevel(level - 1);
					}
				}
				this.setUsable(false);
				return true;
			}
		}
		else	return false;
	}
	
}
