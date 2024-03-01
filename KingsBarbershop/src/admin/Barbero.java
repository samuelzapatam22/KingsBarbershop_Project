/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import java.io.Serializable;

/**
 *
 * @author SamuelZapata
 */
public class Barbero extends  Persona implements  Serializable{
    private static final long serialVersionUID = 1L; //version de serializacion 

    public Barbero(String nombre, String telefono, String id) {
        super(nombre, telefono, id);
    }

    

    

    public Barbero() {
    }
    
    
    
}
