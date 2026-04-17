/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;
import view.Panel_citas;
import view.Panel_clientes;
import view.Panel_empleados;
import view.Panel_talleres;

/**
 * Clase dedicada a el control de las creaciones de elementos
 */
public class Control_creacion {

	private Acceso_BD modelo;
	private Panel_clientes panel_cliente;
	private Panel_empleados panel_empleados;
	private Panel_citas panel_cita;
	private Panel_talleres panel_taller;

	public Control_creacion(Acceso_BD modelo, Panel_clientes clientes, Panel_empleados empleados,
			Panel_citas citas, Panel_talleres talleres) {
		
		this.modelo = modelo;
		this.panel_cliente = clientes;
		this.panel_cita = citas;
		this.panel_empleados = empleados;
		this.panel_taller = talleres;
	}
	
	
	/**
	 * Metodo que rellena el formulario de creacion de citas
	 * 
	 * @see Acceso_BD#mostradoCitas()
	 * @see Acceso_BD#mostrarClientes()
	 * @see Acceso_BD#mostrarEmpleados()
	 * @see Acceso_BD#mostrarTalleres()
	 * @see #cargarTrajesPorCliente()
	 */
	@SuppressWarnings("unchecked")
	public void formularioCitas() {
		//cargado de los arrays con los datos de la base de datos
	    ArrayList<Cliente> clientes = modelo.mostrarClientes();
	    ArrayList<Empleado> empleados = modelo.mostrarEmpleados();
	    ArrayList<Taller> talleres = modelo.mostrarTalleres();
	    
	    // Limpiado de los combos para evitar duplicados
	    panel_cita.getCbCliente().removeAllItems();
	    panel_cita.getCbEncargado().removeAllItems();
	    panel_cita.getCbTaller().removeAllItems();
	    panel_cita.getCbTrajes().removeAllItems();
	    
	    // Llenado combo de clientes
	    if (clientes != null) {
	        for (Cliente n : clientes) {
	            panel_cita.getCbCliente().addItem(n.getNombre());
	        }
	    }
	    
	    // Llenado combo de encargados por categoria
	    if (empleados != null) {
	        for (Empleado n : empleados) {
	            if (n.getCategoria().toLowerCase().equals("maestro") || 
	                n.getCategoria().toLowerCase().equals("oficial")) {
	                panel_cita.getCbEncargado().addItem(n.getNombre());
	            }
	        }
	    }
	    
	    // Llenado combo de talleres
	    if (talleres != null) {
	        for (Taller n : talleres) {
	            panel_cita.getCbTaller().addItem(n.getTipo_sala());
	        }
	    }
	    
	    // Cargar los trajes del primer cliente seleccionado
	    cargarTrajesPorCliente();
	    
	    // Añadir listener para cuando cambie el cliente seleccionado (sin lambda)
	    panel_cita.getCbCliente().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            cargarTrajesPorCliente();
	        }
	    });
	}

	
	/**
	 * Método auxiliar para cargar los trajes del cliente seleccionado
	 * en el comboBox
	 */
	@SuppressWarnings("unchecked")
	private void cargarTrajesPorCliente() {
	    String seleccion = (String) panel_cita.getCbCliente().getSelectedItem();
	    
	    if (seleccion != null) {
	    	//llenado del array de trajes 
	        ArrayList<Traje> trajes = modelo.mostrarTrajes(seleccion);
	        
	        // Limpiado combo de trajes
	        panel_cita.getCbTrajes().removeAllItems();
	        
	        // Llenado combo de trajes
	        if (trajes != null) {
	            for (Traje n : trajes) {
	                panel_cita.getCbTrajes().addItem(n.getNombre());
	            }
	            System.out.println("Trajes cargados para " + seleccion + ": " + trajes.size());
	        } else {
	            panel_cita.getCbTrajes().addItem("Sin trajes disponibles");
	            System.out.println("No hay trajes disponibles para: " + seleccion);
	        }
	    }
	}
	
	/**
	 * Metodo que crea una nueva cita con los datos del formulario
	 * 
	 * cuenta con diversos prints de debug para implementar
	 * despues las ventanas emergentes
	 */
	public void crearCita() {
	    System.out.println("=== Creando nueva cita ===");
	    
	    try {
	        // Obtencion valores de los combobox
	        String cliente = (String) panel_cita.getCbCliente().getSelectedItem();
	        String taller = (String) panel_cita.getCbTaller().getSelectedItem();
	        String traje = (String) panel_cita.getCbTrajes().getSelectedItem();
	        String encargado = (String) panel_cita.getCbEncargado().getSelectedItem();
	        
	        // Conversion de la fecha del spinner al formato de la base de datos
	        Date date = (Date) panel_cita.getSpFecha().getValue();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String fecha = sdf.format(date);
	        
	        // Obtener duracion de la cita
	        int duracion = (int) panel_cita.getSpDuracion().getValue();
	        
	        // Validar que no haya campos vacíos
	        if (cliente == null || taller == null || traje == null || encargado == null) {
	            System.out.println("ERROR: Campos vacíos en el formulario");
	            return;
	        }
	        
	        // Validar que no haya seleccionado "Sin trajes disponibles"
	        if (traje.equals("Sin trajes disponibles")) {
	            System.out.println("ERROR: El cliente no tiene trajes disponibles");
	            return;
	        }
	        
	        // Llamar al metodo crearCita del modelo
	        boolean exito = modelo.crearCita(cliente, taller, fecha, duracion, traje, encargado);
	        
	        if (exito) {
	            limpiarFormularioCitas();
	            System.out.println("EXITO: Cita creada correctamente");
	        } else {
	            System.out.println("ERROR: Fallo al crear la cita en la base de datos");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("ERROR: Excepción al crear cita - " + e.getMessage());
	    }
	}
	
	/**
	 * Metodo para limpiar el formulario de citas después de crear una
	 */
	private void limpiarFormularioCitas() {
	    //limpiado de los comboBox
	    panel_cita.getCbCliente().setSelectedIndex(0);
	    panel_cita.getCbEncargado().setSelectedIndex(0);
	    panel_cita.getCbTaller().setSelectedIndex(0);
	    
	    //limpiado de los spinner
	    panel_cita.getSpFecha().setValue(new Date()); 
	    panel_cita.getSpDuracion().setValue(1); 
	    
	    // Recargar los trajes del primer cliente
	    cargarTrajesPorCliente();
	}
}
