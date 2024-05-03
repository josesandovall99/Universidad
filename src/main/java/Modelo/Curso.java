/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderAsignaturas;
import CodigoAcademico.BuilderCursos;
import CodigoAcademico.CodigoAsignaturas;
import CodigoAcademico.CodigoCursos;
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
public class Curso {

    public String codigoAcademico;
    public String nombre;
    public String salonT;
    public String salonP;
    public String estadoAcademico;
    public Asignatura asignatura;

    public String getCodigoAcademico() {
        return codigoAcademico;
    }

    public void setCodigoAcademico(String codigoAcademico) {
        this.codigoAcademico = codigoAcademico;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalonT() {
        return salonT;
    }

    public void setSalonT(String salonT) {
        this.salonT = salonT;
    }

    public String getSalonP() {
        return salonP;
    }

    public void setSalonP(String salonP) {
        this.salonP = salonP;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    public void InsetarCurso(JTextField nombre, JTextField salT, JTextField salP, JTextField cod) { //******

        cod.setText("");

        setNombre(nombre.getText()); //******
        setSalonT(salT.getText());//******
        setSalonP(salP.getText());
        setCodigoAcademico(generarCodigo());//******
        setEstadoAcademico("ACTIVADO");//******

        Conexion co = new Conexion();

        String consulta = "INSERT INTO Curso (codigoAcademico,nombre, salonT, salonP, estado) VALUES (?,?,?,?,?);";//******
        
        Object[] opciones = {"Sí", "No"};
        
        int respuesta = JOptionPane.showOptionDialog(null, 
                "¿Estás seguro de que quieres CREAR un CURSO?", "Confirmación", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
        

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());//******
            cs.setString(2, getNombre());//******
            cs.setString(3, getSalonT());//******
            cs.setString(4, getSalonP());//******
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
            
        BuilderCursos a = new BuilderCursos();
        Director b = new Director();

        b.construirCodigoAcademico(a);

        CodigoCursos codig = a.getResult();

        codigo = codig.pasarAString();

        //----------------------------------
        return codigo;
    }

    public void visualizarCurso(JTable tablaCur, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaCur.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Salon Teorico");//******
        modelo.addColumn("Salon Practico");//******
        modelo.addColumn("profesor");//******

        tablaCur.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {
            System.out.println("ccc");
            sql = "SELECT * FROM vista_cursos WHERE vista_cursos.estado = 'ACTIVADO'; ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_cursos WHERE vista_cursos.nombre LIKE '%" + valor + "%'"
                        + "AND vista_cursos.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_cursos WHERE vista_cursos.salonT LIKE '%" + valor + "%'"
                            + "AND vista_cursos.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_cursos WHERE vista_cursos.salonP LIKE '%" + valor + "%'"
                                + "AND vista_cursos.estado ='ACTIVADO'";//******

                    } else {

                        if (opbuscar == 4 && valor != null) {

                            sql = "SELECT * from vista_cursos WHERE COALESCE(Usuario_nombre, 'null') LIKE '%" + valor + "%'"
                                    + "AND vista_cursos.estado ='ACTIVADO'";//*********

                        } else {

                            sql = "SELECT * FROM vista_cursos WHERE vista_cursos.estado = 'ACTIVADO'";//******

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

            tablaCur.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txcod, JTextField txnom, JTextField txsalt, JTextField txsalp) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txcod.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 1).toString());//******
                txsalt.setText(tabla.getValueAt(fila, 2).toString());//******
                txsalp.setText(tabla.getValueAt(fila, 3).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarCurso(JTextField txcod, JTextField txnom, JTextField txsalt, JTextField txsalp) {

        setCodigoAcademico(txcod.getText());//******
        setNombre(txnom.getText());//******
        setSalonT(txsalt.getText());//******
        setSalonP(txsalp.getText());//******

        Conexion co = new Conexion();

        String consulta = "UPDATE Curso SET Curso.nombre = ?, "
                + "Curso.salonT = ?, Curso.salonP = ? WHERE Curso.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getNombre());//******
            cs.setString(2, getSalonT());//******
            cs.setString(3, getSalonP());//******
            cs.setString(4, getCodigoAcademico());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarCurso(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Curso SET Curso.estado=? WHERE Curso.codigoAcademico = ?";//******

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

    public void activarCurso(JTextField codtx) {

        setCodigoAcademico(codtx.getText());//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Curso SET Curso.estado=? WHERE Curso.codigoAcademico = ?";//******

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

    public void visualizarCursoDesactivado(JTable tablaCur, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaCur.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("Salon Teorico");//******
        modelo.addColumn("Salon Practico");//******
        modelo.addColumn("profesor");//******

        tablaCur.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_cursos WHERE vista_cursos.estado = 'DESACTIVADO' ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_cursos WHERE vista_cursos.nombre LIKE '%" + valor + "%'"
                        + "AND vista_cursos.estado ='DESACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_cursos WHERE vista_cursos.salonT LIKE '%" + valor + "%'"
                            + "AND vista_cursos.estado ='DESACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_cursos WHERE vista_cursos.salonP LIKE '%" + valor + "%'"
                                + "AND vista_cursos.estado ='DESACTIVADO'";//******

                    } else {

                        if (opbuscar == 4 && valor != null) {

                            sql = "SELECT * from vista_cursos WHERE COALESCE(Usuario_nombre, 'null') LIKE '%" + valor + "%'"
                                    + "AND vista_cursos.estado ='DESACTIVADO'";//*********

                        } else {

                            sql = "SELECT * FROM vista_cursos WHERE vista_cursos.estado = 'DESACTIVADO'";//******

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

                tablaCur.setModel(modelo);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

            }

        }
    }

    public void asignarAsignatura(JTextField codtx1, JTextField codtx2) {

        Asignatura asignatura = new Asignatura();

        setCodigoAcademico(codtx1.getText());//******
        setAsignatura(asignatura);
        this.asignatura.setCodigoAcademico(codtx2.getText());

        Conexion co = new Conexion();

        String sql = "SELECT id FROM Asignatura WHERE Asignatura.codigoAcademico = '" + this.asignatura.getCodigoAcademico() + "'";

        try {

            Statement st;

            st = co.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            rs.next();
            this.asignatura.setIdInterno(rs.getInt("id"));

        } catch (Exception e) {
        }

//        String consulta1 = "UPDATE Pensum"
//                + "JOIN ProgramaAcademico ON Pensum.id_programa = ProgramaAcademico.id"
//                + "SET Pensum.id_programa = "+this.programa.getIdInterno()+""
//                + "WHERE Pensum.codigoAcademico = '"+getCodigoAcademico()+"';";//******
        System.out.println(this.asignatura.getIdInterno());

        String consulta = "UPDATE Curso SET Curso.id_asignatura = " + this.asignatura.getIdInterno() + " "
                + "WHERE Curso.codigoAcademico = '" + getCodigoAcademico() + "';";

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.execute();

            JOptionPane.showMessageDialog(null, "Asignatura ASIGNADA correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo ASIGNAR correctamente: " + e);
        }

    }

}
