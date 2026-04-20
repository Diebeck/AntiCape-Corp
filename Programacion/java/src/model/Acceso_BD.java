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
	private Connection connection = null;
	private String user_db = "Rokokoso0812";



	private String password_db = "Rokokoso0812";


	
	/*
	 * instancia de la coneccion pivada para compartir
	 * con los otros modelos
	 */
	private static Acceso_BD instancia = null;


	/**
	 * Constructor
	 * que prende la conexion con la base de datos
	 */
	private Acceso_BD() {
		getConexion();
	}
	
	/**
	 * Metodo que obtiene la instancia para conmpartir 
	 * @return
	 */
	public static Acceso_BD instancia() {
		if (instancia == null) {
			instancia = new Acceso_BD();
		}
		return instancia;
	}

	/**
	 * Metodo que inicializa la conexion con la base de datos
	 */
	public Connection getConexion() {
		try {
			if(connection == null) {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user_db, password_db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Metodo que cierra una conexion con la base de datos
	 */
	public void closeConnect() {
		try {
			if (connection != null && !connection.isClosed() ) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo publico que devuelve un objeto de tipo empleado en base a su
	 * coincidencia de usuario y contraseña
	 * 
	 * @param usuario    apodo del empleado en la base de datos
	 * @param contraseña contraseña del empleado en la base de datos
	 * @return objeto de tipo empleado, null si no existe
	 */
	public Empleado login(String usuario, String contraseña) {
		System.out.println("metodo login");
		
		// consulta de usuario y contraseña unica
		String query = "SELECT id_empleado, nombre, apellidos, categoria FROM Empleado WHERE apodo = ? AND contraseña = ?";
		
		/*
		 * NOTA: en todos los prepared statements, si se crea el statement en unos ()
		 * la clausula try se encarga de abrir y cerrar el cursor mejorando la lectura y sintaxis del codigo
		 */
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, usuario);
			stmt.setString(2, contraseña);
			
			try (ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
					int id = resultado.getInt("id_empleado");
					String nombre = resultado.getString("nombre");
					String apellidos = resultado.getString("apellidos");
					String categoria = resultado.getString("categoria");
					
					return new Empleado(id, nombre, apellidos, usuario, categoria, contraseña);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
