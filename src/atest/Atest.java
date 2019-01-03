package atest;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Atest {
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(2,2));
		Object[] fruits = {"苹果 20点券","梨子 20点券","香蕉 20点券","西瓜 20点券"}; 
		int op = JOptionPane.showOptionDialog(f, "你需要购买什么卡片？", "欢迎来到卡片商店",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("icons/buttons/shop.png"), fruits, fruits[0]); 
		System.out.print((String)fruits[op]);
		
	}
}
