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
 * Clase que contiene los metodos que realizan acciones a la tabla Empleados en
 * la base de datos
 */
public class ConsultasEmpleado {
	private Connection instance;

	public ConsultasEmpleado(Connection modelo) {
		this.instance = modelo;
	}

	/**
	 * Metodo que consulta a la base de datos la totalidad de empleados inscritos en
	 * el sistema
	 * 
	 * @return ArrayList de objetos de tipo empleado
	 */
	public ArrayList<Empleado> mostrarEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<>();
		String query = "SELECT * FROM Empleado";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Empleado empleado = new Empleado(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5), resultado.getString(6));
				empleados.add(empleado);
			}

			return empleados;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Metodo para añadir un nuevo empleado a la base de datos
	 * 
	 * @param nombre     String con el nombre del empleado
	 * @param apellidos  String con los apellidos del empleado
	 * @param apodo      String con el apodo que sera usado como usuario del
	 *                   empleado
	 * @param categoria  String con la categoria del empleado (Maestro, Oficial,
	 *                   Aprendiz)
	 * @param contraseña String con la contraseña de la cuenta del empleado
	 * 
	 * @return true en caso de creacion exitosa y false para cracion fallida
	 */
	public boolean crearEmpleado(String nombre, String apellidos, String apodo, String categoria, String contraseña) {
		String query = "INSERT INTO Empleado (nombre, apellidos, apodo, categoria, contraseña) VALUES (?,?,?,?,?)";

		try (PreparedStatement stmt = instance.prepareStatement(query)) {
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setString(3, apodo);
			stmt.setString(4, categoria);
			stmt.setString(5, contraseña);

			int filas = stmt.executeUpdate();

			if (filas > 0) {
				System.out.println("Añadido de empleado exitoso");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo para modificar un empleado existente
	 * 
	 * @param id         ID del empleado a modificar
	 * @param nombre     nuevo nombre
	 * @param apellidos  nuevos apellidos
	 * @param apodo      nuevo apodo
	 * @param categoria  nueva categoria
	 * @param contraseña nueva contraseña
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarEmpleado(int id, String nombre, String apellidos, String apodo, String categoria,
			String contraseña) {
		String query = "UPDATE Empleado SET nombre = ?, apellidos = ?, apodo = ?, categoria = ?, contraseña = ? WHERE id_empleado = ?";

		try (PreparedStatement stmt = instance.prepareStatement(query)) {

			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setString(3, apodo);
			stmt.setString(4, categoria);
			stmt.setString(5, contraseña);
			stmt.setInt(6, id);

			int filasAfectadas = stmt.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Empleado modificado exitosamente. ID: " + id);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Metodo para eliminar un empleado existente
	 * 
	 * @param id ID del empleado a eliminar
	 * @return true si se eliminó correctamente, false si no
	 */
	public boolean eliminarEmpleado(int id) {
	    String query = "DELETE FROM Empleado WHERE id_empleado = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Empleado eliminado exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al eliminar empleado: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}
