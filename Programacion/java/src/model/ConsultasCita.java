/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase que contiene los metodos que realizan 
 * acciones a la tabla citas en la base de datos 
 */
public class ConsultasCita {
	private Connection instance;
	private ObtencionID ids;
	
	public ConsultasCita(Connection modelo) {
		this.ids = new ObtencionID(modelo);
		this.instance = modelo;
	}
	
	/**
	 * Metodo publico que consulta a la base de datos todos los registros 
	 * de citas en el sistema 
	 * 
	 * @return ArrayList de objetos tipo cita 
	 */
	public ArrayList<Cita> mostradoCitas() {
		ArrayList<Cita> citas = new ArrayList<>();
		String query = "SELECT * FROM Citas";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cita cita = new Cita(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getInt(5), resultado.getInt(6), resultado.getInt(7), resultado.getInt(8));
				citas.add(cita);
			}

			return citas;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	/**
	 * Metodo que consulta a la base de datos la totalidad 
	 * de citas en base a su fecha (de la mas proxima a la mas lejana)
	 * 
	 * @return ArrayList de objetos de tipo cita 
	 */
	public ArrayList<Cita> CitasRecientes() {
		ArrayList<Cita> citasRecientes = new ArrayList<>();
		String query = "SELECT * FROM Citas ORDER BY fecha ASC";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cita cita = new Cita(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getInt(5), resultado.getInt(6), resultado.getInt(7), resultado.getInt(8));
				citasRecientes.add(cita);
			}

			return citasRecientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	
	/**
	 * Metodo de insercion de una nueva cita en la base de datos 
	 * 
	 * @param cliente Nombre del cliente de la cita 
	 * @param taller Nombre del taller donde se realizara la cita 
	 * @param fecha Fecha en la que se realizara la cita 
	 * @param duracion Duracion que tendra la cita ( en formato ejem: 1 H )
	 * @param traje Nombre del traje que sera trabajado 
	 * @param encargado Nombre del empleado encargado de la cita 
	 * 
	 * @return true si se creo la cita, false si no 
	 */
	public boolean crearCita(String cliente, String taller, String fecha, int duracion, String traje, String encargado) {
		//probando un poco el prepared statemend :)
	    String query = "INSERT INTO Citas (fecha, duracion, id_cliente, id_encargado, id_taller, id_traje) VALUES (?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        //Obtencion id del cliente por su nombre
	        int idCliente = ids.obtenerIdCliente(cliente);
	        if (idCliente == -1) {
	        	//prints de debug futuro
	            System.out.println("Cliente no encontrado: " + cliente);
	            return false;
	        }
	        
	        // btencion id del empleado por su nombre
	        int idEncargado = ids.obtenerIdEmpleado(encargado);
	        if (idEncargado == -1) {
	            System.out.println("Empleado no encontrado: " + encargado);
	            return false;
	        }
	        
	        // Obtencion id del taller por su nombre
	        int idTaller = ids.obtenerIdTaller(taller);
	        if (idTaller == -1) {
	            System.out.println("Taller no encontrado: " + taller);
	            return false;
	        }
	        
	        // Obtencion id del traje por nombre del cliente y nombre del traje
	        int idTraje = ids.obtenerIdTraje(cliente, traje);
	        if (idTraje == -1) {
	            System.out.println("Traje no encontrado para el cliente: " + cliente);
	            return false;
	        }
	        
	        //Cambio la duración a string, convertir el número del formulario a "X H"
	        String duracionFormateada = duracion + " H";
	        
	        //Insertar la cita
	        stmt.setString(1, fecha);
	        stmt.setString(2, duracionFormateada);
	        stmt.setInt(3, idCliente);
	        stmt.setInt(4, idEncargado);
	        stmt.setInt(5, idTaller);
	        stmt.setInt(6, idTraje);
	        
	        //metodo usado para cosas de DML en la base de datos 
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	        	//print de debug 
	            System.out.println("Cita creada exitosamente");
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	/**
	 * Metodo para modificar una cita existente
	 * 
	 * @param id ID de la cita a modificar
	 * @param cliente nombre del cliente
	 * @param taller nombre del taller
	 * @param fecha fecha de la cita
	 * @param duracion duracion en horas
	 * @param traje nombre del traje
	 * @param encargado nombre del encargado
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarCita(int id, String cliente, String taller, String fecha, int duracion, String traje, String encargado) {
	    String query = "UPDATE Citas SET fecha = ?, duracion = ?, id_cliente = ?, id_encargado = ?, id_taller = ?, id_traje = ? WHERE id_cita = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        int idCliente = ids.obtenerIdCliente(cliente);
	        int idEncargado = ids.obtenerIdEmpleado(encargado);
	        int idTaller = ids.obtenerIdTaller(taller);
	        int idTraje = ids.obtenerIdTraje(cliente, traje);
	        
	        if (idCliente == -1 || idEncargado == -1 || idTaller == -1 || idTraje == -1) {
	            System.out.println("ERROR: No se encontraron todos los datos necesarios");
	            return false;
	        }
	        
	        String duracionFormateada = duracion + " H";
	        
	        stmt.setString(1, fecha);
	        stmt.setString(2, duracionFormateada);
	        stmt.setInt(3, idCliente);
	        stmt.setInt(4, idEncargado);
	        stmt.setInt(5, idTaller);
	        stmt.setInt(6, idTraje);
	        stmt.setInt(7, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Cita modificada exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	/**
	 * Metodo para eliminar una cita existente
	 * 
	 * @param id ID de la cita a eliminar
	 * @return true si se eliminó correctamente, false si no
	 */
	public boolean eliminarCita(int id) {
	    String query = "DELETE FROM Citas WHERE id_cita = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Cita eliminada exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}
