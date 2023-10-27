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
public class Barberia  implements Serializable {
    private static final long serialVersionUID = 1L; //version de serializacion 
    private String nombre, telefono;
    private long id;

    public Barberia() {
    }

    public Barberia(String nombre, String telefono, long id) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
