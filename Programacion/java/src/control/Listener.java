package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Acceso_BD;
import model.Cita;
import model.Empleado;
import model.Cliente;
import view.*;

public class Listener implements ActionListener {
	

	private Acceso_BD modelo = new Acceso_BD();
	private Empleado sesion;
	
	private Ventana vent;
	
	Panel_citas panel_citas = new Panel_citas(this);
	Panel_clientes panel_clientes = new Panel_clientes(this);
	Panel_cuenta panel_cuenta = new Panel_cuenta(this);
	Panel_empleados panel_empleados = new Panel_empleados(this);
	Panel_home panel_home = new Panel_home(this);
	Panel_login panel_login = new Panel_login(this);
	Panel_talleres panel_talleres = new Panel_talleres(this);
	Panel_x panel_x = new Panel_x(this);
	Panel_nav_maestro panel_nav_maestro = new Panel_nav_maestro(this); // Nav para maestro
	Panel_logo panel_logo = new Panel_logo(this); // Nav logo
	Panel_nav_aprendiz panel_nav_aprendiz = new Panel_nav_aprendiz(this);
	Panel_nav_oficial panel_nav_oficial = new Panel_nav_oficial(this);
	Panel_prim_aprendiz panel_prim_aprendiz = new Panel_prim_aprendiz(this);
	
	/** Asigna la referencia a la ventana principal
	 * @param vent
	 */
	public void setVentana(Ventana vent) {
		this.vent = vent;
		vent.cambiarCajaPrimario(panel_login);
		vent.cambiarCajaNav(panel_logo);
	}
	
	private void iniciarMaestro() {
		vent.cambiarCajaPrimario(panel_home);
		vent.cambiarCajaNav(panel_nav_maestro);
	}
	private void iniciarOficial() {
		vent.cambiarCajaPrimario(panel_x);
		vent.cambiarCajaNav(panel_nav_oficial);
	}
	private void iniciarAprendiz() {
		vent.cambiarCajaPrimario(panel_prim_aprendiz);
		vent.cambiarCajaNav(panel_nav_aprendiz);
		panel_cuenta.esconderHome();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		System.out.println("===\nBoton presionado: "+ cmd);
		
		if (cmd.equals("Login")) {
			sesion = modelo.login(panel_login.getTextField_usuario().getText(), panel_login.getPasswordField_contrasena());
			String tipoCuenta = sesion.getCategoria();
			vent.cambiarCajaCuenta(panel_cuenta);
			panel_cuenta.mostrarHome();
			if (tipoCuenta.equals("Maestro")) {
				iniciarMaestro();
			} else if (tipoCuenta.equals("Oficial")) {
				iniciarOficial();
			} else if (tipoCuenta.equals("Aprendiz")){
				iniciarAprendiz();
			}
			
		} else if (e.getSource()== panel_cuenta.getBtn_logout()) { //Para que funcione el botón Logout con la imagen
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
		} else if (cmd.equals("Citas")) {
		    ArrayList<Cita> citas = modelo.mostradoCitas();
		    vent.cambiarCajaPrimario(panel_x);
		    panel_x.setEstado("citas");
		    
		    // El controlador crea y configura el modelo
		    String[] columnas = {"ID", "Fecha", "Duración", "Cliente", "Encargado", "Taller", "Traje"};
		    DefaultTableModel nuevoModelo = new DefaultTableModel(columnas, 0);
		    
		    // Llenar el modelo con los datos
		    if (citas != null) {
		        for (Cita n : citas) {
		            Object[] fila = {
		                n.getId_cita(),
		                n.getFecha(),
		                n.getDuracion(),
		                n.getId_cliente(),
		                n.getId_encargado(),
		                n.getId_taller(),
		                n.getId_traje()
		            };
		            nuevoModelo.addRow(fila);
		        }
		        // Asignar el nuevo modelo a la tabla existente
			    panel_x.getTable().setModel(nuevoModelo);
			
		    }
			
		} else if (cmd.equals("Clientes")) {
			ArrayList <Cliente> clientes = modelo.mostrarClientes();
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("clientes");
			
			 // El controlador crea y configura el modelo
		    String[] columnas = {"ID", "Nombre", "Colores", "Superpoder"};
		    DefaultTableModel nuevoModelo = new DefaultTableModel(columnas, 0);
		    
		    if (clientes != null) {
		    	for (Cliente n : clientes) {
		    		Object [] fila = {
		    				n.getId_cliente(),
		    				n.getNombre(),
		    				n.getColores(),
		    				n.getSuperpoder()
		    		};
		    		nuevoModelo.addRow(fila);
		    	}
		    	panel_x.getTable().setModel(nuevoModelo);;
		    }
			
		} else if (cmd.equals("Empleados")) {
			 ArrayList<Empleado> empleados = modelo.mostrarEmpleados();
			    vent.cambiarCajaPrimario(panel_x);
			    panel_x.setEstado("empleados");
			    
			    // El controlador crea y configura el modelo
			    String[] columnas = {"ID", "Nombre", "Apellidos", "Apodo", "Categoria", "Contraseña"};
			    DefaultTableModel nuevoModelo = new DefaultTableModel(columnas, 0);
			    
			    // Llenar el modelo con los datos
			    if (empleados != null) {
			        for (Empleado n : empleados) {
			            Object[] fila = {
			                n.getId_empleado(),
			                n.getNombre(),
			                n.getApellidos(),
			                n.getApodo(),
			                n.getCategoria(),
			                n.getContraseña()
			            };
			            nuevoModelo.addRow(fila);
			        }
			        // Asignar el nuevo modelo a la tabla existente
				    panel_x.getTable().setModel(nuevoModelo);
				
			    }
			
		} else if (cmd.equals("Talleres")) {
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("talleres");
			
		} else if (cmd.equals("Crear") || cmd.equals("Modificar")) {
			if (panel_x.getEstado().equals("citas")) {
				vent.cambiarCajaPrimario(panel_citas);
			} else if (panel_x.getEstado().equals("clientes")) {
				vent.cambiarCajaPrimario(panel_clientes);
			} else if (panel_x.getEstado().equals("talleres")) {
				vent.cambiarCajaPrimario(panel_talleres);
			} else if (panel_x.getEstado().equals("empleados")) {
				vent.cambiarCajaPrimario(panel_empleados);
			}
			// Pone el modo a "Crear" o "Modificar"
			panel_citas.setModo(cmd);
			panel_clientes.setModo(cmd);
			panel_talleres.setModo(cmd);
			panel_empleados.setModo(cmd);

		} else if (e.getSource() == panel_cuenta.getBotonHome()) {
			vent.cambiarCajaPrimario(panel_home);
		
		} else if (e.getSource() == panel_talleres.getBtn_homeTaller()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_clientes.getBtn_homeClientes()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_empleados.getBtn_homeEmpleado()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_citas.getBtn_homeCitas()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (cmd.equals("Confirmar")) {
			vent.cambiarCajaPrimario(panel_x);
			
		}
	}

}
