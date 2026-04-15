package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_logo extends JPanel {
	
//	ImageIcon ii;

	public Panel_logo(Listener list) {
		
//		ii = new ImageIcon("src/img/logo.png");
		
		
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setSize(362, 410);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(Ventana.escalarImagen("/img/logo.png", 300, 300));
		lblNewLabel.setBounds(0, 0, 362, 310);
		add(lblNewLabel);
		
		
		

	}
	
//	protected void paintComponent(Graphics g) {
//		
//		setBackground(new Color(240, 240, 240));
//		g.drawImage(ii.getImage(), 40, 0, 300, 300, null);
//		
//		
//		this.setOpaque(false);
//		
//		super.paintChildren(g);
//	}
}
