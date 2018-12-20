package Project2;

import java.util.Iterator;
import java.util.List;

public class Land extends Square{
	private int block = 0;// 是否有路障 0无路障1有路障
	private int street = 0;// 所属街道
	private int houseLevel = 1;// 房屋等级
	private int houseValue[] = new int[5];// 房屋价值
	private String itemName = "";// 房屋名字
	private String owner = "无";// 房屋主人
	private int buyprice;
	private int status = 0;
	private int buildprice;
	private int[] paidmoney;
	List<Land> neighbour;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaidmoney() {
		return paidmoney[houseLevel];
	}

	public void setPaidmoney(int[] paidmoney) {
		this.paidmoney = paidmoney;
	}

	public int getBuyprice() {
		return buyprice;
	}

	public int[] getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(int[] houseValue) {
		this.houseValue = houseValue;
	}

	public void setBuyprice(int buyprice) {
		this.buyprice = buyprice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBuildprice() {
		return buildprice;
	}

	public void setBuildprice(int buildprice) {
		this.buildprice = buildprice;
	}

	public List<Land> getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(List<Land> neighbour) {
		this.neighbour = neighbour;
	}

	public void setHouseLevel(int houseLevel) {
		this.houseLevel = houseLevel;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	// public int getPosition() {
	// return position;
	// }
	//
	// public void setPosition(int p) {
	// position = p;
	// }

	public int getBlock() {
		return block;
	}

	public void setBlock(int b) {
		block = b;
	}

	public int getStreet() {
		return street;
	}

	public void setStreet(int s) {
		street = s;
	}

	public int getHouseLevel() {
		return houseLevel;
	}

	void build(Player a) {
		if (checkbuild()&&owner==a.getName()) {
			a.setCash(-buildprice);
			houseLevel += 1;
		} else {
			System.out.println("建造不合法");
			// reject
		}
	}

	boolean checkbuild() {
		Iterator it = neighbour.iterator();
		int max = houseLevel;
		int min = houseLevel;
		while (it.hasNext()) {
			Land tmp = (Land) it.next();
			if (tmp.houseLevel < min)
				min = tmp.houseLevel;
			if (tmp.houseLevel > max)
				max = tmp.houseLevel;
			if (tmp.owner == owner && tmp.status == 1) {
				continue;
			} else {
				return false;
			}

		}
		if (max - min > 1)
			return false;
		return true;
	}

	void demolish(Player a) {
		if (checkbuild()&&owner==a.getName()) {
			a.setCash(1 / 2 * buildprice);
			houseLevel-=1;
		} else {
			System.out.println("拆房不合法");
			// reject
		}
	}

	boolean checkdemolish() {
		Iterator it = neighbour.iterator();
		int max = houseLevel - 1;
		int min = houseLevel - 1;
		while (it.hasNext()) {
			Land tmp = (Land) it.next();
			if (tmp.houseLevel < min)
				min = tmp.houseLevel;
			if (tmp.houseLevel > max)
				max = tmp.houseLevel;
			if (tmp.owner == owner && tmp.status == 1) {
				continue;
			} else {
				return false;
			}

		}
		if (max - min > 1)
			return false;
		return true;
	}
	void mortgage(Player a) {
		if (checkmortgage()) {
			a.setCash(1 / 2 * buildprice);
			houseLevel-=1;
		} else {
			System.out.println("抵押不合法");
			// reject
		}
	}
	boolean checkmortgage()
	{
		Iterator it = neighbour.iterator();
		int max = houseLevel;
		int min = houseLevel;
		while (it.hasNext()) {
			Land tmp = (Land) it.next();
			if (tmp.houseLevel < min)
				min = tmp.houseLevel;
			if (tmp.houseLevel > max)
				max = tmp.houseLevel;
		}
		if (max ==0 && min==0 )
			return true;
		else
			return false;
	}
	public String getOwner() {
		return owner;
	}

	public void setOwner(String q) {
		this.owner = q;
	}

	// 构造函数
	public Land() {
	}

	// File read
	public void readOwner(String owner) {
		this.owner = owner;
	}

	public void readHouseValue(int[] houseValue) {
		this.houseValue = houseValue;
	}

	public void readLevel(int houseLevel) {
		this.houseLevel = houseLevel;
	}

	public void readItemName(String itemName) {
		this.itemName = itemName;
	}
}
