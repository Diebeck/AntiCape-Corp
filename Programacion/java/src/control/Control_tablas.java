/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;
import model.*;
import view.Panel_home;
import view.Panel_prim_aprendiz;
import view.Panel_x;

/**
 * Clase dedicada a el control de las tablas de la vista 
 */
public class Control_tablas {

	private Acceso_BD modelo;
    private Panel_x panel_x;
    private Panel_home panel_home;
    private ObtencionID ids;
    private ConsultasCliente consultas_cliente;
    private ConsultasCita consultas_cita;
    private ConsultasTaller consultas_taller;
    private ConsultasEmpleado consultas_empleado;
    private Panel_prim_aprendiz panel_aprendiz;
    /**
     * Contructor de la clase de control de tablas 
     * 
     * @param modelo acceso a la base de datos necesario para invocacion de metodos
     * @param panel_x panel principal donde se encuentran las tablas
     */
    public Control_tablas(Panel_x panel_x, Panel_home panel_home, Panel_prim_aprendiz panel_aprendiz) {
    	//invocacion de la instancia
        this.modelo = Acceso_BD.instancia();
        this.panel_x = panel_x;
        this.panel_home = panel_home;
        this.ids = new ObtencionID(modelo.getConexion());
        this.panel_aprendiz = panel_aprendiz;
        
        //usar la intancia del Acceso_BD sin crear una nueva
        Connection conexion = modelo.getConexion();
        this.consultas_cliente = new ConsultasCliente(conexion);
        this.consultas_cita = new ConsultasCita(conexion);
        this.consultas_taller = new ConsultasTaller(conexion);
        this.consultas_empleado = new ConsultasEmpleado(conexion);
    }
    
    /**
     * Pequeña implementacion privada local del metodo isCellEditable
     * de la clase DefaultTableModel para impedir que el usuario 
     * edite las tablas de la aplicacion. También aplica estilo a los títulos.
     * 
     * @param columna columna que sera la cabecera de la tabla 
     * @param tabla JTable a la que se aplicará el modelo y estilo
     * @return objeto de tipo DefaultTableModel
     * @see DefaultTableModel#isCellEditable(int, int)
     * @see JTableHeader
     */
    private DefaultTableModel crearModelo(String[] columna, JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel(columna, 0) {
            @Override
            // eliminacion de la propiedad editable de las celdas
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabla.setModel(modelo);
        
        // Evitar que se puedan mover/reordenar las columnas
        tabla.getTableHeader().setReorderingAllowed(false);
        
        // Evitar seleccion multiple de filas 
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // Estilo para la cabecera de la tabla
        JTableHeader cabecera = tabla.getTableHeader();
        //fuente 
        cabecera.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
        //color
        cabecera.setForeground(new Color(128, 0, 64));
        
        //renderizado de las tablas al centro
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Aplicar el renderizador centrado a todas las columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        return modelo;
    }
    
    /**
     * Metodo que renderiza las celdas en funcion de su ancho
     * 
     * @param tabla tabla que sera renderizada 
     */
    private void ajustarAncho(JTable tabla) {
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            TableColumn column = tabla.getColumnModel().getColumn(columna);
            int ancho = 0;
            
            // Ancho de la cabecera
            FontMetrics headerFM = tabla.getTableHeader().getFontMetrics(tabla.getTableHeader().getFont());
            ancho = headerFM.stringWidth(column.getHeaderValue().toString()) + 20;
            
            // Ancho de los datos
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                Object valor = tabla.getValueAt(fila, columna);
                if (valor != null) {
                    FontMetrics dataFM = tabla.getFontMetrics(tabla.getFont());
                    int anchoData = dataFM.stringWidth(valor.toString()) + 20;
                    ancho = Math.max(ancho, anchoData);
                }
            }
            
            column.setPreferredWidth(ancho);
        }
    }
    
    
    /**
     * Metodo publico que genera un rellenado de la tabla
     * que muestra los datos de todas las citas pendientes
     * 
     * @see #crearModelo(String[])
     * @see Acceso_BD#mostradoCitas()
     * 
     * Los cometarios en este metodo funcionan como explicacion
     * a los metodos de rellenado de tablas 
     * 
     * @see #cargarTalleres()
     * @see #cargarClientes()
     * @see #cargarEmpleados()
     * @see #cargarTalleres()
     */
    public void cargarCitas() {
    	//Almacenado del array con los datos de la base de datos
        ArrayList<Cita> citas = consultas_cita.mostradoCitas();
        //Array de strings con los titulos de la tabla 
        String[] columnas = {"Encargado", "Fecha", "Hora", "Taller", "Cliente", "Traje"};
        //Creacion del modelo de la tabla 
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);
        
        //si el array logro capturar datos 
        if (citas != null) {
            for (Cita n : citas) {
                // Obtener los nombres usando los IDs asociados a las citas 
                String nombreCliente = ids.obtenerNombreCliente(n.getId_cliente());
                String nombreEncargado = ids.obtenerNombreEmpleado(n.getId_encargado());
                String nombreTaller = ids.obtenerNombreTaller(n.getId_taller());
                String nombreTraje = ids.obtenerNombreTraje(n.getId_traje());
                
                //formateo del formato de la hora 
                String hora = n.getHora().substring(0,5);
                
                //Añadido al modelo de la tabla los datos de la cita
                tableModel.addRow(new Object[]{
                	nombreEncargado,
                	n.getFecha(),
                	hora,
                	nombreTaller,
                	nombreCliente, 
                	nombreTraje
                });
            }
        }
        //A la tabla de panel le pasamos el modelo formateado
        panel_x.getTable().setModel(tableModel);
        panel_x.setEstado("citas");
    }
    
    /**
     * Metodo publico que genera un rellenado de la tabla
     * que muestra los datos de todos los clientes en el sistema
     * 
     * @see #crearModelo(String[])
     * @see Acceso_BD#mostrarClientes()
     */
    public void cargarClientes() {
        ArrayList<Cliente> clientes = consultas_cliente.mostrarClientes();
        String[] columnas = { "Alineacion", "Nombre", "Colores", "Superpoder"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (clientes != null) {
            for (Cliente n : clientes) {
                tableModel.addRow(new Object[]{
                	n.getAlineacion(),
                    n.getNombre(),
                    n.getColores(),
                    n.getSuperpoder()
                });
            }
        }
        panel_x.getTable().setModel(tableModel);
        //ajustar ancho de las celdas
        ajustarAncho(panel_x.getTable());
        panel_x.setEstado("clientes");
    }

    /**
     * Metodo publico que genera un rellenado de la tabla
     * que muestra los datos de todos los empleados en el sistema
     * 
     * @see #crearModelo(String[])
     * @see Acceso_BD#mostrarEmpleados()
     */
    public void cargarEmpleados() {
        ArrayList<Empleado> empleados = consultas_empleado.mostrarEmpleados();
        String[] columnas = {"Nombre", "Apellidos", "Apodo", "Categoria"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (empleados != null) {
            for (Empleado n : empleados) {
                tableModel.addRow(new Object[]{
                    n.getNombre(),
                    n.getApellidos(),
                    n.getApodo(),
                    n.getCategoria()
                });
            }
        }
        panel_x.getTable().setModel(tableModel);
        panel_x.setEstado("empleados");
    }
    
    /**
     * Metodo publico que genera un rellenado de la tabla
     * que muestra los datos de todos los talleres en el sistema
     * 
     * @see #crearModelo(String[])
     * @see ConsultasTaller#mostrarTalleres()
     */
    public void cargarTalleres() {
        ArrayList<Taller> talleres = consultas_taller.mostrarTalleres();
        String[] columnas = {"Nombre", "Tipo de sala"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (talleres != null) {
            for (Taller n : talleres) {
                tableModel.addRow(new Object[]{
                    n.getNombre_sala(),
                    n.getTipo_sala(),
                });
            }
        }
        panel_x.getTable().setModel(tableModel);
        panel_x.setEstado("talleres");
    }
    
    
    /**
     * Metodo que rellena la tabla de citas recientes
     * con el contenido de un ArrayList de citas 
     * 
     * @see Acceso_BD#CitasRecientes()
     * @see #crearModelo(String[])
     */
    public void citasRecientes() {
        ArrayList<Cita> citas = consultas_cita.CitasRecientes();
        String[] columnas = {"Encargado", "Fecha", "Hora", "Taller", "Cliente", "Traje"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_home.getTablaClientes());
        tableModel.setRowCount(0);
        
        if (citas != null) {
            for (Cita n : citas) { 
                String nombreCliente = ids.obtenerNombreCliente(n.getId_cliente());
                String nombreEncargado = ids.obtenerNombreEmpleado(n.getId_encargado());
                String nombreTaller = ids.obtenerNombreTaller(n.getId_taller());
                String nombreTraje = ids.obtenerNombreTraje(n.getId_traje());
                String hora = n.getHora().substring(0,5);
                
                tableModel.addRow(new Object[]{
                	nombreEncargado,
                	n.getFecha(),
                	hora,
                	nombreTaller,
                	nombreCliente, 
                	nombreTraje
                });
            }
        }
        panel_home.getTablaClientes().setModel(tableModel);
    }
    
    /**
     * Metodo publico que genera un rellenado de la tabla
     * que muestra la ocupación de cada taller (nombre y número de citas)
     * 
     * @see #crearModelo(String[], JTable)
     * @see ConsultasTaller#ocupacionTaller()
     */
    public void cargarOcupacionTalleres() {
        ArrayList<String[]> ocupacionTalleres = consultas_taller.ocupacionTaller();
        String[] columnas = {"Taller", "Número de Citas"};

        DefaultTableModel tableModel = crearModelo(columnas, panel_home.getTablaTalleres());
        
        tableModel.setRowCount(0);
        
        if (ocupacionTalleres != null) {
            for (String[] taller : ocupacionTalleres) {
                tableModel.addRow(new Object[]{
                    taller[0],  
                    taller[1]  
                });
            }
        } else {
            // Como es un string manejamos mensaje de inexistencia de citas 
            tableModel.addRow(new Object[]{"No hay talleres con citas", "0"});
        }
       
        panel_home.getTablaTalleres().setModel(tableModel);
        
    }
    
    /**
     * Metodo que rellena la tabla de citas de aprendiz
     * con el contenido de un ArrayList de citas 
     * 
     * @param id id del aprendiz que inicia sesion
     * @see Acceso_BD#CitasRecientes()
     * @see Control_tablas#crearModelo(String[])
     */
	public void citasRecientesAprendiz(int id) {
		ArrayList<Cita> citas = consultas_cita.citasAprendiz(id);
		String[] columnas = { "Encargado", "Taller", "Cliente", "Traje", "Fecha", "Duración" };
		DefaultTableModel tableModel = crearModelo(columnas, panel_aprendiz.getTable());
		tableModel.setRowCount(0);

		if (citas != null) {
			for (Cita n : citas) {
				String nombreCliente = ids.obtenerNombreCliente(n.getId_cliente());
				String nombreEncargado = ids.obtenerNombreEmpleado(n.getId_encargado());
				String nombreTaller = ids.obtenerNombreTaller(n.getId_taller());
				String nombreTraje = ids.obtenerNombreTraje(n.getId_traje());

				tableModel.addRow(new Object[] { 
						nombreEncargado, 
						nombreTaller, 
						nombreCliente, 
						nombreTraje,
						n.getFecha(), n.getDuracion() });
			}
		}
		panel_home.getTablaClientes().setModel(tableModel);
	}
	
	/**
	 * Metodo para rellenar las tablas del oficial 
	 * y restringir su edicion 
	 * 
	 * @param id id del oficial
	 */
	public void cargarCitasOficial(int id) {
        ArrayList<Cita> citas = consultas_cita.citasOficial(id); 
        String[] columnas = {"Encargado", "Fecha", "Hora", "Taller", "Cliente", "Traje"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);
        
        if (citas != null) {
            for (Cita n : citas) {
                String nombreCliente = ids.obtenerNombreCliente(n.getId_cliente());
                String nombreEncargado = ids.obtenerNombreEmpleado(n.getId_encargado());
                String nombreTaller = ids.obtenerNombreTaller(n.getId_taller());
                String nombreTraje = ids.obtenerNombreTraje(n.getId_traje());
                
                String hora = n.getHora().substring(0,5);
                
                tableModel.addRow(new Object[]{
                	nombreEncargado,
                	n.getFecha(),
                	hora,
                	nombreTaller,
                	nombreCliente, 
                	nombreTraje
                });
            }
        }
        panel_x.getTable().setModel(tableModel);
        panel_x.setEstado("citas");
    }
}