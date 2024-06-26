/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderAdministradores;
import CodigoAcademico.BuilderAsignaturas;
import CodigoAcademico.CodigoAdministradores;
import CodigoAcademico.CodigoAsignaturas;
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
public class Asignatura {

    public String codigoAcademico;
    public String nombre;
    public String creditos;
    public String tipo;
    public String estadoAcademico;
    public int idInterno;
    public Pensum pensum;

    public int getIdInterno() {
        return idInterno;
    }

    public Pensum getPensum() {
        return pensum;
    }

    public void setPensum(Pensum pensum) {
        this.pensum = pensum;
    }

    
    
    
    public void setIdInterno(int idInterno) {
        this.idInterno = idInterno;
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

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    public void InsetarAsignatura(JTextField nombre, JTextField cred, JTextField tip, JTextField cod) { //******

        cod.setText("");

        setNombre(nombre.getText()); //******
        setCreditos(cred.getText());//******
        setTipo(tip.getText());
        setCodigoAcademico(generarCodigo());//******
        setEstadoAcademico("ACTIVADO");//******

        Conexion co = new Conexion();

        String consulta = "INSERT INTO Asignatura (codigoAcademico,nombre, creditos, tipo, estado) VALUES (?,?,?,?,?);";//******

        Object[] opciones = {"Sí", "No"};
        
        int respuesta = JOptionPane.showOptionDialog(null, 
                "¿Estás seguro de que quieres CREAR una ASIGNATURA?", "Confirmación", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
        
        
        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());//******
            cs.setString(2, getNombre());//******
            cs.setString(3, getCreditos());//******
            cs.setString(4, getTipo());//******
            cs.setString(5, getEstadoAcademico());//******

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

        BuilderAsignaturas a = new BuilderAsignaturas();
        Director b = new Director();

        b.construirCodigoAcademico(a);

        CodigoAsignaturas codig = a.getResult();

        codigo = codig.pasarAString();

        //----------------------------------
        return codigo;
    }

    public void visualizarAsignatura(JTable tablaA, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaA.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Numero de Creditos");//******
        modelo.addColumn("Tipo de Asignatura");//******
        modelo.addColumn("Pensum Asignado");//******

        tablaA.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.estado = 'ACTIVADO';";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.nombre LIKE '%" + valor + "%'"
                        + "AND vista_asignaturas.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.creditos LIKE '%" + valor + "%'"
                            + "AND vista_asignaturas.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.tipo LIKE '%" + valor + "%'"
                                + "AND vista_asignaturas.estado ='ACTIVADO'";//******

                    } else {

                        if (opbuscar == 4 && valor != null) {

                            sql = "SELECT * from vista_asignatura WHERE COALESCE(pensum, 'null') LIKE '%" + valor + "%'"
                                    + "AND vista_asignaturas.estado ='ACTIVADO'";//*********

                        } else {

                            sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.estado='ACTIVADO'";//******

                        }
                    }
                }
            }
        }

        String[] datos = new String[5];
        Statement st;

        try {

            st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);//******
                datos[1] = rs.getString(2);//******
                datos[2] = rs.getString(3);//******
                datos[3] = rs.getString(4);//******
                datos[4] = rs.getString(5);//******

                if (datos[4] == null) {

                    datos[4] = "Sin Asignacion";

                }

                modelo.addRow(datos);

            }

            tablaA.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txcod, JTextField txnom, JTextField txcred, JTextField txtip) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txcod.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 1).toString());//******
                txcred.setText(tabla.getValueAt(fila, 2).toString());//******
                txtip.setText(tabla.getValueAt(fila, 3).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarAsignatura(JTextField txcod, JTextField txnom, JTextField txcred, JTextField txtip) {

        setCodigoAcademico(txcod.getText());//******
        setNombre(txnom.getText());//******
        setCreditos(txcred.getText());//******
        setTipo(txtip.getText());//******

        Conexion co = new Conexion();

        String consulta = "UPDATE Asignatura SET Asignatura.nombre = ?, "
                + "Asignatura.creditos = ?, Asignatura.tipo = ? WHERE Asignatura.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getNombre());//******
            cs.setString(2, getCreditos());//******
            cs.setString(3, getTipo());//******
            cs.setString(4, getCodigoAcademico());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarAsignatura(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Asignatura SET Asignatura.estado=? WHERE Asignatura.codigoAcademico =?;";//******

        Object[] opciones = {"Sí", "No"};

        // Mostrar un cuadro de diálogo de confirmación con botones en español
        int respuesta = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres Desactivar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {

                CallableStatement cs = co.establecerConexion().prepareCall(consulta);
                cs.setString(1, getEstadoAcademico());
                cs.setString(2, getCodigoAcademico());
                cs.execute();

                JOptionPane.showMessageDialog(null, "Registro DESACTIVADO correctamente");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Se cancelo la Desactivacion");
        }

    }

    public void activarAsignatura(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Asignatura SET Asignatura.estado=? WHERE Asignatura.codigoAcademico =?;";//******

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

    public void visualizarAsignaturaDesactivadas(JTable tablaA, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaA.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Numero de Creditos");//******
        modelo.addColumn("Tipo de Asignatura");//******
        modelo.addColumn("Pensum Asignado");//******

        tablaA.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.estado = 'DESACTIVADO'";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.nombre LIKE '%" + valor + "%'"
                        + "AND vista_asignaturas.estado='DESACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.creditos LIKE '%" + valor + "%'"
                            + "AND vista_asignaturas.estado='DESACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.tipo LIKE '%" + valor + "%'"
                                + "AND vista_asignaturas.estado='DESACTIVADO'";//******

                    } else {

                        if (opbuscar == 4 && valor != null) {

                            sql = "SELECT * from vista_asignatura WHERE COALESCE(pensum, 'null') LIKE '%" + valor + "%'"
                                    + "AND vista_asignaturas.estado='DESACTIVADO'";//*********

                        } else {

                            sql = "SELECT * FROM vista_asignaturas WHERE vista_asignaturas.estado='DESACTIVADO'";//******

                        }
                    }
                }
            }

            String[] datos = new String[5];
            Statement st;

            try {

                st = con.establecerConexion().createStatement();

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    datos[0] = rs.getString(1);//******
                    datos[1] = rs.getString(2);//******
                    datos[2] = rs.getString(3);//******
                    datos[3] = rs.getString(4);//******
                    datos[4] = rs.getString(5);//******

                    if (datos[4] == null) {

                        datos[4] = "Sin Asignacion";

                    }

                    modelo.addRow(datos);

                }

                tablaA.setModel(modelo);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

            }

        }
    }
    
    public void asignarPensum(JTextField codtx1, JTextField codtx2) {

        Pensum pensum = new Pensum();

        setCodigoAcademico(codtx1.getText());//******
        setPensum(pensum);
        this.pensum.setCodigoAcademico(codtx2.getText());

        Conexion co = new Conexion();

        String sql = "SELECT id FROM Pensum WHERE Pensum.codigoAcademico = '" + this.pensum.getCodigoAcademico() + "'";

        try {

            Statement st;

            st = co.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            rs.next();
            this.pensum.setIdInterno(rs.getInt("id"));

        } catch (Exception e) {
        }

//        String consulta1 = "UPDATE Pensum"
//                + "JOIN ProgramaAcademico ON Pensum.id_programa = ProgramaAcademico.id"
//                + "SET Pensum.id_programa = "+this.programa.getIdInterno()+""
//                + "WHERE Pensum.codigoAcademico = '"+getCodigoAcademico()+"';";//******
        System.out.println(this.pensum.getIdInterno());

        String consulta = "UPDATE Asignatura SET Asignatura.id_pensum = " + this.pensum.getIdInterno() + " "
                + "WHERE Asignatura.codigoAcademico = '" + getCodigoAcademico() + "';";

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.execute();

            JOptionPane.showMessageDialog(null, "Pensum ASIGNADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo ASIGNAR correctamente: " + e);
        }

    }
}
