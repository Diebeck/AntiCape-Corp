/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import control.Listener;

/**
 * Clase que modela la unica ventana del aprendiz
 */
@SuppressWarnings("serial")
public class Panel_prim_aprendiz extends JPanel {
	
	private JLabel lbl_gestionX;
	private JScrollPane scrollPane;
	private JTable table;
	
	public Panel_prim_aprendiz(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_gestionX = new JLabel("Proximas Citas");
		lbl_gestionX.setBackground(new Color(76, 17, 48));
		lbl_gestionX.setForeground(new Color(116, 27, 71));
		lbl_gestionX.setFont(new Font("Century Schoolbook", Font.PLAIN, 28));
		lbl_gestionX.setBounds(40, 21, 302, 52);
		add(lbl_gestionX);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 101, 632, 374);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JLabel getLbl_gestionX() {
		return lbl_gestionX;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTable() {
		return table;
	}
	
	
}
