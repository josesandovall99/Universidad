/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderCursos;
import CodigoAcademico.BuilderSemestres;
import CodigoAcademico.CodigoCursos;
import CodigoAcademico.CodigoSemestres;
import CodigoAcademico.Director;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JOSE SANDOVAL
 */
public class SemestreAcademico {

    public String nombre;
    public String codigoAcademico;
    public String año;
    public String semestre;
    public String estadoAcademico;

    public SemestreAcademico(String nombre, String año, String semestre) {
        this.nombre = nombre;
        this.año = año;
        this.semestre = semestre;
    }

    public SemestreAcademico() {
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

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    
    
    public void InsetarSemestre(JTextField año, JTextField semestre, JTextField codigo) {

        codigo.setText("");
        
        setAño(año.getText());
        setSemestre(semestre.getText());
        setCodigoAcademico(generarCodigo());
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "INSERT INTO SemestreAcademico (codigoAcademico,año,semestre, estado) VALUES (?,?,?,?);";
        
        Object[] opciones = {"Sí", "No"};
        
        int respuesta = JOptionPane.showOptionDialog(null, 
                "¿Estás seguro de que quieres CREAR un SEMESTRE ACAEMICO?", "Confirmación", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());
            cs.setString(2, getAño());
            cs.setString(3, getSemestre());
            cs.setString(4, getEstadoAcademico());

            cs.execute();

            JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }
        
        } else {
            JOptionPane.showMessageDialog(null, "Inercion Cancelada");}

    }

    public String generarCodigo() {

        //PATRON DE DISEÑO------------------
        
        String codigo="";
        
        BuilderSemestres a = new BuilderSemestres();
        Director b = new Director();
        
        b.construirCodigoAcademico(a);
        
        CodigoSemestres codig = a.getResult();
        
        codigo = codig.pasarAString();
        
        //----------------------------------

        return codigo;
    }

    public void visualizarSemestre(JTable tablaSemestre, int opbuscar, String valor) {

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaSemestre.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");
        modelo.addColumn("Año");
        modelo.addColumn("Semestre");

        tablaSemestre.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";

        if (opbuscar == 0 && valor == null) {

            sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.estado='ACTIVADO'";
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND SemestreAcademico.estado ='ACTIVADO'";

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.año LIKE '%" + valor + "%'"
                            + "AND SemestreAcademico.estado ='ACTIVADO'";

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.semestre LIKE '%" + valor + "%'"
                                + "AND SemestreAcademico.estado ='ACTIVADO'";

                    } else {

                        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.estado='ACTIVADO'";

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

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);

            }

            tablaSemestre.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txcod, JTextField txaño, JTextField txsem) {

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txcod.setText(tabla.getValueAt(fila, 0).toString());
                txaño.setText(tabla.getValueAt(fila, 1).toString());
                txsem.setText(tabla.getValueAt(fila, 2).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarSemestre(JTextField txcod, JTextField txaño, JTextField txsem) {

        setCodigoAcademico(txcod.getText());
        setAño(txaño.getText());
        setSemestre(txsem.getText());

        Conexion co = new Conexion();

        String consulta = "UPDATE SemestreAcademico SET SemestreAcademico.año = ?, SemestreAcademico.semestre = ? WHERE SemestreAcademico.codigoAcademico=?";

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getAño());
            cs.setString(2, getSemestre());
            cs.setString(3, getCodigoAcademico());

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarSemestre(JTextField codtx) {

        setCodigoAcademico(codtx.getText());
        setEstadoAcademico("DESACTIVADO");
        

        Conexion co = new Conexion();

        String consulta = "UPDATE SemestreAcademico SET SemestreAcademico.estado=? WHERE SemestreAcademico.codigoAcademico=?";

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
    
    public void visualizarSemestreDesactivado(JTable tablaSemestre, int opbuscar, String valor) {

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaSemestre.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");
        modelo.addColumn("Año");
        modelo.addColumn("Semestre");

        tablaSemestre.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";

        if (opbuscar == 0 && valor == null) {

            sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.estado='DESACTIVADO'";
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND SemestreAcademico.estado ='DESACTIVADO'";

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.año LIKE '%" + valor + "%'"
                            + "AND SemestreAcademico.estado ='DESACTIVADO'";

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.semestre LIKE '%" + valor + "%'"
                                + "AND SemestreAcademico.estado ='DESACTIVADO'";

                    } else {

                        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico WHERE SemestreAcademico.estado='DESACTIVADO'";

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

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);

            }

            tablaSemestre.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }
    
    public void activarSemestre(JTextField codtx) {

        setCodigoAcademico(codtx.getText());
            setEstadoAcademico("ACTIVADO");
        

        Conexion co = new Conexion();

        String consulta = "UPDATE SemestreAcademico SET SemestreAcademico.estado=? WHERE SemestreAcademico.codigoAcademico=?";

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
