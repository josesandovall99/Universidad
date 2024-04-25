/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodigoAcademico;

/**
 *
 * @author JOSE SANDOVAL
 */
public class CodigoProgramas {
    public String ciudad;
    public String numeroCaracteristico;
    public String año;
    public String numerosfinales;

    public CodigoProgramas() {
    }

    public CodigoProgramas(String ciudad, String numeroCaracteristico, String año, String numerosfinales) {
        this.ciudad = ciudad;
        this.numeroCaracteristico = numeroCaracteristico;
        this.año = año;
        this.numerosfinales = numerosfinales;
    }
    
    
    
    
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumeroCaracteristico() {
        return numeroCaracteristico;
    }

    public void setNumeroCaracteristico(String numeroCaracteristico) {
        this.numeroCaracteristico = numeroCaracteristico;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getNumerosfinales() {
        return numerosfinales;
    }

    public void setNumerosfinales(String numerosfinales) {
        this.numerosfinales = numerosfinales;
    }
    
    public String pasarAString() {

        String a = this.getCiudad();
        String b = this.getNumeroCaracteristico();
        String c = this.getAño();
        String d = this.getNumeroCaracteristico();

        return a + b + c + d;

    }
}
