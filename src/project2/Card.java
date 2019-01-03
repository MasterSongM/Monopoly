package project2;
import java.awt.Image;

import project2.Player;
import project2.PlayerPanel;

public abstract class Card {
	 //卡片名字
	protected boolean usable = false;
	

	protected String name;
	protected Image img;
	 // 拥有者
	protected int price = 100;
	
	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}


	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	public abstract boolean cardUse(Player p);
	
	public abstract boolean cardUse(Player p, Square s);
	
	public boolean cardBuy() {
			if(this.usable)	return false;
			else{
				this.usable = true;
				return true;
			}
	}
	
}
