package Project2;

import java.util.*;

public class Player {
	static int i = 0;
	static int j = 0;
	private String name = "";// 记录玩家姓名
	public static String[] nameOfPlayer = { "", "" };// 记录所有玩家姓名
	private int cash = 10000;// 玩家现金 单位：分
	private int deposit = 10000;// 玩家存款 单位：分
	private int gold = 100;// 玩家点券
	private int houseValue = 0;// 玩家房产 单位：分
	private int houseNum = 0;// 玩家房产数量 单位：分
	private int totalValue = 0;// 玩家总资产 单位：分
	private int location = 0;// 地点代表map1中的位置 从0到39，然后回到0
	private ArrayList haveProp = new ArrayList();// 记录道具的项目

	Player() {
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

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int d) {
		deposit += d;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int g) {
		gold += g;
	}

	public int getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(int h) {
		houseValue += h;
	}

	public int getTotalValue() {
		return houseValue + cash + deposit;
	}

	public void setTotalValue() {
		totalValue = cash + deposit + houseValue;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int m) {

		// 控制玩家的行走的路线和步数
		location += m;

		// 大于63时要取余
		location = location % 40;

		// 不能出现负数
		if (location < 0) {
			location += 40;
		}
	}



	public void setHaveProp(String a) {
		haveProp.add(a);
		// i++;
		// if (i >= 5) {
		// i = 0;
		// }
	}

	public ArrayList getHaveProp() {
		return haveProp;
	}

	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum() {
		houseNum++;
	}

	// 文件读取
	public void readCash(int cash) {
		this.cash = cash;
	}

	public void readDeposit(int deposit) {
		this.cash = cash;
	}

	public void readLocation(int location) {
		this.location = location;
	}

	public void readGold(int gold) {
		this.gold = gold;
	}

	public void readHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public void readHouseValue(int housevalue) {
		this.houseValue = houseValue;
	}

	public void readHaveProp(ArrayList haveProp) {
		this.haveProp = haveProp;
	}

	public void readTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	// public void readHaveProp(ArrayList wait) {
	// TODO Auto-generated method stub
	// this.haveProp = wait;
	// }
}
