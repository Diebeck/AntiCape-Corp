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
	private String user = "root";
	private String password = "1941";
	private String usuario;
	private String contraseña;
	
	public Acceso_BD () {
		getConexion();
	}
	
	public Connection getConexion() {
		try {
			Class.forName(driver);
			instance = DriverManager.getConnection(url, user, password);
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
	
	public boolean consultaUser() {
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
	
	public boolean consultaContra() {
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
	
	public String categoria(String usuario, String contraseña) {
		try {
			System.out.println("llamado a metodo categoria");
			String query = "SELECT * FROM empleado WHERE apodo = " + usuario + " and contraseña =  " + contraseña;
			Statement stmt = instance.createStatement();
			ResultSet resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				return resultado.getString(5);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

