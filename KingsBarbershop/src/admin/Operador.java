package admin;

import java.io.Serializable;

public class Operador extends Persona implements Serializable {

    private static final long serialVersionUID = 1L; //version de serializacion 
    String usuarioOp;

    public Operador(String usuarioOp, String nombre, String telefono, String id) {
        super(nombre, telefono, id);
        this.usuarioOp = usuarioOp;
    }

    

    public String getUsuarioOp() {
        return usuarioOp;
    }

    public void setUsuarioOp(String usuarioOp) {
        this.usuarioOp = usuarioOp;
    }
   

    public Operador() {
    }

}
