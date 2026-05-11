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
 * acciones a la tabla de Taller en la base de datos
 */
public class ConsultasTaller {
	private Connection instance;
	
	public ConsultasTaller(Connection modelo) {
		this.instance = modelo;
	}
	
	/**
	 * Metodo que consult a la base de datos la informacion de todos los talleres
	 * existentes en el sistema
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
	 * Metodo que busca en la base de datos talleres en funcion del
	 * estado de un traje 
	 * 
	 * @param estado estado del traje para el que se buscan talleres
	 * @return Arraylist lista de los talleres para manejar ese estado
	 */
	public ArrayList<Taller> mostrarTalleresTraje(String estado) {
	    ArrayList<Taller> talleres = new ArrayList<>();
	    String query = "SELECT * FROM Taller WHERE tipo_sala = ?";

	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        
	        switch (estado.toLowerCase()){
	            case "diseño":
	                stmt.setString(1, "diseño");
	                break;
	            case "costura":
	                stmt.setString(1, "costura");
	                break;
	            case "taller":
	                stmt.setString(1, "pruebas");
	                break;
	        }

	        try (ResultSet resultado = stmt.executeQuery()) {
	            while (resultado.next()) {
	                Taller taller = new Taller(
	                    resultado.getInt(1), 
	                    resultado.getString(2), 
	                    resultado.getString(3)
	                );
	                talleres.add(taller);
	            }
	        }

	        return talleres;
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
		String query = "SELECT t.nombre_sala, COUNT(c.id_cita) AS total_citas " + "FROM Taller t, Citas c "
				+ "WHERE t.id_taller = c.id_taller " + "GROUP BY t.id_taller, t.nombre_sala "
				+ "ORDER BY total_citas DESC";

		try (Statement stmt = instance.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

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
	 * Metodo para añadir un nuevo taller a la base de datos
	 * 
	 * @param tipo   String del tipo de sala del taller
	 * @param nombre String del nombre del taller
	 * 
	 * @return true si la creacion se hizo correctamente, false para el caso
	 *         contrario
	 */
	public boolean crearTaller(String tipo, String nombre) {
		String query = "INSERT INTO Taller (tipo_sala, nombre_sala) VALUES (?,?)";

		try (PreparedStatement stmt = instance.prepareStatement(query)) {
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
	 * Metodo para modificar un taller existente
	 * 
	 * @param id          ID del taller a modificar
	 * @param tipo_sala   nuevo tipo de sala
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
	
	/**
	 * Metodo para eliminar un taller existente
	 * 
	 * @param id ID del taller a eliminar
	 * @return true si se eliminó correctamente, false si no
	 */
	public boolean eliminarTaller(int id) {
	    String query = "DELETE FROM Taller WHERE id_taller = ?";
	    
	    try (PreparedStatement stmt = instance.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            System.out.println("Taller eliminado exitosamente. ID: " + id);
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al eliminar taller: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return false;
	}	
}
