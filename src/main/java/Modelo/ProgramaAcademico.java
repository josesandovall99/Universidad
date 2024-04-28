/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderCursos;
import CodigoAcademico.BuilderProgramas;
import CodigoAcademico.CodigoCursos;
import CodigoAcademico.CodigoProgramas;
import CodigoAcademico.Director;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JOSE SANDOVAL
 */
public class ProgramaAcademico {

    public String codigoAcademico;
    public String nombre;
    public String facultad;
    public String estadoAcademico;

    public ProgramaAcademico(String codigoAcademico, String nombre, String facultad) {
        this.codigoAcademico = codigoAcademico;
        this.nombre = nombre;
        this.facultad = facultad;
    }

    public ProgramaAcademico() {
    }

    public String getCodigoAcademico() {
        return codigoAcademico;
    }

    public void setCodigoAcademico(String codigoAcademico) {
        this.codigoAcademico = codigoAcademico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }
    
    

    public void InsetarPrograma(JTextField nombre, JTextField facultad, JTextField codigo) { //******

        
        codigo.setText("");
        
        setNombre(nombre.getText()); //******
        setFacultad(facultad.getText());//******
        setCodigoAcademico(generarCodigo());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "INSERT INTO ProgramaAcademico (codigoAcademico,nombre,facultad, estado) VALUES (?,?,?,?);";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());//******
            cs.setString(2, getNombre());//******
            cs.setString(3, getFacultad());//******
            cs.setString(4, getEstadoAcademico());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }

    }

    public String generarCodigo() {

        //PATRON DE DISEÑO------------------
        
        String codigo="";
        
        BuilderProgramas a = new BuilderProgramas();
        Director b = new Director();
        
        b.construirCodigoAcademico(a);
        
        CodigoProgramas codig = a.getResult();
        
        codigo = codig.pasarAString();
        
        //----------------------------------

        return codigo;
    }

    public void visualizarPrograma(JTable tablaProg, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaProg.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Facultad");//******

        tablaProg.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.estado = 'ACTIVADO'";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND ProgramaAcademico.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.nombre LIKE '%" + valor + "%'"
                            + "AND ProgramaAcademico.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.facultad LIKE '%" + valor + "%'"
                                + "AND ProgramaAcademico.estado ='ACTIVADO'";//******

                    } else {

                        sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.estado = 'ACTIVADO'";//******

                    }
                }
            }
        }

        String[] datos = new String[3];
        Statement st;

        try {

            st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);//******
                datos[1] = rs.getString(2);//******
                datos[2] = rs.getString(3);//******

                modelo.addRow(datos);

            }

            tablaProg.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txcod, JTextField txnom, JTextField txfac) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txcod.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 1).toString());//******
                txfac.setText(tabla.getValueAt(fila, 2).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarPrograma(JTextField txcod, JTextField txnom, JTextField txfac) {

        setCodigoAcademico(txcod.getText());//******
        setNombre(txnom.getText());//******
        setFacultad(txfac.getText());//******

        Conexion co = new Conexion();

        String consulta = "UPDATE ProgramaAcademico SET ProgramaAcademico.nombre = ?, "
                + "ProgramaAcademico.facultad = ? WHERE ProgramaAcademico.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getNombre());//******
            cs.setString(2, getFacultad());//******
            cs.setString(3, getCodigoAcademico());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarPrograma(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE ProgramaAcademico SET ProgramaAcademico.estado=? WHERE ProgramaAcademico.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, getCodigoAcademico());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro DESACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
        }

    }
    
    public void visualizarProgramaDesactivado(JTable tablaProg, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaProg.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Facultad");//******

        tablaProg.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.estado = 'DESACTIVADO'";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND ProgramaAcademico.estado ='DESACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.nombre LIKE '%" + valor + "%'"
                            + "AND ProgramaAcademico.estado ='DESACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.facultad LIKE '%" + valor + "%'"
                                + "AND ProgramaAcademico.estado ='DESACTIVADO'";//******

                    } else {

                        sql = "SELECT codigoAcademico,nombre,facultad FROM ProgramaAcademico WHERE ProgramaAcademico.estado = 'DESACTIVADO'";//******

                    }
                }
            }
        }

        String[] datos = new String[3];
        Statement st;

        try {

            st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);//******
                datos[1] = rs.getString(2);//******
                datos[2] = rs.getString(3);//******

                modelo.addRow(datos);

            }

            tablaProg.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }
    
    public void activarPrograma(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE ProgramaAcademico SET ProgramaAcademico.estado=? WHERE ProgramaAcademico.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, getCodigoAcademico());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro ACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
        }

    }
    
}
