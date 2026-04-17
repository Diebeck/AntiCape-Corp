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
	protected int obtenerIdCliente(String nombre) {
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
	protected int obtenerIdEmpleado(String nombreEmpleado) {
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
		String query = "SELECT id_taller FROM Taller WHERE tipo_sala = ?";
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
}
