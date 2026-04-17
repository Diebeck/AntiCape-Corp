/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

import java.sql.*;
import java.util.*;

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
	 * @return true en caso de que la contraseña se halla encontrado, false si no se
	 *         encontro
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

	public ArrayList<Cita> mostradoCitas() {
		ArrayList<Cita> citas = new ArrayList<>();
		String query = "SELECT * FROM Citas";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cita cita = new Cita(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getInt(4), resultado.getInt(5), resultado.getInt(6), resultado.getInt(7));
				citas.add(cita);
			}

			return citas;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public ArrayList<Cliente> mostrarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String query = "SELECT * FROM Cliente";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cliente cliente = new Cliente(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4));
				clientes.add(cliente);
			}

			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

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

	public ArrayList<Taller> mostrarTalleres() {
		ArrayList<Taller> talleres = new ArrayList<>();
		String query = "SELECT * FROM Taller";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Taller taller = new Taller(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
				talleres.add(taller);
			}

			return talleres;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public ArrayList<Cita> CitasRecientes() {
		ArrayList<Cita> citasRecientes = new ArrayList<>();
		String query = "SELECT * FROM Citas ORDER BY fecha ASC";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Cita cita = new Cita(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getInt(4), resultado.getInt(5), resultado.getInt(6), resultado.getInt(7));
				citasRecientes.add(cita);
			}

			return citasRecientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	/**
	 * Metodo que obtiene el nombre del taller y su número de citas
	 * 
	 * @return ArrayList de arrays con [nombre_taller, numero_citas]
	 */
	public ArrayList<String[]> ocupacionTaller() {
	    ArrayList<String[]> citasPorTaller = new ArrayList<>();
	    String query = "SELECT t.tipo_sala, COUNT(c.id_cita) AS total_citas " +
	                   "FROM Taller t, Citas c " +
	                   "WHERE t.id_taller = c.id_taller " +
	                   "GROUP BY t.id_taller, t.nombre_sala " +
	                   "ORDER BY total_citas DESC";
	    
	    try (Statement stmt = instance.createStatement(); 
	         ResultSet rs = stmt.executeQuery(query)) {
	        
	        while (rs.next()) {
	            String[] tallerInfo = new String[2];
	            tallerInfo[0] = rs.getString("tipo_sala");
	            tallerInfo[1] = String.valueOf(rs.getInt("total_citas"));
	            citasPorTaller.add(tallerInfo);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return citasPorTaller;
	}
	
	
}
