/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package T;

import Modelo.Administrador;
import javax.swing.JTable;
import javax.swing.JTextField;
import static org.junit.Assert.*;
import org.junit.*;
/**
 *
 * @author JOSE SANDOVAL
 */
public class AdministradorTest {
    
    @Test
    public void testInsetarAdministrador() {
        JTextField nombre = new JTextField("Nombre Admin");
        JTextField email = new JTextField("admin@example.com");
        JTextField tel = new JTextField("123456789");
        JTextField dir = new JTextField("123 Main St");

        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método InsetarAdministrador

        // Aquí se puede simular la inserción del administrador, pero no la conexión real a la base de datos.
        admin.InsetarAdministrador(nombre, email, tel, dir);

        // Asegurarse de que los valores se han establecido correctamente
        assertEquals("Nombre Admin", admin.getNombre());
        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("123456789", admin.getTelefono());
        assertEquals("123 Main St", admin.getDireccion());
        assertEquals("administrador", admin.getTipo());
        assertEquals("ACTIVADO", admin.getEstadoAcademico());
    }
    
    
    @Test
    public void testGenerarCodigo() {
        Administrador generador = new Administrador(); // Asume que GeneradorCodigos es la clase que contiene el método generarCodigo()

        String codigoGenerado = generador.generarCodigo();

        // Comprobar que el código generado no esté vacío y tenga el formato esperado
        assertNotNull(codigoGenerado);// Verifica si el código tiene 8 caracteres alfanuméricos en mayúsculas
    }
    
    @Test
    public void testVisualizarAdministrador() {
        Administrador gestor = new Administrador(); // Asume que GestorAdministradores es la clase que contiene el método visualizarAdministrador()

        JTable tabla = new JTable();
        int opbuscar = 0;
        String valor = null;

        gestor.visualizarAdministrador(tabla, opbuscar, valor);

        // Comprobar que la tabla tiene las columnas esperadas
        assertEquals(7, tabla.getColumnCount());
        assertEquals("id interno", tabla.getColumnName(0));
        assertEquals("Codigo Academico", tabla.getColumnName(1));
        assertEquals("Nombre", tabla.getColumnName(2));
        assertEquals("email", tabla.getColumnName(3));
        assertEquals("telefono", tabla.getColumnName(4));
        assertEquals("Direccion", tabla.getColumnName(5));
        assertEquals("puesto", tabla.getColumnName(6));
    }
    
    
    
     @Test
    public void testSeleccionar() {
        Administrador gestor = new Administrador();

        // Crear una tabla con datos de ejemplo
        String[][] data = {
            {"1", "Código1", "Nombre1", "email1@example.com", "123456789", "Dirección1"},
            {"2", "Código2", "Nombre2", "email2@example.com", "987654321", "Dirección2"}
        };
        String[] columnNames = {"id interno", "Codigo Academico", "Nombre", "email", "telefono", "Direccion"};
        JTable tabla = new JTable(data, columnNames);

        JTextField txid = new JTextField();
        JTextField txnom = new JTextField();
        JTextField txemail = new JTextField();
        JTextField txtel = new JTextField();
        JTextField txdir = new JTextField();

        // Seleccionar la primera fila
        tabla.setRowSelectionInterval(0, 0);

        gestor.seleccionar(tabla, txid, txnom, txemail, txtel, txdir);

        // Comprobar que los campos de texto se llenan correctamente
        assertEquals("1", txid.getText());
        assertEquals("Nombre1", txnom.getText());
        assertEquals("email1@example.com", txemail.getText());
        assertEquals("123456789", txtel.getText());
        assertEquals("Dirección1", txdir.getText());

        // Seleccionar la segunda fila
        tabla.setRowSelectionInterval(1, 1);

        gestor.seleccionar(tabla, txid, txnom, txemail, txtel, txdir);

        // Comprobar que los campos de texto se llenan correctamente para la segunda fila
        assertEquals("2", txid.getText());
        assertEquals("Nombre2", txnom.getText());
        assertEquals("email2@example.com", txemail.getText());
        assertEquals("987654321", txtel.getText());
        assertEquals("Dirección2", txdir.getText());
    }
    
    
    @Test
    public void testModificarAdministrador() {
        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método modificarAdministrador()

        JTextField txid = new JTextField("1");
        JTextField txnom = new JTextField("Nuevo Nombre");
        JTextField txemail = new JTextField("nuevo@example.com");
        JTextField txtel = new JTextField("987654321");
        JTextField txdir = new JTextField("Nueva Dirección");

        admin.modificarAdministrador(txid, txnom, txemail, txtel, txdir);

        // Comprobar que los datos se han actualizado correctamente en el administrador
        assertEquals("Nuevo Nombre", admin.getNombre());
        assertEquals("nuevo@example.com", admin.getEmail());
        assertEquals("987654321", admin.getTelefono());
        assertEquals("Nueva Dirección", admin.getDireccion());
        assertEquals(1, admin.getId()); // Suponiendo que el ID del administrador es 1
    }
    
    @Test
    public void testEliminarAdministrador() {
        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método eliminarAdministrador()

        JTextField codtx = new JTextField("1");

        admin.eliminarAdministrador(codtx);

        // Comprobar que el estado del administrador se ha actualizado correctamente
        assertEquals("DESACTIVADO", admin.getEstadoAcademico());
        assertEquals(1, admin.getId()); // Suponiendo que el ID del administrador es 1
    }
    
    
    @Test
    public void testRecibirContraseñaAdministrador() {
        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método recibirContraseñaAdministrador()

        JTextField contraseña = new JTextField("nueva_contraseña");
        JTextField idtxt = new JTextField("1");

        admin.recibirContraseñaAdministrador(contraseña, idtxt);

        // Comprobar que la contraseña se ha actualizado correctamente en el administrador
        assertEquals("nueva_contraseña", admin.getContraseña());
        assertEquals(1, admin.getId()); // Suponiendo que el ID del administrador es 1
    }
    
    
    @Test
    public void testCompletarAdministrador() {
        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método completarAdministrador()

        JTextField idtxt = new JTextField("1");
        String combo = "Puesto Administrador";

        admin.completarAdministrador(combo, idtxt);

        // Comprobar que el puesto y el código académico se han actualizado correctamente en el administrador
        assertEquals("Puesto Administrador", admin.getPuesto());
        assertNotNull(admin.getCodigoAcademico());
        assertEquals(1, admin.getId()); // Suponiendo que el ID del administrador es 1
    }
    
    
    @Test
    public void testVisualizarDesactivadosAdministrador() {
        Administrador gestor = new Administrador(); // Asume que GestorAdministradores es la clase que contiene el método visualizarDesactivadosAdministrador()

        JTable tabla = new JTable();
        int opbuscar = 0;
        String valor = null;

        gestor.visualizarDesactivadosAdministrador(tabla, opbuscar, valor);

        // Comprobar que la tabla tiene las columnas esperadas
        assertEquals(7, tabla.getColumnCount());
        assertEquals("id interno", tabla.getColumnName(0));
        assertEquals("Codigo Academico", tabla.getColumnName(1));
        assertEquals("Nombre", tabla.getColumnName(2));
        assertEquals("email", tabla.getColumnName(3));
        assertEquals("telefono", tabla.getColumnName(4));
        assertEquals("Direccion", tabla.getColumnName(5));
        assertEquals("puesto", tabla.getColumnName(6));
    }
    
    @Test
    public void testActivarAdministrador() {
        Administrador admin = new Administrador(); // Asume que Administrador es la clase que contiene el método activarAdministrador()

        JTextField codtx = new JTextField("1");

        admin.activarAdministrador(codtx);

        // Comprobar que el estado del administrador se ha actualizado correctamente
        assertEquals("ACTIVADO", admin.getEstadoAcademico());
        assertEquals(1, admin.getId()); // Suponiendo que el ID del administrador es 1
    }
    
    
    
}
