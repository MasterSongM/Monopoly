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
	private JTextField jtfCash = new JTextField("请输入存款金额");
	private JTextField jtfDecash = new JTextField("请输入取款金额");
	JLabel tipBuy = new JLabel();
	JLabel tipLevel = new JLabel();
	JLabel tipDo = new JLabel();
	static JButton btLandOK = new JButton("OK");
	static JButton btLandNO = new JButton("NO");
	static JButton btCash = new JButton("存款");
	static JButton btDecash = new JButton("取款");
	private int location;
	private Player p;
	private Player np;
	static Location[] land = new Location[38];
	static {
		for (int i = 0; i < 38; i++) {
			land[i] = new Location();
		}
		// 初始化土地类型
		land[0].setItemName("类型：空地");
		land[1].setItemName("类型：空地");
		land[2].setItemName("恭喜你，获得10个点券！！");
		land[3].setItemName("欢迎来到艾尔·马强银行~~~~");
		land[4].setItemName("欢迎来到大富翁道具店，\n这里你可以买到你想要的\n各种道具，哈哈，进来玩玩吧！!");
		land[5].setItemName("欢迎来到金星1号，\n这里可是一块好地");
		land[6].setItemName("欢迎来到金星2号，这里是一块好地，");
		land[7].setItemName("欢迎来到金星3号，这里是一块好地，");
		land[8].setItemName("欢迎来到金星4号，这里是一块好地，");
		land[9].setItemName("恭喜你，获得10个点券！！");
		land[10].setItemName("恭喜你，获得30个点券！！");
		land[11].setItemName("欢迎来到木星1号，这里是一块好地，");
		land[12].setItemName("欢迎来到木星2号，这里是一块好地，");
		land[13].setItemName("这里是东拉西扯电视台，\n下面为你随机播报一则新闻~~");
		land[14].setItemName("欢迎来到木星3号，这里是一块好地，");
		land[15].setItemName("欢迎来到木星4号，这里是一块好地，");
		land[16].setItemName("欢迎来到水星1号，这里是一块好地，");
		land[17].setItemName("欢迎来到水星2号，这里是一块好地，");
		land[18].setItemName("欢迎来到土豪福利彩票店，你可以选择购买彩票！");
		// land[19]空地
		land[20].setItemName("恭喜你，获得50个点券！！");
		land[21].setItemName("欢迎来到水星3号，这里是一块好地，");
		land[22].setItemName("欢迎来到水星4号，这里是一块好地，");
		land[23].setItemName("欢迎来到火星1号，这里是一块好地，");
		land[24].setItemName("欢迎来到火星2号，这里是一块好地，");
		land[25].setItemName("欢迎来到火星3号，这里是一块好地，");
		land[26].setItemName("恭喜你，获得10个点券！！");
		land[27].setItemName("欢迎来到大富翁银行~~~~");
		land[28].setItemName("欢迎来到大富翁道具店，\n这里你可以买到你想要的\n各种道具，进来玩玩吧！!");
		land[29].setItemName("欢迎来到土豪福利彩票店，你可以选择购买彩票！");
		land[30].setItemName("恭喜你，获得30个点券！！");
		land[31].setItemName("这里是东拉西扯电视台，\n下面为你随机播报一则新闻~~");
		// 空地
		land[33].setItemName("欢迎来到火星4号，这里是一块好地，");
		land[34].setItemName("欢迎来到土星1号，这里是一块好地，");
		land[35].setItemName("欢迎来到土星2号，这里是一块好地，");
		land[36].setItemName("欢迎来到土星3号，这里是一块好地，");
		land[37].setItemName("欢迎来到土星4号，这里是一块好地，");
	}

	public FireEvent() {
	}

	public <CloseWindowIn5> FireEvent(int location, Player p, Player np) {
		this.location = location;
		this.p = p;
		setLayout(null);
		switch (location) {
		// 可出售的房产
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

			// 当所在的土地为自己的房产时
			if (land[location].getOwner() == p.getName()) {
				int option = JOptionPane.showConfirmDialog(null,
						land[location].getItemName() + "\n当前等级："
								+ land[location].getHouseLevel() + ",   拥有者： "
								+ land[location].getOwner() + "\n     你想升级它吗？");

				if (option == 0) {
					// 判断现金是否足够
					if (p.getCash() > 500) {
						// 判断房产是不是达到级别的上限
						if (land[location].getHouseValue() >= 5) {
							JOptionPane
									.showMessageDialog(null, "您的房产已经达到最高级别！");
						} else {
							JOptionPane.showMessageDialog(null,
									"恭喜，花费500元升级成功！");
							land[location].setHouseLevel(p);
							p.setCash(-500);
							p.setHouseValue(1000);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"对不起，你的现金不足，无法升级该土地");
					}
				}

			}// 当土地尚未售出时
			else if (land[location].getOwner().equals("无")) {
				int option = JOptionPane.showConfirmDialog(null,
						land[location].getItemName() + "\n当前等级："
								+ land[location].getHouseLevel() + ",   拥有者： "
								+ land[location].getOwner() + "\n     你想买下它吗？");
				// 判断现金是否足够
				if (p.getCash() > 1000) {
					JOptionPane.showMessageDialog(null, "恭喜，花费1000购得该土地");
					land[location].setOwner(p.getName());
					p.setCash(-1000);
					p.setHouseNum();
					p.setHouseValue(1000);
				} else {
					JOptionPane
							.showMessageDialog(null, "对步起，你的现金不足，暂且无法购买该土地！");

				}
			}
			// }
			else {
				// 当所在地为别人的房产时
				int roadMoney = 200 * land[location].getHouseLevel();

				// 判断现金是否够用于支付过路费
				if (p.getCash() >= roadMoney) {
					JOptionPane.showMessageDialog(null, "啊哦，你踩在了别人的地盘上，"
							+ "\n被强行交了" + roadMoney + "的过路费~~555");
					p.setCash(-roadMoney);
					np.setCash(roadMoney);
				}
				// 现金不够用时要用存款来支付过路费
				else {
					int lastMoney = roadMoney - p.getCash();
					if (p.getDeposit() >= lastMoney) {
						JOptionPane.showMessageDialog(null,
								"啊哦，你踩在了别人的地盘上，并且你的现金" + "\n不够用，所以从你的存款中提取一部分"
										+ "\n被强行交了" + roadMoney + "的过路费~~555");
						p.setCash(-p.getCash());
						p.setDeposit(-lastMoney);
						np.setCash(roadMoney);
					} else {
						// 当存款也不够用于支付过路费时。卖房子
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
									"对不起，您的家产都被卖光交钱了，您以破产" + "\n恭喜玩家"
											+ np.getName() + "获胜！！！");
							System.exit(0);
						}
					}
				}
			}
		}

			break;
		// 银行
		case 3:
			break;
		// the 赠送点券点
		case 2:
		case 9:
		case 26:
			JOptionPane.showMessageDialog(jtfDecash, "恭喜你获得10点券！");
			p.setGold(10);
			break;
		// the prop shop
		case 4:
		
		// the 赠送点券点
		case 10:
		case 30:
			JOptionPane.showMessageDialog(null, "恭喜你获得30点券！");
			p.setGold(30);
			break;
		// the 赠送点券点
		case 20:
			JOptionPane.showMessageDialog(null, "恭喜你获得50点券！");
			p.setGold(50);
			break;
		// 新闻
		case 13:
		case 31:
			int random = (int) (Math.random() * 4 + 1);
			if (random == 1) {
				// event1 : 公开奖励房产最多的玩家
				if (p.getHouseValue() > np.getHouseValue()) {
					p.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"公开奖励房产最多的玩家" + p.getName() + "1000元");
				}// 如果俩人房产相同时
				else if (p.getHouseValue() == np.getHouseValue()) {
					int ii = (int) (Math.random() * 2 + 1);
					if (ii == 1) {
						p.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"公开奖励幸运的玩家" + p.getName() + "1000元");
					} else {
						np.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"公开奖励幸运的玩家" + np.getName() + "1000元");
					}
				} else {
					np.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"公开奖励房产最多的玩家" + np.getName() + "1000元");
				}
			}
			if (random == 2) {
				// event1 : 公开补助房产最少的玩家
				if (p.getHouseValue() > np.getHouseValue()) {
					np.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"公开补助房产最少的玩家" + np.getName() + "1000元");
				}// 如果俩人房产相同时
				else if (p.getHouseValue() == np.getHouseValue()) {
					int ii = (int) (Math.random() * 2 + 1);
					if (ii == 1) {
						p.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"公开奖励幸运的玩家" + p.getName() + "1000元");
					} else {
						np.setCash(1000);
						JOptionPane.showMessageDialog(null,
								"公开奖励幸运的玩家" + np.getName() + "1000元");
					}
				} else {
					p.setCash(1000);
					JOptionPane.showMessageDialog(null,
							"公开补助房产最少的玩家" + p.getName() + "1000元");
				}
			}
			if (random == 3) {
				// event3 :
				JOptionPane.showMessageDialog(null, "天上掉馅饼了，每人奖励10%的利息");
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
		JOptionPane.showMessageDialog(null, "土地卡使用成功，这块地是你的了！");

	}

	public void run() {
		try {
			Thread.sleep(2000);
			setVisible(false); // 关闭窗口
			// System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// 买地
		// if (e.getSource() == btLandOK) {
		// if (p.getCash() < 1000) {
		// JOptionPane.showMessageDialog(null, "额，土豪，先去取点钱吧，你的钱不多了。。");
		// this.setVisible(false);
		// } else {
		// land[location].setOwner(p.getName());
		// p.setCash(-1000);
		// this.setVisible(false);
		// JOptionPane.showMessageDialog(null, "恭喜！购买成功！");
		// }
		// }
		// // 不买地
		// if (e.getSource() == btLandNO) {
		// this.setVisible(false);
		// }

	}

}
