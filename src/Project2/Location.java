package Project2;

public class Location {
	private int block = 0;// �Ƿ���·�� 0��·��1��·��
	private int street = 0;// �����ֵ�
	private int houseLevel = 1;// ���ݵȼ�
	private int houseValue = 0;// ���ݼ�ֵ
	private String itemName = "";// ��������
	private String owner = "��";// ��������

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

		// �����������������ֻ��������6��
		++houseLevel;

		// ��Ϊ��ҳ��Խ�������ʱ���Ѿ�����������ط��ã����ԣ���Ȼû�ܹ������ɹ����Ͳ�����ȡ����ط���
		if (this.houseLevel > 6) {
			System.out.println("�ȵȣ��÷����Ѿ��ﵽ��߼����ˣ������������ˣ��������յ�500Ԫ��");
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

	// ���캯��
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
