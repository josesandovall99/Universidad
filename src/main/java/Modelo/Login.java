/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import BD.Conexion;
import Vista.Administrador.Administradores;
import Vista.Administrador.Principal;
import Vista.Administrador.Sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
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
    
    
    
    public void validar(JTextField usuario, JTextField contraseña){
        
        setCodigo(usuario.getText());
        setContraseña(contraseña.getText());
        
        
        if (!getCodigo().equals("")||!getContraseña().equals("")) {
            
            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();
                
                PreparedStatement ps = cn.prepareStatement("SELECT usuariotip FROM "
                        + "credencialesAdministradores where codigoAcademico = '"+getCodigo()+"' AND contraseña = '"+getContraseña()+"' ");
                
                ResultSet rs= ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");
                    
                    String tipo = rs.getString("usuariotip");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("administrador")) {
                        
                        Principal ad = new Principal();
                        ad.setVisible(true) ;
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.dispose();
                       
                        
                    }else{
                    
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                    
                    }
                    
                }else{JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");}
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: "+e);
            }
            
        }else{JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");}
    
    
    
    }
    
    
        public void validarSinCredenciales(JTextField usuario){
        
        setId(usuario.getText());
        
        
        
        if (!getId().equals("")) {
            
            try {
                Conexion co = new Conexion();
                Connection cn = co.establecerConexion();
                
                PreparedStatement ps = cn.prepareStatement("SELECT tipoUsuario FROM "
                        + "Usuario where id = '"+getId()+"'");
                
                ResultSet rs= ps.executeQuery();
                if (rs.next()) {
                    System.out.println("entra");
                    
                    String tipo = rs.getString("tipoUsuario");
                    System.out.println(tipo);
                    if (tipo.equalsIgnoreCase("administrador")) {
                        
                        Principal ad = new Principal();
                        ad.setVisible(true) ;
                        JOptionPane.showMessageDialog(null, "DATOS CORRECTOS");
                        Sesion s = new Sesion();
                        s.setVisible(false);
                       
                        
                    }else{
                    
                    JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
                    
                    }
                    
                }else{JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");}
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION: "+e);
            }
            
        }else{JOptionPane.showMessageDialog(null, "DEBE  COMPLETAR LOS DATOS");}
    
    
    
    }
    
    
}
