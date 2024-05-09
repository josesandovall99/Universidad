/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import Vista.Administrador.Administradores;
import Vista.Administrador.CompletarAdministrador;
import Vista.Profesor.PrincipalProfesor;
import Vista.Administrador.Principal;
import Vista.Administrador.Sesion;
import Vista.Profesor.CompletarProfesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author JOSE SANDOVAL
 */
public class Login {

    public String codigo;
    public String contraseña;
    public String id;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void validar(JTextField usuario, JPasswordField contraseña) {

        setCodigo(usuario.getText());
        setContraseña(contraseña.getText());

        if (!getCodigo().equals("") || !getContraseña().equals("")) {

            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();

                PreparedStatement ps = cn.prepareStatement("SELECT usuariotip FROM "
                        + "credencialesAdministradores where codigoAcademico = '" + getCodigo() + "' AND contraseña = '" + getContraseña() + "' ");

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");

                    String tipo = rs.getString("usuariotip");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("administrador")) {

                        Principal ad = new Principal();
                        ad.setVisible(true);
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.dispose();

                    } else {

                        JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");
        }

    }

    public void validarSinCredenciales(JTextField usuario) {

        setId(usuario.getText());

        
        
        if (!getId().equals("")) {

            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();

                PreparedStatement ps = cn.prepareStatement("SELECT tipoUsuario FROM "
                        + "Usuario where id = '" + getId() + "'");

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");

                    String tipo = rs.getString("tipoUsuario");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("administrador")) {

                        CompletarAdministrador ad = new CompletarAdministrador();
                        ad.setVisible(true);
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.setVisible(false);

                    } else {

                        JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");
        }

    }
    
    
    public void a(){
    
    Object[] opciones = {"Sí", "No"};
        
        // Mostrar un cuadro de diálogo de confirmación con botones en español
        int respuesta = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            System.out.println("El usuario ha confirmado.");
            // Aquí puedes poner el código que quieres ejecutar si el usuario confirma
        } else {
            System.out.println("El usuario ha cancelado.");
            // Aquí puedes poner el código que quieres ejecutar si el usuario cancela
        }
    
    }
    
    
    public boolean verificar_Email (String correo){
    
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher coincidir = patron.matcher(correo);
        
        return coincidir.find();
    
    }

    
        public void validarProfesor(JTextField usuario, JPasswordField contraseña) {

        setCodigo(usuario.getText());
        setContraseña(contraseña.getText());

        if (!getCodigo().equals("") || !getContraseña().equals("")) {

            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();

                PreparedStatement ps = cn.prepareStatement("SELECT usuariotip FROM "
                        + "credencialesProfesores where codigoAcademico = '" + getCodigo() + "' AND contraseña = '" + getContraseña() + "' ");

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");

                    String tipo = rs.getString("usuariotip");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("profesor")) {

                        PrincipalProfesor ad = new PrincipalProfesor(String.valueOf(getCodigo()));
                        ad.setVisible(true);
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.dispose();

                    } else {

                        JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");
        }

    }

    public void validarSinCredencialesProfesor(JTextField usuario) {

        setId(usuario.getText());

        
        
        if (!getId().equals("")) {

            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();

                PreparedStatement ps = cn.prepareStatement("SELECT tipoUsuario FROM "
                        + "Usuario where id = '" + getId() + "'");

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");

                    String tipo = rs.getString("tipoUsuario");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("profesor")) {

                        CompletarProfesor ad = new CompletarProfesor(getId());
                        ad.setVisible(true);
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.setVisible(false);

                    } else {

                        JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");
        }

    }
    
    
    
}
