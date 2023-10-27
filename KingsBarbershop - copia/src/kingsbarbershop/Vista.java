package kingsbarbershop;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Vista {

    int opcion;
    GestionPersona gestionPersona = new GestionPersona();

    public Vista() {
    }

    //Menu inicial
    public void inicio() {
        ImageIcon icono1 = new ImageIcon(getClass().getResource("/kingsbarbershop/king.png"));
        JOptionPane.showMessageDialog(null, " ", "¡BIENVENIDOS!", JOptionPane.INFORMATION_MESSAGE, icono1);
        menu1();
    }

    public void menu1() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "BIENVENIDO!!!!\n\n1. Iniciar Sesion  \n\n0. Salir\n", "KINGS BARBERSHOP", JOptionPane.INFORMATION_MESSAGE));
        switch (opcion) {
            case 1:
                Login login = new Login();

                login.inicioSesion();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                menu1();
        }
    }

    public void menuAdmin() {
       

        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Gestion Operadores  \n2. Gestion Barberos\n\n9. Volver     0. Salir", "ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE));
        switch (opcion) {
            case 1:
                gestionOperadores();
                
                break;
            case 2:
                gestionBarberos();
                break;
            case 9:

                menuAdmin();
                break;
            case 0:

                menuSalida();
                break;
            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                menuAdmin();
        }
    }

    public void menuOperadores() {
       
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Gestion Barberos  \n2. Gestion Clientes\n3. Gestion de Citas\n\n9. Volver     0. Salir", "OPERADOR", JOptionPane.INFORMATION_MESSAGE));
        switch (opcion) {
            case 1:
                gestionBarberos();
                break;
            case 2:
                gestionClientes();
                break;
            case 3:
                gestionCitas();

                break;
            case 9:
                menuOperadores();

                break;
            case 0:
                menuSalida();

                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                menuOperadores();
        }
    }

    public void menuSalida() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Cerrar Sesion  \n2. Salir\n", "SALIDA", JOptionPane.WARNING_MESSAGE));
        switch (opcion) {
            case 1:
                menu1();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "MUCHAS GRACIAS!!!", " SALIENDO...", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                menuSalida();
        }
    }

    public void gestionBarberos() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Crear Barbero  \n2. Editar Barbero\n3. Eliminar Barbero\n4. Ver Barberos\n\n9. Volver     0. Salir", "GESTION BARBEROS", JOptionPane.INFORMATION_MESSAGE));
        Barbero barbero = new Barbero();
        GestionDatos crudEB = new GestionDatos();

        switch (opcion) {
            case 1:

                gestionPersona.crearPersona(barbero);
                gestionBarberos();
                break;
            case 2:
                long identifB = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Editar:"));
                crudEB.editarPersona(identifB, "Barberos.txt");
                gestionBarberos();
                break;
            case 3:
                identifB = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Eliminar:"));
                crudEB.editarPersona(identifB, "Barberos.txt");
                gestionBarberos();
                break;
            case 4:
                crudEB.leerPersona("Barberos.txt");
                gestionBarberos();
                break;

            case 9:
                menuOperadores();
                break;
            case 0:
                menuSalida();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                gestionBarberos();
        }
    }

    public void gestionOperadores() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Crear Operador  \n2. Editar Operador\n3. Editar Cuenta de Operador\n4. Eliminar Operador\n5. Ver Operadores\n\n9. Volver     0. Salir", "GESTION OPERADORES", JOptionPane.INFORMATION_MESSAGE));
        Operador operador = new Operador();
        GestionDatos crudEO = new GestionDatos();

        switch (opcion) {
            case 1:
                gestionPersona.crearPersona(operador);
                gestionOperadores();

                break;
            case 2:
                long identifO = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Editar:"));
                crudEO.editarPersona(identifO, "Operadores.txt");
                gestionOperadores();

                break;
            case 3:
                identifO = Long.parseLong(JOptionPane.showInputDialog("Ingrese la contraseña:"));
                crudEO.editarCuenta(identifO);
                gestionOperadores();

                break;
            case 4:
                identifO = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Eliminar:"));
                crudEO.eliminarPersona(identifO, "Operadores.txt");
                gestionOperadores();

                break;
            case 5:
                crudEO.leerPersona("Operadores.txt");
                gestionOperadores();

                break;
            case 9:
                menuAdmin();
                break;
            case 0:
                menuSalida();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                gestionOperadores();
        }

    }

    public void gestionClientes() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Crear Cliente  \n2. Editar Cliente\n3. Eliminar Cliente\n4. Ver Clientes\n\n9. Volver     0. Salir", "GESTION CLIENTES", JOptionPane.INFORMATION_MESSAGE));
        Cliente cliente = new Cliente();
        GestionDatos crudEC = new GestionDatos();
        switch (opcion) {
            case 1:
                gestionPersona.crearPersona(cliente);
                gestionClientes();
                break;
            case 2:
                long identifC = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Editar:"));
                crudEC.editarPersona(identifC, "Clientes.txt");
                gestionClientes();
                break;
            case 3:
                identifC = Long.parseLong(JOptionPane.showInputDialog("Ingrese la Identificacion a Eliminar:"));
                crudEC.eliminarPersona(identifC, "Clientes.txt");
                gestionClientes();

                break;
            case 4:
                crudEC.leerPersona("Clientes.txt");
                gestionClientes();
            case 9:
                menuOperadores();
                break;
            case 0:
                menuSalida();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                gestionClientes();
        }
    }

    public void gestionCitas() {
        GestionCitas gestionCitas = new GestionCitas(); // instancia de GestionCitas
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Crear Cita\n2. Editar Cita\n3. Eliminar Cita\n4. Ver Agenda\n\n9. Volver     0. Salir", "GESTION CITAS", JOptionPane.INFORMATION_MESSAGE));
        GestionDatos crud4 = new GestionDatos();
        switch (opcion) {
            case 1:
                // Crear una nueva cita
                long idCita = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Cliente:"));
                String fechaCita = JOptionPane.showInputDialog("Ingrese la fecha de la cita (Ejemplo: 2023-11-01):").toLowerCase();
                String horaCita = JOptionPane.showInputDialog("Ingrese la hora de la cita (Ejemplo: 10:00 AM)\n\nHORARIO:\nLunes a sabado\n10:00 AM-7:00 PM:").toLowerCase().replaceAll("\\s+", "");
                StringBuilder msj = crud4.leerPersona("Barberos.txt");

                long barberoseleccionado = (Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Barbero\nBARBEROS DISPONIBLES:\n\n" + msj)));
                StringBuilder service = menuServicios();
                Cita nuevaCita = new Cita(idCita, fechaCita, horaCita, barberoseleccionado, service);
                if (gestionCitas.validarCita(nuevaCita)) {
                    JOptionPane.showMessageDialog(null, "LA CITA YA EXISTE");
                    gestionCitas();
                } else {

                    gestionCitas.crearCita(nuevaCita);
                    gestionCitas();
                }

                break;

            case 2:
                // Editar una cita existente
                long idCitaAEditar = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Cliente::"));
                gestionCitas.editarCita(idCitaAEditar);
                gestionCitas();
                break;

            case 3:
                // Eliminar una cita
                long idCitaAEliminar = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del Cliente:"));
                gestionCitas.eliminarCita(idCitaAEliminar);
                gestionCitas();
                break;
            case 4:
                StringBuilder agenda = new StringBuilder();
                agenda = gestionCitas.mostrarAgenda();
                JOptionPane.showMessageDialog(null, agenda, "AGENDA", JOptionPane.INFORMATION_MESSAGE);
                gestionCitas();
                break;

            case 9:
                menuOperadores();
                break;

            case 0:
                menuSalida();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCIÓN INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                gestionCitas();
        }
    }

    public StringBuilder menuServicios() {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Corte  \n2. Barba\n3. Cejas\n4. MIXES\n\n9. Volver     0. Salir", "!!NUESTROS SERVICIOS!!", JOptionPane.INFORMATION_MESSAGE));
        StringBuilder servicio = new StringBuilder();
        switch (opcion) {
            case 1:
                int opcionCorte = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Corte Especial  \n2. Corte Sencillo\n\n9. Cancelar     0. Salir", "!!NUESTROS SERVICIOS!!", JOptionPane.INFORMATION_MESSAGE));
                switch (opcionCorte) {
                    case 1:
                        servicio.append("Corte Especial");
                        break;
                    case 2:
                        servicio.append("Corte Sencillo");
                        break;

                    case 9:
                        menuServicios();
                        break;
                    case 0:
                        menuSalida();
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                        gestionClientes();
                }
                break;
            case 2:
                servicio.append("Barba");
                break;
            case 3:
                servicio.append("Cejas");

                break;
            case 4:
                int opcionMix = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Corte + Barba \n2. Corte + Cejas\n3. Cejas + Barba\n4. Corte + Cejas + Barba\n\n9. Cancelar     0. Salir", "!!NUESTROS SERVICIOS!!", JOptionPane.INFORMATION_MESSAGE));
                switch (opcionMix) {
                    case 1:
                        servicio.append("Corte + Barba");
                        break;
                    case 2:
                        servicio.append("Corte + Cejas");

                        break;
                    case 3:
                        servicio.append("Cejas + Barba");

                        break;
                    case 4:
                        servicio.append("Corte +Cejas + Barba");

                        break;

                    case 9:
                        menuServicios();
                        break;
                    case 0:
                        menuSalida();
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                        gestionClientes();
                }
                break;
            case 9:
                JOptionPane.showMessageDialog(null, "Reserva Cancelada", null, JOptionPane.ERROR_MESSAGE);
                gestionCitas();
                break;
            case 0:
                menuSalida();
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INCORRECTA", " ", JOptionPane.ERROR_MESSAGE);
                gestionClientes();
        }
        return servicio;

    }

}
