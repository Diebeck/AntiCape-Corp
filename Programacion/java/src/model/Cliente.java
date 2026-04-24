/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

/**
 * Clase de modelado de los clientes en el sistema 
 */
public class Cliente {
	//atributos de la clase
	private int id_cliente;
	private String nombre;
	private String colores;
	private String superpoder;
	private String alineacion;
	
	/**
	 * Constructor de instancia de la clase 
	 * 
	 * @param id identificador unico del cliente 
	 * @param nombre nombre del cliente
	 * @param color color identificador del cliente 
	 * @param poder poder del cliente
	 */
	public Cliente(int id, String nombre, String color, String poder, String alineacion) {
		this.id_cliente = id;
		this.nombre = nombre;
		this.colores = color;
		this.superpoder = poder;
		this.alineacion = alineacion;
	}

	// Metodos get para mostrado de datos 
	public int getId_cliente() {
		return id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getColores() {
		return colores;
	}

	public String getSuperpoder() {
		return superpoder;
	}
	
	public String getAlineacion() {
		return alineacion;
	}
}
