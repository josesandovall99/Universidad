/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderAsignaturas;
import CodigoAcademico.BuilderPensum;
import CodigoAcademico.CodigoAsignaturas;
import CodigoAcademico.CodigoPensum;
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
public class Pensum {

    public int idInterno;
    public String codigoAcademico;
    public String nombre;
    public String numeroSemestres;
    public String estadoAcademico;
    public ProgramaAcademico programa;

    public String getCodigoAcademico() {
        return codigoAcademico;
    }

    public int getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(int idInterno) {
        this.idInterno = idInterno;
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

    public String getNumeroSemestres() {
        return numeroSemestres;
    }

    public void setNumeroSemestres(String numeroSemestres) {
        this.numeroSemestres = numeroSemestres;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    public ProgramaAcademico getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
    }

    public void InsetarPensum(JTextField nombre, JTextField num, JTextField codigo) { //******

        codigo.setText("");

        setNombre(nombre.getText()); //******
        setNumeroSemestres(num.getText());//******
        setCodigoAcademico(generarCodigo());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "INSERT INTO Pensum (codigoAcademico,nombre, semestres, estado) VALUES (?,?,?,?);";//******
        
        Object[] opciones = {"Sí", "No"};
        
        int respuesta = JOptionPane.showOptionDialog(null, 
                "¿Estás seguro de que quieres CREAR un PENSUM?", "Confirmación", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());//******
            cs.setString(2, getNombre());//******
            cs.setString(3, getNumeroSemestres());//******
            cs.setString(4, getEstadoAcademico());//******

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
        String codigo = "";

        BuilderPensum a = new BuilderPensum();
        Director b = new Director();

        b.construirCodigoAcademico(a);

        CodigoPensum codig = a.getResult();

        codigo = codig.pasarAString();

        //----------------------------------
        return codigo;
    }

    public void visualizarPensum(JTable tablaPen, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaPen.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Numero de Semestres");//******
        modelo.addColumn("Programa Asignado");//******

        tablaPen.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_pensums WHERE vista_pensums.estado ='ACTIVADO' ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_pensums WHERE vista_pensums.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND vista_pensums.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_pensums WHERE vista_pensums.nombre LIKE '%" + valor + "%'"
                            + "AND vista_pensums.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * from vista_pensums WHERE COALESCE(ProgramaAcademico, 'null') LIKE '%" + valor + "%'"
                                + "AND vista_pensums.estado ='ACTIVADO'";//*********

                    } else {

                        sql = "SELECT * FROM vista_pensums WHERE vista_pensums.estado ='ACTIVADO'";//******

                    }
                }
            }
        }

        String[] datos = new String[4];
        Statement st;

        try {

            st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);//******
                datos[1] = rs.getString(2);//******
                datos[2] = rs.getString(3);//******
                datos[3] = rs.getString(4);//******

                if (datos[3] == null) {

                    datos[3] = "Sin Asignacion";

                }

                modelo.addRow(datos);

            }

            tablaPen.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txcod, JTextField txnom, JTextField txnum) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txcod.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 1).toString());//******
                txnum.setText(tabla.getValueAt(fila, 2).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarPensum(JTextField txcod, JTextField txnom, JTextField txnum) {

        setCodigoAcademico(txcod.getText());//******
        setNombre(txnom.getText());//******
        setNumeroSemestres(txnum.getText());//******

        Conexion co = new Conexion();

        String consulta = "UPDATE Pensum SET Pensum.nombre = ?, "
                + "Pensum.semestres = ? WHERE Pensum.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getNombre());//******
            cs.setString(2, getNumeroSemestres());//******
            cs.setString(3, getCodigoAcademico());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarPensum(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Pensum SET Pensum.estado = ? WHERE Pensum.codigoAcademico =?;";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, getCodigoAcademico());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro DESACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo DESACTIVAR correctamente: " + e);
        }

    }

    public void visualizarPensumDesactivado(JTable tablaPen, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaPen.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Numero de Semestres");//******
        modelo.addColumn("Programa Asignado");//******

        tablaPen.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_pensums WHERE vista_pensums.estado ='DESACTIVADO' ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_pensums WHERE vista_pensums.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND vista_pensums.estado='DESACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_pensums WHERE vista_pensums.nombre LIKE '%" + valor + "%'"
                            + "AND vista_pensums.estado='DESACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * from vista_pensums WHERE COALESCE(ProgramaAcademico, 'null') LIKE '%" + valor + "%'"
                                + "AND vista_pensums.estado='DESACTIVADO'";//*********

                    } else {

                        sql = "SELECT * FROM vista_pensums WHERE vista_pensums.estado ='DESACTIVADO' ";//******

                    }
                }
            }
        }

        String[] datos = new String[4];
        Statement st;

        try {

            st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);//******
                datos[1] = rs.getString(2);//******
                datos[2] = rs.getString(3);//******
                datos[3] = rs.getString(4);//******

                if (datos[3] == null) {

                    datos[3] = "Sin Asignacion";

                }

                modelo.addRow(datos);

            }

            tablaPen.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void activarPensum(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Pensum SET Pensum.estado = ? WHERE Pensum.codigoAcademico =?;";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, getCodigoAcademico());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro ACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo DESACTIVAR correctamente: " + e);
        }

    }

    public void asignarPrograma(JTextField codtx1, JTextField codtx2) {

        ProgramaAcademico programa = new ProgramaAcademico();

        setCodigoAcademico(codtx1.getText());//******
        setPrograma(programa);
        this.programa.setCodigoAcademico(codtx2.getText());

        Conexion co = new Conexion();

        String sql = "SELECT id FROM ProgramaAcademico WHERE ProgramaAcademico.codigoAcademico = '" + this.programa.getCodigoAcademico() + "'";

        
        
        try {

            Statement st;

            st = co.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            rs.next();
            this.programa.setIdInterno(rs.getInt("id"));

        } catch (Exception e) {
        }

//        String consulta1 = "UPDATE Pensum"
//                + "JOIN ProgramaAcademico ON Pensum.id_programa = ProgramaAcademico.id"
//                + "SET Pensum.id_programa = "+this.programa.getIdInterno()+""
//                + "WHERE Pensum.codigoAcademico = '"+getCodigoAcademico()+"';";//******
        
        System.out.println(this.programa.getIdInterno());

        String consulta = "UPDATE Pensum SET Pensum.id_programa = "+this.programa.getIdInterno()+" "
                + "WHERE Pensum.codigoAcademico = '"+getCodigoAcademico()+"';";

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            
            cs.execute();

            JOptionPane.showMessageDialog(null, "Programa ASIGNADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo ASIGNAR correctamente: " + e);
        }

    }

}
