/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udes.universidad;

import javax.swing.JTextField;

/**
 *
 * @author JOSE SANDOVAL
 */
public class SemestreAcademico {
    
    public String nombre;
    public String año;
    public String semestre;

    public SemestreAcademico(String nombre, String año, String semestre) {
        this.nombre = nombre;
        this.año = año;
        this.semestre = semestre;
    }

    public SemestreAcademico() {
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public void InsetarSemestre (JTextField año , JTextField semestre){
        
        setAño(año.getText());
        setSemestre(semestre.getText());
        
        
    
    
    }
    
}
