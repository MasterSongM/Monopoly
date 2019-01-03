package Project2;
import java.awt.Image;
import Project2.Player;
import Project2.PlayerPanel;
//父类

public abstract class Card {
	 //卡片名字
	protected String name;
	protected Image img;
	 // 拥有者
	protected int price = 100;
	

	 //使用卡片效果
	public abstract int useCard ();
	//卡片持续效果
	public int cardBuff(){ return 0;}


	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}


	public int getPrice() {
		return price;
	}

	
	public void cardUse() {
		
	}
	
	public void cardUse(Player p) {
			
	}
	
	public void cardBuy() {
			
	}
	
}
