
import BD.Conexion;
import CodigoAcademico.BuilderEstudiantes;
import CodigoAcademico.CodigoEstudiantes;
import CodigoAcademico.Director;
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
        
        
        //PATRON DE DISEÑO------------------
        
        String codigo;
        
        BuilderEstudiantes a = new BuilderEstudiantes();
        Director b = new Director();
        
        b.construirCodigoAcademico(a);
        
        CodigoEstudiantes codig = a.getResult();
        
        System.out.println(codig.pasarAString());
        
        //----------------------------------
        
    }
    
}
