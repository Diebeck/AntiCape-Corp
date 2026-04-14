package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Panel_home extends JPanel {
	private JTable table;
	private JTable table_1;

	public Panel_home(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		JLabel lbl_home1 = new JLabel("Proximas Citas");
		lbl_home1.setBounds(108, 33, 373, 52);
		lbl_home1.setForeground(new Color(116, 27, 71));
		lbl_home1.setFont(new Font("Century Schoolbook", Font.ITALIC, 32));
		add(lbl_home1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 100, 625, 135);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lbl_talleres = new JLabel("Ocupación Talleres");
		lbl_talleres.setForeground(new Color(116, 27, 71));
		lbl_talleres.setFont(new Font("Century Schoolbook", Font.ITALIC, 28));
		lbl_talleres.setBounds(49, 275, 373, 52);
		add(lbl_talleres);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 354, 489, 128);
		add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}
}
