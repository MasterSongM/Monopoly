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

	public int pNum = 2;//玩家数量
	boolean isForwarding = false;
	// The background map
	int conDice = 0;
	static List<Square> squares = new SquareList().intial();
	static List<Player> players=new ArrayList<Player>();//出于对动态选择玩家数量的考虑，目前尚未开发
	{
		for(int q = 0; q < pNum; q++) {
			players.add(new Player());
		}
	}
	static int conPlay = 0;
	static int playTimes = 0;
	static int playerNum = 0;
	static int diceTimes = 0;//连续掷骰子次数
	static int diceResult;
	static int diceResult1;
	static int diceResult2;
	static int sqr = 72;//Square size
	int i;// = a.getLocation() + 1; // - diceResult + 1;
	int i2;// = 20 + b.getLocation();// - diceResult;
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
	//private JPanel playPanel1 = new JPanel(new GridBagLayout());
	private ImageIcon player0 = new ImageIcon("icons/players/player1.png");
	PlayerPanel pp1 = new PlayerPanel(players.get(0),player0);


	int px2 = 10, py2 = 35;
	private ImageIcon player1 = new ImageIcon("icons/players/player2.png");
	PlayerPanel pp2 = new PlayerPanel(players.get(1),player1);
	
	int px3 = 10, py3 = 35;
	private ImageIcon player3 = new ImageIcon("icons/players/player3.png");
	
	int px4 = 10, py4 = 35;
	private ImageIcon player4 = new ImageIcon("icons/players/player4.png");

	// the dices
	private ImageIcon dice_0 = new ImageIcon("icons/dice0.png");
	private ImageIcon dice_1 = new ImageIcon("icons/dice1.png");
	private ImageIcon dice_2 = new ImageIcon("icons/dice2.png");
	private ImageIcon dice_3 = new ImageIcon("icons/dice3.png");
	private ImageIcon dice_4 = new ImageIcon("icons/dice4.png");
	private ImageIcon dice_5 = new ImageIcon("icons/dice5.png");
	private ImageIcon dice_6 = new ImageIcon("icons/dice6.png");
	
	//房子等级图标
	private ImageIcon house0 = new ImageIcon("icons/buttons/0house.png");
	private ImageIcon house1 = new ImageIcon("icons/buttons/1house.png");
	private ImageIcon house2 = new ImageIcon("icons/buttons/2house.png");
	private ImageIcon house3 = new ImageIcon("icons/buttons/3house.png");
	private ImageIcon house4 = new ImageIcon("icons/buttons/4house.png");
	private ImageIcon house5 = new ImageIcon("icons/buttons/5house.png");
	
	//24个拥有者标签
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
	
	private List<JLabel> own = new ArrayList<JLabel>();
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
	
	//Operation Icon to Squares
	private ImageIcon tradeIcon = new ImageIcon("icons/buttons/1trade.png");
	private List<JButton> tradeArray = new ArrayList<JButton>();//trade Button 列表
	private ImageIcon buildIcon = new ImageIcon("icons/buttons/1build.png");
	private List<JButton> buildArray = new ArrayList<JButton>();//build Button 列表
	private ImageIcon sellIcon = new ImageIcon("icons/buttons/1sell.png");
	private List<JButton> sellArray = new ArrayList<JButton>();//sell Button 列表
	private ImageIcon mortIcon = new ImageIcon("icons/buttons/1mort.png");
	private List<JButton> mortArray = new ArrayList<JButton>();//mortgage Button 列表
	private ImageIcon redeemIcon = new ImageIcon("icons/buttons/1redeem.png");
	private List<JButton> redeemArray = new ArrayList<JButton>();//reddem Button 列表
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
	{//add tButtons to Array
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
	{
		//add buildBUttons to Array
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
	{
		//add sellButtons to Array
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
	{
		//add sellButtons to Array
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
	{
		//add redeemButtons to Array
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


	// Create buttons to hold dices
	JButton dice1 = new JButton(dice_6);
	JButton dice2 = new JButton(dice_6);
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
	JLabel btn01 = new JLabel(house0);
	JLabel btn02 = new JLabel(house0);
	JLabel btn03 = new JLabel(house0);
	JLabel btn04 = new JLabel(sqrImg);
	JLabel btn05 = new JLabel(sqrImg);
	JLabel btn06 = new JLabel(sqrImg);
	JLabel btn07 = new JLabel(house0);
	JLabel btn08 = new JLabel(house0);
	JLabel btn09 = new JLabel(house0);
	JLabel btn10 = new JLabel(new ImageIcon("icons/buttons/jail.png"));
	JLabel btn11 = new JLabel(house0);
	JLabel btn12 = new JLabel(house0);
	JLabel btn13 = new JLabel(house0);
	JLabel btn14 = new JLabel(sqrImg);
	JLabel btn15 = new JLabel(sqrImg);
	JLabel btn16 = new JLabel(sqrImg);
	JLabel btn17 = new JLabel(house0);
	JLabel btn18 = new JLabel(house0);
	JLabel btn19 = new JLabel(house0);
	JLabel btn20 = new JLabel(new ImageIcon("icons/buttons/shop.png"));
	JLabel btn21 = new JLabel(house0);
	JLabel btn22 = new JLabel(house0);
	JLabel btn23 = new JLabel(house0);
	JLabel btn24 = new JLabel(sqrImg);
	JLabel btn25 = new JLabel(sqrImg);
	JLabel btn26 = new JLabel(sqrImg);
	JLabel btn27 = new JLabel(house0);
	JLabel btn28 = new JLabel(house0);
	JLabel btn29 = new JLabel(house0);
	JLabel btn30 = new JLabel(new ImageIcon("icons/buttons/toJail.png"));
	JLabel btn31 = new JLabel(house0);
	JLabel btn32 = new JLabel(house0);
	JLabel btn33 = new JLabel(house0);
	JLabel btn34 = new JLabel(sqrImg);
	JLabel btn35 = new JLabel(sqrImg);
	JLabel btn36 = new JLabel(sqrImg);
	JLabel btn37 = new JLabel(house0);
	JLabel btn38 = new JLabel(house0);
	JLabel btn39 = new JLabel(house0);
	
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
		//squaresInitial();
		setLayout(null);
		players.get(0).setName("玩家1111");
		players.get(1).setName("玩家2222");

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
		playing.setText(players.get(playerNum).getName());
		playing.setFont(f2);
		whoPlay.add(new JLabel("操作时间！"));
		whoPlay.setBackground(Color.CYAN);
		whoPlay.setForeground(Color.blue);

		gameMessage.add(message);
		gameMessage.add(whoPlay);
		gameMessage.setBorder(new TitledBorder("游戏信息"));
		gameMessage.setBounds(950, 99, 200, 180);
		add(gameMessage);//把游戏信息加入面板

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
		//MyAdds
		

		//add squares
		addSquares();
		
		//添加标签位置用于标记owner
		/*own01.setBounds(bbs[1][0], bbs[1][1], bbs[1][2], bbs[1][3]);
		own02.setBounds(bbs[2][0], bbs[2][1], bbs[2][2], bbs[2][3]);
		own03.setBounds(bbs[3][0], bbs[3][1], bbs[3][2], bbs[3][3]);
		own07.setBounds(bbs[7][0], bbs[7][1], bbs[7][2], bbs[7][3]);
		own08.setBounds(bbs[8][0], bbs[8][1], bbs[8][2], bbs[8][3]);
		own09.setBounds(bbs[9][0], bbs[9][1], bbs[9][2], bbs[9][3]);

		own11.setBounds(bbs[11][0], bbs[11][1], bbs[11][2], bbs[11][3]);
		own12.setBounds(bbs[12][0], bbs[12][1], bbs[12][2], bbs[12][3]);
		own13.setBounds(bbs[13][0], bbs[13][1], bbs[13][2], bbs[13][3]);
		own17.setBounds(bbs[17][0], bbs[17][1], bbs[17][2], bbs[17][3]);
		own18.setBounds(bbs[18][0], bbs[18][1], bbs[18][2], bbs[18][3]);
		own19.setBounds(bbs[19][0], bbs[19][1], bbs[19][2], bbs[19][3]);

		own21.setBounds(bbs[21][0], bbs[21][1], bbs[21][2], bbs[21][3]);
		own22.setBounds(bbs[22][0], bbs[22][1], bbs[22][2], bbs[22][3]);
		own23.setBounds(bbs[23][0], bbs[23][1], bbs[23][2], bbs[23][3]);
		own27.setBounds(bbs[27][0], bbs[27][1], bbs[27][2], bbs[27][3]);
		own28.setBounds(bbs[28][0], bbs[28][1], bbs[28][2], bbs[28][3]);
		own29.setBounds(bbs[29][0], bbs[29][1], bbs[29][2], bbs[29][3]);

		own31.setBounds(bbs[31][0], bbs[31][1], bbs[31][2], bbs[31][3]);
		own32.setBounds(bbs[32][0], bbs[32][1], bbs[32][2], bbs[32][3]);
		own33.setBounds(bbs[33][0], bbs[33][1], bbs[33][2], bbs[33][3]);
		own37.setBounds(bbs[37][0], bbs[37][1], bbs[37][2], bbs[37][3]);
		own38.setBounds(bbs[38][0], bbs[38][1], bbs[38][2], bbs[38][3]);
		own39.setBounds(bbs[39][0], bbs[39][1], bbs[39][2], bbs[39][3]);*/
		for(int o = 0; o < 40; o++) {
			own.get(o).setBounds(bbs[o][0], bbs[o][1], bbs[o][2], bbs[o][3]);
		}
		
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
		
		//test
		
		
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
	        	for(int temp = 0; temp < 40; temp++) {
	        		switch(temp % 10) {
	        		case 1:
	        		case 2:
	        		case 3:
	        		case 7:
	        		case 8:
	        		case 9:
	        			Land t = (Land)squares.get(temp);
	        			if(t.getOwner() != null) {
	        				if(t.getOwner() != players.get(playerNum) && t.getStatus() == 1) {
	        					System.out.println("Show trade: "+temp);
	        					jp.add(tradeArray.get(temp));
	        					tradeArray.get(temp).setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
	        				}
	        			}
	        		}
	        	}
	            jp.repaint();
	         }
	         else if( e.getSource() == buildBtn) {
		        	System.out.println(playerNum + "申请建房！");
		        	reset();
		        	for(int temp = 0; temp < 40; temp++) {
		        		switch(temp % 10) {
		        		case 1:
		        		case 2:
		        		case 3:
		        		case 7:
		        		case 8:
		        		case 9:
		        			Land t = (Land)squares.get(temp);
		        			if(t.getOwner() != null) {
		        				if(t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
		        					JButton tempButton= buildArray.get(temp);
		        					jp.add(tempButton);
		        					tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
		        				}
		        			}
		        		}
		        	}
		            jp.repaint();
		         }
	         else if( e.getSource() == sellBtn) {
		        	System.out.println(playerNum + "申请卖房！");
		        	reset();
		        	for(int temp = 0; temp < 40; temp++) {
		        		switch(temp % 10) {
		        		case 1:
		        		case 2:
		        		case 3:
		        		case 7:
		        		case 8:
		        		case 9:
		        			Land t = (Land)squares.get(i);
		        			if(t.getOwner() != null) {
		        				if(t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
		        					JButton tempButton= sellArray.get(temp);
		        					jp.add(tempButton);
		        					tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
		        				}
		        			}
		        		}
		        	}
		            jp.repaint();
		         }
	         else if( e.getSource() == mortBtn) {
		        	System.out.println(playerNum + "申请抵押！");
		        	reset();
		        	for(int temp = 0; temp < 40; temp++) {
		        		switch(temp % 10) {
		        		case 1:
		        		case 2:
		        		case 3:
		        		case 7:
		        		case 8:
		        		case 9:
		        			Land t = (Land)squares.get(temp);
		        			if(t.getOwner() != null) {
		        				if(t.getOwner() == players.get(playerNum) && t.getStatus() == 1) {
		        					JButton tempButton= mortArray.get(temp);
		        					jp.add(tempButton);
		        					tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[i][3]);
		        				}
		        			}
		        		}
		        	}
		            jp.repaint();
		         }
	         else if( e.getSource() == redeemBtn) {
		        	System.out.println(playerNum + "申请赎回！");
		        	reset();
		        	for(int temp = 0; temp < 40; temp++) {
		        		switch(temp % 10) {
		        		case 1:
		        		case 2:
		        		case 3:
		        		case 7:
		        		case 8:
		        		case 9:
		        			Land t = (Land)squares.get(temp);
		        			if(t.getOwner() != null) {
		        				if(t.getOwner() == players.get(playerNum) && t.getStatus() == 0) {
		        					JButton tempButton= redeemArray.get(temp);
		        					jp.add(tempButton);
		        					tempButton.setBounds(bbs[temp][0], bbs[temp][1], bbs[temp][2], bbs[temp][3]);
		        				}
		        			}
		        		}
		        	}
		            jp.repaint();
		         }
		}
	}

	//骰子动画计时器
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(isForwarding == false) {//没有正在前进的动画
				//是否在监狱
				if(players.get(playerNum).jailDays > 0) {
					int option = JOptionPane.showConfirmDialog(null, "是否支付200出狱？");
					if(option == 0) {
						players.get(playerNum).setCash(-200);
						players.get(playerNum).setJailDays(0);
					}
					else {//跳过掷骰子步骤
						showFinished();
					}
				}
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
					isForwarding = true;//开始前进动画
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
					//三次相同点进监狱
					if(diceResult1 == diceResult2) {
						diceTimes++;
						if(diceTimes >= 3 ) {
							//go to jail
							JOptionPane.showMessageDialog(null, "你连续三次掷出了相同点数，\n我们怀疑你作弊,移送监狱！");
							new GoToJail().goJail(players.get(playerNum));
							switch(playerNum) {
							case 0:
								playerIcon1.setBounds(bounds[10][0]+px1, bounds[10][1]+py1, 20, 30);
								break;
							case 1:
								playerIcon2.setBounds(bounds[10][0]+px2, bounds[10][1]+py2, 20, 30);
								break;
							}
							showFinished();
						}
					}
					if(isForwarding) {//即掷出骰子，且没有因为三次相同而进监狱
						i = (players.get(playerNum).getLocation() + 1) % 40;
						System.out.println("diceResult: "+diceResult);
						System.out.println("i test :"+ i);
						conDice = 0;
						if(players.get(playerNum).getJailDays() == 0 || diceResult1 == diceResult2) {
							timer2.start();
						}
						else {
							players.get(playerNum).setJailDays(players.get(playerNum).getJailDays() - 1);
						}
						System.out.println("开始前进！");
					}
				}
			}
			else {
				System.out.println("在前进时点击了骰子，不做处理！");
			}
		}
	}
	
	//玩家前进计时器

	class Timer2Listener implements ActionListener {
		int step = 0;

		// Handle ActionEvent
		public void actionPerformed(ActionEvent e) {
			// FireEvent sign = new FireEvent(j);

				if (step >= diceResult) {
					timer2.stop();
					players.get(playerNum).setLocation(diceResult);
					//前进完毕，处理到达土地后的事件
					int squareNum = players.get(playerNum).getLocation();
					switch(squareNum%10){
						case 1:
						case 2:
						case 3:
						case 7:
						case 8:
						case 9:
						System.out.println("到达了一块可交易土地");
						Land currentLocation = (Land)(squares.get(squareNum));
						System.out.println(players.get(playerNum).getName()+"的当前位置 : "
									+ squareNum);
						AutoEvent sign = new AutoEvent(currentLocation, players.get(playerNum));
						sign.setVisible(true);
						showOwner(squareNum);
						break;
						case 30:
							JOptionPane.showMessageDialog(null, "资本违法，移送监狱！");
							new GoToJail().goJail(players.get(playerNum));
							switch(playerNum) {
							case 0:
								playerIcon1.setBounds(bounds[10][0]+px1, bounds[10][1]+py1, 20, 30);
								break;
							case 1:
								playerIcon2.setBounds(bounds[10][0]+px2, bounds[10][1]+py2, 20, 30);
								break;
							}
						default:
							System.out.println("到达了一块非交易型的土地");
							switch(players.get(playerNum).getLocation()) {
							case 0:
								//经过起点+200
							case 4:
								//其他事件
							default:
							}
					}
					
					showFinished();
					conPlay++;
					step = 0;
					diceResult = 0;
				} else {//动画前进一步
					switch(playerNum) {
					case 0:
						playerIcon1.setBounds(bounds[i][0]+px1, bounds[i][1]+py1, 20, 30);
						break;
					case 1:
						playerIcon2.setBounds(bounds[i][0]+px2, bounds[i][1]+py2, 20, 30);
						break;
					default:
						JOptionPane.showMessageDialog(null, "啊哦，出现了某些问题！");
					}
					i = (i + 1) % 40;
					step++;
					System.out.println("Forward step " + step + " of " + diceResult + " steps");
				}
				
				//更新右侧玩家信息
				pp1.cash.setText("现金:"+players.get(0).getCash());
				pp1.more.setText("地产数:"+players.get(0).getHouseNum());
				pp2.cash.setText("现金:"+players.get(1).getCash());
				pp2.more.setText("地产数:"+players.get(1).getHouseNum());
			
		}
	}


		
	//展示完成按钮
	public void showFinished() {
		isForwarding = false;
		diceTimes = 0;
		jp.add(finished);
		jp.remove(dice1);
		jp.remove(dice2);
        jp.repaint();
	}

	
	public void showOwner(int location) {
		// TODO Auto-generated method stub
		System.out.println("调用了showOwner函数！");
		Land la = (Land)squares.get(location);
		int ownerNum = players.indexOf(la.getOwner());
		switch(ownerNum) {
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


	//完成按钮监听器
	class FinishListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	         if( e.getSource() == finished) {
	        	System.out.println(playerNum + "点击了完成按钮！");
	        	playerNum++;
	        	if(playerNum >= pNum)	playerNum = 0;
	        	System.out.println("现在是" + playerNum + "操作时间！");
	        	playing.setText(players.get(playerNum).getName());
	            jp.remove(finished);
	            dice1.setIcon(dice_6);
	            dice2.setIcon(dice_6);
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
	
	//MyListeners

	// 交易事件监听器
	class TradeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked TradeListenerS!");
			switch (e.getActionCommand()) {
			case "tButton01":
				new OpEvent(new Land(), players.get(1)).setVisible(true);
				System.out.println("Try to trade Square 01 !");
				if(true) {
					
				}
				break;
			case "tButton02":
				System.out.println("Try to trade Square 02 !");
				break;
			case "tButton03":
				System.out.println("Try to trade Square 03 !");
				break;
			case "tButton04":
				System.out.println("Try to trade Square 04 !");
				break;
			case "tButton05":
				System.out.println("Try to trade Square 05 !");
				break;
			case "tButton06":
				System.out.println("Try to trade Square 06 !");
				break;
			case "tButton07":
				System.out.println("Try to trade Square 07 !");
				break;
			case "tButton08":
				System.out.println("Try to trade Square 08 !");
				break;
			case "tButton09":
				System.out.println("Try to trade Square 09 !");
				break;
			case "tButton11":
				System.out.println("Try to trade Square 11 !");
				break;
			case "tButton12":
				System.out.println("Try to trade Square 12 !");
				break;
			case "tButton13":
				System.out.println("Try to trade Square 13 !");
				break;
			case "tButton14":
				System.out.println("Try to trade Square 14 !");
				break;
			case "tButton15":
				System.out.println("Try to trade Square 15 !");
				break;
			case "tButton16":
				System.out.println("Try to trade Square 16 !");
				break;
			case "tButton17":
				System.out.println("Try to trade Square 17 !");
				break;
			case "tButton18":
				System.out.println("Try to trade Square 18 !");
				break;
			case "tButton19":
				System.out.println("Try to trade Square 19 !");
				break;
			case "tButton21":
				System.out.println("Try to trade Square 21 !");
				break;
			case "tButton22":
				System.out.println("Try to trade Square 22 !");
				break;
			case "tButton23":
				System.out.println("Try to trade Square 23 !");
				break;
			case "tButton24":
				System.out.println("Try to trade Square 24 !");
				break;
			case "tButton25":
				System.out.println("Try to trade Square 25 !");
				break;
			case "tButton26":
				System.out.println("Try to trade Square 26 !");
				break;
			case "tButton27":
				System.out.println("Try to trade Square 27 !");
				break;
			case "tButton28":
				System.out.println("Try to trade Square 28 !");
				break;
			case "tButton29":
				System.out.println("Try to trade Square 29 !");
				break;
			case "tButton31":
				System.out.println("Try to trade Square 31 !");
				break;
			case "tButton32":
				System.out.println("Try to trade Square 32 !");
				break;
			case "tButton33":
				System.out.println("Try to trade Square 33 !");
				break;
			case "tButton34":
				System.out.println("Try to trade Square 34 !");
				break;
			case "tButton35":
				System.out.println("Try to trade Square 35 !");
				break;
			case "tButton36":
				System.out.println("Try to trade Square 36 !");
				break;
			case "tButton37":
				System.out.println("Try to trade Square 37 !");
				break;
			case "tButton38":
				System.out.println("Try to trade Square 38 !");
				break;
			case "tButton39":
				System.out.println("Try to trade Square 39 !");
				break;
			}
		}
	}

	// 建房事件监听器
	class BuildListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked BuildListenerS!");
			switch (e.getActionCommand()) {
			case "bButton01":
				System.out.println("Try to build Square 01 !");
				break;
			case "bButton02":
				System.out.println("Try to build Square 02 !");
				break;
			case "bButton03":
				System.out.println("Try to build Square 03 !");
				break;
			case "bButton04":
				System.out.println("Try to build Square 04 !");
				break;
			case "bButton05":
				System.out.println("Try to build Square 05 !");
				break;
			case "bButton06":
				System.out.println("Try to build Square 06 !");
				break;
			case "bButton07":
				System.out.println("Try to build Square 07 !");
				break;
			case "bButton08":
				System.out.println("Try to build Square 08 !");
				break;
			case "bButton09":
				System.out.println("Try to build Square 09 !");
				break;
			case "bButton11":
				System.out.println("Try to build Square 11 !");
				break;
			case "bButton12":
				System.out.println("Try to build Square 12 !");
				break;
			case "bButton13":
				System.out.println("Try to build Square 13 !");
				break;
			case "bButton14":
				System.out.println("Try to build Square 14 !");
				break;
			case "bButton15":
				System.out.println("Try to build Square 15 !");
				break;
			case "bButton16":
				System.out.println("Try to build Square 16 !");
				break;
			case "bButton17":
				System.out.println("Try to build Square 17 !");
				break;
			case "bButton18":
				System.out.println("Try to build Square 18 !");
				break;
			case "bButton19":
				System.out.println("Try to build Square 19 !");
				break;
			case "bButton21":
				System.out.println("Try to build Square 21 !");
				break;
			case "bButton22":
				System.out.println("Try to build Square 22 !");
				break;
			case "bButton23":
				System.out.println("Try to build Square 23 !");
				break;
			case "bButton24":
				System.out.println("Try to build Square 24 !");
				break;
			case "bButton25":
				System.out.println("Try to build Square 25 !");
				break;
			case "bButton26":
				System.out.println("Try to build Square 26 !");
				break;
			case "bButton27":
				System.out.println("Try to build Square 27 !");
				break;
			case "bButton28":
				System.out.println("Try to build Square 28 !");
				break;
			case "bButton29":
				System.out.println("Try to build Square 29 !");
				break;
			case "bButton31":
				System.out.println("Try to build Square 31 !");
				break;
			case "bButton32":
				System.out.println("Try to build Square 32 !");
				break;
			case "bButton33":
				System.out.println("Try to build Square 33 !");
				break;
			case "bButton34":
				System.out.println("Try to build Square 34 !");
				break;
			case "bButton35":
				System.out.println("Try to build Square 35 !");
				break;
			case "bButton36":
				System.out.println("Try to build Square 36 !");
				break;
			case "bButton37":
				System.out.println("Try to build Square 37 !");
				break;
			case "bButton38":
				System.out.println("Try to build Square 38 !");
				break;
			case "bButton39":
				System.out.println("Try to build Square 39 !");
				break;
			}
		}
	}

	// 卖房事件监听器
	class SellListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked SellListenerS!");
			switch (e.getActionCommand()) {
			case "sButton01":
				System.out.println("Try to sell house on Square 01 !");
				break;
			case "sButton02":
				System.out.println("Try to sell house on Square 02 !");
				break;
			case "sButton03":
				System.out.println("Try to sell house on Square 03 !");
				break;
			case "sButton04":
				System.out.println("Try to sell house on Square 04 !");
				break;
			case "sButton05":
				System.out.println("Try to sell house on Square 05 !");
				break;
			case "sButton06":
				System.out.println("Try to sell house on Square 06 !");
				break;
			case "sButton07":
				System.out.println("Try to sell house on Square 07 !");
				break;
			case "sButton08":
				System.out.println("Try to sell house on Square 08 !");
				break;
			case "sButton09":
				System.out.println("Try to sell house on Square 09 !");
				break;
			case "sButton11":
				System.out.println("Try to sell house on Square 11 !");
				break;
			case "sButton12":
				System.out.println("Try to sell house on Square 12 !");
				break;
			case "sButton13":
				System.out.println("Try to sell house on Square 13 !");
				break;
			case "sButton14":
				System.out.println("Try to sell house on Square 14 !");
				break;
			case "sButton15":
				System.out.println("Try to sell house on Square 15 !");
				break;
			case "sButton16":
				System.out.println("Try to sell house on Square 16 !");
				break;
			case "sButton17":
				System.out.println("Try to sell house on Square 17 !");
				break;
			case "sButton18":
				System.out.println("Try to sell house on Square 18 !");
				break;
			case "sButton19":
				System.out.println("Try to sell house on Square 19 !");
				break;
			case "sButton21":
				System.out.println("Try to sell house on Square 21 !");
				break;
			case "sButton22":
				System.out.println("Try to sell house on Square 22 !");
				break;
			case "sButton23":
				System.out.println("Try to sell house on Square 23 !");
				break;
			case "sButton24":
				System.out.println("Try to sell house on Square 24 !");
				break;
			case "sButton25":
				System.out.println("Try to sell house on Square 25 !");
				break;
			case "sButton26":
				System.out.println("Try to sell house on Square 26 !");
				break;
			case "sButton27":
				System.out.println("Try to sell house on Square 27 !");
				break;
			case "sButton28":
				System.out.println("Try to sell house on Square 28 !");
				break;
			case "sButton29":
				System.out.println("Try to sell house on Square 29 !");
				break;
			case "sButton31":
				System.out.println("Try to sell house on Square 31 !");
				break;
			case "sButton32":
				System.out.println("Try to sell house on Square 32 !");
				break;
			case "sButton33":
				System.out.println("Try to sell house on Square 33 !");
				break;
			case "sButton34":
				System.out.println("Try to sell house on Square 34 !");
				break;
			case "sButton35":
				System.out.println("Try to sell house on Square 35 !");
				break;
			case "sButton36":
				System.out.println("Try to sell house on Square 36 !");
				break;
			case "sButton37":
				System.out.println("Try to sell house on Square 37 !");
				break;
			case "sButton38":
				System.out.println("Try to sell house on Square 38 !");
				break;
			case "sButton39":
				System.out.println("Try to sell house on Square 39 !");
				break;
			}
		}
	}

	// 抵押事件监听器
	class MortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked MortListenerS!");
			switch (e.getActionCommand()) {
			case "mButton01":
				System.out.println("Try to mortgage Square 01 !");
				break;
			case "mButton02":
				System.out.println("Try to mortgage Square 02 !");
				break;
			case "mButton03":
				System.out.println("Try to mortgage Square 03 !");
				break;
			case "mButton04":
				System.out.println("Try to mortgage Square 04 !");
				break;
			case "mButton05":
				System.out.println("Try to mortgage Square 05 !");
				break;
			case "mButton06":
				System.out.println("Try to mortgage Square 06 !");
				break;
			case "mButton07":
				System.out.println("Try to mortgage Square 07 !");
				break;
			case "mButton08":
				System.out.println("Try to mortgage Square 08 !");
				break;
			case "mButton09":
				System.out.println("Try to mortgage Square 09 !");
				break;
			case "mButton11":
				System.out.println("Try to mortgage Square 11 !");
				break;
			case "mButton12":
				System.out.println("Try to mortgage Square 12 !");
				break;
			case "mButton13":
				System.out.println("Try to mortgage Square 13 !");
				break;
			case "mButton14":
				System.out.println("Try to mortgage Square 14 !");
				break;
			case "mButton15":
				System.out.println("Try to mortgage Square 15 !");
				break;
			case "mButton16":
				System.out.println("Try to mortgage Square 16 !");
				break;
			case "mButton17":
				System.out.println("Try to mortgage Square 17 !");
				break;
			case "mButton18":
				System.out.println("Try to mortgage Square 18 !");
				break;
			case "mButton19":
				System.out.println("Try to mortgage Square 19 !");
				break;
			case "mButton21":
				System.out.println("Try to mortgage Square 21 !");
				break;
			case "mButton22":
				System.out.println("Try to mortgage Square 22 !");
				break;
			case "mButton23":
				System.out.println("Try to mortgage Square 23 !");
				break;
			case "mButton24":
				System.out.println("Try to mortgage Square 24 !");
				break;
			case "mButton25":
				System.out.println("Try to mortgage Square 25 !");
				break;
			case "mButton26":
				System.out.println("Try to mortgage Square 26 !");
				break;
			case "mButton27":
				System.out.println("Try to mortgage Square 27 !");
				break;
			case "mButton28":
				System.out.println("Try to mortgage Square 28 !");
				break;
			case "mButton29":
				System.out.println("Try to mortgage Square 29 !");
				break;
			case "mButton31":
				System.out.println("Try to mortgage Square 31 !");
				break;
			case "mButton32":
				System.out.println("Try to mortgage Square 32 !");
				break;
			case "mButton33":
				System.out.println("Try to mortgage Square 33 !");
				break;
			case "mButton34":
				System.out.println("Try to mortgage Square 34 !");
				break;
			case "mButton35":
				System.out.println("Try to mortgage Square 35 !");
				break;
			case "mButton36":
				System.out.println("Try to mortgage Square 36 !");
				break;
			case "mButton37":
				System.out.println("Try to mortgage Square 37 !");
				break;
			case "mButton38":
				System.out.println("Try to mortgage Square 38 !");
				break;
			case "mButton39":
				System.out.println("Try to mortgage Square 39 !");
				break;
			}
		}
	}

	// 赎回事件监听器
	class RedeemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clicked RedeemListenerS!");
			switch (e.getActionCommand()) {
			case "rButton01":
				System.out.println("Try to redeem Square 01 !");
				break;
			case "rButton02":
				System.out.println("Try to redeem Square 02 !");
				break;
			case "rButton03":
				System.out.println("Try to redeem Square 03 !");
				break;
			case "rButton04":
				System.out.println("Try to redeem Square 04 !");
				break;
			case "rButton05":
				System.out.println("Try to redeem Square 05 !");
				break;
			case "rButton06":
				System.out.println("Try to redeem Square 06 !");
				break;
			case "rButton07":
				System.out.println("Try to redeem Square 07 !");
				break;
			case "rButton08":
				System.out.println("Try to redeem Square 08 !");
				break;
			case "rButton09":
				System.out.println("Try to redeem Square 09 !");
				break;
			case "rButton11":
				System.out.println("Try to redeem Square 11 !");
				break;
			case "rButton12":
				System.out.println("Try to redeem Square 12 !");
				break;
			case "rButton13":
				System.out.println("Try to redeem Square 13 !");
				break;
			case "rButton14":
				System.out.println("Try to redeem Square 14 !");
				break;
			case "rButton15":
				System.out.println("Try to redeem Square 15 !");
				break;
			case "rButton16":
				System.out.println("Try to redeem Square 16 !");
				break;
			case "rButton17":
				System.out.println("Try to redeem Square 17 !");
				break;
			case "rButton18":
				System.out.println("Try to redeem Square 18 !");
				break;
			case "rButton19":
				System.out.println("Try to redeem Square 19 !");
				break;
			case "rButton21":
				System.out.println("Try to redeem Square 21 !");
				break;
			case "rButton22":
				System.out.println("Try to redeem Square 22 !");
				break;
			case "rButton23":
				System.out.println("Try to redeem Square 23 !");
				break;
			case "rButton24":
				System.out.println("Try to redeem Square 24 !");
				break;
			case "rButton25":
				System.out.println("Try to redeem Square 25 !");
				break;
			case "rButton26":
				System.out.println("Try to redeem Square 26 !");
				break;
			case "rButton27":
				System.out.println("Try to redeem Square 27 !");
				break;
			case "rButton28":
				System.out.println("Try to redeem Square 28 !");
				break;
			case "rButton29":
				System.out.println("Try to redeem Square 29 !");
				break;
			case "rButton31":
				System.out.println("Try to redeem Square 31 !");
				break;
			case "rButton32":
				System.out.println("Try to redeem Square 32 !");
				break;
			case "rButton33":
				System.out.println("Try to redeem Square 33 !");
				break;
			case "rButton34":
				System.out.println("Try to redeem Square 34 !");
				break;
			case "rButton35":
				System.out.println("Try to redeem Square 35 !");
				break;
			case "rButton36":
				System.out.println("Try to redeem Square 36 !");
				break;
			case "rButton37":
				System.out.println("Try to redeem Square 37 !");
				break;
			case "rButton38":
				System.out.println("Try to redeem Square 38 !");
				break;
			case "rButton39":
				System.out.println("Try to redeem Square 39 !");
				break;
			}
		}
	}



}

