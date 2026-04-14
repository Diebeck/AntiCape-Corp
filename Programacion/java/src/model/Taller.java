/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

/**
 * Clase de modelado de Talleres en el sistema 
 */
public class Taller {
	private int id_taller;
	private String tipo_sala;
	private String nombre_sala;
	
	/**
	 * Constructor de la instancia de la clase Taller 
	 * 
	 * @param id identificador unico del taller 
	 * @param tipo tipo de especializacion del taller (diseño, pruebas, costura)
	 * @param nombre nombre del taller
	 */
	public Taller(int id, String tipo, String nombre) {
		this.id_taller = id;
		this.tipo_sala = tipo;
		this.nombre_sala = nombre;
	}

	//getters de mostrado de los datos 
	public int getId_taller() {
		return id_taller;
	}

	public String getTipo_sala() {
		return tipo_sala;
	}

	public String getNombre_sala() {
		return nombre_sala;
	}
	
	
}
