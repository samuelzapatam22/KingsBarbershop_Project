package kingsbarbershop;

import javax.swing.JOptionPane;

public class GestionPersona {

    public GestionPersona() {
    }

    public void crearPersona(Persona obj) {

        GestionDatos gestionDatos = new GestionDatos();
        obj.setNombre(JOptionPane.showInputDialog("Nombre: ").toLowerCase());
        obj.setId(Long.parseLong(JOptionPane.showInputDialog("Identificacion: ")));
        obj.setTelefono(JOptionPane.showInputDialog("Telefono : "));
        if (obj instanceof Barbero) {
            gestionDatos.ingresarPersona(obj, "Barberos.txt");
        }
        if (obj instanceof Operador) {
            gestionDatos.ingresarPersona(obj, "Operadores.txt");
        }
        if (obj instanceof Cliente) {
            gestionDatos.ingresarPersona(obj, "Clientes.txt");
        }
       /* if (obj instanceof Cita) {
            gestionDatos.ingresarPersona(obj, "Citas.txt");
        }*/

    }

}
