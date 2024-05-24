/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package prueba;



import Modelo.Administrador;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

import org.junit.Test;

import javax.swing.JTable;

public class ProfesorTestTest {
    
    @Test
    public void testVerHorario() {
        JTable tabla = new JTable();
        String codigo = "123456";

        // Llama al método verHorario
        verHorario(tabla, codigo);

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        // Verifica que la tabla tenga al menos una fila
        assertTrue(modelo.getRowCount() > 0);

        // Verifica que los datos de la primera fila sean correctos
        assertEquals("Lunes", modelo.getValueAt(0, 0));
        assertEquals("Matemáticas", modelo.getValueAt(0, 1));
        assertEquals("A101", modelo.getValueAt(0, 2));
        assertEquals("08:00", modelo.getValueAt(0, 3));
        assertEquals("10:00", modelo.getValueAt(0, 4));
    }

    @Test
    public void testVerCursos() {
        JTable tabla = new JTable();
        String codigo = "123456";

        // Llama al método verCursos
        verCursos(tabla, codigo);

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        // Verifica que la tabla tenga al menos una fila
        assertTrue(modelo.getRowCount() > 0);

        // Verifica que los datos de la primera fila sean correctos
        assertEquals("Matemáticas", modelo.getValueAt(0, 0));
        assertEquals("A101", modelo.getValueAt(0, 1));
        assertEquals("B102", modelo.getValueAt(0, 2));
    }

    public void verHorario(JTable tabla, String codigo) {
        // Implementación del método verHorario
    }

    public void verCursos(JTable tabla, String codigo) {
        // Implementación del método verCursos
    }
}

