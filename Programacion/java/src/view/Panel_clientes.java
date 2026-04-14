package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.ImageIcon;

public class Panel_clientes extends JPanel {
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	JLabel lbl_yClientes = new JLabel(modo +" un cliente");
	
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yClientes.setText(modo +" un cliente");
		System.out.println("Cambiado el modo del panel clientes a "+ modo);
	}
	
	public String getModo() {
		return modo;
	}
	
	public Panel_clientes(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yClientes.setForeground(new Color(116, 27, 71));
		lbl_yClientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yClientes.setBackground(new Color(76, 17, 48));
		lbl_yClientes.setBounds(38, 25, 437, 52);
		add(lbl_yClientes);
		
		JButton btn_homeClientes = new JButton("Home");
		btn_homeClientes.setIcon(new ImageIcon(Panel_clientes.class.getResource("/img/home.png")));
		btn_homeClientes.setForeground(Color.WHITE);
		btn_homeClientes.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeClientes.setBackground(new Color(76, 17, 48));
		btn_homeClientes.setBounds(38, 455, 221, 44);
		add(btn_homeClientes);
		btn_homeClientes.addActionListener(list);
		
		JButton btn_cCliente = new JButton("Confirmar");
		btn_cCliente.setForeground(Color.WHITE);
		btn_cCliente.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCliente.setBackground(new Color(76, 17, 48));
		btn_cCliente.setBounds(454, 455, 221, 44);
		add(btn_cCliente);
		btn_cCliente.addActionListener(list);
	}
}
