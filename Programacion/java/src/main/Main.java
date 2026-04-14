package main;

import control.Listener;
import view.Ventana;

public class Main {

	public static void main(String[] args) {
		Listener list = new Listener();
		Ventana vent = new Ventana(list);
		list.setVentana(vent);
		
		vent.setVisible(true);
	}

}
