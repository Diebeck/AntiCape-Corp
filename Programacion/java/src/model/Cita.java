/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package model;

/**
 * Clase de modelado de Cita en el sistema 
 */
public class Cita {
	private int id_cita;
	private String fecha;
	private String hora;
	private String duracion;
	private int id_cliente;
	private int id_encargado;
	private int id_traje;
	private int id_taller;
	
	/**
	 * Constructor de instancia de la clase Cita 
	 * 
	 * @param id identificador unico de la cita 
	 * @param fecha fecha de cita 
	 * @param duracion duracion (en horas) de la cita
	 * @param id_c identificador del cliente a atender en la cita
	 * @param id_e identificador del encargado de la cita
	 * @param id_tj identificador del traje a trabajar en la cita
	 * @param id_t identaficar del taller de trabajo
	 */
	public Cita(int id, String fecha, String hora, String duracion, int id_c, int id_e, int id_t, int id_tj) {
		this.id_cita = id;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
		this.id_cliente = id_c;
		this.id_encargado = id_e;
		this.id_traje = id_tj;
		this.id_taller = id_t;
		
		
	}

	//getters de acceso a los atributos
	public int getId_cita() {
		return id_cita;
	}

	public String getFecha() {
		return fecha;
	}

	public String getDuracion() {
		return duracion;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public int getId_encargado() {
		return id_encargado;
	}

	public int getId_traje() {
		return id_traje;
	}

	public int getId_taller() {
		return id_taller;
	}

	public String getHora(){
		return hora;
	}
	
	/** Devuelve la hora de la cita como un int
	 * @return la hora de la hora
	 */
	public int getHoraInt() {
		String[] tiempos = hora.split(":");
		return Integer.parseInt(tiempos[0]);
	}
	/** Devuelve el minuto de la cita como un int
	 * @return el minuto de la hora
	 */
	public int getMinutoInt() {
		String[] tiempos = hora.split(":");
		return Integer.parseInt(tiempos[1]);
	}
	
	/** Devuelve la duracion como un int
	 * @return la duracion
	 */
	public int getDuracionInt() {
		return Integer.parseInt(duracion.substring(0, 1));
	}
}
