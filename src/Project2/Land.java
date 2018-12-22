package Project2;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class Land extends Square{
	private int block = 0;// �Ƿ���·�� 0��·��1��·��
	private int street = 0;// �����ֵ�
	private int houseLevel = 0;// ���ݵȼ�
	private int houseValue[] = new int[5];// ���ݼ�ֵ
	private String itemName = "";// ��������
	private Player owner;// ��������
	private int buyprice;
	private int status = 0;//1��ʾ��Ѻ״̬
	private int buildprice;
	private int[] paidmoney = new int[5];
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
		if (checkbuild() && owner.getName() == a.getName()&& houseLevel<5) {
			a.setCash(-buildprice);
			houseLevel += 1;
		} else {
			JOptionPane.showMessageDialog(null,"���첻�Ϸ�");
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
		if (checkbuild() && owner.getName() ==a.getName()&& houseLevel>=1) {
			a.setCash(1 / 2 * buildprice);
			houseLevel-=1;
		} else {
			JOptionPane.showMessageDialog(null,"�𷿲��Ϸ�");
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
		if (checkmortgage()&&status==1) {
			a.setCash(1 / 2 * buildprice);
			status=0;
		} else {
			JOptionPane.showMessageDialog(null,"��Ѻ���Ϸ�");
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
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player q) {
		this.owner = q;
	}

	// ���캯��
	public Land() {
	}

	public boolean collect(Player p) {
		if(p.getCash() < this.getPaidmoney())	return false;
		else {
			p.setCash(-this.getPaidmoney());
			this.owner.setCash(this.getPaidmoney());
			return true;
		}
	}

}
