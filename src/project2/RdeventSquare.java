package project2;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class RdeventSquare extends Square {
	public String event(List<Player> players,Player a)
	{
		int i=1+(int)(Math.random()*3);
		switch(i)
		{
		case 1:
			event1(players);
			return "发工资";
		case 2:
			event2(players);
			return "扣钱";
		case 3:
			event3(a);
			return "去监狱";
		default:
			
		}
		return "wrong";
	}
	//发工资
	public void event1(List<Player> players)
	{
		Iterator it=players.iterator();
		while(it.hasNext())
		{
			Player a=(Player)it.next();
			a.setCash(200);
		}
		JOptionPane.showMessageDialog(null,"每人发工资200");
	}
	//扣钱
	public void event2(List<Player> players)
	{
		Iterator it=players.iterator();
		while(it.hasNext())
		{
			Player a=(Player)it.next();
			a.setCash(-200);
		}
		JOptionPane.showMessageDialog(null,"每人扣工资200");
	}
	//gotojail
	public void event3(Player a)
	{
		JOptionPane.showMessageDialog(null,"啊哦，你因为超速被送进监狱啦~");
	}
}
