package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.JLabel;

public class Panel_nav_general extends JPanel {

	public Panel_nav_general(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		JLabel lblNewLabel = new JLabel("General");
		lblNewLabel.setBounds(140, 172, 44, 12);
		add(lblNewLabel);
	}
}
