/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author JOSE SANDOVAL
 */
public class Adminstrador {

    public static void main(String[] args) {
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Obtiene el año actual
        String year = String.valueOf(fechaActual.getYear());
        String month = String.valueOf(fechaActual.getMonthValue());
        

        // Muestra el año actual
        
        
        
        String a = year + month;
       System.out.println("Año actual: " + a);

    }

}
