/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kingsbarbershop;

import java.io.Serializable;

/**
 *
 * @author sanal
 */
public class Cita implements Serializable {
            private static final long serialVersionUID = 1L; //version de serializacion 

    private long id;
    private String fecha;
    private String hora;
    private long barberoEscogido;
    private String servicio;

    public Cita() {
    }
    
    
    // Constructor
    public Cita(long id, String fecha, String hora, long barberoEscogido, String servicio) {    
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.barberoEscogido = barberoEscogido;
        this.servicio = servicio;
    }

    // Getters y setters para los atributos
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }

    public long getBarberoEscogido() {
        return barberoEscogido;
    }

    public void setBarberoEscogido(long barberoEscogido) {
        this.barberoEscogido = barberoEscogido;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    
    

}