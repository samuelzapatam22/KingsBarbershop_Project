/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kingsbarbershop;

import java.io.Serializable;

/**
 *
 * @author SamuelZapata
 */
public class Administrador  extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; //version de serializacion 

    public Administrador(String nombre, String telefono, long id) {
        super(nombre, telefono, id);
    }

    public Administrador() {
        
    }
    
    
}
