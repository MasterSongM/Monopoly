package Project2;

import java.util.*;

public class Player {
	static int i = 0;
	static int j = 0;
	private String name = "";// ��¼�������
	public static String[] nameOfPlayer = { "", "" };// ��¼�����������
	private int cash = 10000;// ����ֽ� ��λ����
	private int deposit = 10000;// ��Ҵ�� ��λ����
	private int gold = 100;// ��ҵ�ȯ
	private int houseValue = 0;// ��ҷ��� ��λ����
	private int houseNum = 0;// ��ҷ������� ��λ����
	private int totalValue = 0;// ������ʲ� ��λ����
	private int location = 0;// �ص����map1�е�λ�� ��0��39��Ȼ��ص�0
	private ArrayList haveProp = new ArrayList();// ��¼���ߵ���Ŀ

	Player() {
	}

	// ���涼�Ǹ���ֵ��GET,SET
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

		// ������ҵ����ߵ�·�ߺͲ���
		location += m;

		// ����63ʱҪȡ��
		location = location % 40;

		// ���ܳ��ָ���
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

	// �ļ���ȡ
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
