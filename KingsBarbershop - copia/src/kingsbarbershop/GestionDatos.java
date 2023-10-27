package kingsbarbershop;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author SamuelZapata
 */
public class GestionDatos {

    File archivo;

    public void ingresarAdmin(Administrador obj) {

        try {
            archivo = new File("Administrador.txt");
            if (archivo.exists()) {
            } else {
                FileOutputStream archivoOut = new FileOutputStream("Administrador.txt");
                ObjectOutputStream out = new ObjectOutputStream(archivoOut);
                out.writeObject(obj);
                archivoOut.close();
                out.close();
                crearCuenta(obj);

            }
        } catch (Exception e) {
        }
    }

    public void ingresarPersona(Persona obj, String nombreArchivo) {

        try {
            archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                if (validarPersona(obj, nombreArchivo)) {
                    JOptionPane.showMessageDialog(null, "LA PERSONA A CREAR YA EXISTE", " ", JOptionPane.ERROR_MESSAGE);
                } else {

                    FileOutputStream salida = new FileOutputStream(nombreArchivo, true);
                    MyObjectOutputStream salidaAbre = new MyObjectOutputStream(salida);
                    salidaAbre.writeObject(obj);
                    salida.close();
                    salidaAbre.close();
                    JOptionPane.showMessageDialog(null, "¡GUARDADO CON EXITO!");
                    crearCuenta(obj);
                }

            } else {
                FileOutputStream salida2 = new FileOutputStream(nombreArchivo, true);
                ObjectOutputStream salidaAbre2 = new ObjectOutputStream(salida2);
                salidaAbre2.writeObject(obj);

                salida2.close();
                salidaAbre2.close();
                JOptionPane.showMessageDialog(null, "¡GUARDADO CON EXITO!");
                crearCuenta(obj);

            }

        } catch (Exception e) {
        }

    }

    public StringBuilder leerPersona(String nombreArchivo) {
        List<Persona> listaPersonasMostrar = new ArrayList<>();
        archivo = new File(nombreArchivo);
        StringBuilder mensaje = new StringBuilder(); // Usar StringBuilder para construir el mensaje

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            ObjectInputStream entrada = new ObjectInputStream(fileIn);

            if (archivo.exists()) {
                while (true) {
                    try {
                        Persona persona = (Persona) entrada.readObject();
                        listaPersonasMostrar.add(persona);
                    } catch (EOFException e) {
                        // Se lanza cuando llega al final del archivo
                        break;
                    }
                }
                fileIn.close();
                entrada.close();

                for (Persona pM : listaPersonasMostrar) {
                    mensaje.append("\nNOMBRE: ").append(pM.getNombre()).append("\nID: ").append(pM.getId()).append("\nTELEFONO: ").append(pM.getTelefono()).append("\n-------------------------\n");
                }
                
                JOptionPane.showMessageDialog(null, mensaje.toString(), "LISTA", JOptionPane.INFORMATION_MESSAGE, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    public void crearCuenta(Persona obj) {
        CuentaDeUsuario cuenta = new CuentaDeUsuario();
        cuenta.setUsuario(obj.getNombre());
        cuenta.setContraseña(obj.getId());
        guardarCuenta(cuenta);
    }

    public void guardarCuenta(CuentaDeUsuario obj) {

        try {
            archivo = new File("CuentasUsuarios.txt");
            if (archivo.exists()) {

                FileOutputStream salida = new FileOutputStream("CuentasUsuarios.txt", true);
                MyObjectOutputStream salidaAbre = new MyObjectOutputStream(salida);
                salidaAbre.writeObject(obj);
                salida.close();
                salidaAbre.close();
                JOptionPane.showInternalMessageDialog(null, "CUENTA CREADA!!");

            } else {
                FileOutputStream salida2 = new FileOutputStream("CuentasUsuarios.txt", true);
                ObjectOutputStream salidaAbre2 = new ObjectOutputStream(salida2);
                salidaAbre2.writeObject(obj);

                salida2.close();
                salidaAbre2.close();
                JOptionPane.showInternalMessageDialog(null, "CUENTA CREADA!!");

            }

        } catch (Exception e) {
        }
    }

    public boolean validarCuenta(String user, long contraseña) {
        boolean existe = false;
        Map<String, Long> mapa = new HashMap<>();
        List<CuentaDeUsuario> listaValidar = new ArrayList<>();

        CuentaDeUsuario c = new CuentaDeUsuario();
        archivo = new File("CuentasUsuarios.txt");

        if (archivo.exists()) {
            try {
                FileInputStream abrirArchivo = new FileInputStream("CuentasUsuarios.txt");
                ObjectInputStream busqueda = new ObjectInputStream(abrirArchivo);
                while (true) {
                    try {

                        c = (CuentaDeUsuario) busqueda.readObject();
                        listaValidar.add(c);

                    } catch (Exception e) {
                        break;
                    }
                }
                abrirArchivo.close();
                busqueda.close();

                for (CuentaDeUsuario cU : listaValidar) {
                    String usuario = cU.getUsuario();
                    long password = cU.getContraseña();
                    mapa.put(usuario, password);

                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if (mapa.containsKey(user) && mapa.containsValue(contraseña)) {
                existe = true;

            } else {
                existe = false;

            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE");

        }
        System.out.println(existe);
        Vista menu1 = new Vista();
        if (existe && contraseña == 000) {
           
 ImageIcon icono2 = new ImageIcon(getClass().getResource("/kingsbarbershop/administracion.png"));
                JOptionPane.showMessageDialog(null, " ", "INGRESO COMO  ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE, icono2);
            menu1.menuAdmin();
        } else {
            if (existe && contraseña != 000) {
                  ImageIcon icono2 = new ImageIcon(getClass().getResource("/kingsbarbershop/operador.png"));
                JOptionPane.showMessageDialog(null, " ", "INGRESO COMO  OPERADOR", JOptionPane.INFORMATION_MESSAGE, icono2);
                menu1.menuOperadores();
            } else {
                if (existe == false && contraseña != 000) {
                    System.out.println();
                }

            }

        }
        return existe;
    }

    public boolean validarPersona(Persona obj, String nombreArchivo) {
        boolean exist = false;
        Map<String, Long> mapa = new HashMap<>();
        List<Persona> listaValidarP = new ArrayList<>();

        Persona person = new Persona();
        archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            try {
                FileInputStream abrir = new FileInputStream(nombreArchivo);
                ObjectInputStream validar = new ObjectInputStream(abrir);
                while (true) {
                    try {

                        person = (Persona) validar.readObject();
                        listaValidarP.add(person);

                    } catch (Exception e) {
                        break;
                    }
                }
                abrir.close();
                validar.close();

                for (Persona per : listaValidarP) {
                    String name = per.getNombre();
                    long identification = per.getId();
                    mapa.put(name, identification);

                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if (mapa.containsKey(obj.getNombre()) && mapa.containsValue(obj.getId())) {
                exist = true;

            } else {
                exist = false;

            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO");

        }

        return exist;
    }

    public void editarPersona(long identification, String nombreArchivo) {
        HashMap<Long, Persona> map = new HashMap<>();
        List<Persona> listaEditar = new ArrayList<>();
        boolean existeEditar = false;

        Persona p = new Persona();
        archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try {
                FileInputStream open = new FileInputStream(nombreArchivo);
                ObjectInputStream edicion = new ObjectInputStream(open);
                while (true) {
                    try {

                        p = (Persona) edicion.readObject();
                        listaEditar.add(p);

                    } catch (Exception e) {
                        break;
                    }
                }
                open.close();
                edicion.close();
                for (Persona per : listaEditar) {
                    long idcion = per.getId();

                    map.put(idcion, per);

                }

                for (Persona person : listaEditar) {
                    if (person.getId() == identification) {
                        existeEditar = true;
                        int op = Integer.parseInt(JOptionPane.showInputDialog("¿Que desea Editar?\n\n1. Nombre\n2. Identificacion\n3. Telefono\n\n0. Cancelar"));
                        Persona pEditada = new Persona();
                        switch (op) {
                            case 1:
                                pEditada.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre Nuevo:"));
                                pEditada.setId(person.getId());
                                pEditada.setTelefono(person.getTelefono());
                                map.replace(person.getId(), person, pEditada);
                                update(map, nombreArchivo);

                                break;
                            case 2:
                                pEditada.setId(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Identificacion Nueva:")));
                                pEditada.setNombre(person.getNombre());
                                pEditada.setTelefono(person.getTelefono());
                                map.replace(person.getId(), person, pEditada);
                                update(map, nombreArchivo);

                                break;
                            case 3:
                                pEditada.setTelefono(JOptionPane.showInputDialog("Ingrese el Telefono Nuevo:"));
                                pEditada.setNombre(person.getNombre());
                                pEditada.setId(person.getId());
                                map.replace(person.getId(), person, pEditada);
                                update(map, nombreArchivo);

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
                JOptionPane.showMessageDialog(null, "PERSONA EDITADA");
                map.clear();

            } else {
                JOptionPane.showMessageDialog(null, "NO EXISTE PERSONA PARA EDITAR");
                map.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE!!!");
        }
    }

    public void eliminarPersona(long identification, String nombreArchivo) {
        HashMap<Long, Persona> map = new HashMap<>();
        List<Persona> listaEliminar = new ArrayList<>();
        boolean existeEliminar = false;

        Persona pE = new Persona();
        archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try {
                FileInputStream openE = new FileInputStream(nombreArchivo);
                ObjectInputStream eliminacion = new ObjectInputStream(openE);
                while (true) {
                    try {

                        pE = (Persona) eliminacion.readObject();
                        listaEliminar.add(pE);

                    } catch (Exception e) {
                        break;
                    }
                }
                openE.close();
                eliminacion.close();
                for (Persona per : listaEliminar) {
                    long idcion = per.getId();

                    map.put(idcion, per);

                }

                for (Persona personE : listaEliminar) {
                    if (personE.getId() == identification) {
                        existeEliminar = true;
                        map.remove(personE.getId());
                        update(map, nombreArchivo);

                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            if (existeEliminar) {
                JOptionPane.showMessageDialog(null, "PERSONA ELIMINADA");

                leerPersona(nombreArchivo);
                map.clear();

            } else {
                JOptionPane.showMessageDialog(null, "NO EXISTE PERSONA PARA ELIMINAR");
                map.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE!!!");
        }
    }

    public Persona buscarPersona(long id, String nombreArchivo) {
        boolean exist = false;
        List<Persona> listaBuscarP = new ArrayList<>();

        Persona personaB = new Persona();
        Persona personaReturn = new Persona();
        archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            try {
                FileInputStream abrirB = new FileInputStream(nombreArchivo);
                ObjectInputStream buscar = new ObjectInputStream(abrirB);
                while (true) {
                    try {

                        personaB = (Persona) buscar.readObject();
                        listaBuscarP.add(personaB);

                    } catch (Exception e) {
                        break;
                    }
                }
                abrirB.close();
                buscar.close();

                for (Persona perB : listaBuscarP) {
                    if (perB.getId() == id) {
                        personaReturn = perB;
                        exist = true;
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO");

        }
        if (exist) {
           // JOptionPane.showMessageDialog(null, "Objeto encontrado");

        } else {
            //JOptionPane.showMessageDialog(null, "Objeto no encontrado");
        }
        return personaReturn;

    }

    public void update(HashMap<Long, Persona> mapaUpdate, String nombreArchivo) {
        try {
            archivo = new File(nombreArchivo);
            FileOutputStream outUpdate = new FileOutputStream(nombreArchivo);
            ObjectOutputStream updates = new ObjectOutputStream(outUpdate);
            for (Persona PersonUpdate : mapaUpdate.values()) {
                updates.writeObject(PersonUpdate);
            }

            outUpdate.close();
            updates.close();
            JOptionPane.showMessageDialog(null, "¡ACTUALIZADO CON EXITO!");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void editarCuenta(long password) {
        HashMap<Long, CuentaDeUsuario> mapCuentas = new HashMap<>();
        List<CuentaDeUsuario> listaCuentas = new ArrayList<>();
        boolean existeCuenta = false;

        CuentaDeUsuario cDU = new CuentaDeUsuario();
        archivo = new File("CuentasUsuarios.txt");

        try {
            FileInputStream openC = new FileInputStream("CuentasUsuarios.txt");
            ObjectInputStream edicionC = new ObjectInputStream(openC);
            while (true) {
                try {

                    cDU = (CuentaDeUsuario) edicionC.readObject();
                    listaCuentas.add(cDU);

                } catch (Exception e) {
                    break;
                }
            }
            openC.close();
            edicionC.close();
            for (CuentaDeUsuario count : listaCuentas) {
                long pasword = count.getContraseña();

                mapCuentas.put(pasword, count);

            }

            for (CuentaDeUsuario countU : listaCuentas) {
                if (countU.getContraseña() == password) {
                    existeCuenta = true;
                    int opc = Integer.parseInt(JOptionPane.showInputDialog("¿Que desea Editar?\n\n1. Usuario\n2. Contraseña\n\n0. Cancelar"));
                    CuentaDeUsuario cEditada = new CuentaDeUsuario();
                    switch (opc) {
                        case 1:
                            cEditada.setUsuario(JOptionPane.showInputDialog("Ingrese el Usuario Nuevo:"));
                            cEditada.setContraseña(countU.getContraseña());
                            mapCuentas.replace(countU.getContraseña(), countU, cEditada);
                            updateCuenta(mapCuentas, "CuentasUsuarios.txt");

                            break;
                        case 2:
                            cEditada.setContraseña(Long.valueOf(JOptionPane.showInputDialog("Ingrese la contraseña Nueva:")));
                            cEditada.setUsuario(countU.getUsuario());
                            mapCuentas.replace(countU.getContraseña(), countU, cEditada);
                            updateCuenta(mapCuentas, "CuentasUsuarios.txt");

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
        if (existeCuenta) {
            JOptionPane.showMessageDialog(null, "CUENTA EDITADA");
            mapCuentas.clear();

        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE CUENTA PARA EDITAR");
            mapCuentas.clear();
        }
    }

    public void updateCuenta(HashMap<Long, CuentaDeUsuario> mapaCuentaUpdate, String nombreArchivo) {
        try {
            archivo = new File(nombreArchivo);
            FileOutputStream outUpdateC = new FileOutputStream(nombreArchivo);
            ObjectOutputStream updatesC = new ObjectOutputStream(outUpdateC);
            for (CuentaDeUsuario cuentaUpdate : mapaCuentaUpdate.values()) {
                updatesC.writeObject(cuentaUpdate);
            }

            outUpdateC.close();
            updatesC.close();
            JOptionPane.showMessageDialog(null, "¡CUENTA ACTUALIZADA CON EXITO!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
