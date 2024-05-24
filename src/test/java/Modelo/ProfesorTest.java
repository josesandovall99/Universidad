package Modelo;

import javax.swing.JTable;
import javax.swing.JTextField;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
  import org.junit.Assert;

public class ProfesorTest {

    @Test
    public void testGetId() {
        Profesor instance = new Profesor();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        int id = 1;
        Profesor instance = new Profesor();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    @Test
    public void testGetCodigoAcademico() {
        Profesor instance = new Profesor();
        String codigoAcademico = "123456";
        instance.setCodigoAcademico(codigoAcademico);
        assertEquals(codigoAcademico, instance.getCodigoAcademico());
    }

    @Test
    public void testSetCodigoAcademico() {
        String codigoAcademico = "123456";
        Profesor instance = new Profesor();
        instance.setCodigoAcademico(codigoAcademico);
        assertEquals(codigoAcademico, instance.getCodigoAcademico());
    }

 

@Test
public void testVerHorario() {
   
    Profesor instance = new Profesor();
    JTable tabla = new JTable();
    String codigo = "123456";

   
    instance.verHorario(tabla, codigo);

   
    
}

@Test
public void testVerCursos() {
  
    Profesor instance = new Profesor();
    JTable tabla = new JTable();
    String codigo = "123456";

    
    instance.verCursos(tabla, codigo);

    
    
}



}
