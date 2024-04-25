/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodigoAcademico;


/**
 *
 * @author JOSE SANDOVAL
 */
public class BuilderPensum implements CodigoBuilder {
    
    public String ciudad = "CT";
    public String numeroCaracteristico = "05";
    public String año;
    public String numerosfinales;

    public BuilderPensum(String año, String numerosfinales) {
        this.año = año;
        this.numerosfinales = numerosfinales;
    }

    public BuilderPensum() {
    }

    
    
    
    @Override
    public void darCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public void darNumeroCaracteristico(String numCar) {
        this.numeroCaracteristico = numCar;
    }

    @Override
    public void darAño(String año) {
        this.año = año;
    }

    @Override
    public void darNumerosFinales(String numFinal) {
        this.numerosfinales = numFinal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNumeroCaracteristico() {
        return numeroCaracteristico;
    }

    public String getAño() {
        return año;
    }

    public String getNumerosfinales() {
        return numerosfinales;
    }

    public CodigoPensum getResult() {

        return new CodigoPensum(ciudad, numeroCaracteristico, año, numerosfinales);

    }

    public String pasarAString() {

        String a = this.getCiudad();
        String b = this.getNumeroCaracteristico();
        String c = this.getAño();
        String d = this.getNumeroCaracteristico();

        return a + b + c + d;

    }
    
  
}
