
import BD.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author JOSE SANDOVAL
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con = new Conexion();
        Statement st;
        
        String sql= "SELECT semestre from SemestreAcademico WHERE SemestreAcademico.id=1;";
        
        
        
            try {
                
                st = con.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);
            
            String a= rs.getString(sql);
                System.out.println(a);
        } catch (Exception e) {
        }
            
    }
    
}
