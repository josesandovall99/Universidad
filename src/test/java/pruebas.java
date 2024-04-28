
import BD.Conexion;
import CodigoAcademico.BuilderEstudiantes;
import CodigoAcademico.CodigoEstudiantes;
import CodigoAcademico.Director;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.time.LocalDate;
import javax.swing.JOptionPane;

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
//        
//        String codigo;
//        
//        BuilderEstudiantes a = new BuilderEstudiantes();
//        Director b = new Director();
//        
//        b.construirCodigoAcademico(a);
//        
//        CodigoEstudiantes codig = a.getResult();
//        
//        System.out.println(codig.pasarAString());
//        
//        System.out.println(a.getNumerosfinales());
//        System.out.println(codig.getNumerosfinales());
        
        //----------------------------------
        
        
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
    
}
