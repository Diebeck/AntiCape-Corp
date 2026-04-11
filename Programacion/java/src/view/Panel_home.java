package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

public class Panel_home extends JPanel {

	public Panel_home(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		JLabel lbl_home1 = new JLabel("Home");
		lbl_home1.setBounds(40, 33, 441, 52);
		lbl_home1.setForeground(new Color(116, 27, 71));
		lbl_home1.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		add(lbl_home1);
	}
}
