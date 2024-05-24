/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Vista.Estudiante.CompletarEstudiante;
import Vista.Estudiante.CursosEstudiante;
import Vista.Estudiante.HorarioEstudiante;
import Vista.Estudiante.NotasEstudiante;
import Vista.Estudiante.PrincipalEstudiante;
import Vista.Estudiante.SesionEstudiante;
import Vista.Estudiante.SesionSinRolEstudiante;
import javax.swing.JFrame;

/**
 *
 * @author JOSE SANDOVAL
 */
public class EstudiantesFacade {

    private CompletarEstudiante completarEstudiante;
    private CursosEstudiante cursosEstudiante;
    private HorarioEstudiante horarioEstudiante;
    private NotasEstudiante notasEstudiante;
    private PrincipalEstudiante principalEstudiante;
    private SesionEstudiante sesionEstudiante;
    private SesionSinRolEstudiante sesionSinRolEstudiante;

    public EstudiantesFacade() {
    }

    
    
    
    public void mostrarCursos(JFrame frame, String codigo) {

        this.cursosEstudiante = new CursosEstudiante(codigo);
        this.cursosEstudiante.setVisible(true);
        frame.dispose();

    }

    public void mostrarHorario(JFrame frame, String codigo) {

        this.horarioEstudiante = new HorarioEstudiante(codigo);
        this.horarioEstudiante.setVisible(true);
        frame.dispose();

    }

    public void mostrarMenuPrincipal(JFrame frame, String codigo) {

        this.principalEstudiante = new PrincipalEstudiante(codigo);
        this.principalEstudiante.setVisible(true);
        frame.dispose();

    }

    public void mostrarLoginSinCredenciales(JFrame frame) {

        this.sesionSinRolEstudiante = new SesionSinRolEstudiante();
        this.sesionSinRolEstudiante.setVisible(true);
        frame.dispose();

    }
    
    public void mostrarCompletarEstudiante(JFrame frame, String codigo){
    
        this.completarEstudiante = new CompletarEstudiante(codigo);
        this.completarEstudiante.setVisible(true);
        frame.dispose();
    
    }
    
    public void mostrarLogin(JFrame frame){
    
        this.sesionEstudiante = new SesionEstudiante();
        this.sesionEstudiante.setVisible(true);
        frame.dispose();
    
    }

}
