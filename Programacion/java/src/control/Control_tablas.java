/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.*;
import model.*;
import view.Panel_home;
import view.Panel_x;

/**
 * Clase dedicada a el control de las tablas de la vista 
 */
public class Control_tablas {

    private Acceso_BD modelo;
    private Panel_x panel_x;
    private Panel_home panel_home;

    /**
     * Contructor de la clase de control de tablas 
     * 
     * @param modelo acceso a la base de datos necesario para invocacion de metodos
     * @param panel_x panel principal donde se encuentran las tablas
     */
    public Control_tablas(Acceso_BD modelo, Panel_x panel_x, Panel_home panel_home) {
        this.modelo = modelo;
        this.panel_x = panel_x;
        this.panel_home = panel_home;
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
        
        // Estilo para la cabecera de la tabla
        JTableHeader cabecera = tabla.getTableHeader();
        //fuente 
        cabecera.setFont(new Font("Century Schoolbook", Font.ITALIC, 15));
        //color
        cabecera.setForeground(new Color(128, 0, 64));
        
        return modelo;
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
    	// invocacion del metodo que consulta a la base de datos
        ArrayList<Cita> citas = modelo.mostradoCitas();
        // titulos de la tabla y su posterior añadido al modelo de la tabla 
        String[] columnas = {"ID", "Fecha", "Duración", "Cliente", "Encargado", "Taller", "Traje"};
        // genero un nuevo modelo de tabla y le asigno los titulos 
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        
        //reset de la tabla para evitar duplicado de datos 
        tableModel.setRowCount(0);
        
        //verificacion de que si existan citas en el sistema 
        if (citas != null) {
            for (Cita n : citas) {
            	
            	/** en base a cada objeto de tipo cita en el array de citas
            	 * creo un Objeto generico que contenga cada elemento de la cita
            	 * y a su vez lo añado al array 
            	 */
                tableModel.addRow(new Object[]{
                    n.getId_cita(),
                    n.getFecha(),
                    n.getDuracion(),
                    n.getId_cliente(),
                    n.getId_encargado(),
                    n.getId_taller(),
                    n.getId_traje()
                });
            }
        }
        //le paso el modelo ya formado a la tabla del panel_x
        panel_x.getTable().setModel(tableModel);
        //muevo el estado del panel 
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
        ArrayList<Cliente> clientes = modelo.mostrarClientes();
        String[] columnas = {"ID", "Nombre", "Colores", "Superpoder"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (clientes != null) {
            for (Cliente n : clientes) {
                tableModel.addRow(new Object[]{
                    n.getId_cliente(),
                    n.getNombre(),
                    n.getColores(),
                    n.getSuperpoder()
                });
            }
        }
        panel_x.getTable().setModel(tableModel);
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
        ArrayList<Empleado> empleados = modelo.mostrarEmpleados();
        String[] columnas = {"ID", "Nombre", "Apellidos", "Apodo", "Categoria"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (empleados != null) {
            for (Empleado n : empleados) {
                tableModel.addRow(new Object[]{
                    n.getId_empleado(),
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
     * @see Acceso_BD#mostrarTalleres()
     */
    public void cargarTalleres() {
        ArrayList<Taller> talleres = modelo.mostrarTalleres();
        String[] columnas = {"ID", "Tipo de sala", "Nombre"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_x.getTable());
        tableModel.setRowCount(0);

        if (talleres != null) {
            for (Taller n : talleres) {
                tableModel.addRow(new Object[]{
                    n.getId_taller(),
                    n.getTipo_sala(),
                    n.getNombre_sala()
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
     */
    public void citasRecientes() {
        ArrayList<Cita> citas = modelo.CitasRecientes();
        String[] columnas = {"ID", "Fecha", "Duración", "Cliente", "Encargado", "Taller", "Traje"};
        DefaultTableModel tableModel = crearModelo(columnas, panel_home.getTablaClientes());
        tableModel.setRowCount(0);

        if (citas != null) {
            for (Cita n : citas) {
 
                tableModel.addRow(new Object[]{
                    n.getId_cita(),
                    n.getFecha(),
                    n.getDuracion(),
                    n.getId_cliente(),
                    n.getId_encargado(),
                    n.getId_taller(),
                    n.getId_traje()
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
     * @see Acceso_BD#ocupacionTaller()
     */
    public void cargarOcupacionTalleres() {
        ArrayList<String[]> ocupacionTalleres = modelo.ocupacionTaller();
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
}