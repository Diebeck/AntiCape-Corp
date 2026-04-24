/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

import java.sql.*;

/**
 * Clase creada para implementar metodos de busqueda de ids en la base de datos
 */
public class ObtencionID {

	private Connection instance = null;

	public ObtencionID(Connection instance) {
		this.instance = instance;
	}

	/**
	 * Metodo que obtiene el id de un cliente en base a su nombre
	 * 
	 * @param nombre nombre del cliente
	 * @return int con el valor del id_del cliente (-1 en caso de que no exista el cliente)
	 */
	public int obtenerIdCliente(String nombre) {
		String query = "SELECT id_cliente FROM Cliente WHERE nombre = ?";
		try (PreparedStatement pstmt = instance.prepareStatement(query)) {
			pstmt.setString(1, nombre);
			ResultSet resultado = pstmt.executeQuery();
			if (resultado.next()) {
				return resultado.getInt("id_cliente");
			}
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que obtiene el id de un empleado en base a su nombre
	 * 
	 * @param nombre nombre del empleado
	 * @return int con el valor del id del empleado (-1 en caso de que no exista el empleado)
	 */
	public int obtenerIdEmpleado(String nombreEmpleado) {
		String query = "SELECT id_empleado FROM Empleado WHERE nombre = ?";
		try (PreparedStatement pstmt = instance.prepareStatement(query)) {
			pstmt.setString(1, nombreEmpleado);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_empleado");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Metodo que obtiene el id de un Taller en base a su nombre
	 * 
	 * @param nombre nombre del taller
	 * @return int con el valor del id del taller (-1 en caso de que no exista el taller)
	 */
	protected int obtenerIdTaller(String nombreTaller) {
		String query = "SELECT id_taller FROM Taller WHERE nombre_sala = ?";
		try (PreparedStatement pstmt = instance.prepareStatement(query)) {
			pstmt.setString(1, nombreTaller);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_taller");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que obtiene el id de un traje en base a su nombre y a su cliente asociado
	 * 
	 * @param nombreCliente nombre del cliente del traje 
	 * @param nombreTraje nombre del traje
	 * @return int con el valor del traje encontrado (-1 si no se encuentra)
	 */
	protected int obtenerIdTraje(String nombreCliente, String nombreTraje) {
		String query = "SELECT t.id_traje FROM Traje t " + "JOIN Cliente c ON t.id_cliente = c.id_cliente "
				+ "WHERE c.nombre = ? AND t.nombre = ?";
		try (PreparedStatement pstmt = instance.prepareStatement(query)) {
			pstmt.setString(1, nombreCliente);
			pstmt.setString(2, nombreTraje);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_traje");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 	Metodo para obtener el nombre de un cliente
	 * en base a su id
	 * 
	 * @param idCliente int del id del cliente a bucar
	 * @return String con el nombre asociado al id
	 */
	public String obtenerNombreCliente(int idCliente) {
	    String query = "SELECT nombre FROM Cliente WHERE id_cliente = ?";
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
	    //print de error
	    return "Desconocido";
	}
	
	/**
	 * Metodo para obtener el nombre de un empleado en base a su id
	 * 
	 * @param idEmpleado int del id del empleado
	 * 
	 * @return String con el nombre relacionado al id
	 */
	public String obtenerNombreEmpleado(int idEmpleado) {
	    String query = "SELECT nombre FROM Empleado WHERE id_empleado = ?";
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, idEmpleado);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("nombre");
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "Desconocido";
	}

	/**
	 * Metodo para obtener el nombre de un taller en base a su id
	 * 
	 * @param idTaller int del id de un taller
	 * @return String con el nombre asociado al id 
	 */
	public String obtenerNombreTaller(int idTaller) {
	    String query = "SELECT nombre_sala FROM Taller WHERE id_taller = ?";
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, idTaller);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("nombre_sala");
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "Desconocido";
	}
	
	/**
	 * Metodo para obtener el nombre de un traje a partir de su id
	 * 
	 * @param idTraje int del id del traje 
	 * @return string del nombre asociado al id
	 */
	public String obtenerNombreTraje(int idTraje) {
	    String query = "SELECT nombre FROM Traje WHERE id_traje = ?";
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, idTraje);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("nombre");
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "Desconocido";
	}
	
	/**
	 * Metodo para obtener el id de un traje por su nombre y sala
	 * 
	 * @param nombre nombre del taller
	 * @param sala tipo de sala
	 * @return
	 */
	public int obtenerIdTaller(String nombre, String sala) {
		String query = "SELECT id_taller FROM Taller WHERE nombre_sala = ? and tipo_sala = ?";
		try (PreparedStatement stmt = instance.prepareStatement(query)){
			stmt.setString(1, nombre);
			stmt.setString(2, sala);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id_taller");
			}
			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo para obtener el id de una cita en base a su encargado, fecha y hora
	 * 
	 * @param encargado encargado de la cita 
	 * @param fecha fecha de la cita 
	 * @param hora hora de la cita 
	 * @return
	 */
	public int obtenerIdCita (String encargado, String fecha, String hora) {
		//Buscado del id del encargado en base a su nombre 
		int idEncargado = obtenerIdEmpleado(encargado);
		String query = "SELECT id_cita FROM Citas WHERE id_encargado = ? and fecha = ? and hora = ?";
		try(PreparedStatement stmt = instance.prepareStatement(query)){
			stmt.setInt(1, idEncargado);
			stmt.setString(2, fecha);
			stmt.setString(3, hora);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id_cita");
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
