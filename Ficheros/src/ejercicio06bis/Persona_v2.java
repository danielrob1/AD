
package ejercicio06bis;

import java.io.Serializable;
import java.util.Date;

public class Persona_v2 implements Serializable {
    
    private static final long serialVersionUID = 7L;
    private StringBuilder nombre;
    private StringBuilder apellido1;
    private StringBuilder apellido2;
    private Date nacimiento;
    
    public Persona_v2() {
    }
    
    public Persona_v2(StringBuilder nombre, StringBuilder apellido1, StringBuilder apellido2, Date nacimiento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nacimiento = nacimiento;
    }
    
    @Override
    public String toString() {
        return apellido1 + " " + apellido2 + ", " + nombre + " (" + nacimiento + ")";
    }
    
    public StringBuilder getNombre() {
        return nombre;
    }
    
    public void setNombre(StringBuilder nombre) {
        this.nombre = nombre;
    }
    
    public StringBuilder getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(StringBuilder apellido1) {
        this.apellido1 = apellido1;
    }
    
    public StringBuilder getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(StringBuilder apellido2) {
        this.apellido2 = apellido2;
    }
    
    public Date getNacimiento() {
        return nacimiento;
    }
    
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
}
