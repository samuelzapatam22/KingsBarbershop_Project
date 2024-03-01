
package admin;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
        private static final long serialVersionUID = 1L; //version de serializacion 

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, String id) {
        super(nombre, telefono, id);
    }

    

    

}
