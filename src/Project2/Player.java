package Project2;

import java.util.*;

public class Player {
	int jailDays = 0;
	private String name = "";// 记录玩家姓名
	private int cash = 3456;// 玩家现金 单位：分
	//private int gold = 100;// 玩家点券
	private int houseNum = 0;// 玩家房产数量 单位：分
	private int location = 0;// 地点代表map1中的位置 从0到39，然后回到0

	Player() {
	}
	Player(String name) {
		this.name = name;
	}

	public int getJailDays() {
		return jailDays;
	}

	public void setJailDays(int jailDays) {
		this.jailDays = jailDays;
	}
	// 下面都是各个值得GET,SET
	public String getName() {
		return name;
	}

	public void setName(String s) { // int i =0;
		name = s;
		// i++;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int c) {
		cash += c;
	}

	/*public int getGold() {
		return gold;
	}

	public void setGold(int g) {
		gold += g;
	}*/


	public int getLocation() {
		return location;
	}

	public void setLocation(int m) {

		// 控制玩家的行走的路线和步数
		location += m;

		// 大于40时要取余
		if(location > 40) {
			location = location % 40;
			setCash(200);
		}

	}


	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum() {
		houseNum++;
	}

//getters & setters 以外的函数
	public boolean buyLand(Land a) {
		if(a.getBuyprice() <= this.getCash()) {
			setCash(-a.getBuyprice());
			a.setOwner(this);
			this.houseNum++;
			a.setStatus(1);
			return true;
		}
		else	return false;
	}


}
