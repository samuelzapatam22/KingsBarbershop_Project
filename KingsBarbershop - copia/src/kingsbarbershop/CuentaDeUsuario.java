package kingsbarbershop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class CuentaDeUsuario implements Serializable {

    private static final long serialVersionUID = 1L; //version de serializacion 
    //Atributos
    private String usuario;
    private Long contraseña;
    File cuentas;

    public CuentaDeUsuario() {
    }

    public CuentaDeUsuario(String user, Long contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getContraseña() {
        return contraseña;
    }

    public void setContraseña(Long contraseña) {
        this.contraseña = contraseña;
    }

   
}
