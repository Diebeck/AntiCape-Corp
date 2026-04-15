package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import control.Listener;
import javax.swing.ImageIcon;

public class Panel_logo extends JPanel {
	
	ImageIcon ii;

	public Panel_logo(Listener list) {
		
		ii = new ImageIcon("src/img/logo.png");
		
		
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setSize(362, 410);
		

	}
	
	protected void paintComponent(Graphics g) {
		
		setBackground(new Color(240, 240, 240));
		g.drawImage(ii.getImage(), 40, 0, 300, 300, null);
		
		
		setOpaque(false);
		
		super.paintChildren(g);
	}
}
