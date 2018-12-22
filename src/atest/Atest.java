package atest;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class Atest {
	public static void main(String[] args) {
		int option = JOptionPane.showConfirmDialog(null, "买地伐？");
		if(option == 1) {
			
				JOptionPane.showMessageDialog(null, "对不起，现金不足！");
			
			
		}
	}
}
