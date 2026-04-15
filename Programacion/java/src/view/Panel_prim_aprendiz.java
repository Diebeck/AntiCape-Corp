package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import control.Listener;

public class Panel_prim_aprendiz extends JPanel {
	
	public Panel_prim_aprendiz(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		JLabel lbl_gestionX = new JLabel("Proximas Citas");
		lbl_gestionX.setBackground(new Color(76, 17, 48));
		lbl_gestionX.setForeground(new Color(116, 27, 71));
		lbl_gestionX.setFont(new Font("Century Schoolbook", Font.PLAIN, 28));
		lbl_gestionX.setBounds(40, 21, 302, 52);
		add(lbl_gestionX);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 101, 632, 374);
		add(scrollPane);
		
		JTable table;
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
