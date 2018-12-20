package Project2;

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

	
	static Player a = new Player();
	static Player b = new Player();
	// The background map
	int conDice = 0;
	static List<Square> squares = new SquareList().intial();
	static int conPlay = 0;
	static int playTimes = 0;
	static int playerNum = 1;
	static int diceTimes = 0;//连续掷骰子次数
	static int diceResult;
	static int diceResult1;
	static int diceResult2;
	static int sqr = 72;//Square size
	int i;// = a.getLocation() + 1; // - diceResult + 1;
	int i2;// = 20 + b.getLocation();// - diceResult;
	Location[] land = FireEvent.land;
	//初始化玩家土地的位置信息
	int[][] bounds = {
			{sqr*11,sqr*11,sqr,sqr},{sqr*10,sqr*11,sqr,sqr},{sqr*9,sqr*11,sqr,sqr},{sqr*8,sqr*11,sqr,sqr},
			{sqr*7,sqr*11,sqr,sqr},{sqr*6,sqr*11,sqr,sqr},{sqr*5,sqr*11,sqr,sqr},{sqr*4,sqr*11,sqr,sqr},
			{sqr*3,sqr*11,sqr,sqr},{sqr*2,sqr*11,sqr,sqr},{sqr*1,sqr*11,sqr,sqr},//0-10
			{sqr*1,sqr*10,sqr,sqr},{sqr*1,sqr*9,sqr,sqr},{sqr*1,sqr*8,sqr,sqr},{sqr*1,sqr*7,sqr,sqr},
			{sqr*1,sqr*6,sqr,sqr},{sqr*1,sqr*5,sqr,sqr},{sqr*1,sqr*4,sqr,sqr},{sqr*1,sqr*3,sqr,sqr},
			{sqr*1,sqr*2,sqr,sqr},{sqr*1,sqr*1,sqr,sqr},//11-20
			{sqr*2,sqr*1,sqr,sqr},{sqr*3,sqr*1,sqr,sqr},{sqr*4,sqr*1,sqr,sqr},{sqr*5,sqr*1,sqr,sqr},
			{sqr*6,sqr*1,sqr,sqr},{sqr*7,sqr*1,sqr,sqr},{sqr*8,sqr*1,sqr,sqr},{sqr*9,sqr*1,sqr,sqr},
			{sqr*10,sqr*1,sqr,sqr},{sqr*11,sqr*1,sqr,sqr},//21-30
			{sqr*11,sqr*2,sqr,sqr},{sqr*11,sqr*3,sqr,sqr},{sqr*11,sqr*4,sqr,sqr},{sqr*11,sqr*5,sqr,sqr},
			{sqr*11,sqr*6,sqr,sqr},{sqr*11,sqr*7,sqr,sqr},{sqr*11,sqr*8,sqr,sqr},{sqr*11,sqr*9,sqr,sqr},
			{sqr*11,sqr*10,sqr,sqr}	
	};
	
	//附加Button的位置
		int[][] bbs = new int[40][4];
		{
			int c = 0;
			while(c < 10) {
				bbs[c][0] = bounds[c][0]+16;
				bbs[c][1] = bounds[c][1]-32;
				bbs[c][2] = 32;
				bbs[c][3] = 32;
				c++;
			}
			while(c < 20) {
				bbs[c][0] = bounds[c][0]+70;
				bbs[c][1] = bounds[c][1]+16;
				bbs[c][2] = 32;
				bbs[c][3] = 32;
				c++;
			}
			while(c < 30) {
				bbs[c][0] = bounds[c][0]+16;
				bbs[c][1] = bounds[c][1]+70;
				bbs[c][2] = 32;
				bbs[c][3] = 32;
				c++;
			}
			while(c < 40) {
				bbs[c][0] = bounds[c][0]-32;
				bbs[c][1] = bounds[c][1]+16;
				bbs[c][2] = 32;
				bbs[c][3] = 32;
				c++;
			}
		}
		
	

	static int diceNumber = 0;
	private ImageIcon bigMap = new ImageIcon("icons/start/background.png");
	// All kinds of buttons
	private ImageIcon sqrImg = new ImageIcon("icons/buttons/square.png");
	

	//  Players
	int px1 = 10, py1 = 5;//格子内的偏移
	private JPanel playPanel1 = new JPanel(new GridBagLayout());
	private ImageIcon player1 = new ImageIcon("icons/players/player1.png");
	//private ImageIcon player1Icon = new ImageIcon("icons/pGrilIcon.jpg");
	private ImageIcon messageOfAImage = new ImageIcon("icons/newGril2.jpg");


	int px2 = 10, py2 = 35;
	private JPanel playPanel2 = new JPanel(new GridBagLayout());
	private ImageIcon player2 = new ImageIcon("icons/players/player2.png");
	//private ImageIcon player2Icon = new ImageIcon("icons/pBoyIcon.jpg");
	private ImageIcon messageOfBImage = new ImageIcon("icons/newBoy2.jpg");
	
	int px3 = 10, py3 = 35;
	private JPanel playPanel3 = new JPanel(new GridBagLayout());
	private ImageIcon player3 = new ImageIcon("icons/players/player3.png");
	
	int px4 = 10, py4 = 35;
	private JPanel playLabel4 = new JPanel(new GridBagLayout());
	private ImageIcon player4 = new ImageIcon("icons/players/player4.png");

	// the dices
	private ImageIcon dice_0 = new ImageIcon("icons/dice0.png");
	private ImageIcon dice_1 = new ImageIcon("icons/dice1.png");
	private ImageIcon dice_2 = new ImageIcon("icons/dice2.png");
	private ImageIcon dice_3 = new ImageIcon("icons/dice3.png");
	private ImageIcon dice_4 = new ImageIcon("icons/dice4.png");
	private ImageIcon dice_5 = new ImageIcon("icons/dice5.png");
	private ImageIcon dice_6 = new ImageIcon("icons/dice6.png");
	
	//Operations to Squares
	private ImageIcon tradeIcon = new ImageIcon("icons/buttons/1trade.png");
	private ImageIcon buildIcon = new ImageIcon("icons/buttons/1build.png");
	private ImageIcon sellIcon = new ImageIcon("icons/buttons/1sell.png");
	private ImageIcon mortIcon = new ImageIcon("icons/buttons/1mort.png");
	private ImageIcon redeemIcon = new ImageIcon("icons/buttons/1redeem.png");
	//Operations to Buttons
	//tButton 1-9
	JButton tButton01 = new JButton("tButton01",tradeIcon);
	JButton tButton02 = new JButton("tButton02",tradeIcon);
	JButton tButton03 = new JButton("tButton03",tradeIcon);
	JButton tButton04 = new JButton("tButton04",tradeIcon);
	JButton tButton05 = new JButton("tButton05",tradeIcon);
	JButton tButton06 = new JButton("tButton06",tradeIcon);
	JButton tButton07 = new JButton("tButton07",tradeIcon);
	JButton tButton08 = new JButton("tButton08",tradeIcon);
	JButton tButton09 = new JButton("tButton09",tradeIcon);
	//tButton 11-19
	JButton tButton11 = new JButton("tButton11",tradeIcon);
	JButton tButton12 = new JButton("tButton12",tradeIcon);
	JButton tButton13 = new JButton("tButton13",tradeIcon);
	JButton tButton14 = new JButton("tButton14",tradeIcon);
	JButton tButton15 = new JButton("tButton15",tradeIcon);
	JButton tButton16 = new JButton("tButton16",tradeIcon);
	JButton tButton17 = new JButton("tButton17",tradeIcon);
	JButton tButton18 = new JButton("tButton18",tradeIcon);
	JButton tButton19 = new JButton("tButton19",tradeIcon);
	//tButton 21-29
	JButton tButton21 = new JButton("tButton21",tradeIcon);
	JButton tButton22 = new JButton("tButton22",tradeIcon);
	JButton tButton23 = new JButton("tButton23",tradeIcon);
	JButton tButton24 = new JButton("tButton24",tradeIcon);
	JButton tButton25 = new JButton("tButton25",tradeIcon);
	JButton tButton26 = new JButton("tButton26",tradeIcon);
	JButton tButton27 = new JButton("tButton27",tradeIcon);
	JButton tButton28 = new JButton("tButton28",tradeIcon);
	JButton tButton29 = new JButton("tButton29",tradeIcon);
	//tButton 31-39
	JButton tButton31 = new JButton("tButton31",tradeIcon);
	JButton tButton32 = new JButton("tButton32",tradeIcon);
	JButton tButton33 = new JButton("tButton33",tradeIcon);
	JButton tButton34 = new JButton("tButton34",tradeIcon);
	JButton tButton35 = new JButton("tButton35",tradeIcon);
	JButton tButton36 = new JButton("tButton36",tradeIcon);
	JButton tButton37 = new JButton("tButton37",tradeIcon);
	JButton tButton38 = new JButton("tButton38",tradeIcon);
	JButton tButton39 = new JButton("tButton39",tradeIcon);

	//bButton 1-9
	JButton bButton01 = new JButton("bButton01",buildIcon);
	JButton bButton02 = new JButton("bButton02",buildIcon);
	JButton bButton03 = new JButton("bButton03",buildIcon);
	JButton bButton04 = new JButton("bButton04",buildIcon);
	JButton bButton05 = new JButton("bButton05",buildIcon);
	JButton bButton06 = new JButton("bButton06",buildIcon);
	JButton bButton07 = new JButton("bButton07",buildIcon);
	JButton bButton08 = new JButton("bButton08",buildIcon);
	JButton bButton09 = new JButton("bButton09",buildIcon);
	//bButton 11-19
	JButton bButton11 = new JButton("bButton11",buildIcon);
	JButton bButton12 = new JButton("bButton12",buildIcon);
	JButton bButton13 = new JButton("bButton13",buildIcon);
	JButton bButton14 = new JButton("bButton14",buildIcon);
	JButton bButton15 = new JButton("bButton15",buildIcon);
	JButton bButton16 = new JButton("bButton16",buildIcon);
	JButton bButton17 = new JButton("bButton17",buildIcon);
	JButton bButton18 = new JButton("bButton18",buildIcon);
	JButton bButton19 = new JButton("bButton19",buildIcon);
	//bButton 21-29
	JButton bButton21 = new JButton("bButton21",buildIcon);
	JButton bButton22 = new JButton("bButton22",buildIcon);
	JButton bButton23 = new JButton("bButton23",buildIcon);
	JButton bButton24 = new JButton("bButton24",buildIcon);
	JButton bButton25 = new JButton("bButton25",buildIcon);
	JButton bButton26 = new JButton("bButton26",buildIcon);
	JButton bButton27 = new JButton("bButton27",buildIcon);
	JButton bButton28 = new JButton("bButton28",buildIcon);
	JButton bButton29 = new JButton("bButton29",buildIcon);
	//bButton 31-39
	JButton bButton31 = new JButton("bButton31",buildIcon);
	JButton bButton32 = new JButton("bButton32",buildIcon);
	JButton bButton33 = new JButton("bButton33",buildIcon);
	JButton bButton34 = new JButton("bButton34",buildIcon);
	JButton bButton35 = new JButton("bButton35",buildIcon);
	JButton bButton36 = new JButton("bButton36",buildIcon);
	JButton bButton37 = new JButton("bButton37",buildIcon);
	JButton bButton38 = new JButton("bButton38",buildIcon);
	JButton bButton39 = new JButton("bButton39",buildIcon);
	
	//sButton 1-9
	JButton sButton01 = new JButton("sButton01",sellIcon);
	JButton sButton02 = new JButton("sButton02",sellIcon);
	JButton sButton03 = new JButton("sButton03",sellIcon);
	JButton sButton04 = new JButton("sButton04",sellIcon);
	JButton sButton05 = new JButton("sButton05",sellIcon);
	JButton sButton06 = new JButton("sButton06",sellIcon);
	JButton sButton07 = new JButton("sButton07",sellIcon);
	JButton sButton08 = new JButton("sButton08",sellIcon);
	JButton sButton09 = new JButton("sButton09",sellIcon);
	//sButton 11-19
	JButton sButton11 = new JButton("sButton11",sellIcon);
	JButton sButton12 = new JButton("sButton11",sellIcon);
	JButton sButton13 = new JButton("sButton11",sellIcon);
	JButton sButton14 = new JButton("sButton11",sellIcon);
	JButton sButton15 = new JButton("sButton11",sellIcon);
	JButton sButton16 = new JButton("sButton11",sellIcon);
	JButton sButton17 = new JButton("sButton11",sellIcon);
	JButton sButton18 = new JButton("sButton11",sellIcon);
	JButton sButton19 = new JButton("sButton11",sellIcon);
	//sButton 21-29
	JButton sButton21 = new JButton("sButton11",sellIcon);
	JButton sButton22 = new JButton("sButton21",sellIcon);
	JButton sButton23 = new JButton("sButton21",sellIcon);
	JButton sButton24 = new JButton("sButton21",sellIcon);
	JButton sButton25 = new JButton("sButton21",sellIcon);
	JButton sButton26 = new JButton("sButton21",sellIcon);
	JButton sButton27 = new JButton("sButton21",sellIcon);
	JButton sButton28 = new JButton("sButton21",sellIcon);
	JButton sButton29 = new JButton("sButton21",sellIcon);
	//sButton 31-39
	JButton sButton31 = new JButton("sButton11",sellIcon);
	JButton sButton32 = new JButton("sButton32",sellIcon);
	JButton sButton33 = new JButton("sButton33",sellIcon);
	JButton sButton34 = new JButton("sButton34",sellIcon);
	JButton sButton35 = new JButton("sButton35",sellIcon);
	JButton sButton36 = new JButton("sButton36",sellIcon);
	JButton sButton37 = new JButton("sButton37",sellIcon);
	JButton sButton38 = new JButton("sButton38",sellIcon);
	JButton sButton39 = new JButton("sButton39",sellIcon);
	
	//mButton 1-9
	JButton mButton01 = new JButton("mButton01",mortIcon);
	JButton mButton02 = new JButton("mButton02",mortIcon);
	JButton mButton03 = new JButton("mButton03",mortIcon);
	JButton mButton04 = new JButton("mButton04",mortIcon);
	JButton mButton05 = new JButton("mButton05",mortIcon);
	JButton mButton06 = new JButton("mButton06",mortIcon);
	JButton mButton07 = new JButton("mButton07",mortIcon);
	JButton mButton08 = new JButton("mButton08",mortIcon);
	JButton mButton09 = new JButton("mButton09",mortIcon);
	//mButton 11-19
	JButton mButton11 = new JButton("mButton11",mortIcon);
	JButton mButton12 = new JButton("mButton11",mortIcon);
	JButton mButton13 = new JButton("mButton11",mortIcon);
	JButton mButton14 = new JButton("mButton11",mortIcon);
	JButton mButton15 = new JButton("mButton11",mortIcon);
	JButton mButton16 = new JButton("mButton11",mortIcon);
	JButton mButton17 = new JButton("mButton11",mortIcon);
	JButton mButton18 = new JButton("mButton11",mortIcon);
	JButton mButton19 = new JButton("mButton11",mortIcon);
	//mButton 21-29
	JButton mButton21 = new JButton("mButton21",mortIcon);
	JButton mButton22 = new JButton("mButton22",mortIcon);
	JButton mButton23 = new JButton("mButton23",mortIcon);
	JButton mButton24 = new JButton("mButton24",mortIcon);
	JButton mButton25 = new JButton("mButton25",mortIcon);
	JButton mButton26 = new JButton("mButton26",mortIcon);
	JButton mButton27 = new JButton("mButton27",mortIcon);
	JButton mButton28 = new JButton("mButton28",mortIcon);
	JButton mButton29 = new JButton("mButton29",mortIcon);
	//mButton 31-39
	JButton mButton31 = new JButton("mButton31",mortIcon);
	JButton mButton32 = new JButton("mButton32",mortIcon);
	JButton mButton33 = new JButton("mButton33",mortIcon);
	JButton mButton34 = new JButton("mButton34",mortIcon);
	JButton mButton35 = new JButton("mButton35",mortIcon);
	JButton mButton36 = new JButton("mButton36",mortIcon);
	JButton mButton37 = new JButton("mButton37",mortIcon);
	JButton mButton38 = new JButton("mButton38",mortIcon);
	JButton mButton39 = new JButton("mButton39",mortIcon);

	//rButton 1-9
	JButton rButton01 = new JButton("rButton01",redeemIcon);
	JButton rButton02 = new JButton("rButton02",redeemIcon);
	JButton rButton03 = new JButton("rButton03",redeemIcon);
	JButton rButton04 = new JButton("rButton04",redeemIcon);
	JButton rButton05 = new JButton("rButton05",redeemIcon);
	JButton rButton06 = new JButton("rButton06",redeemIcon);
	JButton rButton07 = new JButton("rButton07",redeemIcon);
	JButton rButton08 = new JButton("rButton08",redeemIcon);
	JButton rButton09 = new JButton("rButton09",redeemIcon);
	//rButton 11-19
	JButton rButton11 = new JButton("rButton11",redeemIcon);
	JButton rButton12 = new JButton("rButton12",redeemIcon);
	JButton rButton13 = new JButton("rButton13",redeemIcon);
	JButton rButton14 = new JButton("rButton14",redeemIcon);
	JButton rButton15 = new JButton("rButton15",redeemIcon);
	JButton rButton16 = new JButton("rButton16",redeemIcon);
	JButton rButton17 = new JButton("rButton17",redeemIcon);
	JButton rButton18 = new JButton("rButton18",redeemIcon);
	JButton rButton19 = new JButton("rButton19",redeemIcon);
	//rButton 21-29
	JButton rButton21 = new JButton("rButton21",redeemIcon);
	JButton rButton22 = new JButton("rButton22",redeemIcon);
	JButton rButton23 = new JButton("rButton23",redeemIcon);
	JButton rButton24 = new JButton("rButton24",redeemIcon);
	JButton rButton25 = new JButton("rButton25",redeemIcon);
	JButton rButton26 = new JButton("rButton26",redeemIcon);
	JButton rButton27 = new JButton("rButton27",redeemIcon);
	JButton rButton28 = new JButton("rButton28",redeemIcon);
	JButton rButton29 = new JButton("rButton29",redeemIcon);
	//rButton 31-39
	JButton rButton31 = new JButton("rButton31",redeemIcon);
	JButton rButton32 = new JButton("rButton32",redeemIcon);
	JButton rButton33 = new JButton("rButton33",redeemIcon);
	JButton rButton34 = new JButton("rButton34",redeemIcon);
	JButton rButton35 = new JButton("rButton35",redeemIcon);
	JButton rButton36 = new JButton("rButton36",redeemIcon);
	JButton rButton37 = new JButton("rButton37",redeemIcon);
	JButton rButton38 = new JButton("rButton38",redeemIcon);
	JButton rButton39 = new JButton("rButton39",redeemIcon);
	
	
	//Listeners
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
	JLabel jp = new JLabel(bigMap);//地图组件

	// Create a label to hold player1
	JLabel pjl1 = new JLabel(player1);

	// Create a label to hold player2
	JLabel pjl2 = new JLabel(player2);
	// Create buttons to hold dices
	JButton dice1 = new JButton(dice_1);
	JButton dice2 = new JButton(dice_1);
	//
	JButton btConfirm = new JButton();
	//
	
	//Finished Button
	JButton finished = new JButton(new ImageIcon("icons/buttons/finished1.png"));

	//Create operation Buttons
	JButton tradeBtn = new JButton("交易");
	JButton buildBtn = new JButton("建房");
	JButton sellBtn = new JButton("卖房");
	JButton mortBtn = new JButton("抵押");
	JButton redeemBtn = new JButton("赎回");
	
	//MyJButtons
	JLabel btn00 = new JLabel(new ImageIcon("icons/buttons/startPoint.png"));
	JLabel btn01 = new JLabel(sqrImg);
	JLabel btn02 = new JLabel(sqrImg);
	JLabel btn03 = new JLabel(sqrImg);
	JLabel btn04 = new JLabel(sqrImg);
	JLabel btn05 = new JLabel(sqrImg);
	JLabel btn06 = new JLabel(sqrImg);
	JLabel btn07 = new JLabel(sqrImg);
	JLabel btn08 = new JLabel(sqrImg);
	JLabel btn09 = new JLabel(sqrImg);
	JLabel btn10 = new JLabel(sqrImg);
	JLabel btn11 = new JLabel(sqrImg);
	JLabel btn12 = new JLabel(sqrImg);
	JLabel btn13 = new JLabel(sqrImg);
	JLabel btn14 = new JLabel(sqrImg);
	JLabel btn15 = new JLabel(sqrImg);
	JLabel btn16 = new JLabel(sqrImg);
	JLabel btn17 = new JLabel(sqrImg);
	JLabel btn18 = new JLabel(sqrImg);
	JLabel btn19 = new JLabel(sqrImg);
	JLabel btn20 = new JLabel(sqrImg);
	JLabel btn21 = new JLabel(sqrImg);
	JLabel btn22 = new JLabel(sqrImg);
	JLabel btn23 = new JLabel(sqrImg);
	JLabel btn24 = new JLabel(sqrImg);
	JLabel btn25 = new JLabel(sqrImg);
	JLabel btn26 = new JLabel(sqrImg);
	JLabel btn27 = new JLabel(sqrImg);
	JLabel btn28 = new JLabel(sqrImg);
	JLabel btn29 = new JLabel(sqrImg);
	JLabel btn30 = new JLabel(sqrImg);
	JLabel btn31 = new JLabel(sqrImg);
	JLabel btn32 = new JLabel(sqrImg);
	JLabel btn33 = new JLabel(sqrImg);
	JLabel btn34 = new JLabel(sqrImg);
	JLabel btn35 = new JLabel(sqrImg);
	JLabel btn36 = new JLabel(sqrImg);
	JLabel btn37 = new JLabel(sqrImg);
	JLabel btn38 = new JLabel(sqrImg);
	JLabel btn39 = new JLabel(sqrImg);
	
	//


	// JPanel playerAIcon = new JPanel();
	JLabel playerIcon1 = new JLabel(player1);
	JButton btmessageOfA = new JButton(messageOfAImage);
	JLabel playerIcon2 = new JLabel(player2);
	JButton btmessageOfB = new JButton(messageOfBImage);

	
	// PlayerA's message
	JLabel name1 = new JLabel("玩家A ");
	JPanel p1 = new JPanel(new GridLayout(2, 2));

	// PlayerA's message
	JLabel cash11 = new JLabel("现金：");
	JLabel cash1 = new JLabel("10000");
	JLabel deposit11 = new JLabel("存款：");
	JLabel deposit1 = new JLabel("10000");
	JLabel gold11 = new JLabel("点券：");
	JLabel gold1 = new JLabel("2000 ");
	JLabel houseValue11 = new JLabel("房产：");
	JLabel houseValue1 = new JLabel("20000");
	JLabel totalValue11 = new JLabel("资产：");
	JLabel totalValue1 = new JLabel("200000");

	// PlayerB's message
	JLabel name2 = new JLabel("玩家B");
	JPanel p2 = new JPanel(new GridLayout(2, 1));

	// PlayerB's message
	JLabel cash21 = new JLabel("现金：");
	JLabel cash2 = new JLabel();
	JLabel deposit21 = new JLabel("存款：");
	JLabel deposit2 = new JLabel("10000");
	JLabel gold21 = new JLabel("点券：");
	JLabel gold2 = new JLabel("2000 ");
	JLabel houseValue21 = new JLabel("房产：");
	JLabel houseValue2 = new JLabel("20000");
	JLabel totalValue21 = new JLabel("资产：");
	JLabel totalValue2 = new JLabel("200000");
	JPanel p4 = new JPanel(new GridLayout(5, 2));

	// 游戏信息
	JPanel gameMessage = new JPanel();
	JTextArea message = new JTextArea();
	JTextArea playing = new JTextArea("Player1");
	JLabel time = new JLabel();
	JPanel whoPlay = new JPanel(new GridLayout(3, 1));


	public BigMap() {
		//squaresInitial();
		setLayout(null);
		a.setName("玩家1111");
		b.setName("玩家2222");

		// the game message
		// Date a1 = new Date();
		String theDisplay = "WINNER WINNER" + "\n"+"CHICKEN DINNER!";
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
		playing.setFont(f2);
		whoPlay.add(new JLabel("操作时间！"));
		whoPlay.setBackground(Color.CYAN);
		whoPlay.setForeground(Color.blue);

		gameMessage.add(message);
		gameMessage.add(whoPlay);
		gameMessage.setBorder(new TitledBorder("游戏信息"));
		gameMessage.setBounds(950, 99, 200, 180);
		add(gameMessage);//把游戏信息加入面板

		// The message of playerA
		Font font = new Font("Times", Font.ITALIC, 20);
		p1.add(pjl1);
		p1.add(name1);
		p1.add(new JLabel("身价：几个亿"));
		p1.add(new JLabel("等级：6"));
		name1.setFont(font);
		name1.setForeground(Color.RED);
		p1.setBorder(new TitledBorder("玩家1信息"));
		p1.setBounds(950, 369, 240, 160);
		add(p1);

		p2.add(pjl2);
		p2.add(name2);
		p2.add(new JLabel("身价：几个一"));
		p2.add(new JLabel("等级：3"));
		name2.setFont(font);
		name2.setForeground(Color.RED);
		p2.setBorder(new TitledBorder("玩家2信息"));
		p2.setBounds(950, 555, 240, 160);
		add(p2);

		// put buttons into panel
		jp.add(playerIcon1);
		jp.add(playerIcon2);

		//jp.add(btSave);
		// jp.add(btRead);
		
		
		
		//add(btmessageOfA);
		btmessageOfA.addActionListener(listener);
		//add(btmessageOfB);
		btmessageOfB.addActionListener(listener);

		btmessageOfA.setBounds(693, 138, 158, 128);
		btmessageOfB.setBounds(693, 268, 158, 128);
		

		// put the panel of player into panel
		add(jp);
		jp.setBounds(0, 0, 930, 888);
		jp.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		//MyAdds
		

		//add squares
		addSquares();
		
		//因为想监听上百个Button,为每个Button命名……
		//setNames();
		//交易操作监听
		addListeners();
		
		//add operations to Squares
		
		
		//add dices
		jp.add(dice1);
		dice1.addActionListener(listener);
		dice1.setBounds(sqr*5+20, sqr*9, 66, 66);
		jp.add(dice2);
		dice2.addActionListener(listener);
		dice2.setBounds(sqr*6+20, sqr*9, 66, 66);
		
		//add players
		// set buttons's position
		playerIcon1.setBounds(bounds[0][0]+px1, bounds[0][1]+py1, 20, 30);
		playerIcon2.setBounds(bounds[0][0]+px2, bounds[0][1]+py2, 20, 30);
		
		//add operations
		jp.add(tradeBtn);
		tradeBtn.setBounds(sqr*2+70, sqr*8, 88, 40);
		tradeBtn.setBackground(Color.CYAN);
		jp.add(buildBtn);
		buildBtn.setBounds(sqr*4+35, sqr*8, 88, 40);
		buildBtn.setBackground(Color.ORANGE);
		jp.add(sellBtn);
		sellBtn.setBounds(sqr*6, sqr*8, 88, 40);
		sellBtn.setBackground(Color.CYAN);
		jp.add(mortBtn);
		mortBtn.setBounds(sqr*8-35, sqr*8, 88, 40);
		mortBtn.setBackground(Color.ORANGE);
		jp.add(redeemBtn);
		redeemBtn.setBounds(sqr*10-70, sqr*8, 88, 40);
		redeemBtn.setBackground(Color.CYAN);
		Font ff = new Font("Times", Font.BOLD, 24);
		tradeBtn.setFont(ff);
		buildBtn.setFont(ff);
		sellBtn.setFont(ff);
		mortBtn.setFont(ff);
		redeemBtn.setFont(ff);
		//加入触发事件
		tradeBtn.addActionListener(listener);
		buildBtn.addActionListener(listener);
		sellBtn.addActionListener(listener);
		mortBtn.addActionListener(listener);
		redeemBtn.addActionListener(listener);
		
		//add finished button
		jp.add(finished);
		finished.setBounds(sqr*5+10, sqr*6+20, 200, 60);
		finished.addActionListener(finishTimer);
		jp.remove(finished);
		
	}


	// 第一层次Button监听
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			playTimes++;

			if (e.getSource() == dice1 || e.getSource() == dice2) {
				System.out.println(playerNum + "开始掷骰子！");
				//time.setText(shijian.getTime(playTimes, a, b));
				timer.start();
			}
			else if( e.getSource() == tradeBtn) {
	        	System.out.println(playerNum + "申请交易！");
	        	reset();
	        	jp.add(tButton01);
	        	tButton01.setBounds(bbs[1][0], bbs[1][1], bbs[1][2], bbs[1][3]);
	        	jp.add(tButton09);
	        	tButton09.setBounds(bbs[9][0], bbs[9][1], bbs[9][2], bbs[9][3]);
	        	jp.add(tButton33);
	        	tButton33.setBounds(bbs[33][0], bbs[33][1], bbs[33][2], bbs[33][3]);
	            jp.repaint();
	         }
	         else if( e.getSource() == buildBtn) {
		        	System.out.println(playerNum + "申请建房！");
		        	reset();
		        	jp.add(bButton11);
		        	bButton11.setBounds(bbs[11][0], bbs[11][1], bbs[11][2], bbs[11][3]);
		            jp.repaint();
		         }
	         else if( e.getSource() == sellBtn) {
		        	System.out.println(playerNum + "申请卖房！");
		        	reset();
		        	jp.add(sButton19);
		        	sButton19.setBounds(bbs[19][0], bbs[19][1], bbs[19][2], bbs[19][3]);
		            jp.repaint();
		         }
	         else if( e.getSource() == mortBtn) {
		        	System.out.println(playerNum + "申请抵押！");
		        	reset();
		        	jp.add(mButton24);
		        	mButton24.setBounds(bbs[24][0], bbs[24][1], bbs[24][2], bbs[24][3]);
		            jp.repaint();
		         }
	         else if( e.getSource() == redeemBtn) {
		        	System.out.println(playerNum + "申请赎回！");
		        	reset();
		        	jp.add(rButton39);
		        	rButton39.setBounds(bbs[39][0], bbs[39][1], bbs[39][2], bbs[39][3]);
		            jp.repaint();
		         }
		}
	}

	//骰子动画计时器
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int random = (int) (Math.random() * 6 + 1);
			switch (random) {
			case 1: {

				dice1.setIcon(dice_1);
			}
				break;
			case 2: {
				dice1.setIcon(dice_2);
			}
				break;
			case 3: {
				dice1.setIcon(dice_3);
			}
				break;
			case 4: {
				dice1.setIcon(dice_4);
			}
				break;
			case 5: {
				dice1.setIcon(dice_5);
			}
				break;
			case 6: {
				dice1.setIcon(dice_6);
			}
				break;
			default:
			}
			random = (int) (Math.random() * 6 + 1);
			switch (random) {
			case 1: {

				dice2.setIcon(dice_1);
			}
				break;
			case 2: {
				dice2.setIcon(dice_2);
			}
				break;
			case 3: {
				dice2.setIcon(dice_3);
			}
				break;
			case 4: {
				dice2.setIcon(dice_4);
			}
				break;
			case 5: {
				dice2.setIcon(dice_5);
			}
				break;
			case 6: {
				dice2.setIcon(dice_6);
			}
				break;
			default:
			}
			conDice++;
			if (conDice > 30) {
				timer.stop();
				diceResult1 = (int) (Math.random() * 6 + 1);
				diceResult2 = (int) (Math.random() * 6 + 1);
				diceResult = diceResult1 + diceResult2;
				
				switch (diceResult1) {
				case 0: {
					dice1.setIcon(dice_0);
				}
					break;
				case 1: {
					dice1.setIcon(dice_1);
				}
					break;
				case 2: {
					dice1.setIcon(dice_2);
				}
					break;
				case 3: {
					dice1.setIcon(dice_3);
				}
					break;
				case 4: {
					dice1.setIcon(dice_4);
				}
					break;
				case 5: {
					dice1.setIcon(dice_5);
				}
					break;
				case 6: {
					dice1.setIcon(dice_6);
				}
					break;
				default:
				}
				switch (diceResult2) {
				case 0: {
					dice2.setIcon(dice_0);
				}
					break;
				case 1: {
					dice2.setIcon(dice_1);
				}
					break;
				case 2: {
					dice2.setIcon(dice_2);
				}
					break;
				case 3: {
					dice2.setIcon(dice_3);
				}
					break;
				case 4: {
					dice2.setIcon(dice_4);
				}
					break;
				case 5: {
					dice2.setIcon(dice_5);
				}
					break;
				case 6: {
					dice2.setIcon(dice_6);
				}
					break;
				default:
				}
				i = (a.getLocation() + 1) % 40; 
				i2 = (b.getLocation() + 1) % 40;
				System.out.println("diceResult: "+diceResult);
				conDice = 0;
				timer2.start();
				System.out.println("开始前进！");
			}
		}
	}
	
	//玩家前进计时器

	class Timer2Listener implements ActionListener {
		int step = 0;

		// int i = a.getLocation() + 1; // - diceResult + 1;
		// int i_f = (a.getLocation() - 1 + 38) % 38;
		// int i2 = 20 + b.getLocation();// - diceResult;
		// int i2_f = (b.getLocation() + 19 - 1 + 38) % 38;

		// Handle ActionEvent
		public void actionPerformed(ActionEvent e) {
			// FireEvent sign = new FireEvent(j);

			if (playerNum == 1) {
				if (step >= diceResult) {
					timer2.stop();
					a.setLocation(diceResult);
					//前进完毕，处理到达土地后的事件
					FireEvent sign = new FireEvent(b.getLocation(), b, a);
					System.out.println("b.Position : "+ b.getLocation());
					sign.setVisible(true);
					//处理：相同点继续掷骰子
					if(diceResult1 == diceResult2) {
						if(diceTimes >= 3) {
							diceTimes = 0;
							//go to jail
						}
						else
							diceTimes++;
					}
					else {
						showFinished();
					}
					conPlay++;
					step = 0;
					diceResult = 0;
				} else {
					playerIcon1.setBounds(bounds[i][0]+px1, bounds[i][1]+py1, 20, 30);
					i = (i + 1) % 40;
					step++;
					System.out.println("Forward step " + step + " of " + diceResult + " steps");
				}
				// 玩家b的操作
			} 
			else if(playerNum == 2) {
				if (step >= diceResult) {
					timer2.stop();
					b.setLocation(diceResult);
					FireEvent sign = new FireEvent(b.getLocation(), b, a);
					System.out.println("b.Position : "+ b.getLocation());
					sign.setVisible(true);
					conPlay++;
					step = 0;
					//处理：相同点继续掷骰子
					if(diceResult1 == diceResult2) {
						if(diceTimes >= 3) {
							diceTimes = 0;
							//go to jail
						}
						else
							diceTimes++;
					}
					else {
						showFinished();
					}
				} else {
					playerIcon2.setBounds(bounds[i2][0]+px2, bounds[i2][1]+py2,  20, 30);
					i2 = (i2 + 1) % 40;
					step++;
					System.out.println("Forward step " + step + " of " + diceResult + " steps");
				}
			}
			
		}
	}

	//？？？
	class TimerPropMoneyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 监听购买土地的事件
			if (e.getSource() == FireEvent.btLandOK) {
				if (conPlay % 2 == 0)
					cash1.setText(String.valueOf(a.getCash() - 1000));
				else
					cash2.setText(String.valueOf(a.getCash() - 1000));
			}
			// 监听取款事件
			if (e.getSource() == FireEvent.btCash) {
				cash1.setText(String.valueOf(a.getCash()));
				cash2.setText(String.valueOf(b.getCash()));
				deposit1.setText(String.valueOf(a.getDeposit()));
				deposit2.setText(String.valueOf(b.getDeposit()));
			}
			// 监听存款事件。
			if (e.getSource() == FireEvent.btDecash) {
				cash1.setText(String.valueOf(a.getCash()));
				cash2.setText(String.valueOf(b.getCash()));
				deposit1.setText(String.valueOf(a.getDeposit()));
				deposit2.setText(String.valueOf(b.getDeposit()));
			}
		}
	}
	
	//下一个玩家
	public void showFinished() {
		jp.add(finished);
		jp.remove(dice1);
		jp.remove(dice2);
        jp.repaint();
	}

	
	//完成按钮监听
	class FinishListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	         if( e.getSource() == finished) {
	        	System.out.println(playerNum + "点击了完成按钮！");
	        	playerNum++;
	        	if(playerNum > 2)	playerNum = 1;
	        	System.out.println("现在是" + playerNum + "操作时间！");
	        	playing.setText("Player"+ playerNum);
	            jp.remove(finished);
	            jp.add(dice1);
	            jp.add(dice2);
	            reset();
	            jp.repaint();
	         }      
	      }     
	   }
	
	//为操作事件的Button添加监听
	public void addListeners() {
		System.out.println("Adding ListenerS!");
		addTradeListeners();
		addBuildListeners();
		addSellListeners();
		addMortListeners();
		addRedeemListeners();
	}
	
	//监听并区分交易事件
	public void addTradeListeners() {
		System.out.println("Adding TradeListenerS!");
		tButton01.addActionListener(tradeListener);
		tButton02.addActionListener(tradeListener);
		tButton03.addActionListener(tradeListener);
		tButton04.addActionListener(tradeListener);
		tButton05.addActionListener(tradeListener);
		tButton06.addActionListener(tradeListener);
		tButton07.addActionListener(tradeListener);
		tButton08.addActionListener(tradeListener);
		tButton09.addActionListener(tradeListener);
		tButton11.addActionListener(tradeListener);
		tButton12.addActionListener(tradeListener);
		tButton13.addActionListener(tradeListener);
		tButton14.addActionListener(tradeListener);
		tButton15.addActionListener(tradeListener);
		tButton16.addActionListener(tradeListener);
		tButton17.addActionListener(tradeListener);
		tButton18.addActionListener(tradeListener);
		tButton19.addActionListener(tradeListener);
		tButton21.addActionListener(tradeListener);
		tButton22.addActionListener(tradeListener);
		tButton23.addActionListener(tradeListener);
		tButton24.addActionListener(tradeListener);
		tButton25.addActionListener(tradeListener);
		tButton26.addActionListener(tradeListener);
		tButton27.addActionListener(tradeListener);
		tButton28.addActionListener(tradeListener);
		tButton29.addActionListener(tradeListener);
		tButton31.addActionListener(tradeListener);
		tButton32.addActionListener(tradeListener);
		tButton33.addActionListener(tradeListener);
		tButton34.addActionListener(tradeListener);
		tButton35.addActionListener(tradeListener);
		tButton36.addActionListener(tradeListener);
		tButton37.addActionListener(tradeListener);
		tButton38.addActionListener(tradeListener);
		tButton39.addActionListener(tradeListener);
	}
	
	//监听建房事件
	
	public void addBuildListeners() {
		System.out.println("Adding BuildListenerS!");
		bButton01.addActionListener(buildListener);
		bButton02.addActionListener(buildListener);
		bButton03.addActionListener(buildListener);
		bButton04.addActionListener(buildListener);
		bButton05.addActionListener(buildListener);
		bButton06.addActionListener(buildListener);
		bButton07.addActionListener(buildListener);
		bButton08.addActionListener(buildListener);
		bButton09.addActionListener(buildListener);
		bButton11.addActionListener(buildListener);
		bButton12.addActionListener(buildListener);
		bButton13.addActionListener(buildListener);
		bButton14.addActionListener(buildListener);
		bButton15.addActionListener(buildListener);
		bButton16.addActionListener(buildListener);
		bButton17.addActionListener(buildListener);
		bButton18.addActionListener(buildListener);
		bButton19.addActionListener(buildListener);
		bButton21.addActionListener(buildListener);
		bButton22.addActionListener(buildListener);
		bButton23.addActionListener(buildListener);
		bButton24.addActionListener(buildListener);
		bButton25.addActionListener(buildListener);
		bButton26.addActionListener(buildListener);
		bButton27.addActionListener(buildListener);
		bButton28.addActionListener(buildListener);
		bButton29.addActionListener(buildListener);
		bButton31.addActionListener(buildListener);
		bButton32.addActionListener(buildListener);
		bButton33.addActionListener(buildListener);
		bButton34.addActionListener(buildListener);
		bButton35.addActionListener(buildListener);
		bButton36.addActionListener(buildListener);
		bButton37.addActionListener(buildListener);
		bButton38.addActionListener(buildListener);
		bButton39.addActionListener(buildListener);
	}
	
	//监听卖房事件

	public void addSellListeners() {
		System.out.println("Adding SellListenerS!");
		sButton01.addActionListener(sellListener);
		sButton02.addActionListener(sellListener);
		sButton03.addActionListener(sellListener);
		sButton04.addActionListener(sellListener);
		sButton05.addActionListener(sellListener);
		sButton06.addActionListener(sellListener);
		sButton07.addActionListener(sellListener);
		sButton08.addActionListener(sellListener);
		sButton09.addActionListener(sellListener);
		sButton11.addActionListener(sellListener);
		sButton12.addActionListener(sellListener);
		sButton13.addActionListener(sellListener);
		sButton14.addActionListener(sellListener);
		sButton15.addActionListener(sellListener);
		sButton16.addActionListener(sellListener);
		sButton17.addActionListener(sellListener);
		sButton18.addActionListener(sellListener);
		sButton19.addActionListener(sellListener);
		sButton21.addActionListener(sellListener);
		sButton22.addActionListener(sellListener);
		sButton23.addActionListener(sellListener);
		sButton24.addActionListener(sellListener);
		sButton25.addActionListener(sellListener);
		sButton26.addActionListener(sellListener);
		sButton27.addActionListener(sellListener);
		sButton28.addActionListener(sellListener);
		sButton29.addActionListener(sellListener);
		sButton31.addActionListener(sellListener);
		sButton32.addActionListener(sellListener);
		sButton33.addActionListener(sellListener);
		sButton34.addActionListener(sellListener);
		sButton35.addActionListener(sellListener);
		sButton36.addActionListener(sellListener);
		sButton37.addActionListener(sellListener);
		sButton38.addActionListener(sellListener);
		sButton39.addActionListener(sellListener);
	}
	
	//监听抵押事件
	
	public void addMortListeners() {
		System.out.println("Adding MortListenerS!");
		mButton01.addActionListener(mortListener);
		mButton02.addActionListener(mortListener);
		mButton03.addActionListener(mortListener);
		mButton04.addActionListener(mortListener);
		mButton05.addActionListener(mortListener);
		mButton06.addActionListener(mortListener);
		mButton07.addActionListener(mortListener);
		mButton08.addActionListener(mortListener);
		mButton09.addActionListener(mortListener);
		mButton11.addActionListener(mortListener);
		mButton12.addActionListener(mortListener);
		mButton13.addActionListener(mortListener);
		mButton14.addActionListener(mortListener);
		mButton15.addActionListener(mortListener);
		mButton16.addActionListener(mortListener);
		mButton17.addActionListener(mortListener);
		mButton18.addActionListener(mortListener);
		mButton19.addActionListener(mortListener);
		mButton21.addActionListener(mortListener);
		mButton22.addActionListener(mortListener);
		mButton23.addActionListener(mortListener);
		mButton24.addActionListener(mortListener);
		mButton25.addActionListener(mortListener);
		mButton26.addActionListener(mortListener);
		mButton27.addActionListener(mortListener);
		mButton28.addActionListener(mortListener);
		mButton29.addActionListener(mortListener);
		mButton31.addActionListener(mortListener);
		mButton32.addActionListener(mortListener);
		mButton33.addActionListener(mortListener);
		mButton34.addActionListener(mortListener);
		mButton35.addActionListener(mortListener);
		mButton36.addActionListener(mortListener);
		mButton37.addActionListener(mortListener);
		mButton38.addActionListener(mortListener);
		mButton39.addActionListener(mortListener);
	}
	
	//监听赎回事件
	
	public void addRedeemListeners() {
		System.out.println("Adding RedeemListenerS!");
		rButton01.addActionListener(redeemListener);
		rButton02.addActionListener(redeemListener);
		rButton03.addActionListener(redeemListener);
		rButton04.addActionListener(redeemListener);
		rButton05.addActionListener(redeemListener);
		rButton06.addActionListener(redeemListener);
		rButton07.addActionListener(redeemListener);
		rButton08.addActionListener(redeemListener);
		rButton09.addActionListener(redeemListener);
		rButton11.addActionListener(redeemListener);
		rButton12.addActionListener(redeemListener);
		rButton13.addActionListener(redeemListener);
		rButton14.addActionListener(redeemListener);
		rButton15.addActionListener(redeemListener);
		rButton16.addActionListener(redeemListener);
		rButton17.addActionListener(redeemListener);
		rButton18.addActionListener(redeemListener);
		rButton19.addActionListener(redeemListener);
		rButton21.addActionListener(redeemListener);
		rButton22.addActionListener(redeemListener);
		rButton23.addActionListener(redeemListener);
		rButton24.addActionListener(redeemListener);
		rButton25.addActionListener(redeemListener);
		rButton26.addActionListener(redeemListener);
		rButton27.addActionListener(redeemListener);
		rButton28.addActionListener(redeemListener);
		rButton29.addActionListener(redeemListener);
		rButton31.addActionListener(redeemListener);
		rButton32.addActionListener(redeemListener);
		rButton33.addActionListener(redeemListener);
		rButton34.addActionListener(redeemListener);
		rButton35.addActionListener(redeemListener);
		rButton36.addActionListener(redeemListener);
		rButton37.addActionListener(redeemListener);
		rButton38.addActionListener(redeemListener);
		rButton39.addActionListener(redeemListener);
	}
	
	//土地加入面板并确定土地的位置
	public void addSquares() {
		jp.add(btn00);
		btn00.setBounds(bounds[0][0], bounds[0][1], bounds[0][2], bounds[0][3]);
		jp.add(btn01);
		btn01.setBounds(bounds[1][0], bounds[1][1], bounds[1][2], bounds[1][3]);
		jp.add(btn02);
		btn02.setBounds(bounds[2][0], bounds[2][1], bounds[2][2], bounds[2][3]);
		jp.add(btn03);
		btn03.setBounds(bounds[3][0], bounds[3][1], bounds[3][2], bounds[3][3]);
		jp.add(btn04);
		btn04.setBounds(bounds[4][0], bounds[4][1], bounds[4][2], bounds[4][3]);
		jp.add(btn05);
		btn05.setBounds(bounds[5][0], bounds[5][1], bounds[5][2], bounds[5][3]);
		jp.add(btn06);
		btn06.setBounds(bounds[6][0], bounds[6][1], bounds[6][2], bounds[6][3]);
		jp.add(btn07);
		btn07.setBounds(bounds[7][0], bounds[7][1], bounds[7][2], bounds[7][3]);
		jp.add(btn08);
		btn08.setBounds(bounds[8][0], bounds[8][1], bounds[8][2], bounds[8][3]);
		jp.add(btn09);
		btn09.setBounds(bounds[9][0], bounds[9][1], bounds[9][2], bounds[9][3]);
		jp.add(btn10);
		btn10.setBounds(bounds[10][0], bounds[10][1], bounds[10][2], bounds[10][3]);
		jp.add(btn11);
		btn11.setBounds(bounds[11][0], bounds[11][1], bounds[11][2], bounds[11][3]);
		jp.add(btn12);
		btn12.setBounds(bounds[12][0], bounds[12][1], bounds[12][2], bounds[12][3]);
		jp.add(btn13);
		btn13.setBounds(bounds[13][0], bounds[13][1], bounds[13][2], bounds[13][3]);
		jp.add(btn14);
		btn14.setBounds(bounds[14][0], bounds[14][1], bounds[14][2], bounds[14][3]);
		jp.add(btn15);
		btn15.setBounds(bounds[15][0], bounds[15][1], bounds[15][2], bounds[15][3]);
		jp.add(btn16);
		btn16.setBounds(bounds[16][0], bounds[16][1], bounds[16][2], bounds[16][3]);
		jp.add(btn17);
		btn17.setBounds(bounds[17][0], bounds[17][1], bounds[17][2], bounds[17][3]);
		jp.add(btn18);
		btn18.setBounds(bounds[18][0], bounds[18][1], bounds[18][2], bounds[18][3]);
		jp.add(btn19);
		btn19.setBounds(bounds[19][0], bounds[19][1], bounds[19][2], bounds[19][3]);
		jp.add(btn20);
		btn20.setBounds(bounds[20][0], bounds[20][1], bounds[20][2], bounds[20][3]);
		jp.add(btn21);
		btn21.setBounds(bounds[21][0], bounds[21][1], bounds[21][2], bounds[21][3]);
		jp.add(btn22);
		btn22.setBounds(bounds[22][0], bounds[22][1], bounds[22][2], bounds[22][3]);
		jp.add(btn23);
		btn23.setBounds(bounds[23][0], bounds[23][1], bounds[23][2], bounds[23][3]);
		jp.add(btn24);
		btn24.setBounds(bounds[24][0], bounds[24][1], bounds[24][2], bounds[24][3]);
		jp.add(btn25);
		btn25.setBounds(bounds[25][0], bounds[25][1], bounds[25][2], bounds[25][3]);
		jp.add(btn26);
		btn26.setBounds(bounds[26][0], bounds[26][1], bounds[26][2], bounds[26][3]);
		jp.add(btn27);
		btn27.setBounds(bounds[27][0], bounds[27][1], bounds[27][2], bounds[27][3]);
		jp.add(btn28);
		btn28.setBounds(bounds[28][0], bounds[28][1], bounds[28][2], bounds[28][3]);
		jp.add(btn29);
		btn29.setBounds(bounds[29][0], bounds[29][1], bounds[29][2], bounds[29][3]);
		jp.add(btn30);
		btn30.setBounds(bounds[30][0], bounds[30][1], bounds[30][2], bounds[30][3]);
		//System.out.println(bounds[30][0]+","+bounds[30][1]);
		jp.add(btn31);
		btn31.setBounds(bounds[31][0], bounds[31][1], bounds[31][2], bounds[31][3]);
		//System.out.println(bounds[31][0]+","+bounds[31][1]);
		jp.add(btn32);
		btn32.setBounds(bounds[32][0], bounds[32][1], bounds[32][2], bounds[32][3]);
		jp.add(btn33);
		btn33.setBounds(bounds[33][0], bounds[33][1], bounds[33][2], bounds[33][3]);
		jp.add(btn34);
		btn34.setBounds(bounds[34][0], bounds[34][1], bounds[34][2], bounds[34][3]);
		jp.add(btn35);
		btn35.setBounds(bounds[35][0], bounds[35][1], bounds[35][2], bounds[35][3]);
		jp.add(btn36);
		btn36.setBounds(bounds[36][0], bounds[36][1], bounds[36][2], bounds[36][3]);
		jp.add(btn37);
		btn37.setBounds(bounds[37][0], bounds[37][1], bounds[37][2], bounds[37][3]);
		jp.add(btn38);
		btn38.setBounds(bounds[38][0], bounds[38][1], bounds[38][2], bounds[38][3]);
		jp.add(btn39);
		btn39.setBounds(bounds[39][0], bounds[39][1], bounds[39][2], bounds[39][3]);
	}
	

	// 移除所有交易Button

	public void reset() {
		jp.remove(tButton01);
		jp.remove(tButton02);
		jp.remove(tButton03);
		jp.remove(tButton04);
		jp.remove(tButton05);
		jp.remove(tButton06);
		jp.remove(tButton07);
		jp.remove(tButton08);
		jp.remove(tButton09);
		jp.remove(tButton11);
		jp.remove(tButton12);
		jp.remove(tButton13);
		jp.remove(tButton14);
		jp.remove(tButton15);
		jp.remove(tButton16);
		jp.remove(tButton17);
		jp.remove(tButton18);
		jp.remove(tButton19);
		jp.remove(tButton21);
		jp.remove(tButton22);
		jp.remove(tButton23);
		jp.remove(tButton24);
		jp.remove(tButton25);
		jp.remove(tButton26);
		jp.remove(tButton27);
		jp.remove(tButton28);
		jp.remove(tButton29);
		jp.remove(tButton31);
		jp.remove(tButton32);
		jp.remove(tButton33);
		jp.remove(tButton34);
		jp.remove(tButton35);
		jp.remove(tButton36);
		jp.remove(tButton37);
		jp.remove(tButton38);
		jp.remove(tButton39);

		// 移除所有建房Button
		jp.remove(bButton01);
		jp.remove(bButton02);
		jp.remove(bButton03);
		jp.remove(bButton04);
		jp.remove(bButton05);
		jp.remove(bButton06);
		jp.remove(bButton07);
		jp.remove(bButton08);
		jp.remove(bButton09);
		jp.remove(bButton11);
		jp.remove(bButton12);
		jp.remove(bButton13);
		jp.remove(bButton14);
		jp.remove(bButton15);
		jp.remove(bButton16);
		jp.remove(bButton17);
		jp.remove(bButton18);
		jp.remove(bButton19);
		jp.remove(bButton21);
		jp.remove(bButton22);
		jp.remove(bButton23);
		jp.remove(bButton24);
		jp.remove(bButton25);
		jp.remove(bButton26);
		jp.remove(bButton27);
		jp.remove(bButton28);
		jp.remove(bButton29);
		jp.remove(bButton31);
		jp.remove(bButton32);
		jp.remove(bButton33);
		jp.remove(bButton34);
		jp.remove(bButton35);
		jp.remove(bButton36);
		jp.remove(bButton37);
		jp.remove(bButton38);
		jp.remove(bButton39);

		// 移除所有卖房Button
		jp.remove(sButton01);
		jp.remove(sButton02);
		jp.remove(sButton03);
		jp.remove(sButton04);
		jp.remove(sButton05);
		jp.remove(sButton06);
		jp.remove(sButton07);
		jp.remove(sButton08);
		jp.remove(sButton09);
		jp.remove(sButton11);
		jp.remove(sButton12);
		jp.remove(sButton13);
		jp.remove(sButton14);
		jp.remove(sButton15);
		jp.remove(sButton16);
		jp.remove(sButton17);
		jp.remove(sButton18);
		jp.remove(sButton19);
		jp.remove(sButton21);
		jp.remove(sButton22);
		jp.remove(sButton23);
		jp.remove(sButton24);
		jp.remove(sButton25);
		jp.remove(sButton26);
		jp.remove(sButton27);
		jp.remove(sButton28);
		jp.remove(sButton29);
		jp.remove(sButton31);
		jp.remove(sButton32);
		jp.remove(sButton33);
		jp.remove(sButton34);
		jp.remove(sButton35);
		jp.remove(sButton36);
		jp.remove(sButton37);
		jp.remove(sButton38);
		jp.remove(sButton39);

		// 移除所有抵押Button
		jp.remove(mButton01);
		jp.remove(mButton02);
		jp.remove(mButton03);
		jp.remove(mButton04);
		jp.remove(mButton05);
		jp.remove(mButton06);
		jp.remove(mButton07);
		jp.remove(mButton08);
		jp.remove(mButton09);
		jp.remove(mButton11);
		jp.remove(mButton12);
		jp.remove(mButton13);
		jp.remove(mButton14);
		jp.remove(mButton15);
		jp.remove(mButton16);
		jp.remove(mButton17);
		jp.remove(mButton18);
		jp.remove(mButton19);
		jp.remove(mButton21);
		jp.remove(mButton22);
		jp.remove(mButton23);
		jp.remove(mButton24);
		jp.remove(mButton25);
		jp.remove(mButton26);
		jp.remove(mButton27);
		jp.remove(mButton28);
		jp.remove(mButton29);
		jp.remove(mButton31);
		jp.remove(mButton32);
		jp.remove(mButton33);
		jp.remove(mButton34);
		jp.remove(mButton35);
		jp.remove(mButton36);
		jp.remove(mButton37);
		jp.remove(mButton38);
		jp.remove(mButton39);

		// 移除所有赎回Button
		jp.remove(rButton01);
		jp.remove(rButton02);
		jp.remove(rButton03);
		jp.remove(rButton04);
		jp.remove(rButton05);
		jp.remove(rButton06);
		jp.remove(rButton07);
		jp.remove(rButton08);
		jp.remove(rButton09);
		jp.remove(rButton11);
		jp.remove(rButton12);
		jp.remove(rButton13);
		jp.remove(rButton14);
		jp.remove(rButton15);
		jp.remove(rButton16);
		jp.remove(rButton17);
		jp.remove(rButton18);
		jp.remove(rButton19);
		jp.remove(rButton21);
		jp.remove(rButton22);
		jp.remove(rButton23);
		jp.remove(rButton24);
		jp.remove(rButton25);
		jp.remove(rButton26);
		jp.remove(rButton27);
		jp.remove(rButton28);
		jp.remove(rButton29);
		jp.remove(rButton31);
		jp.remove(rButton32);
		jp.remove(rButton33);
		jp.remove(rButton34);
		jp.remove(rButton35);
		jp.remove(rButton36);
		jp.remove(rButton37);
		jp.remove(rButton38);
		jp.remove(rButton39);
	}
	
	//初始化土地列表
}

