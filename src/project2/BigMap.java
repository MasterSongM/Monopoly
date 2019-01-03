package project2;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class BigMap extends JPanel {// implements java.io.Serializable {

	public int pNum = 2;// 玩家数量
	Dice diceObject1 = new Dice();
	Dice diceObject2 = new Dice();
	Dice diceObject3 = new Dice();
	
	boolean isForwarding = false;
	// The background map
	int conDice = 0;
	static List<Square> squares = new SquareList().intial();
	static List<Player> players = new ArrayList<Player>();// 出于对动态选择玩家数量的考虑，目前尚未开发
	private List<JLabel> own = new ArrayList<JLabel>();// 对每块地设置拥有者的标签
	private List<JLabel> squareView = new ArrayList<JLabel>();// 40块地的标签
	// Operation Icon to Squares
	private ImageIcon tradeIcon = new ImageIcon("icons/buttons/1trade.png");
	private List<JButton> tradeArray = new ArrayList<JButton>();// 用于提示哪些地可以交易的trade Button 列表
	private ImageIcon buildIcon = new ImageIcon("icons/buttons/1build.png");
	private List<JButton> buildArray = new ArrayList<JButton>();// 用于提示哪些地可以建造的build Button 列表
	private ImageIcon sellIcon = new ImageIcon("icons/buttons/1sell.png");
	private List<JButton> sellArray = new ArrayList<JButton>();// 用于提示哪些地可以卖房的sell Button 列表
	private ImageIcon mortIcon = new ImageIcon("icons/buttons/1mort.png");
	private List<JButton> mortArray = new ArrayList<JButton>();// 用于提示哪些地可以抵押的mortgage Button 列表
	private ImageIcon redeemIcon = new ImageIcon("icons/buttons/1redeem.png");
	private List<JButton> redeemArray = new ArrayList<JButton>();// 用于提示哪些地可以赎回的redeem Button 列表
	{
		for (int q = 0; q < pNum; q++) {
			players.add(new Player());
		}
	}
	static int conPlay = 0;
	static int playTimes = 0;
	static int playerNum = 0;
	static int diceTimes = 0;// 连续掷骰子次数
	static int diceResult;
	static int diceResult1;
	static int diceResult2;
	static int diceResult3;
	static int sqr = 72;// Square size
	int i;// = a.getLocation() + 1; // - diceResult + 1;
	int i2;// = 20 + b.getLocation();// - diceResult;
	// 初始化玩家土地的位置信息
	int[][] bounds = { { sqr * 11, sqr * 11, sqr, sqr }, { sqr * 10, sqr * 11, sqr, sqr },
			{ sqr * 9, sqr * 11, sqr, sqr }, { sqr * 8, sqr * 11, sqr, sqr }, { sqr * 7, sqr * 11, sqr, sqr },
			{ sqr * 6, sqr * 11, sqr, sqr }, { sqr * 5, sqr * 11, sqr, sqr }, { sqr * 4, sqr * 11, sqr, sqr },
			{ sqr * 3, sqr * 11, sqr, sqr }, { sqr * 2, sqr * 11, sqr, sqr }, { sqr * 1, sqr * 11, sqr, sqr }, // 0-10
			{ sqr * 1, sqr * 10, sqr, sqr }, { sqr * 1, sqr * 9, sqr, sqr }, { sqr * 1, sqr * 8, sqr, sqr },
			{ sqr * 1, sqr * 7, sqr, sqr }, { sqr * 1, sqr * 6, sqr, sqr }, { sqr * 1, sqr * 5, sqr, sqr },
			{ sqr * 1, sqr * 4, sqr, sqr }, { sqr * 1, sqr * 3, sqr, sqr }, { sqr * 1, sqr * 2, sqr, sqr },
			{ sqr * 1, sqr * 1, sqr, sqr }, // 11-20
			{ sqr * 2, sqr * 1, sqr, sqr }, { sqr * 3, sqr * 1, sqr, sqr }, { sqr * 4, sqr * 1, sqr, sqr },
			{ sqr * 5, sqr * 1, sqr, sqr }, { sqr * 6, sqr * 1, sqr, sqr }, { sqr * 7, sqr * 1, sqr, sqr },
			{ sqr * 8, sqr * 1, sqr, sqr }, { sqr * 9, sqr * 1, sqr, sqr }, { sqr * 10, sqr * 1, sqr, sqr },
			{ sqr * 11, sqr * 1, sqr, sqr }, // 21-30
			{ sqr * 11, sqr * 2, sqr, sqr }, { sqr * 11, sqr * 3, sqr, sqr }, { sqr * 11, sqr * 4, sqr, sqr },
			{ sqr * 11, sqr * 5, sqr, sqr }, { sqr * 11, sqr * 6, sqr, sqr }, { sqr * 11, sqr * 7, sqr, sqr },
			{ sqr * 11, sqr * 8, sqr, sqr }, { sqr * 11, sqr * 9, sqr, sqr }, { sqr * 11, sqr * 10, sqr, sqr } };

	// 附加Button的位置
	int[][] bbs = new int[40][4];
	{
		int c = 0;
		while (c < 10) {
			bbs[c][0] = bounds[c][0] + 16;
			bbs[c][1] = bounds[c][1] - 32;
			bbs[c][2] = 32;
			bbs[c][3] = 32;
			c++;
		}
		while (c < 20) {
			bbs[c][0] = bounds[c][0] + 70;
			bbs[c][1] = bounds[c][1] + 16;
			bbs[c][2] = 32;
			bbs[c][3] = 32;
			c++;
		}
		while (c < 30) {
			bbs[c][0] = bounds[c][0] + 16;
			bbs[c][1] = bounds[c][1] + 70;
			bbs[c][2] = 32;
			bbs[c][3] = 32;
			c++;
		}
		while (c < 40) {
			bbs[c][0] = bounds[c][0] - 32;
			bbs[c][1] = bounds[c][1] + 16;
			bbs[c][2] = 32;
			bbs[c][3] = 32;
			c++;
		}
	}

	static int diceNumber = 0;
	private ImageIcon bigMap = new ImageIcon("icons/start/background.png");
	// All kinds of buttons
	private ImageIcon sqrImg = new ImageIcon("icons/buttons/square.png");
	
	//使用卡片图
	private ImageIcon cardIcon = new ImageIcon("icons/buttons/useCard.png");
	private JButton card = new JButton(cardIcon);
	private CardListener cardListen = new CardListener();
	{
		card.setBounds(400, 280, 166, 99);
		card.addActionListener(cardListen);
	}

	// Players
	int px1 = 10, py1 = 5;// 格子内的偏移
	// private JPanel playPanel1 = new JPanel(new GridBagLayout());
	private ImageIcon player0 = new ImageIcon("icons/players/player1.png");
	PlayerPanel pp1 = new PlayerPanel(players.get(0), player0);

	int px2 = 10, py2 = 35;
	private ImageIcon player1 = new ImageIcon("icons/players/player2.png");
	PlayerPanel pp2 = new PlayerPanel(players.get(1), player1);

	/*int px3 = 10, py3 = 35;
	private ImageIcon player3 = new ImageIcon("icons/players/player3.png");

	int px4 = 10, py4 = 35;
	private ImageIcon player4 = new ImageIcon("icons/players/player4.png");
*/
	// the dices
	private List<ImageIcon> diceIcons = new ArrayList<ImageIcon>();
	private ImageIcon dice_0 = new ImageIcon("icons/dice0.png");
	private ImageIcon dice_1 = new ImageIcon("icons/dice1.png");
	private ImageIcon dice_2 = new ImageIcon("icons/dice2.png");
	private ImageIcon dice_3 = new ImageIcon("icons/dice3.png");
	private ImageIcon dice_4 = new ImageIcon("icons/dice4.png");
	private ImageIcon dice_5 = new ImageIcon("icons/dice5.png");
	private ImageIcon dice_6 = new ImageIcon("icons/dice6.png");
	{
		diceIcons.add(dice_0);
		diceIcons.add(dice_1);
		diceIcons.add(dice_2);
		diceIcons.add(dice_3);
		diceIcons.add(dice_4);
		diceIcons.add(dice_5);
		diceIcons.add(dice_6);
	}
	
	private ImageIcon randomIcon = new ImageIcon("icons/buttons/random.png");//随机事件土地的图标
	private ImageIcon q20Icon = new ImageIcon("icons/buttons/quan20.png");
	private ImageIcon q30Icon = new ImageIcon("icons/buttons/quan30.png");
	private ImageIcon q50Icon = new ImageIcon("icons/buttons/quan50.png");

	// 房子等级图标
	private ImageIcon mortgaged = new ImageIcon("icons/buttons/mortgaged.png");
	private ImageIcon house0 = new ImageIcon("icons/buttons/0house.png");
	private ImageIcon house1 = new ImageIcon("icons/buttons/1house.png");
	private ImageIcon house2 = new ImageIcon("icons/buttons/2house.png");
	private ImageIcon house3 = new ImageIcon("icons/buttons/3house.png");
	private ImageIcon house4 = new ImageIcon("icons/buttons/4house.png");
	private ImageIcon house5 = new ImageIcon("icons/buttons/5house.png");

	// 24个拥有者标签
	private JLabel own00 = new JLabel();
	private JLabel own01 = new JLabel();
	private JLabel own02 = new JLabel();
	private JLabel own03 = new JLabel();
	private JLabel own04 = new JLabel();
	private JLabel own05 = new JLabel();
	private JLabel own06 = new JLabel();
	private JLabel own07 = new JLabel();
	private JLabel own08 = new JLabel();
	private JLabel own09 = new JLabel();

	private JLabel own10 = new JLabel();
	private JLabel own11 = new JLabel();
	private JLabel own12 = new JLabel();
	private JLabel own13 = new JLabel();
	private JLabel own14 = new JLabel();
	private JLabel own15 = new JLabel();
	private JLabel own16 = new JLabel();
	private JLabel own17 = new JLabel();
	private JLabel own18 = new JLabel();
	private JLabel own19 = new JLabel();

	private JLabel own20 = new JLabel();
	private JLabel own21 = new JLabel();
	private JLabel own22 = new JLabel();
	private JLabel own23 = new JLabel();
	private JLabel own24 = new JLabel();
	private JLabel own25 = new JLabel();
	private JLabel own26 = new JLabel();
	private JLabel own27 = new JLabel();
	private JLabel own28 = new JLabel();
	private JLabel own29 = new JLabel();

	private JLabel own30 = new JLabel();
	private JLabel own31 = new JLabel();
	private JLabel own32 = new JLabel();
	private JLabel own33 = new JLabel();
	private JLabel own34 = new JLabel();
	private JLabel own35 = new JLabel();
	private JLabel own36 = new JLabel();
	private JLabel own37 = new JLabel();
	private JLabel own38 = new JLabel();
	private JLabel own39 = new JLabel();

	{
		own.add(own00);
		own.add(own01);
		own.add(own02);
		own.add(own03);
		own.add(own04);
		own.add(own05);
		own.add(own06);
		own.add(own07);
		own.add(own08);
		own.add(own09);
		own.add(own10);
		own.add(own11);
		own.add(own12);
		own.add(own13);
		own.add(own14);
		own.add(own15);
		own.add(own16);
		own.add(own17);
		own.add(own18);
		own.add(own19);
		own.add(own20);
		own.add(own21);
		own.add(own22);
		own.add(own23);
		own.add(own24);
		own.add(own25);
		own.add(own26);
		own.add(own27);
		own.add(own28);
		own.add(own29);
		own.add(own30);
		own.add(own31);
		own.add(own32);
		own.add(own33);
		own.add(own34);
		own.add(own35);
		own.add(own36);
		own.add(own37);
		own.add(own38);
		own.add(own39);
	}

	// Operations to Buttons
	// tButton 1-9
	JButton tButton01 = new JButton("tButton01", tradeIcon);
	JButton tButton02 = new JButton("tButton02", tradeIcon);
	JButton tButton03 = new JButton("tButton03", tradeIcon);
	JButton tButton04 = new JButton("tButton04", tradeIcon);
	JButton tButton05 = new JButton("tButton05", tradeIcon);
	JButton tButton06 = new JButton("tButton06", tradeIcon);
	JButton tButton07 = new JButton("tButton07", tradeIcon);
	JButton tButton08 = new JButton("tButton08", tradeIcon);
	JButton tButton09 = new JButton("tButton09", tradeIcon);
	// tButton 11-19
	JButton tButton11 = new JButton("tButton11", tradeIcon);
	JButton tButton12 = new JButton("tButton12", tradeIcon);
	JButton tButton13 = new JButton("tButton13", tradeIcon);
	JButton tButton14 = new JButton("tButton14", tradeIcon);
	JButton tButton15 = new JButton("tButton15", tradeIcon);
	JButton tButton16 = new JButton("tButton16", tradeIcon);
	JButton tButton17 = new JButton("tButton17", tradeIcon);
	JButton tButton18 = new JButton("tButton18", tradeIcon);
	JButton tButton19 = new JButton("tButton19", tradeIcon);
	// tButton 21-29
	JButton tButton21 = new JButton("tButton21", tradeIcon);
	JButton tButton22 = new JButton("tButton22", tradeIcon);
	JButton tButton23 = new JButton("tButton23", tradeIcon);
	JButton tButton24 = new JButton("tButton24", tradeIcon);
	JButton tButton25 = new JButton("tButton25", tradeIcon);
	JButton tButton26 = new JButton("tButton26", tradeIcon);
	JButton tButton27 = new JButton("tButton27", tradeIcon);
	JButton tButton28 = new JButton("tButton28", tradeIcon);
	JButton tButton29 = new JButton("tButton29", tradeIcon);
	// tButton 31-39
	JButton tButton31 = new JButton("tButton31", tradeIcon);
	JButton tButton32 = new JButton("tButton32", tradeIcon);
	JButton tButton33 = new JButton("tButton33", tradeIcon);
	JButton tButton34 = new JButton("tButton34", tradeIcon);
	JButton tButton35 = new JButton("tButton35", tradeIcon);
	JButton tButton36 = new JButton("tButton36", tradeIcon);
	JButton tButton37 = new JButton("tButton37", tradeIcon);
	JButton tButton38 = new JButton("tButton38", tradeIcon);
	JButton tButton39 = new JButton("tButton39", tradeIcon);
	{// add tButtons to Array
		tradeArray.add(new JButton());
		tradeArray.add(tButton01);
		tradeArray.add(tButton02);
		tradeArray.add(tButton03);
		tradeArray.add(tButton04);
		tradeArray.add(tButton05);
		tradeArray.add(tButton06);
		tradeArray.add(tButton07);
		tradeArray.add(tButton08);
		tradeArray.add(tButton09);

		tradeArray.add(new JButton());
		tradeArray.add(tButton11);
		tradeArray.add(tButton12);
		tradeArray.add(tButton13);
		tradeArray.add(tButton14);
		tradeArray.add(tButton15);
		tradeArray.add(tButton16);
		tradeArray.add(tButton17);
		tradeArray.add(tButton18);
		tradeArray.add(tButton19);

		tradeArray.add(new JButton());
		tradeArray.add(tButton21);
		tradeArray.add(tButton22);
		tradeArray.add(tButton23);
		tradeArray.add(tButton24);
		tradeArray.add(tButton25);
		tradeArray.add(tButton26);
		tradeArray.add(tButton27);
		tradeArray.add(tButton28);
		tradeArray.add(tButton29);

		tradeArray.add(new JButton());
		tradeArray.add(tButton31);
		tradeArray.add(tButton32);
		tradeArray.add(tButton33);
		tradeArray.add(tButton34);
		tradeArray.add(tButton35);
		tradeArray.add(tButton36);
		tradeArray.add(tButton37);
		tradeArray.add(tButton38);
		tradeArray.add(tButton39);
	}

	// bButton 1-9
	JButton bButton01 = new JButton("bButton01", buildIcon);
	JButton bButton02 = new JButton("bButton02", buildIcon);
	JButton bButton03 = new JButton("bButton03", buildIcon);
	JButton bButton04 = new JButton("bButton04", buildIcon);
	JButton bButton05 = new JButton("bButton05", buildIcon);
	JButton bButton06 = new JButton("bButton06", buildIcon);
	JButton bButton07 = new JButton("bButton07", buildIcon);
	JButton bButton08 = new JButton("bButton08", buildIcon);
	JButton bButton09 = new JButton("bButton09", buildIcon);
	// bButton 11-19
	JButton bButton11 = new JButton("bButton11", buildIcon);
	JButton bButton12 = new JButton("bButton12", buildIcon);
	JButton bButton13 = new JButton("bButton13", buildIcon);
	JButton bButton14 = new JButton("bButton14", buildIcon);
	JButton bButton15 = new JButton("bButton15", buildIcon);
	JButton bButton16 = new JButton("bButton16", buildIcon);
	JButton bButton17 = new JButton("bButton17", buildIcon);
	JButton bButton18 = new JButton("bButton18", buildIcon);
	JButton bButton19 = new JButton("bButton19", buildIcon);
	// bButton 21-29
	JButton bButton21 = new JButton("bButton21", buildIcon);
	JButton bButton22 = new JButton("bButton22", buildIcon);
	JButton bButton23 = new JButton("bButton23", buildIcon);
	JButton bButton24 = new JButton("bButton24", buildIcon);
	JButton bButton25 = new JButton("bButton25", buildIcon);
	JButton bButton26 = new JButton("bButton26", buildIcon);
	JButton bButton27 = new JButton("bButton27", buildIcon);
	JButton bButton28 = new JButton("bButton28", buildIcon);
	JButton bButton29 = new JButton("bButton29", buildIcon);
	// bButton 31-39
	JButton bButton31 = new JButton("bButton31", buildIcon);
	JButton bButton32 = new JButton("bButton32", buildIcon);
	JButton bButton33 = new JButton("bButton33", buildIcon);
	JButton bButton34 = new JButton("bButton34", buildIcon);
	JButton bButton35 = new JButton("bButton35", buildIcon);
	JButton bButton36 = new JButton("bButton36", buildIcon);
	JButton bButton37 = new JButton("bButton37", buildIcon);
	JButton bButton38 = new JButton("bButton38", buildIcon);
	JButton bButton39 = new JButton("bButton39", buildIcon);
	{
		// add buildBUttons to Array
		buildArray.add(new JButton());
		buildArray.add(bButton01);
		buildArray.add(bButton02);
		buildArray.add(bButton03);
		buildArray.add(bButton04);
		buildArray.add(bButton05);
		buildArray.add(bButton06);
		buildArray.add(bButton07);
		buildArray.add(bButton08);
		buildArray.add(bButton09);

		buildArray.add(new JButton());
		buildArray.add(bButton11);
		buildArray.add(bButton12);
		buildArray.add(bButton13);
		buildArray.add(bButton14);
		buildArray.add(bButton15);
		buildArray.add(bButton16);
		buildArray.add(bButton17);
		buildArray.add(bButton18);
		buildArray.add(bButton19);

		buildArray.add(new JButton());
		buildArray.add(bButton21);
		buildArray.add(bButton22);
		buildArray.add(bButton23);
		buildArray.add(bButton24);
		buildArray.add(bButton25);
		buildArray.add(bButton26);
		buildArray.add(bButton27);
		buildArray.add(bButton28);
		buildArray.add(bButton29);

		buildArray.add(new JButton());
		buildArray.add(bButton31);
		buildArray.add(bButton32);
		buildArray.add(bButton33);
		buildArray.add(bButton34);
		buildArray.add(bButton35);
		buildArray.add(bButton36);
		buildArray.add(bButton37);
		buildArray.add(bButton38);
		buildArray.add(bButton39);
	}

	// sButton 1-9
	JButton sButton01 = new JButton("sButton01", sellIcon);
	JButton sButton02 = new JButton("sButton02", sellIcon);
	JButton sButton03 = new JButton("sButton03", sellIcon);
	JButton sButton04 = new JButton("sButton04", sellIcon);
	JButton sButton05 = new JButton("sButton05", sellIcon);
	JButton sButton06 = new JButton("sButton06", sellIcon);
	JButton sButton07 = new JButton("sButton07", sellIcon);
	JButton sButton08 = new JButton("sButton08", sellIcon);
	JButton sButton09 = new JButton("sButton09", sellIcon);
	// sButton 11-19
	JButton sButton11 = new JButton("sButton11", sellIcon);
	JButton sButton12 = new JButton("sButton11", sellIcon);
	JButton sButton13 = new JButton("sButton11", sellIcon);
	JButton sButton14 = new JButton("sButton11", sellIcon);
	JButton sButton15 = new JButton("sButton11", sellIcon);
	JButton sButton16 = new JButton("sButton11", sellIcon);
	JButton sButton17 = new JButton("sButton11", sellIcon);
	JButton sButton18 = new JButton("sButton11", sellIcon);
	JButton sButton19 = new JButton("sButton11", sellIcon);
	// sButton 21-29
	JButton sButton21 = new JButton("sButton11", sellIcon);
	JButton sButton22 = new JButton("sButton21", sellIcon);
	JButton sButton23 = new JButton("sButton21", sellIcon);
	JButton sButton24 = new JButton("sButton21", sellIcon);
	JButton sButton25 = new JButton("sButton21", sellIcon);
	JButton sButton26 = new JButton("sButton21", sellIcon);
	JButton sButton27 = new JButton("sButton21", sellIcon);
	JButton sButton28 = new JButton("sButton21", sellIcon);
	JButton sButton29 = new JButton("sButton21", sellIcon);
	// sButton 31-39
	JButton sButton31 = new JButton("sButton11", sellIcon);
	JButton sButton32 = new JButton("sButton32", sellIcon);
	JButton sButton33 = new JButton("sButton33", sellIcon);
	JButton sButton34 = new JButton("sButton34", sellIcon);
	JButton sButton35 = new JButton("sButton35", sellIcon);
	JButton sButton36 = new JButton("sButton36", sellIcon);
	JButton sButton37 = new JButton("sButton37", sellIcon);
	JButton sButton38 = new JButton("sButton38", sellIcon);
	JButton sButton39 = new JButton("sButton39", sellIcon);
	{
		// add sellButtons to Array
		sellArray.add(new JButton());
		sellArray.add(sButton01);
		sellArray.add(sButton02);
		sellArray.add(sButton03);
		sellArray.add(sButton04);
		sellArray.add(sButton05);
		sellArray.add(sButton06);
		sellArray.add(sButton07);
		sellArray.add(sButton08);
		sellArray.add(sButton09);

		sellArray.add(new JButton());
		sellArray.add(sButton11);
		sellArray.add(sButton12);
		sellArray.add(sButton13);
		sellArray.add(sButton14);
		sellArray.add(sButton15);
		sellArray.add(sButton16);
		sellArray.add(sButton17);
		sellArray.add(sButton18);
		sellArray.add(sButton19);

		sellArray.add(new JButton());
		sellArray.add(sButton21);
		sellArray.add(sButton22);
		sellArray.add(sButton23);
		sellArray.add(sButton24);
		sellArray.add(sButton25);
		sellArray.add(sButton26);
		sellArray.add(sButton27);
		sellArray.add(sButton28);
		sellArray.add(sButton29);

		sellArray.add(new JButton());
		sellArray.add(sButton31);
		sellArray.add(sButton32);
		sellArray.add(sButton33);
		sellArray.add(sButton34);
		sellArray.add(sButton35);
		sellArray.add(sButton36);
		sellArray.add(sButton37);
		sellArray.add(sButton38);
		sellArray.add(sButton39);
	}

	// mButton 1-9
	JButton mButton01 = new JButton("mButton01", mortIcon);
	JButton mButton02 = new JButton("mButton02", mortIcon);
	JButton mButton03 = new JButton("mButton03", mortIcon);
	JButton mButton04 = new JButton("mButton04", mortIcon);
	JButton mButton05 = new JButton("mButton05", mortIcon);
	JButton mButton06 = new JButton("mButton06", mortIcon);
	JButton mButton07 = new JButton("mButton07", mortIcon);
	JButton mButton08 = new JButton("mButton08", mortIcon);
	JButton mButton09 = new JButton("mButton09", mortIcon);
	// mButton 11-19
	JButton mButton11 = new JButton("mButton11", mortIcon);
	JButton mButton12 = new JButton("mButton11", mortIcon);
	JButton mButton13 = new JButton("mButton11", mortIcon);
	JButton mButton14 = new JButton("mButton11", mortIcon);
	JButton mButton15 = new JButton("mButton11", mortIcon);
	JButton mButton16 = new JButton("mButton11", mortIcon);
	JButton mButton17 = new JButton("mButton11", mortIcon);
	JButton mButton18 = new JButton("mButton11", mortIcon);
	JButton mButton19 = new JButton("mButton11", mortIcon);
	// mButton 21-29
	JButton mButton21 = new JButton("mButton21", mortIcon);
	JButton mButton22 = new JButton("mButton22", mortIcon);
	JButton mButton23 = new JButton("mButton23", mortIcon);
	JButton mButton24 = new JButton("mButton24", mortIcon);
	JButton mButton25 = new JButton("mButton25", mortIcon);
	JButton mButton26 = new JButton("mButton26", mortIcon);
	JButton mButton27 = new JButton("mButton27", mortIcon);
	JButton mButton28 = new JButton("mButton28", mortIcon);
	JButton mButton29 = new JButton("mButton29", mortIcon);
	// mButton 31-39
	JButton mButton31 = new JButton("mButton31", mortIcon);
	JButton mButton32 = new JButton("mButton32", mortIcon);
	JButton mButton33 = new JButton("mButton33", mortIcon);
	JButton mButton34 = new JButton("mButton34", mortIcon);
	JButton mButton35 = new JButton("mButton35", mortIcon);
	JButton mButton36 = new JButton("mButton36", mortIcon);
	JButton mButton37 = new JButton("mButton37", mortIcon);
	JButton mButton38 = new JButton("mButton38", mortIcon);
	JButton mButton39 = new JButton("mButton39", mortIcon);
	{
		// add sellButtons to Array
		mortArray.add(new JButton());
		mortArray.add(mButton01);
		mortArray.add(mButton02);
		mortArray.add(mButton03);
		mortArray.add(mButton04);
		mortArray.add(mButton05);
		mortArray.add(mButton06);
		mortArray.add(mButton07);
		mortArray.add(mButton08);
		mortArray.add(mButton09);

		mortArray.add(new JButton());
		mortArray.add(mButton11);
		mortArray.add(mButton12);
		mortArray.add(mButton13);
		mortArray.add(mButton14);
		mortArray.add(mButton15);
		mortArray.add(mButton16);
		mortArray.add(mButton17);
		mortArray.add(mButton18);
		mortArray.add(mButton19);

		mortArray.add(new JButton());
		mortArray.add(mButton21);
		mortArray.add(mButton22);
		mortArray.add(mButton23);
		mortArray.add(mButton24);
		mortArray.add(mButton25);
		mortArray.add(mButton26);
		mortArray.add(mButton27);
		mortArray.add(mButton28);
		mortArray.add(mButton29);

		mortArray.add(new JButton());
		mortArray.add(mButton31);
		mortArray.add(mButton32);
		mortArray.add(mButton33);
		mortArray.add(mButton34);
		mortArray.add(mButton35);
		mortArray.add(mButton36);
		mortArray.add(mButton37);
		mortArray.add(mButton38);
		mortArray.add(mButton39);
	}

	// rButton 1-9
	JButton rButton01 = new JButton("rButton01", redeemIcon);
	JButton rButton02 = new JButton("rButton02", redeemIcon);
	JButton rButton03 = new JButton("rButton03", redeemIcon);
	JButton rButton04 = new JButton("rButton04", redeemIcon);
	JButton rButton05 = new JButton("rButton05", redeemIcon);
	JButton rButton06 = new JButton("rButton06", redeemIcon);
	JButton rButton07 = new JButton("rButton07", redeemIcon);
	JButton rButton08 = new JButton("rButton08", redeemIcon);
	JButton rButton09 = new JButton("rButton09", redeemIcon);
	// rButton 11-19
	JButton rButton11 = new JButton("rButton11", redeemIcon);
	JButton rButton12 = new JButton("rButton12", redeemIcon);
	JButton rButton13 = new JButton("rButton13", redeemIcon);
	JButton rButton14 = new JButton("rButton14", redeemIcon);
	JButton rButton15 = new JButton("rButton15", redeemIcon);
	JButton rButton16 = new JButton("rButton16", redeemIcon);
	JButton rButton17 = new JButton("rButton17", redeemIcon);
	JButton rButton18 = new JButton("rButton18", redeemIcon);
	JButton rButton19 = new JButton("rButton19", redeemIcon);
	// rButton 21-29
	JButton rButton21 = new JButton("rButton21", redeemIcon);
	JButton rButton22 = new JButton("rButton22", redeemIcon);
	JButton rButton23 = new JButton("rButton23", redeemIcon);
	JButton rButton24 = new JButton("rButton24", redeemIcon);
	JButton rButton25 = new JButton("rButton25", redeemIcon);
	JButton rButton26 = new JButton("rButton26", redeemIcon);
	JButton rButton27 = new JButton("rButton27", redeemIcon);
	JButton rButton28 = new JButton("rButton28", redeemIcon);
	JButton rButton29 = new JButton("rButton29", redeemIcon);
	// rButton 31-39
	JButton rButton31 = new JButton("rButton31", redeemIcon);
	JButton rButton32 = new JButton("rButton32", redeemIcon);
	JButton rButton33 = new JButton("rButton33", redeemIcon);
	JButton rButton34 = new JButton("rButton34", redeemIcon);
	JButton rButton35 = new JButton("rButton35", redeemIcon);
	JButton rButton36 = new JButton("rButton36", redeemIcon);
	JButton rButton37 = new JButton("rButton37", redeemIcon);
	JButton rButton38 = new JButton("rButton38", redeemIcon);
	JButton rButton39 = new JButton("rButton39", redeemIcon);
	{
		// add redeemButtons to Array
		redeemArray.add(new JButton());
		redeemArray.add(rButton01);
		redeemArray.add(rButton02);
		redeemArray.add(rButton03);
		redeemArray.add(rButton04);
		redeemArray.add(rButton05);
		redeemArray.add(rButton06);
		redeemArray.add(rButton07);
		redeemArray.add(rButton08);
		redeemArray.add(rButton09);

		redeemArray.add(new JButton());
		redeemArray.add(rButton11);
		redeemArray.add(rButton12);
		redeemArray.add(rButton13);
		redeemArray.add(rButton14);
		redeemArray.add(rButton15);
		redeemArray.add(rButton16);
		redeemArray.add(rButton17);
		redeemArray.add(rButton18);
		redeemArray.add(rButton19);

		redeemArray.add(new JButton());
		redeemArray.add(rButton21);
		redeemArray.add(rButton22);
		redeemArray.add(rButton23);
		redeemArray.add(rButton24);
		redeemArray.add(rButton25);
		redeemArray.add(rButton26);
		redeemArray.add(rButton27);
		redeemArray.add(rButton28);
		redeemArray.add(rButton29);

		redeemArray.add(new JButton());
		redeemArray.add(rButton31);
		redeemArray.add(rButton32);
		redeemArray.add(rButton33);
		redeemArray.add(rButton34);
		redeemArray.add(rButton35);
		redeemArray.add(rButton36);
		redeemArray.add(rButton37);
		redeemArray.add(rButton38);
		redeemArray.add(rButton39);
	}

	// Listeners
	ButtonListener listener = new ButtonListener();
	Timer timer = new Timer(50, new TimerListener());
	Timer timer2 = new Timer(500, new Timer2Listener());
	private FinishListener finishTimer = new FinishListener();
	private TradeListener tradeListener = new TradeListener();
	private BuildListener buildListener = new BuildListener();
	private SellListener sellListener = new SellListener();
	private MortListener mortListener = new MortListener();
	private RedeemListener redeemListener = new RedeemListener();

	// Create a label to hold button
	JLabel jp = new JLabel(bigMap);// 地图组件

	// Create buttons to hold dices
	JButton dice1 = new JButton(dice_6);
	JButton dice2 = new JButton(dice_6);
	JButton dice3 = new JButton(dice_6);
	//
	JButton btConfirm = new JButton();
	//

	// Finished Button
	JButton finished = new JButton(new ImageIcon("icons/buttons/finished1.png"));

	// Create operation Buttons
	JButton tradeBtn = new JButton("交易");
	JButton buildBtn = new JButton("建房");
	JButton sellBtn = new JButton("卖房");
	JButton mortBtn = new JButton("抵押");
	JButton redeemBtn = new JButton("赎回");

	// 土地格子初始化

	JLabel btn00 = new JLabel(new ImageIcon("icons/buttons/startPoint.png"));
	JLabel btn01 = new JLabel(house0);
	JLabel btn02 = new JLabel(house0);
	JLabel btn03 = new JLabel(house0);
	JLabel btn04 = new JLabel(q20Icon);
	JLabel btn05 = new JLabel(randomIcon);
	JLabel btn06 = new JLabel(q30Icon);
	JLabel btn07 = new JLabel(house0);
	JLabel btn08 = new JLabel(house0);
	JLabel btn09 = new JLabel(house0);
	JLabel btn10 = new JLabel(new ImageIcon("icons/buttons/jail.png"));
	JLabel btn11 = new JLabel(house0);
	JLabel btn12 = new JLabel(house0);
	JLabel btn13 = new JLabel(house0);
	JLabel btn14 = new JLabel(q20Icon);
	JLabel btn15 = new JLabel(randomIcon);
	JLabel btn16 = new JLabel(q30Icon);
	JLabel btn17 = new JLabel(house0);
	JLabel btn18 = new JLabel(house0);
	JLabel btn19 = new JLabel(house0);
	JLabel btn20 = new JLabel(new ImageIcon("icons/buttons/shop.png"));
	JLabel btn21 = new JLabel(house0);
	JLabel btn22 = new JLabel(house0);
	JLabel btn23 = new JLabel(house0);
	JLabel btn24 = new JLabel(q20Icon);
	JLabel btn25 = new JLabel(randomIcon);
	JLabel btn26 = new JLabel(q30Icon);
	JLabel btn27 = new JLabel(house0);
	JLabel btn28 = new JLabel(house0);
	JLabel btn29 = new JLabel(house0);
	JLabel btn30 = new JLabel(new ImageIcon("icons/buttons/toJail.png"));
	JLabel btn31 = new JLabel(house0);
	JLabel btn32 = new JLabel(house0);
	JLabel btn33 = new JLabel(house0);
	JLabel btn34 = new JLabel(q20Icon);
	JLabel btn35 = new JLabel(randomIcon);
	JLabel btn36 = new JLabel(q50Icon);
	JLabel btn37 = new JLabel(house0);
	JLabel btn38 = new JLabel(house0);
	JLabel btn39 = new JLabel(house0);
	{
		squareView.add(btn00);
		squareView.add(btn01);
		squareView.add(btn02);
		squareView.add(btn03);
		squareView.add(btn04);
		squareView.add(btn05);
		squareView.add(btn06);
		squareView.add(btn07);
		squareView.add(btn08);
		squareView.add(btn09);

		squareView.add(btn10);
		squareView.add(btn11);
		squareView.add(btn12);
		squareView.add(btn13);
		squareView.add(btn14);
		squareView.add(btn15);
		squareView.add(btn16);
		squareView.add(btn17);
		squareView.add(btn18);
		squareView.add(btn19);

		squareView.add(btn20);
		squareView.add(btn21);
		squareView.add(btn22);
		squareView.add(btn23);
		squareView.add(btn24);
		squareView.add(btn25);
		squareView.add(btn26);
		squareView.add(btn27);
		squareView.add(btn28);
		squareView.add(btn29);

		squareView.add(btn30);
		squareView.add(btn31);
		squareView.add(btn32);
		squareView.add(btn33);
		squareView.add(btn34);
		squareView.add(btn35);
		squareView.add(btn36);
		squareView.add(btn37);
		squareView.add(btn38);
		squareView.add(btn39);
	}

	//

	// JPanel playerAIcon = new JPanel();
	JLabel playerIcon1 = new JLabel(player0);
	JLabel playerIcon2 = new JLabel(player1);

	// 游戏信息
	JPanel gameMessage = new JPanel();
	JTextArea message = new JTextArea();
	JTextArea playing = new JTextArea("player0");
	JLabel time = new JLabel();
	JPanel whoPlay = new JPanel(new GridLayout(3, 1));

	public BigMap(int t) {
		this.pNum = t;
		// squaresInitial();
		setLayout(null);
		players.get(0).setName("大雄111");
		players.get(1).setName("胖虎222");

		// the game message
		// Date a1 = new Date();
		String theDisplay = "WINNER WINNER" + "\n" + "CHICKEN DINNER!";
		message.setText(theDisplay);
		Font f = new Font("Times", Font.ITALIC, 20);
		message.setFont(f);
		message.setForeground(Color.RED);
		message.setBackground(Color.PINK);

		//
		Font f1 = new Font("Times", Font.ITALIC, 18);
		Font f2 = new Font("Times", Font.BOLD, 18);
		whoPlay.setFont(f1);
		whoPlay.add(new JLabel("现在是："));
		whoPlay.add(playing);
		playing.setText(players.get(playerNum).getName());
		playing.setFont(f2);
		whoPlay.add(new JLabel("操作时间！"));
		whoPlay.setBackground(Color.CYAN);
		whoPlay.setForeground(Color.blue);

		gameMessage.add(message);
		gameMessage.add(whoPlay);
		gameMessage.setBorder(new TitledBorder("游戏信息"));
		gameMessage.setBounds(950, 99, 200, 180);
		add(gameMessage);// 把游戏信息加入面板

		// The message of player0
		add(pp1.playerPanel);
		pp1.name.setText(players.get(0).getName());
		pp1.playerPanel.setBounds(950, 369, 240, 160);

		add(pp2.playerPanel);
		pp2.name.setText(players.get(1).getName());
		pp2.playerPanel.setBounds(950, 555, 240, 160);

		// put Icons into start point
		jp.add(playerIcon1);
		jp.add(playerIcon2);

		// put the panel of player into panel
		add(jp);
		jp.setBounds(0, 0, 930, 888);
		jp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		// MyAdds

		// add squares
		addSquares();

		for (int o = 0; o < 40; o++) {
			own.get(o).setBounds(bbs[o][0], bbs[o][1], bbs[o][2], bbs[o][3]);
		}

		// 交易操作监听
		addListeners();

		// add operations to Squares

		// add dices
		jp.add(dice1);
		dice1.addActionListener(listener);
		dice1.setBounds(sqr * 5 + 20, sqr * 9, 66, 66);
		jp.add(dice2);
		dice2.addActionListener(listener);
		dice2.setBounds(sqr * 6 + 20, sqr * 9, 66, 66);
		jp.add(dice3);
		dice3.addActionListener(listener);
		dice3.setBounds(sqr * 7 + 20, sqr * 9, 66, 66);

		// add players
		// set buttons's position
		playerIcon1.setBounds(bounds[0][0] + px1, bounds[0][1] + py1, 20, 30);
		playerIcon2.setBounds(bounds[0][0] + px2, bounds[0][1] + py2, 20, 30);
		

		// add operations
		jp.add(tradeBtn);
		tradeBtn.setBounds(sqr * 2 + 70, sqr * 8, 88, 40);
		tradeBtn.setBackground(Color.CYAN);
		jp.add(buildBtn);
		buildBtn.setBounds(sqr * 4 + 35, sqr * 8, 88, 40);
		buildBtn.setBackground(Color.ORANGE);
		jp.add(sellBtn);
		sellBtn.setBounds(sqr * 6, sqr * 8, 88, 40);
		sellBtn.setBackground(Color.CYAN);
		jp.add(mortBtn);
		mortBtn.setBounds(sqr * 8 - 35, sqr * 8, 88, 40);
		mortBtn.setBackground(Color.ORANGE);
		jp.add(redeemBtn);
		redeemBtn.setBounds(sqr * 10 - 70, sqr * 8, 88, 40);
		redeemBtn.setBackground(Color.CYAN);
		Font ff = new Font("Times", Font.BOLD, 24);
		tradeBtn.setFont(ff);
		buildBtn.setFont(ff);
		sellBtn.setFont(ff);
		mortBtn.setFont(ff);
		redeemBtn.setFont(ff);
		// 加入触发事件
		tradeBtn.addActionListener(listener);
		buildBtn.addActionListener(listener);
		sellBtn.addActionListener(listener);
		mortBtn.addActionListener(listener);
		redeemBtn.addActionListener(listener);

		// add finished button
		jp.add(finished);
		finished.setBounds(sqr * 5 + 10, sqr * 6 + 20, 200, 60);
		finished.addActionListener(finishTimer);
		jp.remove(finished);

		jp.add(card);//使用卡片的监听button
		// test

	}

	// 第一层次Button监听
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			playTimes++;

			if (e.getSource() == dice1 || e.getSource() == dice2 || e.getSource() == dice3) {
				System.out.println(playerNum + "开始掷骰子！");
				// time.setText(shijian.getTime(playTimes, a, b));
				timer.start();
			} else if (e.getSource() == tradeBtn) {
				System.out.println(playerNum + "申请交易！");
				if(players.get(playerNum).jailDays == 0) {
					reset();
					for (int temp = 0; temp < 40; temp++) {
						switch (temp % 10) {
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
							Land t = (Land) squares.get(temp);
							if (t.getOwner() != null) {
								if (t.getOwner() != players.get(playerNum) && t.getStatus() == 1) {
									System.out.println("Show trade: " + temp);
									jp.add(tradeArray.get(temp));
									tradeArray.get(temp).setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
								}
							}
						}
					}
					jp.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "你在监狱里，不能进行此操作！");
				}
			} else if (e.getSource() == buildBtn) {
				System.out.println(playerNum + "申请建房！");
				if(players.get(playerNum).jailDays == 0) {
					reset();
					for (int temp = 0; temp < 40; temp++) {
						switch (temp % 10) {
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
							Land t = (Land) squares.get(temp);
							if (t.getOwner() != null) {
								if (t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
									JButton tempButton = buildArray.get(temp);
									jp.add(tempButton);
									tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
								}
							}
						}
					}
					jp.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "你在监狱里，不能进行此操作！");
				}
				
			} else if (e.getSource() == sellBtn) {
				System.out.println(playerNum + "申请卖房！");
				if(players.get(playerNum).jailDays == 0) {
					reset();
					for (int temp = 0; temp < 40; temp++) {
						switch (temp % 10) {
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
							Land t = (Land) squares.get(temp);
							if (t.getOwner() != null) {
								if (t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
									JButton tempButton = sellArray.get(temp);
									jp.add(tempButton);
									tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
								}
							}
						}
					}
					jp.repaint();			
				}
				else {
					JOptionPane.showMessageDialog(null, "你在监狱里，不能进行此操作！");
				}
				
			} else if (e.getSource() == mortBtn) {
				System.out.println(playerNum + "申请抵押！");
				if(players.get(playerNum).jailDays == 0) {
					reset();
					for (int temp = 0; temp < 40; temp++) {
						switch (temp % 10) {
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
							Land t = (Land) squares.get(temp);
							if (t.getOwner() != null) {
								if (t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
									JButton tempButton = mortArray.get(temp);
									jp.add(tempButton);
									tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[i][3]);
								}
							}
						}
					}
					jp.repaint();			
				}
				else {
					JOptionPane.showMessageDialog(null, "你在监狱里，不能进行此操作！");
				}
				
			} else if (e.getSource() == redeemBtn) {
				System.out.println(playerNum + "申请赎回！");
				if(players.get(playerNum).jailDays == 0) {
					reset();
					for (int temp = 0; temp < 40; temp++) {
						switch (temp % 10) {
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
							Land t = (Land) squares.get(temp);
							if (t.getOwner() != null) {
								if (t.getOwner() == players.get(playerNum) && t.getStatus() == 0) {
									JButton tempButton = redeemArray.get(temp);
									jp.add(tempButton);
									tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
								}
							}
						}
					}
					jp.repaint();		
				}
				else {
					JOptionPane.showMessageDialog(null, "你在监狱里，不能进行此操作！");
				}
				
			}
		}
	}

	// 骰子动画计时器
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isForwarding == false) {// 没有正在前进的动画
				int random = (int) (Math.random() * 6 + 1);
				dice1.setIcon(diceIcons.get(random));

				random = (int) (Math.random() * 6 + 1);
				dice2.setIcon(diceIcons.get(random));
				
				random = (int) (Math.random() * 6 + 1);
				dice3.setIcon(diceIcons.get(random));

				conDice++;
				if (conDice > 30) {
					isForwarding = true;// 开始前进动画
					timer.stop();
					diceResult1 = diceObject1.dice();
					diceResult2 = diceObject2.dice();
					diceResult3 = diceObject3.dice();
					diceResult = diceResult1 + diceResult2 + diceResult3;
					// 展现骰子
					dice1.setIcon(diceIcons.get(diceResult1));
					dice2.setIcon(diceIcons.get(diceResult2));
					dice3.setIcon(diceIcons.get(diceResult3));
					// 三次相同点进监狱
					if (diceResult1 == diceResult2 && diceResult1==diceResult3) {
						diceTimes++;
						if (diceTimes >= 3) {
							// go to jail
							JOptionPane.showMessageDialog(null, "你连续三次掷出了相同点数，\n我们怀疑你作弊,移送监狱！");
							new GoToJail().goJail(players.get(playerNum));
							players.get(playerNum).setLocation(10);
							switch (playerNum) {
							case 0:
								playerIcon1.setBounds(bounds[10][0] + px1, bounds[10][1] + py1, 20, 30);
								break;
							case 1:
								playerIcon2.setBounds(bounds[10][0] + px2, bounds[10][1] + py2, 20, 30);
								break;
							}
							System.out.println("因为三次掷出了相同点数而跳过掷骰子~");
							showFinished();
						}
					}
					if (isForwarding) {// 即掷出骰子，且没有因为三次相同而进监狱
						i = (players.get(playerNum).getLocation() + 1) % 40;
						System.out.println("diceResult: " + diceResult);
						System.out.println("i test :" + i);
						conDice = 0;
						if (players.get(playerNum).getJailDays() == 0 ||( (diceResult1 == diceResult2)&&(diceResult1 == diceResult3))) {
							players.get(playerNum).setJailDays(0);
							timer2.start();
							System.out.println("开始前进！");
						} else {
							players.get(playerNum).setJailDays(players.get(playerNum).getJailDays() - 1);
							showFinished();
						}
					}
				}
			} else {
				System.out.println("在前进时点击了骰子，不做处理！");
			}
		}
	}

	// 玩家前进计时器

	class Timer2Listener implements ActionListener {
		int step = 0;

		// Handle ActionEvent
		public void actionPerformed(ActionEvent e) {
			// FireEvent sign = new FireEvent(j);

			if (step >= diceResult) {
				timer2.stop();
				players.get(playerNum).setLocation(diceResult);
				// 前进完毕，处理到达土地后的事件
				int squareNum = players.get(playerNum).getLocation();
				Player p = players.get(playerNum);
				switch (squareNum) {
				case 1:
				case 2:
				case 3:
				case 7:
				case 8:
				case 9:
				case 11:
				case 12:
				case 13:
				case 17:
				case 18:
				case 19:
				case 21:
				case 22:
				case 23:
				case 27:
				case 28:
				case 29:
				case 31:
				case 32:
				case 33:
				case 37:
				case 38:
				case 39:
					System.out.println("到达了一块可交易土地");
					Land currentLocation = (Land) (squares.get(squareNum));
					System.out.println(p.getName() + "的当前位置 : " + squareNum);
					AutoEvent sign = new AutoEvent(currentLocation, p);
					sign.setVisible(true);
					showOwner(squareNum);
					break;
				case 30:
					JOptionPane.showMessageDialog(null, "资本违法，移送监狱！");
					new GoToJail().goJail(p);
					p.setLocation(10);
					switch (playerNum) {
					case 0:
						playerIcon1.setBounds(bounds[10][0] + px1, bounds[10][1] + py1, 20, 30);
						break;
					case 1:
						playerIcon2.setBounds(bounds[10][0] + px2, bounds[10][1] + py2, 20, 30);
						break;
					}
					break;
				case 0://经过起点加钱
					//p.setCash(200);
					break;
				case 4:
				case 6:
				case 14:
				case 16:
				case 24:
				case 26:
				case 34:
				case 36://GoSquare增加点券
					GoSquare s = (GoSquare)squares.get(squareNum);
					
					s.aotuEvent(p);
					JOptionPane.showMessageDialog(null, "恭喜你获得了"+s.getValue()+"点券！");
					break;
				case 5:
				case 15:
				case 25:
				case 35://随机事件
					RdeventSquare rd = (RdeventSquare)squares.get(squareNum);
					String rs = rd.event(players, p);
					if(rs.equals("去监狱")) {
						p.setLocation(10);
						switch (playerNum) {
						case 0:
							playerIcon1.setBounds(bounds[10][0] + px1, bounds[10][1] + py1, 20, 30);
							break;
						case 1:
							playerIcon2.setBounds(bounds[10][0] + px2, bounds[10][1] + py2, 20, 30);
							break;
						}
					}
					break;
				case 20://点券商店
					Object[] cards = {"通行卡  100","好运卡  200","交换卡  300","拆除卡  200"}; 
					int op = JOptionPane.showOptionDialog(null, "你需要购买什么卡片？", "欢迎来到点券商店",JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,new ImageIcon("icons/buttons/shop.png"), cards, cards[0]); 
					switch(op) {
					case 0:
						if(p.getGold() < p.pass.getPrice() ) {
							JOptionPane.showMessageDialog(null, "似乎你的点券不太够哦！");
						}
						else if(p.pass.cardBuy()){
							JOptionPane.showMessageDialog(null, "恭喜你购得了一张通行卡！");
							p.addGold(-p.pass.getPrice());
						}
						else {
							JOptionPane.showMessageDialog(null, "购买失败，通行卡只能持有一张！");
						}
						break;
					case 1:
						if(p.getGold() < p.lucky.getPrice() ) {
							JOptionPane.showMessageDialog(null, "似乎你的点券不太够哦！");
						}
						else if(p.lucky.cardBuy()){
							JOptionPane.showMessageDialog(null, "恭喜你购得了一张好运卡！");
							p.addGold(-p.lucky.getPrice());
							
						}
						else {
							JOptionPane.showMessageDialog(null, "购买失败，好运卡只能持有一张！");
						}
						break;
					case 2:
						if(p.getGold() < p.change.getPrice() ) {
							JOptionPane.showMessageDialog(null, "似乎你的点券不太够哦！");
						}
						else if(p.change.cardBuy()){
							JOptionPane.showMessageDialog(null, "恭喜你购得了一张交换卡！");
							p.addGold(-p.change.getPrice());
							
						}
						else {
							JOptionPane.showMessageDialog(null, "购买失败，交换卡只能持有一张！");
						}
						break;
					case 3:
						if(p.getGold() < p.demolish.getPrice() ) {
							JOptionPane.showMessageDialog(null, "似乎你的点券不太够哦！");
						}
						else if(p.demolish.cardBuy()){
							JOptionPane.showMessageDialog(null, "恭喜你购得了一张拆除卡！");
							p.addGold(-p.demolish.getPrice());
							
						}
						else {
							JOptionPane.showMessageDialog(null, "购买失败，拆除卡只能持有一张！");
						}
						break;
					default:
						
					}
					break;
				default:
					System.out.println("到达了一块非交易型的土地");
					switch (p.getLocation()) {
					case 0:
						// 经过起点+200
					case 4:
						// 其他事件
					default:
					}
				}
				if (diceResult1 != diceResult2 || diceResult1 != diceResult3) {
					System.out.println("正常结束而不再掷骰子");
					showFinished();
				} else {
					isForwarding = false;
				}
				conPlay++;
				step = 0;
				diceResult = 0;
			} else {// 动画前进一步
				switch (playerNum) {
				case 0:
					playerIcon1.setBounds(bounds[i][0] + px1, bounds[i][1] + py1, 20, 30);
					break;
				case 1:
					playerIcon2.setBounds(bounds[i][0] + px2, bounds[i][1] + py2, 20, 30);
					break;
				default:
					JOptionPane.showMessageDialog(null, "啊哦，出现了某些问题！");
				}
				i = i + 1;
				if(i == 40) {//经过起点加200块钱
					i = 0;
					players.get(playerNum).setCash(200);
				}
				step++;
				System.out.println("Forward step " + step + " of " + diceResult + " steps");
			}

			refreshPlayerInfo();

		}
	}

	// 展示完成按钮
	public void showFinished() {
		isForwarding = false;
		diceTimes = 0;
		jp.add(finished);
		jp.remove(dice1);
		jp.remove(dice2);
		jp.remove(dice3);
		jp.repaint();
	}

	public void refreshPlayerInfo() {
		// TODO Auto-generated method stub
		// 更新右侧玩家信息
		pp1.cash.setText("现金:" + players.get(0).getCash());
		pp1.more.setText("点券:" + players.get(0).getGold());
		pp2.cash.setText("现金:" + players.get(1).getCash());
		pp2.more.setText("点券:" + players.get(1).getGold());
	}

	public void showOwner(int location) {
		// TODO Auto-generated method stub
		System.out.println("调用了showOwner函数！");
		Land la = (Land) squares.get(location);
		int ownerNum = players.indexOf(la.getOwner());
		switch (ownerNum) {
		case 0:
			own.get(location).setIcon(player0);
			jp.add(own.get(location));
			jp.repaint();
			break;
		case 1:
			own.get(location).setIcon(player1);
			jp.add(own.get(location));
			jp.repaint();
			break;
		}
	}

	// 完成按钮监听器
	class FinishListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == finished) {
				System.out.println(playerNum + "点击了完成按钮！");
				playerNum++;
				if (playerNum >= pNum)
					playerNum = 0;
				System.out.println("现在是" + playerNum + "操作时间！");
				playing.setText(players.get(playerNum).getName());
				jp.remove(finished);
				reset();
				jp.repaint();
				
				// 是否在监狱
				int jailDays = players.get(playerNum).jailDays;
				if (jailDays > 0) {
					Player thePlayer = players.get(playerNum);
					Object[] ops = {"支付200出狱","使用通行卡","都不"}; 
					int option = JOptionPane.showOptionDialog(null, "你要选择什么操作？", thePlayer.getName()+"处于被关押状态",
							JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("icons/buttons/jail.png"), ops, ops[0]); 
					
					if (option == 0) {
						thePlayer.setCash(-200);
						thePlayer.setJailDays(0);
					} else if(option == 1){// 验证通行卡
						if(thePlayer.pass.usable) {
							thePlayer.pass.cardUse(thePlayer);
						}
						else {
							option = JOptionPane.showConfirmDialog(null, players.get(playerNum).getName()
									+",\n是否要支付200出狱？","你还没有通行卡！",JOptionPane.YES_NO_OPTION);
							if (option == 0) {
								players.get(playerNum).setCash(-200);
								players.get(playerNum).setJailDays(0);
							} else {// 继续掷骰子
								
							}
						}
					}
				}
				dice1.setIcon(dice_6);
				dice2.setIcon(dice_6);
				dice3.setIcon(dice_6);
				jp.add(dice1);
				jp.add(dice2);
				jp.add(dice3);
				reset();
				jp.repaint();
			}
		}
	}

	// 为操作事件的Button添加监听
	public void addListeners() {
		System.out.println("Adding ListenerS!");
		addTradeListeners();
		addBuildListeners();
		addSellListeners();
		addMortListeners();
		addRedeemListeners();
	}

	// 监听并区分交易事件
	public void addTradeListeners() {
		System.out.println("Adding TradeListenerS!");
		for (int temp = 0; temp < 40; temp++) {
			tradeArray.get(temp).addActionListener(tradeListener);
		}
	}

	// 监听建房事件

	public void addBuildListeners() {
		System.out.println("Adding BuildListenerS!");
		for (int temp = 0; temp < 40; temp++) {
			buildArray.get(temp).addActionListener(buildListener);
		}
	}

	// 监听卖房事件

	public void addSellListeners() {
		System.out.println("Adding SellListenerS!");
		for (int temp = 0; temp < 40; temp++) {
			sellArray.get(temp).addActionListener(sellListener);
		}
	}

	// 监听抵押事件

	public void addMortListeners() {
		System.out.println("Adding MortListenerS!");
		for (int temp = 0; temp < 40; temp++) {
			mortArray.get(temp).addActionListener(mortListener);
		}
	}

	// 监听赎回事件

	public void addRedeemListeners() {
		System.out.println("Adding RedeemListenerS!");
		for (int temp = 0; temp < 40; temp++) {
			redeemArray.get(temp).addActionListener(redeemListener);
		}
	}

	// 土地加入面板并确定土地的位置
	public void addSquares() {
		for (int temp = 0; temp < 40; temp++) {
			jp.add(squareView.get(temp));
			squareView.get(temp).setBounds(bounds[temp][0], bounds[temp][1], bounds[temp][2], bounds[temp][3]);
		}
	}

	// 移除所有主动事件的Button
	public void reset() {
		// 移除所有交易Button
		for (int temp = 0; temp < 40; temp++) {
			jp.remove(tradeArray.get(temp));
		}

		// 移除所有建房Button
		for (int temp = 0; temp < 40; temp++) {
			jp.remove(buildArray.get(temp));
		}

		// 移除所有卖房Button
		for (int temp = 0; temp < 40; temp++) {
			jp.remove(sellArray.get(temp));
		}

		// 移除所有抵押Button
		for (int temp = 0; temp < 40; temp++) {
			jp.remove(mortArray.get(temp));
		}

		// 移除所有赎回Button
		for (int temp = 0; temp < 40; temp++) {
			jp.remove(redeemArray.get(temp));
		}
	}

	// MyListeners

	// 交易事件监听器
	class TradeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked TradeListenerS!");
			int tempIndex = tradeArray.indexOf(e.getSource());
			System.out.println("Try to build house on Square " + tempIndex + " !");
			Land change = (Land) (squares.get(tempIndex));
			Player operator = players.get(playerNum);
			Player tradeTo = change.getOwner();
			String num = JOptionPane.showInputDialog(null, "交易金额");
			int op = JOptionPane.showConfirmDialog(null,
					tradeTo.getName() + ",你是否愿意以" + num + "\n将土地售卖给" + operator.getName() + "?", "提示",
					JOptionPane.YES_NO_CANCEL_OPTION);
			int b = Integer.valueOf(num).intValue();
			if (op == JOptionPane.YES_OPTION) {
				change.setOwner(operator);
				operator.setCash(-b);
				tradeTo.setCash(b);
				showOwner(tempIndex);
				reset();
				jp.repaint();
			} else if (op == JOptionPane.NO_OPTION) {
			}
			refreshPlayerInfo();
		}
	}

	// 建房事件监听器
	class BuildListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked BuildListenerS!");
			int tempIndex = buildArray.indexOf(e.getSource());
			System.out.println("Try to build house on Square " + tempIndex + " !");
			Land tempLand = (Land) (squares.get(tempIndex));
			boolean boo = tempLand.build(players.get(playerNum));
			if (boo) {
				switch (tempLand.getHouseLevel()) {
				case 1:
					squareView.get(tempIndex).setIcon(house1);
					break;
				case 2:
					squareView.get(tempIndex).setIcon(house2);
					break;
				case 3:
					squareView.get(tempIndex).setIcon(house3);
					break;
				case 4:
					squareView.get(tempIndex).setIcon(house4);
					break;
				case 5:
					squareView.get(tempIndex).setIcon(house5);
					break;
				default:
				}
			}
			refreshPlayerInfo();
		}
	}

	// 卖房事件监听器
	class SellListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int tempIndex = sellArray.indexOf(e.getSource());
			System.out.println("Try to mort Square " + tempIndex + " !");
			Land tempLand = (Land) (squares.get(tempIndex));
			boolean boo = tempLand.demolish(players.get(playerNum));
			if (boo) {
				switch (tempLand.getHouseLevel()) {
				case 0:
					squareView.get(tempIndex).setIcon(house0);
					break;
				case 1:
					squareView.get(tempIndex).setIcon(house1);
					break;
				case 2:
					squareView.get(tempIndex).setIcon(house2);
					break;
				case 3:
					squareView.get(tempIndex).setIcon(house3);
					break;
				case 4:
					squareView.get(tempIndex).setIcon(house4);
					break;
				default:
				}
			}
			refreshPlayerInfo();
		}
	}

	// 抵押事件监听器
	class MortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked MortListenerS!");
			int tempIndex = mortArray.indexOf(e.getSource());
			if (tempIndex != -1) {
				System.out.println("Try to mort Square " + tempIndex + " !");
				Land tempLand = (Land) (squares.get(tempIndex));
				boolean boo = tempLand.mortgage(players.get(playerNum));
				if (boo) {
					squareView.get(tempIndex).setIcon(mortgaged);
					jp.repaint();
				}
			} else {

			}
			refreshPlayerInfo();
		}
	}

	// 赎回事件监听器
	class RedeemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked RedeemListenerS!");
			int tempIndex = redeemArray.indexOf(e.getSource());
			System.out.println("Try to redeem Square " + tempIndex + " !");
			Land tempLand = (Land) (squares.get(tempIndex));
			boolean boo = tempLand.redeem(players.get(playerNum));
			if (boo) {
				squareView.get(tempIndex).setIcon(house0);
				jp.repaint();
			}
			refreshPlayerInfo();
		}
	}
	
	//卡片使用的监听
	class CardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked CardListenerS!");
			Player p = players.get(playerNum);
			ArrayList<String> list=new ArrayList<String>();
			if(p.pass.isUsable())	list.add(p.pass.getName());
			if(p.lucky.isUsable())	list.add(p.lucky.getName());
			if(p.change.isUsable())	list.add(p.change.getName());
			if(p.demolish.isUsable())	list.add(p.demolish.getName());
			String cards[] = new String[list.size()];
			list.toArray(cards);
			if(cards.length > 0) {
				int op = JOptionPane.showOptionDialog(null, "你想使用什么卡片？", "拥有的卡片",JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,new ImageIcon("icons/buttons/shop.png"), cards, cards[0]);
				if(op != -1) {
					String c = cards[op];
					switch(c) {
					case "通行卡":
						p.pass.cardUse(p);
						break;
					case "好运卡":
						String index = JOptionPane.showInputDialog(null, "输入想去的土地序号：");
						int b = Integer.valueOf(index).intValue();
						Square s = squares.get(b);
						if(p.lucky.cardUse(p, s)) {
							switch(playerNum) {
							case 0:
								playerIcon1.setBounds(bounds[b][0] + px1, bounds[b][1] + py1, 20, 30);
								break;
							case 1:
								playerIcon2.setBounds(bounds[b][0] + px2, bounds[b][1] + py2, 20, 30);
								break;
							}
						}
						break;
					case "交换卡":
						String change = JOptionPane.showInputDialog(null, "输入用于交换的土地序号：");
						int byIndex = Integer.valueOf(change).intValue();
						Land by = (Land)squares.get(byIndex);
						Land it = (Land)squares.get(p.getLocation());
						if(p.change.cardUse(p, by, it)) {
							JOptionPane.showMessageDialog(null, "交换成功！");
						}
						break;
					case "拆除卡":
						Land land = (Land)squares.get(p.getLocation());
						p.demolish.cardUse(p, land);
						break;
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "你还没有购买任何卡片！");
			}
			repaint();
		}
	}

}
