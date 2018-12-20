package Project2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FireEvent extends JPanel implements ActionListener, Runnable {
	int conPlay = BigMap.conPlay;
	BigMap listenerMoney1 = new BigMap();
	BigMap.TimerPropMoneyListener listenerMoney2 = listenerMoney1.new TimerPropMoneyListener();
	private JLabel tipMessage = new JLabel();
	private JPanel panel = new JPanel();
	private JTextField jtfCash = new JTextField("����������");
	private JTextField jtfDecash = new JTextField("������ȡ����");
	JLabel tipBuy = new JLabel();
	JLabel tipLevel = new JLabel();
	JLabel tipDo = new JLabel();
	static JButton btLandOK = new JButton("OK");
	static JButton btLandNO = new JButton("NO");
	static JButton btCash = new JButton("���");
	static JButton btDecash = new JButton("ȡ��");
	private int location;
	private Player p;
	private Player np;
	static Location[] land = new Location[38];
	static {
		for (int i = 0; i < 38; i++) {
			land[i] = new Location();
		}
		// ��ʼ����������
		land[0].setItemName("���ͣ��յ�");
		land[1].setItemName("���ͣ��յ�");
		land[2].setItemName("��ϲ�㣬���10����ȯ����");
		land[3].setItemName("��ӭ������������ǿ����~~~~");
		land[4].setItemName("��ӭ�������̵��ߵ꣬\n���������������Ҫ��\n���ֵ��ߣ���������������ɣ�!");
		land[5].setItemName("��ӭ��������1�ţ�\n�������һ��õ�");
		land[6].setItemName("��ӭ��������2�ţ�������һ��õأ�");
		land[7].setItemName("��ӭ��������3�ţ�������һ��õأ�");
		land[8].setItemName("��ӭ��������4�ţ�������һ��õأ�");
		land[9].setItemName("��ϲ�㣬���10����ȯ����");
		land[10].setItemName("��ϲ�㣬���30����ȯ����");
		land[11].setItemName("��ӭ����ľ��1�ţ�������һ��õأ�");
		land[12].setItemName("��ӭ����ľ��2�ţ�������һ��õأ�");
		land[13].setItemName("�����Ƕ�����������̨��\n����Ϊ���������һ������~~");
		land[14].setItemName("��ӭ����ľ��3�ţ�������һ��õأ�");
		land[15].setItemName("��ӭ����ľ��4�ţ�������һ��õأ�");
		land[16].setItemName("��ӭ����ˮ��1�ţ�������һ��õأ�");
		land[17].setItemName("��ӭ����ˮ��2�ţ�������һ��õأ�");
		land[18].setItemName("��ӭ��������������Ʊ�꣬�����ѡ�����Ʊ��");
		// land[19]�յ�
		land[20].setItemName("��ϲ�㣬���50����ȯ����");
		land[21].setItemName("��ӭ����ˮ��3�ţ�������һ��õأ�");
		land[22].setItemName("��ӭ����ˮ��4�ţ�������һ��õأ�");
		land[23].setItemName("��ӭ��������1�ţ�������һ��õأ�");
		land[24].setItemName("��ӭ��������2�ţ�������һ��õأ�");
		land[25].setItemName("��ӭ��������3�ţ�������һ��õأ�");
		land[26].setItemName("��ϲ�㣬���10����ȯ����");
		land[27].setItemName("��ӭ������������~~~~");
		land[28].setItemName("��ӭ�������̵��ߵ꣬\n���������������Ҫ��\n���ֵ��ߣ���������ɣ�!");
		land[29].setItemName("��ӭ��������������Ʊ�꣬�����ѡ�����Ʊ��");
		land[30].setItemName("��ϲ�㣬���30����ȯ����");
		land[31].setItemName("�����Ƕ�����������̨��\n����Ϊ���������һ������~~");
		// �յ�
		land[33].setItemName("��ӭ��������4�ţ�������һ��õأ�");
		land[34].setItemName("��ӭ��������1�ţ�������һ��õأ�");
		land[35].setItemName("��ӭ��������2�ţ�������һ��õأ�");
		land[36].setItemName("��ӭ��������3�ţ�������һ��õأ�");
		land[37].setItemName("��ӭ��������4�ţ�������һ��õأ�");
	}

	public FireEvent() {
	}

	public <CloseWindowIn5> FireEvent(int location, Player p, Player np) {
		this.location = location;
		this.p = p;
		setLayout(null);
		switch (location) {
		// �ɳ��۵ķ���
		case 5:
		case 6:
		case 7:
		case 8:
		case 11:
		case 12:
		case 14:
		case 15:
		case 16:
		case 17:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 33:
		case 34:
		case 35:
		case 36:
		case 37: {

			// �����ڵ�����Ϊ�Լ��ķ���ʱ
			if (land[location].getOwner() == p.getName()) {
				int option = JOptionPane.showConfirmDialog(null,
						land[location].getItemName() + "\n��ǰ�ȼ���"
								+ land[location].getHouseLevel() + ",   ӵ���ߣ� "
								+ land[location].getOwner() + "\n     ������������");

				if (option == 0) {
					// �ж��ֽ��Ƿ��㹻
					if (p.getCash() > 500) {
						// �жϷ����ǲ��Ǵﵽ���������
						if (land[location].getHouseValue() >= 5) {
							JOptionPane
									.showMessageDialog(null, "���ķ����Ѿ��ﵽ��߼���");
						} else {
							JOptionPane.showMessageDialog(null,
									"��ϲ������500Ԫ�����ɹ���");
							land[location].setHouseLevel(p);
							p.setCash(-500);
							p.setHouseValue(1000);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"�Բ�������ֽ��㣬�޷�����������");
					}
				}

			}// ��������δ�۳�ʱ
			else if (land[location].getOwner().equals("��")) {
				int option = JOptionPane.showConfirmDialog(null,
						land[location].getItemName() + "\n��ǰ�ȼ���"
								+ land[location].getHouseLevel() + ",   ӵ���ߣ� "
								+ land[location].getOwner() + "\n     ������������");
				// �ж��ֽ��Ƿ��㹻
				if (p.getCash() > 1000) {
					JOptionPane.showMessageDialog(null, "��ϲ������1000���ø�����");
					land[location].setOwner(p.getName());
					p.setCash(-1000);
					p.setHouseNum();
					p.setHouseValue(1000);
				} else {
					JOptionPane
							.showMessageDialog(null, "�Բ�������ֽ��㣬�����޷���������أ�");

				}
			}
			// }
			else {
				// �����ڵ�Ϊ���˵ķ���ʱ
				int roadMoney = 200 * land[location].getHouseLevel();

				// �ж��ֽ��Ƿ�����֧����·��
				if (p.getCash() >= roadMoney) {
					JOptionPane.showMessageDialog(null, "��Ŷ��������˱��˵ĵ����ϣ�"
							+ "\n��ǿ�н���" + roadMoney + "�Ĺ�·��~~555");
					p.setCash(-roadMoney);
					np.setCash(roadMoney);
				}
				// �ֽ𲻹���ʱҪ�ô����֧����·��
				else {
					int lastMoney = roadMoney - p.getCash();
					if (p.getDeposit() >= lastMoney) {
						JOptionPane.showMessageDialog(null,
								"��Ŷ��������˱��˵ĵ����ϣ���������ֽ�" + "\n�����ã����Դ���Ĵ������ȡһ����"
										+ "\n��ǿ�н���" + roadMoney + "�Ĺ�·��~~555");
						p.setCash(-p.getCash());
						p.setDeposit(-lastMoney);
						np.setCash(roadMoney);
					} else {
						// �����Ҳ��������֧����·��ʱ��������
						boolean haveHouse = false;
						int leastMoney = roadMoney - p.getCash()
								- p.getDeposit();
						for (int i = 0; i < 38; i++) {
							if (land[i].getOwner() == (p.getName())) {
								p.setCash(-p.getCash());
								p.setDeposit(-p.getDeposit());
								np.setCash(roadMoney);
								haveHouse = true;
								land[i].setOwner(p.getName());
								int houseSellMoney = 1000 * land[i]
										.getHouseLevel();
								int theLittleMoney = houseSellMoney
										- leastMoney;
								p.setCash(theLittleMoney);
							}
						}
						if (haveHouse == false) {
							JOptionPane.showMessageDialog(
									null,
									"�Բ������ļҲ��������⽻Ǯ�ˣ������Ʋ�" + "\n��ϲ���"
											+ np.getName() + "��ʤ������");
							System.exit(0);
						}
					}
				}
			}
		}

			break;
		// ����
		case 3:
			break;
		// the ���͵�ȯ��
		case 2:
		case 9:
		case 26:
			JOptionPane.showMessageDialog(jtfDecash, "��ϲ����10��ȯ��");
			p.setGold(10);
			break;
		// the prop shop
		case 4:
		
		// the ���͵�ȯ��
		case 10:
		case 30:
			JOptionPane.showMessageDialog(null, "��ϲ����30��ȯ��");
			p.setGold(30);
			break;
		// the ���͵�ȯ��
		case 20:
			JOptionPane.showMessageDialog(null, "��ϲ����50��ȯ��");
			p.setGold(50);
			break;
		// ����
		case 13:
		case 31:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 1) {
				// event1 : �������������������
				if (p.getHouseValue() > np.getHouseValue()) {
					p.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"�������������������" + p.getName() + "1000Ԫ");
				}// ������˷�����ͬʱ
				else if (p.getHouseValue() == np.getHouseValue()) {
					int ii = (int) (Math.random() * 2 + 1);
					if (ii == 1) {
						p.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"�����������˵����" + p.getName() + "1000Ԫ");
					} else {
						np.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"�����������˵����" + np.getName() + "1000Ԫ");
					}
				} else {
					np.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"�������������������" + np.getName() + "1000Ԫ");
				}
			}
			if (random == 2) {
				// event1 : ���������������ٵ����
				if (p.getHouseValue() > np.getHouseValue()) {
					np.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"���������������ٵ����" + np.getName() + "1000Ԫ");
				}// ������˷�����ͬʱ
				else if (p.getHouseValue() == np.getHouseValue()) {
					int ii = (int) (Math.random() * 2 + 1);
					if (ii == 1) {
						p.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"�����������˵����" + p.getName() + "1000Ԫ");
					} else {
						np.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"�����������˵����" + np.getName() + "1000Ԫ");
					}
				} else {
					p.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"���������������ٵ����" + p.getName() + "1000Ԫ");
				}
			}
			if (random == 3) {
				// event3 :
				JOptionPane.showMessageDialog(null, "���ϵ��ڱ��ˣ�ÿ�˽���10%����Ϣ");
				p.setDeposit((int) (p.getDeposit() * 0.1));
				np.setDeposit((int) (np.getDeposit() * 0.1));
			}
			
		default:
		}
	}

	public void UseLandProp(int location, Player p) {
		this.location = location;
		this.p = p;
		land[location].setOwner(p.getName());
		JOptionPane.showMessageDialog(null, "���ؿ�ʹ�óɹ�������������ˣ�");

	}

	public void run() {
		try {
			Thread.sleep(2000);
			setVisible(false); // �رմ���
			// System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// ���
		// if (e.getSource() == btLandOK) {
		// if (p.getCash() < 1000) {
		// JOptionPane.showMessageDialog(null, "���������ȥȡ��Ǯ�ɣ����Ǯ�����ˡ���");
		// this.setVisible(false);
		// } else {
		// land[location].setOwner(p.getName());
		// p.setCash(-1000);
		// this.setVisible(false);
		// JOptionPane.showMessageDialog(null, "��ϲ������ɹ���");
		// }
		// }
		// // �����
		// if (e.getSource() == btLandNO) {
		// this.setVisible(false);
		// }

	}

}
