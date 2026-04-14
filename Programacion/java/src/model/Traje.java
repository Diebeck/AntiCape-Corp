/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

/**
 * Clase de modelado de los trajes den el sistema 
 */
public class Traje {
	private int id_traje;
	private int id_cliente;
	private String nombre;
	private String estado;
	
	/**
	 * Contructor de la instancia de la clase
	 * 
	 * @param id identificador del traje 
	 * @param id_c identificador del cliente asociado al traje
	 * @param nombre nombre del traje (si es principal o especifico)s
	 * @param estado estado del traje (taller, costura, diseño)
	 */
	public Traje(int id, int id_c , String nombre, String estado) {
		this.id_traje = id;
		this.id_cliente = id_c;
		this.nombre = nombre;
		this.estado = estado;
	}

	// getters de mostrado de datos
	public int getId_traje() {
		return id_traje;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEstado() {
		return estado;
	}
}
