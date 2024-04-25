/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodigoAcademico;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author JOSE SANDOVAL
 */
public class Director {
    
    public void construirCodigoAcademico(CodigoBuilder cod){
    
        LocalDate date = LocalDate.now();
        Random r = new Random();
        
        cod.darAño(String.valueOf(date.getYear()));
        cod.darNumerosFinales(String.valueOf(r.nextInt(801)));
        
    
    }
    
}
