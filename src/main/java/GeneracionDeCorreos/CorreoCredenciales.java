/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GeneracionDeCorreos;

/**
 *
 * @author JOSE SANDOVAL
 */
public interface CorreoCredenciales {
    
    public void sendEmail();
    
    public void createEmail(String emailTo, String id, String contraseña);
    
}
