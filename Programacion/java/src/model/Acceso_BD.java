/**
 * @author 
 */
package model;

import java.sql.*;
import java.util.*;

/**
 * 
 */
public class Acceso_BD {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/AntiCape_db";
	private Connection instance = null;
	private String user_db = "root";
	private String password_db= "Rokokoso0812";

	public Acceso_BD () {
		getConexion();
	}
	
	public Connection getConexion() {
		try {
			Class.forName(driver);
			instance = DriverManager.getConnection(url, user_db, password_db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public void closeConnect () {
		try {
			instance.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean consultaUser(String usuario) {
		try {
			System.out.println("Llamado metodo consulta");
			String query = "SELECT * FROM empleado";
			Statement stmt = instance.createStatement();
			ResultSet resultado = stmt.executeQuery(query);
			
			while(resultado.next()) {
				System.out.println("Busqueda: " + resultado.getString(4));
				
				if (resultado.getString(4).equals(usuario)) {
					return true;
				}
			}
			resultado.close();
			stmt.close();
		} catch (SQLException e ) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean consultaContra(String contraseña) {
		try {
			System.out.println("Llamado metodo consulta");
			String query = "SELECT * FROM empleado";
			Statement stmt = instance.createStatement();
			ResultSet resultado = stmt.executeQuery(query);
			
			while(resultado.next()) {
				System.out.println("Busqueda: " + resultado.getString(6));
				
				if (resultado.getString(6).equals(contraseña)) {
					return true;
				}
			}
			resultado.close();
			stmt.close();
		} catch (SQLException e ) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * Metodo publico que devuelve un objeto de tipo empleado 
	 * en base a su coincidencia de usuario y contraseña
	 * 
	 * @param usuario apodo del empleado en la base de datos 
	 * @param contraseña contraseña del empleado en la base de datos 
	 * 
	 * @return objeto de tipo empleado 
	 */
	public Empleado login(String usuario, String contraseña) {
		System.out.println("metodo login");
	    int id = 0;
	    String nombre = "";
	    String apellidos = "";
	    String categoria = "";
	    
	    try {
	        String query = "SELECT * FROM Empleado WHERE apodo = '" + usuario + "' AND contraseña = '" + contraseña + "'";
	        Statement stmt = instance.createStatement();
	        ResultSet resultado = stmt.executeQuery(query);
	        
	        if (resultado.next()) {  
	            id = resultado.getInt("id_empleado");
	            nombre = resultado.getString("nombre");
	            apellidos = resultado.getString
	            		("apellidos");
	            categoria = resultado.getString("categoria");
	            
	            Empleado sesionActiva = new Empleado(id, nombre, apellidos, usuario, categoria, contraseña);
	            
	            resultado.close();
	            stmt.close();
	            return sesionActiva;
	        }
	        
	        resultado.close();
	        stmt.close();
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
