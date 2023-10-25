/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kingsbarbershop;

import javax.swing.JOptionPane;

/**
 *
 * @author SamuelZapata
 */
public class GestionBarbero {

    public GestionBarbero() {
    }
    public  void  crearBarbero(){
         Barbero b = new Barbero();
         GestionDatos gestionDatos = new GestionDatos();
                b.setNombre(JOptionPane.showInputDialog("Nombre del Barbero: ").toLowerCase());
                b.setId(Long.parseLong(JOptionPane.showInputDialog("Identificacion del Barbero: ")));
                b.setTelefono(JOptionPane.showInputDialog("Telefono del Barbero: "));
                gestionDatos.ingresarPersona(b, "Barberos.txt");
        
    }
    
    
}
