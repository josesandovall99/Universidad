/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
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

    public void InsetarPensum(JTextField nombre, JTextField salT, JTextField salP, JTextField cod) { //******

        cod.setText("");

        setNombre(nombre.getText()); //******
        setSalonT(salT.getText());//******
        setSalonP(salP.getText());
        setCodigoAcademico(generarCodigo());//******

        Conexion co = new Conexion();

        String consulta = "INSERT INTO Curso (codigoAcademico,nombre, salonT, salonP) VALUES (?,?,?,?);";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());//******
            cs.setString(2, getNombre());//******
            cs.setString(3, getSalonT());//******
            cs.setString(4, getSalonP());//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }

    }

    public String generarCodigo() {

        Random r = new Random();
        LocalDate date = LocalDate.now();
        String codigo = "cuc" + 04 + date.getYear() + r.nextInt(501);//******
        System.out.println(codigo);

        return codigo;
    }

    public void visualizarPensum(JTable tablaCur, int opbuscar, String valor) { //******

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

            sql = "SELECT * FROM vista_cursos ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_cursos WHERE vista_cursos.codigoAcademico LIKE '%" + valor + "%'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_cursos WHERE vista_cursos.nombre LIKE '%" + valor + "%'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * from vista_cursos WHERE COALESCE(ProgramaAcademico, 'null') LIKE '%" + valor + "%'";//*********

                    } else {

                        if (opbuscar == 4 && valor != null) {

                            sql = "SELECT * from vista_cursos WHERE COALESCE(ProgramaAcademico, 'null') LIKE '%" + valor + "%'";//*********

                        } else {

                            sql = "SELECT * FROM vista_cursos";//******

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

                tablaCur.setModel(modelo);

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

        Conexion co = new Conexion();

        String consulta = "DELETE FROM Pensum WHERE Pensum.codigoAcademico=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigoAcademico());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
        }

    }

}
