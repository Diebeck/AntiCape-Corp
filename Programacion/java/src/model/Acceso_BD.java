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
	private ObtencionID ids;

	/**
	 * Metodo que prende la conexion con la base de datos
	 * 
	 * @see #getConexion()
	 */
	public Acceso_BD() {
		getConexion();
		ids = new ObtencionID(instance);
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
						resultado.getInt(4), resultado.getInt(5), resultado.getInt(6), resultado.getInt(7));
				citas.add(cita);
			}

			return citas;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	/**
	 * Metodo publico que consulta a la base de datos los registros
	 * existentes de clientes en el sistema 
	 * 
	 * @return ArraList de objetos tipo cliente 
	 */
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
	
	/**
	 * Metodo que consulta a la base de datos la totalidad de 
	 * empleados inscritos en el sistema 
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
	 * Metodo que consult a la base de datos la informacion 
	 * de todos los talleres existentes en el sistema 
	 * 
	 * @return ArrayList de objetos de tipo taller
	 */
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
	
	/**
	 * Metodo que consulta a la base de datos la totalidad de 
	 * trajes registrados en el sistema 
	 * 
	 * @param nombre del usuario que tiene asociado los trajes
	 * @return ArrayList de objetos de tipo empleado 
	 */
	public ArrayList<Traje> mostrarTrajes(String nombre) {
		ArrayList<Traje> trajes = new ArrayList<>();
		String query = "SELECT * FROM Traje WHERE id_cliente IN (SELECT id_cliente FROM Cliente WHERE nombre = " + "'" + nombre + "')";

		try (Statement stmt = instance.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {

			while (resultado.next()) {
				Traje traje = new Traje(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getString(4));
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
	    String query = "SELECT t.nombre_sala, COUNT(c.id_cita) AS total_citas " +
	                   "FROM Taller t, Citas c " +
	                   "WHERE t.id_taller = c.id_taller " +
	                   "GROUP BY t.id_taller, t.nombre_sala " +
	                   "ORDER BY total_citas DESC";
	    
	    try (Statement stmt = instance.createStatement(); 
	         ResultSet rs = stmt.executeQuery(query)) {
	        
	        while (rs.next()) {
	            String[] tallerInfo = new String[2];
	            tallerInfo[0] = rs.getString("nombre_sala");
	            tallerInfo[1] = String.valueOf(rs.getInt("total_citas"));
	            citasPorTaller.add(tallerInfo);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return citasPorTaller;
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
	 * Metodo para añadir un cliente a la base de datos 
	 * 
	 * @param nombre nombre del nuevo cliente
	 * @param colores colores del nuevo cliente
	 * @param poder poderes del nuevo cliente 
	 * 
	 * @return true si la creacion fue exitosa, false para el caso contrario
	 */
	public boolean crearCliente(String nombre, String colores, String poder) {
			String query = "INSERT INTO Cliente(nombre, colores, superpoder) VALUES (?,?,?)";
			
			try(PreparedStatement stmt = instance.prepareStatement(query)){
			stmt.setString(1, nombre);
			stmt.setString(2, colores);
			stmt.setString(3, poder);
			
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
	 * Metodo para añadir un nuevo taller a la base de datos 
	 * 
	 * @param tipo String del tipo de sala del taller 
	 * @param nombre String del nombre del taller 
	 * 
	 * @return true si la creacion se hizo correctamente, false para el caso contrario
	 */
	public boolean crearTaller(String tipo, String nombre) {
		String query = "INSERT INTO Taller (tipo_sala, nombre_sala) VALUES (?,?)";
		
		try(PreparedStatement stmt = instance.prepareStatement(query)){
		stmt.setString(1, tipo);
		stmt.setString(2, nombre);
		
		int filas = stmt.executeUpdate();
		
		if (filas > 0) {
			System.out.println("Añadido de Taller exitoso");
			return true;
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo para añadir un nuevo empleado a la base de datos 
	 * 
	 * @param nombre String con el nombre del empleado
	 * @param apellidos String con los apellidos del empleado
	 * @param apodo String con el apodo que sera usado como usuario del empleado
	 * @param categoria String con la categoria del empleado (Maestro, Oficial, Aprendiz)
	 * @param contraseña String con la contraseña de la cuenta del empleado 
	 * 
	 * @return true en caso de creacion exitosa y false para cracion fallida
	 */
	public boolean crearEmpleado (String nombre, String apellidos, String apodo, String categoria, String contraseña) {
	String query = "INSERT INTO Empleado (nombre, apellidos, apodo, categoria, contraseña) VALUES (?,?,?,?,?)";
		
		try(PreparedStatement stmt = instance.prepareStatement(query)){
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
	 * Metodo para modificar un cliente existente
	 * 
	 * @param id ID del cliente a modificar
	 * @param nombre nuevo nombre
	 * @param colores nuevos colores
	 * @param superpoder nuevo superpoder
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarCliente(int id, String nombre, String colores, String superpoder) {
	    String query = "UPDATE Cliente SET nombre = ?, colores = ?, superpoder = ? WHERE id_cliente = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        stmt.setString(1, nombre);
	        stmt.setString(2, colores);
	        stmt.setString(3, superpoder);
	        stmt.setInt(4, id);
	        
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

	/**
	 * Metodo para modificar un cliente y su traje simultáneamente
	 * 
	 * @param idCliente ID del cliente a modificar
	 * @param nombre nuevo nombre del cliente
	 * @param colores nuevos colores
	 * @param superpoder nuevo superpoder
	 * @param nombreTrajeActual nombre actual del traje
	 * @param nombreTrajeNuevo nuevo nombre del traje
	 * @param estadoTraje nuevo estado del traje
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarClienteConTraje(int idCliente, String nombre, String colores, String superpoder,
	                                        String nombreTrajeActual, String nombreTrajeNuevo, String estadoTraje) {
	    
	    boolean exitoCliente = modificarCliente(idCliente, nombre, colores, superpoder);
	    
	    if (exitoCliente) {
	        boolean exitoTraje = modificarTraje(idCliente, nombreTrajeActual, nombreTrajeNuevo, estadoTraje);
	        if (exitoTraje) {
	            System.out.println("Cliente y traje modificados exitosamente");
	            return true;
	        } else {
	            System.out.println("ERROR: Cliente modificado pero fallo al modificar el traje");
	        }
	    } else {
	        System.out.println("ERROR: Fallo al modificar el cliente");
	    }
	    
	    return false;
	}

	/**
	 * Metodo para modificar un empleado existente
	 * 
	 * @param id ID del empleado a modificar
	 * @param nombre nuevo nombre
	 * @param apellidos nuevos apellidos
	 * @param apodo nuevo apodo
	 * @param categoria nueva categoria
	 * @param contraseña nueva contraseña
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarEmpleado(int id, String nombre, String apellidos, String apodo, String categoria, String contraseña) {
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
	 * Metodo para modificar un taller existente
	 * 
	 * @param id ID del taller a modificar
	 * @param tipo_sala nuevo tipo de sala
	 * @param nombre_sala nuevo nombre de la sala
	 * @return true si se modificó correctamente, false si no
	 */
	public boolean modificarTaller(int id, String tipo_sala, String nombre_sala) {
	    String query = "UPDATE Taller SET tipo_sala = ?, nombre_sala = ? WHERE id_taller = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        stmt.setString(1, tipo_sala);
	        stmt.setString(2, nombre_sala);
	        stmt.setInt(3, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Taller modificado exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
}
