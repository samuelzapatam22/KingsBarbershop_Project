package kingsbarbershop;

import java.io.EOFException;
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

                FileOutputStream salida = new FileOutputStream("Citas.txt", true);
                MyObjectOutputStream salidaAbre = new MyObjectOutputStream(salida);
                salidaAbre.writeObject(cita);
                salida.close();
                salidaAbre.close();
                JOptionPane.showMessageDialog(null, "¡RESERVA CON EXITO!");

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

    

    public void actualizarCita(HashMap<Long, Cita> updateMap) {
        try {
            archivo = new File("Citas.txt");
            FileOutputStream outUpdate = new FileOutputStream("Citas.txt");
            ObjectOutputStream updates = new ObjectOutputStream(outUpdate);
            for (Cita citaUpdate : updateMap.values()) {
                updates.writeObject(citaUpdate);
            }

            outUpdate.close();
            updates.close();
            JOptionPane.showMessageDialog(null, "¡ACTUALIZADO CON EXITO!");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    

    public void eliminarCita(long id) {
        HashMap<Long, Cita> mapE = new HashMap<>();
        List<Cita> listaEliminar = new ArrayList<>();
        boolean existeEliminar = false;

        Cita cD = new Cita();
        archivo = new File("Citas.txt");
        if (archivo.exists()) {
            try {
                FileInputStream openD= new FileInputStream("Citas.txt");
                ObjectInputStream eliminacion = new ObjectInputStream(openD);
                while (true) {
                    try {

                        cD = (Cita) eliminacion.readObject();
                        listaEliminar.add(cD);

                    } catch (Exception e) {
                        break;
                    }
                }
                openD.close();
                eliminacion.close();
                for (Cita citaDel : listaEliminar) {
                    long idcion = citaDel.getId();

                    mapE.put(idcion, citaDel);

                }

                for (Cita citaDelete : listaEliminar) {
                    if (citaDelete.getId() == id ) {
                        existeEliminar = true;
                       
                        mapE.remove(citaDelete.getId(),citaDelete);
                        actualizarCita(mapE);

                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if (existeEliminar) {
                JOptionPane.showMessageDialog(null, "CITA ELIMINADA");

                mostrarAgenda();
                mapE.clear();

            } else {
                JOptionPane.showMessageDialog(null, "NO EXISTE CITA PARA ELIMINAR");
                mapE.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE ARCHIVO!!!");
        }
    }

    

    public boolean validarCita(Cita cita) {

        boolean existeCita = false;
        boolean existenPersonas = false;
        Map<String, String> mapaFH = new HashMap<>();
        Map<Long, Long> mapaIB = new HashMap<>();
        Map<String, String> citaObject = new HashMap<>();
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
            if (existenPersonas) {
                if (mapaFH.equals(citaObject) && mapaIB.equals(citaPerson)) {
                    existeCita = true;

                } else {
                    existeCita = false;

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO");

        }

        return existeCita;
    }

    public StringBuilder mostrarAgenda() {
        List<Cita> listaCitasMostrar = new ArrayList<>();
        archivo = new File("Citas.txt");
        GestionDatos crM = new GestionDatos();
        StringBuilder mensaje = new StringBuilder(); // Usar StringBuilder para construir el mensaje

        try {
            FileInputStream fileIn = new FileInputStream("Citas.txt");
            ObjectInputStream entrada = new ObjectInputStream(fileIn);

            if (archivo.exists()) {
                while (true) {
                    try {
                        Cita citaM = (Cita) entrada.readObject();
                        listaCitasMostrar.add(citaM);
                    } catch (EOFException e) {
                        // Se lanza cuando llega al final del archivo
                        break;
                    }
                }
                fileIn.close();
                entrada.close();

                for (Cita cM : listaCitasMostrar) {
                    Persona clienteM = new Persona();
                    //con el id se encuentra el objeto Cliente
                    clienteM = crM.buscarPersona(cM.getId(), "Clientes.txt");
                    Persona barberoM = new Persona();
                    //con el id se encuentra el objeto Barbero
                    barberoM = crM.buscarPersona(cM.getBarberoEscogido(), "Barberos.txt");

                    mensaje.append("\nCLIENTE:  ").append(clienteM.getNombre()).append("\nFECHA:  ").append(cM.getFecha()).append("\nHORA:  ").append(cM.getHora()).append("\nBARBERO:  ").append(barberoM.getNombre()).append("\nSERVICIO:  ").append(cM.getServicio()).append("\n-------------------------\n");
                }

                //JOptionPane.showMessageDialog(null, mensaje.toString(), "LISTA", JOptionPane.INFORMATION_MESSAGE, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    public void editarCita(long id) {
        HashMap<Long, Cita> map = new HashMap<>();
        List<Cita> listaEditarCita = new ArrayList<>();
        boolean existeEditar = false;
        GestionDatos cr2 = new GestionDatos();

        Cita c = new Cita();
        archivo = new File("Citas.txt");
        if (archivo.exists()) {
            try {
                FileInputStream open = new FileInputStream("Citas.txt");
                ObjectInputStream edicion = new ObjectInputStream(open);
                while (true) {
                    try {

                        c = (Cita) edicion.readObject();
                        listaEditarCita.add(c);

                    } catch (Exception e) {
                        break;
                    }
                }
                open.close();
                edicion.close();
                for (Cita citation : listaEditarCita) {
                    long idcion = citation.getId();

                    map.put(idcion, citation);

                }

                for (Cita citaE : listaEditarCita) {
                    if (citaE.getId() == id) {
                        existeEditar = true;
                        int op = Integer.parseInt(JOptionPane.showInputDialog("¿Que desea Editar?\n\n1. Cliente\n2. Fecha\n3. Hora\n4. Barbero\n5. Servicio\n\n0. Cancelar"));
                        Cita citaEditada = new Cita();
                        switch (op) {
                            case 1:
                                citaEditada.setId(Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Cliente Nuevo: ")));
                                citaEditada.setFecha(citaE.getFecha());
                                citaEditada.setHora(citaE.getHora());
                                citaEditada.setBarberoEscogido(citaE.getBarberoEscogido());
                                citaEditada.setServicio(citaE.getServicio());

                                map.replace(citaE.getId(), citaE, citaEditada);

                                actualizarCita(map);

                                break;
                            case 2:
                                citaEditada.setFecha(JOptionPane.showInputDialog("Ingrese la Fecha Nueva: "));
                                citaEditada.setId(citaE.getId());
                                citaEditada.setHora(citaE.getHora());
                                citaEditada.setBarberoEscogido(citaE.getBarberoEscogido());
                                citaEditada.setServicio(citaE.getServicio());

                                map.replace(citaE.getId(), citaE, citaEditada);
                                actualizarCita(map);

                                break;
                            case 3:
                                citaEditada.setHora(JOptionPane.showInputDialog("Ingrese la Hora Nueva: "));
                                citaEditada.setId(citaE.getId());
                                citaEditada.setFecha(citaE.getFecha());
                                citaEditada.setBarberoEscogido(citaE.getBarberoEscogido());
                                citaEditada.setServicio(citaE.getServicio());

                                map.replace(citaE.getId(), citaE, citaEditada);
                                actualizarCita(map);

                                break;
                            case 4:
                                GestionDatos crud2 = new GestionDatos();
                                StringBuilder msj = crud2.leerPersona("Barberos.txt");
                                citaEditada.setBarberoEscogido(Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Barbero\nBARBEROS DISPONIBLES:\n\n" + msj)));
                                citaEditada.setFecha(citaE.getFecha());
                                citaEditada.setHora(citaE.getHora());
                                citaEditada.setId(citaE.getId());
                                citaEditada.setServicio(citaE.getServicio());

                                map.replace(citaE.getId(), citaE, citaEditada);

                                actualizarCita(map);

                                break;
                            case 5:
                                Vista s= new Vista();
                                StringBuilder service = s.menuServicios();
                                citaEditada.setServicio(service);
                                citaEditada.setFecha(citaE.getFecha());
                                citaEditada.setHora(citaE.getHora());
                                citaEditada.setBarberoEscogido(citaE.getBarberoEscogido());
                                citaEditada.setServicio(citaE.getServicio());

                                map.replace(citaE.getId(), citaE, citaEditada);

                                actualizarCita(map);

                                break;
                            case 0:
                                System.exit(0);
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if (existeEditar) {
                JOptionPane.showMessageDialog(null, "CITA EDITADA");
                map.clear();

            } else {
                JOptionPane.showMessageDialog(null, "NO EXISTE CITA PARA EDITAR");
                map.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE ARCHIVO!!!");
        }
    }

}
