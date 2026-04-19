package main;

import control.Listener;
import model.Acceso_BD;
import view.Ventana;

public class Main {

	public static void main(String[] args) {
		Listener list = new Listener();
		Ventana vent = new Ventana(list);
		list.setVentana(vent);
		vent.setVisible(true);
		
		
		/*
		 *Esto lo tuve que buscar y masomenos lo entendi
		 *
		 *Resulta que no se estaban cerrando las Connection
		 *de la base de datos, al principio no habia problema.
		 *
		 *Despues de un tiempo de pruebas y codigo, se me lleno 
		 *el MySQL con +2OO CONEXIONES y no me dejaba ejecutar la aplicacion 
		 *
		 *comando en MySQL:
		 *SHOW FULL PROCESSLIST;
		 *
		 *tuve que reiniciar los servicios con estos comandos en cmd:
		 *net stop MySQL96
		 *net start MySQL96
		 *
		 *paralelo a esto tuve que hacer una refactorizacion de
		 *todo el modelo y las instancias de la clase Acceso_BD
		 *para que no se generen mas conecciones
		 *
		 *
		 *Este fracmento de codigo basicamente es
		 *Runtime.getRuntime: accedemos a la ejecucion acual de la aplicacion
		 *.addShutdownHook: haciendo referencia a un ansuelo es basicamente un hilo
		 *					que se ejecutara cuando se cierre la aplicacion
		 *
		 *new Thread: Es el hilo en si, el codigo que se ejecutara
		 *
		 *Y pues el codigo adentro cierra la coneccion y da el mensaje en pantalla
		 */
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Cerrando base de datos");
			Acceso_BD.instancia().closeConnect();
		}));
	}
}
