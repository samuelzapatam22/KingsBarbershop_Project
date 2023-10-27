package kingsbarbershop;

import com.sun.source.tree.IdentifierTree;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class GestionCitas {

    private File archivo;

    public void crearCita(Cita cita) {
        try {
            archivo = new File("Citas.txt");
            if (archivo.exists()) {
                if (validarCita(cita)) {
                    JOptionPane.showMessageDialog(null, "LA CITA A CREAR YA EXISTE", " ", JOptionPane.ERROR_MESSAGE);
                } else {

                    FileOutputStream salida = new FileOutputStream("Citas.txt", true);
                    MyObjectOutputStream salidaAbre = new MyObjectOutputStream(salida);
                    salidaAbre.writeObject(cita);
                    salida.close();
                    salidaAbre.close();
                    JOptionPane.showMessageDialog(null, "¡RESERVA CON EXITO!");
                }

            } else {
                FileOutputStream salida2 = new FileOutputStream("Citas.txt", true);
                ObjectOutputStream salidaAbre2 = new ObjectOutputStream(salida2);
                salidaAbre2.writeObject(cita);

                salida2.close();
                salidaAbre2.close();
                JOptionPane.showMessageDialog(null, "¡RESERVA CON EXITO!");
            }

        } catch (Exception e) {
        }

    }

    public List<Cita> listarCitas() {
        List<Cita> citas = new ArrayList<>();
        try {
            archivo = new File("Citas.txt");
            if (archivo.exists()) {
                try (FileInputStream fileIn = new FileInputStream(archivo); ObjectInputStream entrada = new ObjectInputStream(fileIn)) {
                    while (true) {
                        try {
                            Cita cita = (Cita) entrada.readObject();
                            citas.add(cita);
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citas;
    }

    public void actualizarCita(long id, Cita nuevaCita) {
        List<Cita> citas = listarCitas();
        for (Cita cita : citas) {
            if (cita.getId() == id) {
                cita.setFecha(nuevaCita.getFecha());
                cita.setHora(nuevaCita.getHora());
                guardarCitas(citas);
                JOptionPane.showMessageDialog(null, "¡CITA ACTUALIZADA CON ÉXITO!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "La cita con ID " + id + " no se encontró.");
    }

    public void eliminarCita(long id) {
        List<Cita> citas = listarCitas();
        for (Cita cita : citas) {
            if (cita.getId() == id) {
                citas.remove(cita);
                guardarCitas(citas);
                JOptionPane.showMessageDialog(null, "¡CITA ELIMINADA CON ÉXITO!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "La cita con ID " + id + " no se encontró.");
    }

    private void guardarCitas(List<Cita> citas) {
        try (FileOutputStream out = new FileOutputStream("Citas.txt"); ObjectOutputStream salida = new MyObjectOutputStream(out)) {
            for (Cita cita : citas) {
                salida.writeObject(cita);
            }
            JOptionPane.showMessageDialog(null, "¡CITAS GUARDADAS CON ÉXITO!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar las citas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validarCita(Cita cita) {

        boolean existeCita = false;
        boolean existenPersonas = false;
        Map<String, String> mapaFH = new HashMap<>();
        Map<Long, Long> mapaIB = new HashMap<>();
        Map<String,String> citaObject = new HashMap<>();
        Map<Long, Long> citaPerson = new HashMap<>();
        citaObject.put(cita.getFecha(), cita.getHora());
        citaPerson.put(cita.getId(), cita.getBarberoEscogido());

        

        List<Cita> listaValidarC = new ArrayList<>();
        GestionDatos cr1 = new GestionDatos();
        Cita citaV = new Cita();
        archivo = new File("Citas.txt");

        if (archivo.exists()) {
            try {
                FileInputStream abrir = new FileInputStream("Citas.txt");
                ObjectInputStream validar = new ObjectInputStream(abrir);
                while (true) {
                    try {

                        citaV = (Cita) validar.readObject();
                        listaValidarC.add(citaV);

                    } catch (Exception e) {
                        break;
                    }
                }
                abrir.close();
                validar.close();

                for (Cita cit : listaValidarC) {
                    String fech = cit.getFecha();
                    String hour = cit.getHora();
                    mapaFH.put(fech, hour);
                    long identif = cit.getId();
                    long baber = cit.getBarberoEscogido();
                    mapaIB.put(identif, baber);

                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            //validar si el id del cliente de la cita y el id del barbero existen.
            Persona client = new Persona();
            //con el id se encuentra el objeto Cliente
            client = cr1.buscarPersona(cita.getId(), "Clientes.txt");
            //se valida si existe
            if (cr1.validarPersona(client, "Clientes.txt")) {
                Persona barberoB = new Persona();
                //con el id se encuentra el objeto Barbero
                barberoB = cr1.buscarPersona(cita.getBarberoEscogido(), "Barberos.txt");
                if (cr1.validarPersona(barberoB, "Barberos.txt")) {
                    existenPersonas = true;
                } else {
                    JOptionPane.showMessageDialog(null, "EL BARBERO NO EXISTE!!!", null, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "EL CLIENTE NO EXISTE!!!", null, JOptionPane.ERROR_MESSAGE);
            }

            if (mapaFH.equals(citaObject)&&mapaIB.equals(citaPerson)) {
                existeCita = true;

            } else {
                existeCita = false;

            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO");

        }

        return existeCita;
    }
}
