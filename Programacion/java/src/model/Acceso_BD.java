/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

import java.sql.*;

/**
 * Clase que moldea el acceso a base de datos y todos sus metodos de consultas
 */
public class Acceso_BD {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/AntiCape_db";
	private Connection instance = null;
	private String user_db = "root";
	private String password_db = "Rokokoso0812";

	/**
	 * Metodo que prende la conexion con la base de datos
	 * 
	 * @see #getConexion()
	 */
	public Acceso_BD() {
		getConexion();
	}

	/**
	 * Metodo que inicializa la conexion con la base de datos en base a los
	 * atributos de la clase Acceso_DB
	 * 
	 * @return objeto de tipo Connection
	 */
	public Connection getConexion() {
		try {
			Class.forName(driver);
			instance = DriverManager.getConnection(url, user_db, password_db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * Metodo que cierra una conexion con la base de datos
	 */
	public void closeConnect() {
		try {
			instance.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que consulta la existencia de un usuario en la base de datos
	 * 
	 * @param usuario usuario a buscar en la base de datos
	 * @return true en caso de que se encuentre un registro y false si no se
	 *         encuentra
	 */
	public boolean consultaUser(String usuario) {
		try {
			System.out.println("Llamado metodo consulta");
			String query = "SELECT * FROM empleado";
			Statement stmt = instance.createStatement();
			ResultSet resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				System.out.println("Busqueda: " + resultado.getString(4));

				if (resultado.getString(4).equals(usuario)) {
					return true;
				}
			}
			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo que realiza una consulta a la base de datos para comprobar la
	 * contraseña de un usuario
	 * 
	 * @param contraseña contraseña a buscar en la base de datos
	 * @return true en caso de que la contraseña se encuentre, false si no
	 */
	public boolean consultaContra(String contraseña, String usuario) {
		try {
			System.out.println("Llamado metodo consulta");
			String query = "SELECT * FROM empleado WHERE apodo = '" + usuario + "'";
			Statement stmt = instance.createStatement();
			ResultSet resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				System.out.println("Busqueda: " + resultado.getString(6));

				if (resultado.getString(6).equals(contraseña)) {
					return true;
				}
			}
			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Metodo publico que devuelve un objeto de tipo empleado en base a su
	 * coincidencia de usuario y contraseña
	 * 
	 * @param usuario    apodo del empleado en la base de datos
	 * @param contraseña contraseña del empleado en la base de datos
	 * 
	 * @return objeto de tipo empleado
	 * @see #consultaContra(String)
	 * @see Acceso_BD#consultaUser(String)
	 */
	public Empleado login(String usuario, String contraseña) {
		// print de debug
		System.out.println("metodo login");

		// variables de almacenamiento temporal
		int id = 0;
		String nombre = "";
		String apellidos = "";
		String categoria = "";

		if (consultaUser(usuario) && consultaContra(contraseña, usuario)) {
			try {
				String query = "SELECT * FROM Empleado WHERE apodo = '" + usuario + "' AND contraseña = '" + contraseña
						+ "'";
				Statement stmt = instance.createStatement();
				ResultSet resultado = stmt.executeQuery(query);

				if (resultado.next()) {
					id = resultado.getInt("id_empleado");
					nombre = resultado.getString("nombre");
					apellidos = resultado.getString("apellidos");
					categoria = resultado.getString("categoria");

					Empleado sesionActiva = new Empleado(id, nombre, apellidos, usuario, categoria, contraseña);

					resultado.close();
					stmt.close();
					return sesionActiva;
				}

				resultado.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
