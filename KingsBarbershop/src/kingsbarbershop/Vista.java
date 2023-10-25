/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kingsbarbershop;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author SamuelZapata
 */
public class Vista {
int opcion;
    public Vista() {
    }
    //Menu inicial
    public void inicio() {
        ImageIcon icono = new ImageIcon("king.png");
        
        menu1();
    }
    
    public  void  menu1(){
         opcion = Integer.parseInt(JOptionPane.showInputDialog( null,"BIENVENIDO!!!!\n\n1. Iniciar Sesion  \n2. Salir\n", "KINGS BARBERSHOP", JOptionPane.INFORMATION_MESSAGE));
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                menu1();
        }
    }
    public void iniciarSesion(){
         opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "\n\n1. Administrador\n2. Operador\n\n3. Volver     4. Salir", "INICIO DE SESION", JOptionPane.INFORMATION_MESSAGE));
        switch (opcion) {
            case 1:
                //Login loginA = new Login();
                //loginA.sesionAdmin();
                GestionBarbero gestionBarbero= new  GestionBarbero();
                gestionBarbero.crearBarbero();
                break;
            case 2:
            GestionDatos gestionDatos =new GestionDatos();
                gestionDatos.leerPersona("Barberos.txt");
                //Login loginO = new Login();
                //loginO.sesionOperador();
                break;
            case 3:
                menu1();
                break;
            case 4:

                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                iniciarSesion();

        }
        
    }
    
}
