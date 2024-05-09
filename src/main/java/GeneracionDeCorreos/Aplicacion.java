/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneracionDeCorreos;

/**
 *
 * @author JOSE SANDOVAL
 */
public class Aplicacion {
    
    private CorreoId correoId;
    private CorreoCredenciales correoCredenciales;
    
    public Aplicacion(CorreosFabrica correoFabrica){
        
        correoId = correoFabrica.crearCorreoId();
        correoCredenciales = correoFabrica.CrearCorreoCredenciales();
    
    }
    
    public void enviarCorreoId(String Email, int id){
    
        correoId.createEmail(Email, id);
        correoId.sendEmail();
    
    }
    
    public void enviarCorreoCredenciales(String Email, String codigo, String contraseña){
    
        correoCredenciales.createEmail(Email, codigo, contraseña);
        correoCredenciales.sendEmail();
    
    }
    
    
    
}
