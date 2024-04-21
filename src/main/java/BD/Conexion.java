/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSE SANDOVAL
 */
public class Conexion {

    Connection conectar = null;

    String usuario = "root";
    String contraseña = "";
    String bd = "universidad";
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "CONEXION ESTABLECIDA CORRECTAMENTE");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error  al conectarse a la bd" + e.toString());
        }

        return conectar;

    }
}
