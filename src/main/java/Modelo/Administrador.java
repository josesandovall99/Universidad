/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderAdministradores;
import CodigoAcademico.BuilderEstudiantes;
import CodigoAcademico.CodigoAdministradores;
import CodigoAcademico.CodigoEstudiantes;
import CodigoAcademico.Director;
import Vista.Administrador.CompletarAdministrador;
import Vista.Administrador.Principal;
import Vista.Administrador.Sesion;
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
public class Administrador {

    public int id;
    public String codigoAcademico;
    public String nombre;
    public String email;
    public String telefono;
    public String direccion;
    public String tipo;
    public String puesto;
    public String contraseña;
    public String estadoAcademico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    public void InsetarAdministrador(JTextField nombre, JTextField email, JTextField tel, JTextField dir) { //******

        setNombre(nombre.getText()); //******
        setEmail(email.getText());//******
        setTelefono(tel.getText());
        setDireccion(dir.getText());
        setEstadoAcademico("ACTIVADO");
        setTipo("administrador");

        Conexion co = new Conexion();

        String consulta = "INSERT INTO Usuario (nombre, email, telefono, direccion, tipoUsuario,estado) VALUES (?,?,?,?,?,?);";//******

        Object[] opciones = {"Sí", "No"};

        // Mostrar un cuadro de diálogo de confirmación con botones en español
        int respuesta = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres CREAR un ADMINISTRADOR?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {

                CallableStatement cs = co.establecerConexion().prepareCall(consulta);
                cs.setString(1, getNombre());//******
                cs.setString(2, getEmail());//******
                cs.setString(3, getTelefono());//******
                cs.setString(4, getDireccion());//******
                cs.setString(5, getTipo());//******
                cs.setString(6, getEstadoAcademico());//******

                cs.execute();

                JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "error al insertar: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Inercion Cancelada");

        }

    }

    public String generarCodigo() {

        //PATRON DE DISEÑO------------------
        String codigo = "";

        BuilderAdministradores a = new BuilderAdministradores();
        Director b = new Director();

        b.construirCodigoAcademico(a);

        CodigoAdministradores codig = a.getResult();

        codigo = codig.pasarAString();

        //----------------------------------
        return codigo;
    }

    public void visualizarAdministrador(JTable tablaad, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaad.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("id interno");//******
        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("email");//******
        modelo.addColumn("telefono");//******
        modelo.addColumn("Direccion");//******
        modelo.addColumn("puesto");//******

        tablaad.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_administradores WHERE vista_administradores.estado = 'ACTIVADO' ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_administradores WHERE vista_administradores.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND vista_administradores.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_administradores WHERE vista_administradores.nomusuario LIKE '%" + valor + "%'"
                            + "AND vista_administradores.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_administradores WHERE vista_administradores.puesto LIKE '%" + valor + "%'"
                                + "AND vista_administradores.estado ='ACTIVADO'";//******

                    } else {

                        sql = "SELECT * FROM vista_administradores WHERE vista_administradores.estado = 'ACTIVADO' ";//******

                    }
                }
            }
        }

        String[] datos = new String[7];
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
                datos[5] = rs.getString(6);//******
                datos[6] = rs.getString(7);//******

                if (datos[1] == null) {

                    datos[1] = "Sin Asignar";

                }
                if (datos[6] == null) {

                    datos[6] = "Sin Especificar";

                }

                modelo.addRow(datos);

            }

            tablaad.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionar(JTable tabla, JTextField txid, JTextField txnom, JTextField txemail, JTextField txtel, JTextField txdir) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txid.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 2).toString());//******
                txemail.setText(tabla.getValueAt(fila, 3).toString());//******
                txtel.setText(tabla.getValueAt(fila, 4).toString());//******
                txdir.setText(tabla.getValueAt(fila, 5).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void modificarAdministrador(JTextField txid, JTextField txnom, JTextField txemail, JTextField txtel, JTextField txdir) {

        setNombre(txnom.getText());//******
        setEmail(txemail.getText());//******
        setTelefono(txtel.getText());//******
        setDireccion(txdir.getText());//******
        setId(Integer.parseInt(txid.getText()));

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.nombre = ?, "
                + "Usuario.email = ?, Usuario.telefono = ?, Usuario.direccion = ? WHERE Usuario.id=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);

            cs.setString(1, getNombre());//******
            cs.setString(2, getEmail());//******
            cs.setString(3, getTelefono());//******
            cs.setString(4, getDireccion());//******
            cs.setString(5, String.valueOf(getId()));//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "modificacion exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar: " + e);
        }

    }

    public void eliminarAdministrador(JTextField codtx) {

        setId(Integer.parseInt(codtx.getText()));//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.estado=? WHERE Usuario.id=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, String.valueOf(getId()));
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro DESACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
        }

    }

    public void recibirContraseñaAdministrador(JTextField contraseña, JTextField idtxt) { //******

        setContraseña(contraseña.getText()); //******
        setId(Integer.parseInt(idtxt.getText()));

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.contraseña = ? "
                + "WHERE Usuario.id=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getContraseña());//******
            cs.setString(2, String.valueOf(getId()));//******

            cs.execute();

            JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }

    }

    public void completarAdministrador(String combo, JTextField idtxt) { //******

        setPuesto(combo); //******
        setCodigoAcademico(generarCodigo());
        setId(Integer.parseInt(idtxt.getText()));

        Conexion co = new Conexion();

        String consulta = "UPDATE Administrador SET Administrador.puesto = ?, Administrador.codigoAcademico = ? "
                + "WHERE Administrador.id_usuario=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getPuesto());//******
            cs.setString(2, getCodigoAcademico());//******
            cs.setString(3, String.valueOf(getId()));//******

            cs.execute();

            Principal ad = new Principal();
            ad.setVisible(true);
            CompletarAdministrador s = new CompletarAdministrador();
            s.dispose();

            JOptionPane.showMessageDialog(null, "SE CREO CORRECTAMENTE");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }

    }

    public void visualizarDesactivadosAdministrador(JTable tablaad, int opbuscar, String valor) { //******

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaad.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("id interno");//******
        modelo.addColumn("Codigo Academico");//******
        modelo.addColumn("Nombre");//******
        modelo.addColumn("email");//******
        modelo.addColumn("telefono");//******
        modelo.addColumn("Direccion");//******
        modelo.addColumn("puesto");//******

        tablaad.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_administradores WHERE vista_administradores.estado = 'DESACTIVADO' ";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_administradores WHERE vista_administradores.codigoAcademico LIKE '%" + valor + "%' "
                        + "AND vista_administradores.estado = 'DESACTIVADO' ";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_administradores WHERE vista_administradores.nomusuario LIKE '%" + valor + "%'"
                            + "AND vista_administradores.estado = 'DESACTIVADO' ";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_administradores WHERE vista_administradores.puesto LIKE '%" + valor + "%'"
                                + "AND vista_administradores.estado = 'DESACTIVADO' ";//******

                    } else {

                        sql = "SELECT * FROM vista_administradores WHERE vista_administradores.estado = 'DESACTIVADO' ";//******

                    }
                }
            }
        }

        String[] datos = new String[7];
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
                datos[5] = rs.getString(6);//******
                datos[6] = rs.getString(7);//******

                if (datos[1] == null) {

                    datos[1] = "Sin Asignar";

                }
                if (datos[6] == null) {

                    datos[6] = "Sin Especificar";

                }

                modelo.addRow(datos);

            }

            tablaad.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void seleccionarDesactivados(JTable tabla, JTextField txid, JTextField txnom, JTextField txemail, JTextField txtel, JTextField txdir) {//******

        try {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                txid.setText(tabla.getValueAt(fila, 0).toString());//******
                txnom.setText(tabla.getValueAt(fila, 2).toString());//******
                txemail.setText(tabla.getValueAt(fila, 3).toString());//******
                txtel.setText(tabla.getValueAt(fila, 4).toString());//******
                txdir.setText(tabla.getValueAt(fila, 5).toString());//******

            } else {
                JOptionPane.showMessageDialog(null, "Registro no seleccionado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al seleccionar: " + e);

        }

    }

    public void activarAdministrador(JTextField codtx) {

        setId(Integer.parseInt(codtx.getText()));//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.estado=? WHERE Usuario.id=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getEstadoAcademico());
            cs.setString(2, String.valueOf(getId()));
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro ACTIVADO correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo eliminar correctamente: " + e);
        }

    }

}
