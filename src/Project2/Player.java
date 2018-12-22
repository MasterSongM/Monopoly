package Project2;

import java.util.*;

public class Player {
	int jailDays = 0;
	private String name = "";// ��¼�������
	private int cash = 3456;// ����ֽ� ��λ����
	//private int gold = 100;// ��ҵ�ȯ
	private int houseNum = 0;// ��ҷ������� ��λ����
	private int location = 0;// �ص����map1�е�λ�� ��0��39��Ȼ��ص�0

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

		// ������ҵ����ߵ�·�ߺͲ���
		location += m;

		// ����40ʱҪȡ��
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

//getters & setters ����ĺ���
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
