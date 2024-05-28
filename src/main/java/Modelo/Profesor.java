/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import CodigoAcademico.BuilderAdministradores;
import CodigoAcademico.BuilderProfesores;
import CodigoAcademico.CodigoAdministradores;
import CodigoAcademico.CodigoProfesores;
import CodigoAcademico.Director;
import GeneracionDeCorreos.Aplicacion;
import GeneracionDeCorreos.CorreoAdministradorFabrica;
import GeneracionDeCorreos.CorreoProfesorFabrica;
import GeneracionDeCorreos.CorreosFabrica;
import Vista.Administrador.CompletarAdministrador;
import Vista.Estudiante.CompletarEstudiante;
import Vista.Profesor.CompletarProfesor;
import Vista.Profesor.PrincipalProfesor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class Profesor {

    public int id;
    public String codigoAcademico;
    public String nombre;
    public String email;
    public String telefono;
    public String direccion;
    public String tipo;
    public String maximoTitulo;
    public String especialidad;
    public String contraseña;
    public String estadoAcademico;

    public Profesor() {
    }

    public Profesor(int id, String codigoAcademico, String nombre, String email, String telefono, String direccion, String tipo, String maximoTitulo, String especialidad, String contraseña) {
        this.id = id;
        this.codigoAcademico = codigoAcademico;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipo = tipo;
        this.maximoTitulo = maximoTitulo;
        this.especialidad = especialidad;
        this.contraseña = contraseña;
    }

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

    public String getMaximoTitulo() {
        return maximoTitulo;
    }

    public void setMaximoTitulo(String maximoTitulo) {
        this.maximoTitulo = maximoTitulo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public void InsetarProfesor(JTextField nombre, JTextField email, JTextField tel, JTextField dir) { //******

        setNombre(nombre.getText());
        setEmail(email.getText());
        setTelefono(tel.getText());
        setDireccion(dir.getText());
        setEstadoAcademico("ACTIVADO");
        setTipo("profesor");

        Conexion co = new Conexion();
        String consulta = "INSERT INTO Usuario (nombre, email, telefono, direccion, tipoUsuario, estado) VALUES (?,?,?,?,?,?)";

        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres CREAR un PROFESOR?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                Connection conn = co.establecerConexion();
                PreparedStatement ps = conn.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setString(1, getNombre());
                ps.setString(2, getEmail());
                ps.setString(3, getTelefono());
                ps.setString(4, getDireccion());
                ps.setString(5, getTipo());
                ps.setString(6, getEstadoAcademico());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        JOptionPane.showMessageDialog(null, "SE CREÓ CORRECTAMENTE. ID generado: " + idGenerado);
                        //PATRON ABSTRAC FACTORY----------------------------------------
                        CorreosFabrica fabrica = new CorreoProfesorFabrica();
                        Aplicacion app = new Aplicacion(fabrica);
                        app.enviarCorreoId(getEmail(), idGenerado);
                        //---------------------------------------------------------------
                    }
                }

                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Inserción cancelada");
        }

    }

    public String generarCodigo() {

        //PATRON DE DISEÑO------------------
        String codigo = "";

        BuilderProfesores a = new BuilderProfesores();
        Director b = new Director();

        b.construirCodigoAcademico(a);

        CodigoProfesores codig = a.getResult();

        codigo = codig.pasarAString();

        //----------------------------------
        return codigo;
    }

    public void visualizarProfesor(JTable tablaad, int opbuscar, String valor) { //******

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
        modelo.addColumn("especialidad");//******

        tablaad.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_profesores WHERE vista_profesores.estado ='ACTIVADO'";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_profesores WHERE vista_profesores.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND vista_profesores.estado ='ACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_profesores WHERE vista_profesores.nomusuario LIKE '%" + valor + "%'"
                            + "AND vista_profesores.estado ='ACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_profesores WHERE vista_profesores.especialidad LIKE '%" + valor + "%'"
                                + "AND vista_profesores.estado ='ACTIVADO'";//******

                    } else {

                        sql = "SELECT * FROM vista_profesores WHERE vista_profesores.estado ='ACTIVADO'";//******

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

        }

    }

    public void modificarProfesor(JTextField txid, JTextField txnom, JTextField txemail, JTextField txtel, JTextField txdir) {

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

    public void eliminarProfesor(JTextField codtx) {

        setId(Integer.parseInt(codtx.getText()));//******
        setEstadoAcademico("DESACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.estado =? WHERE Usuario.id=?";//******

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

    public void recibirContraseñaProfesor(JTextField contraseña, String idtxt) { //******

        setContraseña(contraseña.getText()); //******
        setId(Integer.parseInt(idtxt));

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

    public void completarProfesor(String combo, String idtxt, JTextField esp, JFrame f) { //******

        setEspecialidad(esp.getText()); //******
        setMaximoTitulo(combo);
        setCodigoAcademico(generarCodigo());
        setId(Integer.parseInt(idtxt));

        Conexion co = new Conexion();
        
        String sql1 = "SELECT email FROM usuario WHERE usuario.id= '" + getId() + "';";

        try {
            Statement st;

            st = co.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql1);

            rs.next();
            setEmail((rs.getString("email")));

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e);
        }
        
        
        String sql2 = "SELECT contraseña FROM usuario WHERE usuario.id= '" + getId() + "';";

        try {
            Statement st;

            st = co.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql2);

            rs.next();
            setContraseña((rs.getString("contraseña")));

            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e);
        }
        
        

        String consulta = "UPDATE Profesor SET Profesor.maximoTitulo = ?, Profesor.especialidad =?, Profesor.codigoAcademico = ? "
                + "WHERE Profesor.id_usuario=?";//******

        try {

            CallableStatement cs = co.establecerConexion().prepareCall(consulta);
            cs.setString(1, getMaximoTitulo());//******
            cs.setString(2, getEspecialidad());//******
            cs.setString(3, getCodigoAcademico());//******
            cs.setString(4, String.valueOf(getId()));//******

            cs.execute();

            PrincipalProfesor ad = new PrincipalProfesor(getCodigoAcademico());
            ad.setVisible(true);
            CompletarProfesor s = new CompletarProfesor();
            s.dispose();
            f.dispose();

            //PATRON ABSTRACT FACTORY----------------------------------------
            CorreosFabrica fabrica = new CorreoProfesorFabrica();
            Aplicacion app = new Aplicacion(fabrica);
            app.enviarCorreoCredenciales(getEmail(), getCodigoAcademico(),getContraseña());
            //---------------------------------------------------------------

            JOptionPane.showMessageDialog(null, "SE COMPLETO CORRECTAMENTE");
            JOptionPane.showMessageDialog(null, "SU CODIGO ACADEMICO SERA: " + getCodigoAcademico());
            
            System.out.println(getContraseña());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al insertar: " + e);
        }

    }

    public void activarProfesor(JTextField codtx) {

        setId(Integer.parseInt(codtx.getText()));//******
        setEstadoAcademico("ACTIVADO");

        Conexion co = new Conexion();

        String consulta = "UPDATE Usuario SET Usuario.estado =? WHERE Usuario.id=?";//******

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

    public void visualizarProfesorDesactivado(JTable tablaad, int opbuscar, String valor) { //******

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
        modelo.addColumn("especialidad");//******

        tablaad.setModel(modelo);

//        sql = "SELECT codigoAcademico,año,semestre FROM SemestreAcademico";
        if (opbuscar == 0 && valor == null) {

            sql = "SELECT * FROM vista_profesores WHERE vista_profesores.estado ='DESACTIVADO'";//******
        } else {

            if (opbuscar == 1 && valor != null) {

                sql = "SELECT * FROM vista_profesores WHERE vista_profesores.codigoAcademico LIKE '%" + valor + "%'"
                        + "AND vista_profesores.estado ='DESACTIVADO'";//******

            } else {

                if (opbuscar == 2 && valor != null) {

                    sql = "SELECT * FROM vista_profesores WHERE vista_profesores.nomusuario LIKE '%" + valor + "%'"
                            + "AND vista_profesores.estado ='DESACTIVADO'";//******

                } else {

                    if (opbuscar == 3 && valor != null) {

                        sql = "SELECT * FROM vista_profesores WHERE vista_profesores.especialidad LIKE '%" + valor + "%'"
                                + "AND vista_profesores.estado ='DESACTIVADO'";//******

                    } else {

                        sql = "SELECT * FROM vista_profesores WHERE vista_profesores.estado ='DESACTIVADO'";//******

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

    public boolean verificar_Email(String correo) {

        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher coincidir = patron.matcher(correo);

        return coincidir.find();

    }

    public void verHorario(JTable tabla, String codigo) {

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Dia");//******
        modelo.addColumn("Nombre Curso");//******
        modelo.addColumn("Salon");//******
        modelo.addColumn("Hora Inicio");//******
        modelo.addColumn("Hora Finalizacion");//******

        tabla.setModel(modelo);

        sql = "SELECT dia,nombre,salonT,inicio,fin FROM vista_horarioProfesor WHERE vista_horarioProfesor.codigoAcademico = '" + codigo + "'";

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

                modelo.addRow(datos);

            }

            tabla.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

    public void verCursos(JTable tabla, String codigo) {

        Conexion con = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Nombre");//******
        modelo.addColumn("Salon Practico");//******
        modelo.addColumn("Salon Teorico");//******

        tabla.setModel(modelo);

        sql = "SELECT nombre,salonP,salonT FROM vista_cursoProfesor WHERE vista_cursoProfesor.codigoAcademico = '" + codigo + "'";

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

            tabla.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al mostrar la tabla: " + e);

        }

    }

}
