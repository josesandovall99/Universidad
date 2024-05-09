/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneracionDeCorreos;

/**
 *
 * @author JOSE SANDOVAL
 */
public class CorreoAdministradorFabrica implements CorreosFabrica{

    @Override
    public CorreoId crearCorreoId() {
        
        return new CorreoAdministradorId();
        
    }

    @Override
    public CorreoCredenciales CrearCorreoCredenciales() {
        
        return new CorreoAdministradorCredenciales();
        
    }
    
}
