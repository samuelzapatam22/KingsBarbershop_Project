
package admin;

import java.io.Serializable;

/**
 *
 * @author SamuelZapata
 */
public class Administrador  extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; //version de serializacion 

    public Administrador(String nombre, String telefono, String id) {
        super(nombre, telefono, id);
    }

    

    

    public Administrador() {
        
    }

    Object getUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

