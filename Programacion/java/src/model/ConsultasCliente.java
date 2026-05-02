/**
 * /**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

import java.sql.*;
import java.util.*;

/**
 * Clase que contiene los metodos que realizan acciones a la tabla clientes en
 * la base de datos
 */
public class ConsultasCliente {
	private Connection instance;
	private ConsultasTraje consultas_traje;

	public ConsultasCliente(Connection modelo) {
		this.instance = modelo;
		this.consultas_traje = new ConsultasTraje(modelo);
	}

	/**
	 * Metodo publico que consulta a la base de datos los registros existentes de
	 * clientes en el sistema
	 * 
	 * @return ArraList de objetos tipo cliente
	 */
	public ArrayList<Cliente> mostrarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String query = "SELECT * FROM Cliente";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cliente cliente = new Cliente(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5));
				clientes.add(cliente);
			}

			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Metodo para añadir un cliente a la base de datos
	 * 
	 * @param nombre  nombre del nuevo cliente
	 * @param colores colores del nuevo cliente
	 * @param poder   poderes del nuevo cliente
	 * 
	 * @return true si la creacion fue exitosa, false para el caso contrario
	 */
	public boolean crearCliente(String nombre, String colores, String poder, String alineacion) {
		String query = "INSERT INTO Cliente(nombre, colores, superpoder, alineacion) VALUES (?,?,?,?)";

		try (PreparedStatement stmt = instance.prepareStatement(query)) {
			stmt.setString(1, nombre);
			stmt.setString(2, colores);
			stmt.setString(3, poder);
			stmt.setString(4, alineacion);

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
	 * Metodo para modificar un cliente existente
	 * 
	 * @param id         ID del cliente a modificar
	 * @param nombre     nuevo nombre
	 * @param colores    nuevos colores
	 * @param superpoder nuevo superpoder
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarCliente(int id, String nombre, String colores, String superpoder, String alineacion) {
		String query = "UPDATE Cliente SET nombre = ?, colores = ?, superpoder = ?, alineacion = ? WHERE id_cliente = ?";

		try (PreparedStatement stmt = instance.prepareStatement(query)) {

			stmt.setString(1, nombre);
			stmt.setString(2, colores);
			stmt.setString(3, superpoder);
			stmt.setString(4, alineacion);
			stmt.setInt(5, id);

			int filasAfectadas = stmt.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Cliente modificado exitosamente. ID: " + id);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}


	/**
	 * Metodo para eliminar un cliente y su traje asociado
	 * 
	 * @param id ID del cliente a eliminar
	 * @return true si se eliminó correctamente, false si no
	 */
	public boolean eliminarCliente(int id) {
	    
	    String query = "DELETE FROM Cliente WHERE id_cliente = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Cliente eliminado exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al eliminar cliente: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	/**
	 * Metodo que devuelve la alineacion (Heroe / Villano) 
	 * de un cliente en la base de datos 
	 * 
	 * @param id identificador del cliente 
	 * @return String con su alineacion
	 */
	public String alineacionCliente(int id) {
	    
	    String query = "SELECT alineacion FROM Cliente WHERE id_cliente = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        
	        try (ResultSet resultado = stmt.executeQuery()) {  
	            if (resultado.next()) {
	                return resultado.getString("alineacion");  
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
