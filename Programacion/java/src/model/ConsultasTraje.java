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
 * acciones a la tabla Trajes en la base de datos 
 */
public class ConsultasTraje {
	private Connection instance;
	private ObtencionID ids;
	
	public ConsultasTraje(Connection modelo) {
		this.ids = new ObtencionID(modelo);
		this.instance = modelo;
	}
	
	/**
	 * Metodo que consulta a la base de datos la totalidad de trajes registrados en
	 * el sistema
	 * 
	 * @param nombre del usuario que tiene asociado los trajes
	 * @return ArrayList de objetos de tipo empleado
	 */
	public ArrayList<Traje> mostrarTrajes(String nombre) {
		ArrayList<Traje> trajes = new ArrayList<>();
		String query = "SELECT * FROM Traje WHERE id_cliente IN (SELECT id_cliente FROM Cliente WHERE nombre = " + "'"
				+ nombre + "')";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Traje traje = new Traje(resultado.getInt(1), resultado.getInt(2), resultado.getString(3),
						resultado.getString(4));
				trajes.add(traje);
			}

			return trajes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	

	/**
	 * Metodo que obtiene el nombre del traje de un cliente especifico
	 * 
	 * @param idCliente ID del cliente
	 * @return nombre del traje del cliente, null si no tiene
	 */
	public String obtenerNombreTrajePorCliente(int idCliente) {
	    String query = "SELECT nombre FROM Traje WHERE id_cliente = ? LIMIT 1";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getString("nombre");
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * Metodo para añadir un traje a la base de datos 
	 * 
	 * @param cliente nombre del cliente al que sera asociado el traje 
	 * 
	 * @param nombre nombre del nuevo traje 
	 * @param estado estado del nuevo traje 
	 * 
	 * @return true en caso de insercion exitosa, false para lo contrario
	 * @see ObtencionID#obtenerIdCliente(String)
	 */
	public boolean crearTraje(String cliente, String nombre, String estado) {
		String query = "INSERT INTO Traje (id_cliente, nombre, estado) VALUES (?,?,?)";
		int id_cliente;
		
		//llamado al metodo obtenerIdCliente para asociar el id en la misma creacion 
		id_cliente = ids.obtenerIdCliente(cliente);
		
		try(PreparedStatement stmt = instance.prepareStatement(query)){
		stmt.setInt(1, id_cliente);
		stmt.setString(2, nombre);
		stmt.setString(3, estado);
		
		int filas = stmt.executeUpdate();
		
		if (filas > 0) {
			System.out.println("Añadido de cliente exitoso");
			return true;
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo para modificar un traje existente
	 * 
	 * @param idCliente ID del cliente dueño del traje
	 * @param nombreTrajeActual nombre actual del traje
	 * @param nombreTrajeNuevo nuevo nombre del traje
	 * @param estadoTraje nuevo estado del traje
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarTraje(int idCliente, String nombreTrajeActual, String nombreTrajeNuevo, String estadoTraje) {
	    String query = "UPDATE Traje SET nombre = ?, estado = ? WHERE id_cliente = ? AND nombre = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        stmt.setString(1, nombreTrajeNuevo);
	        stmt.setString(2, estadoTraje);
	        stmt.setInt(3, idCliente);
	        stmt.setString(4, nombreTrajeActual);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Traje modificado exitosamente");
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}
