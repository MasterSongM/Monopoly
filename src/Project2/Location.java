package Project2;

public class Location {
	private int block = 0;// 是否有路障 0无路障1有路障
	private int street = 0;// 所属街道
	private int houseLevel = 1;// 房屋等级
	private int houseValue = 0;// 房屋价值
	private String itemName = "";// 房屋名字
	private String owner = "无";// 房屋主人

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

	public void setHouseLevel(Player a) {

		// 房产的升级，且最多只能升级到6级
		++houseLevel;

		// 因为玩家尝试进行升级时就已经收了他的相关费用，所以，既然没能够升级成功，就不能收取其相关费用
		if (this.houseLevel > 6) {
			System.out.println("等等，该房产已经达到最高级别了，不能再升级了！退您刚收的500元。");
			this.houseLevel = 6;
			this.houseValue -= 1000;
			a.setCash(+500);
		}
	}

	public int getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(int h) {
		houseValue += h;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String q) {
		this.owner = q;
	}

	// 构造函数
	public Location() {
	}

	// File read
	public void readOwner(String owner) {
		this.owner = owner;
	}

	public void readHouseValue(int houseValue) {
		this.houseValue = houseValue;
	}

	public void readLevel(int houseLevel) {
		this.houseLevel = houseLevel;
	}

	public void readItemName(String itemName) {
		this.itemName = itemName;
	}
}
