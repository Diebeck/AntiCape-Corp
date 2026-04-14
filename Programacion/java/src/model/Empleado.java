/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

/**
 * 
 */
public class Empleado {
	private int id_empleado;
	private String nombre;
	private String apellidos;
	private String apodo;
	private String categoria;
	private String contraseña;
	
	/**
	 * Constructor de la instancia de Empleado
	 * 
	 * @param id identificador unico del empleado 
	 * @param nombre nombre del empleado
	 * @param apellidos apellidos del empleado 
	 * @param user apodo del empleado para uso en el login 
	 * @param categoria categoria del empleado (Maestro, Oficial, Aprendiz)
	 * @param contraseña contraseña del empleado
	 */
	public Empleado(int id, String nombre, String apellidos, String user, String categoria, String contraseña) {
		this.id_empleado = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.apodo = user;
		this.categoria = categoria;
		this.contraseña = contraseña;
	}

	// getters para mostrar los datos
	public int getId_empleado() {
		return id_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getApodo() {
		return apodo;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getContraseña() {
		return contraseña;
	}
}
