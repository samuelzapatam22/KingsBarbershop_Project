package admin;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SamuelZapata
 */
public class GestionDatos {

    File archivo;

    //Operaciones con Base de Datos
    public void mostrarTabla(Connection conexion) throws SQLException {
        String nombreTabla = "users";
        String sql = "SELECT name  FROM " + nombreTabla;

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_user");
                    String nombre = resultSet.getString("first_name");
                    String apellido = resultSet.getString("first_name");

                    String telefono = resultSet.getString("phone");
                    String usuario = resultSet.getString("user");
                    String contraseña = resultSet.getString("password");
                    int tipo_usuario = resultSet.getInt("type_user");

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", telefono: " + telefono + ", Usuario: " + usuario + ", password: " + contraseña + ", tipo de usuario: " + tipo_usuario);
                }
            }
        }

    }

    static void insertarDatosUsers(Connection conexion, String nombre, String documento, String telefono, String usuario, String contraseña) {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO users (first_name, document, phone,\"user\", password,user_type) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, documento);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, usuario);
            preparedStatement.setString(5, contraseña);
            preparedStatement.setInt(6, 2);

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    static void insertarDatosBarbers(Connection conexion, String nombre, String documento, String telefono) {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO barbers (first_name, document, phone) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, documento);
            preparedStatement.setString(3, telefono);

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    static void insertarDatosClients(Connection conexion, String nombre, String documento, String telefono, int id_user) {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO clients (first_name, document, phone) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, documento);
            preparedStatement.setString(3, telefono);

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    static void insertarServices(Connection conexion, String nombre, Float precio, String descripcion) {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO services (name, price, description) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setString(1, nombre);
            preparedStatement.setFloat(2, precio);
            preparedStatement.setString(3, descripcion);

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    public int tomarIdSerial(Connection conexion, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE password = ? ";
        int id_serial = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id_serial = resultSet.getInt("user_id");
                }
            }
        }

        return id_serial;
    }

    public static ArrayList<String> obtenerDatosCbxB(Connection conexion) throws SQLException {
        String sql = "SELECT first_name  FROM  barbers";
        ArrayList<String> elementos = new ArrayList<>();
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dato = resultSet.getString("first_name");
                    elementos.add(dato);
                }
            }
        }
        return elementos;
    }

    public static ArrayList<String> obtenerDatosCbxS(Connection conexion) throws SQLException {
        String sql = "SELECT name  FROM  services";
        ArrayList<String> elementos = new ArrayList<>();
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dato = resultSet.getString("name");
                    elementos.add(dato);
                }
            }
        }
        return elementos;
    }

    static void insertarDatosReservations(Connection conexion, int idCliente, int idBarbero, int idServicio, String fecha, String hora) throws ParseException {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO reservations (client_id, barber_id, service_id,available,data_time) VALUES (?, ?, ?,?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setInt(1, idCliente);
            preparedStatement.setInt(2, idBarbero);
            preparedStatement.setInt(3, idServicio);
            preparedStatement.setBoolean(4, true);
            String fechaHoraString = fecha + " " + hora;

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                // Parsear la cadena a un objeto Date
                Date fechaHoraDate = formato.parse(fechaHoraString);

                // Crear un Timestamp a partir del objeto Date
                Timestamp timestamp = new Timestamp(fechaHoraDate.getTime());
                preparedStatement.setTimestamp(5, timestamp);
                // Ahora puedes utilizar el Timestamp en tu código
                System.out.println("Timestamp resultante: " + timestamp);
            } catch (ParseException e) {
                // Manejar la excepción si la cadena no puede ser parseada
                e.printStackTrace();
            }

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    static void insertarDatosBills(Connection conexion, int idReserva, String fecha, String hora, float total) throws ParseException {
        // Sentencia SQL con parámetros
        String sql = "INSERT INTO bills (reservation_id, date_invoice, total) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Establecer valores para los parámetros
            preparedStatement.setInt(1, idReserva);
            preparedStatement.setFloat(3, total);
            String fechaHoraString = fecha + " " + hora;

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                // Parsear la cadena a un objeto Date
                Date fechaHoraDate = formato.parse(fechaHoraString);

                // Crear un Timestamp a partir del objeto Date
                Timestamp timestamp = new Timestamp(fechaHoraDate.getTime());
                preparedStatement.setTimestamp(2, timestamp);
                // Ahora puedes utilizar el Timestamp en tu código
                System.out.println("Timestamp resultante: " + timestamp);
            } catch (ParseException e) {
                // Manejar la excepción si la cadena no puede ser parseada
                e.printStackTrace();
            }

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas por la inserción: " + filasAfectadas);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la inserción: " + e.getMessage());
        }
    }

    public int idSerialBarbero(Connection conexion, String nombre) throws SQLException {
        String sql = "SELECT barber_id FROM barbers WHERE first_name = ? ";
        int idSerialB = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idSerialB = resultSet.getInt("barber_id");
                    return idSerialB;
                }

            }

        }
        return idSerialB;
    }

    public int idSerialServicio(Connection conexion, String nombre) throws SQLException {
        String sql = "SELECT service_id FROM services WHERE name = ? ";
        int idSerialS = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idSerialS = resultSet.getInt("service_id");
                    return idSerialS;
                }

            }

        }
        return idSerialS;

    }

    public int idSerialCliente(Connection conexion, String nombre) throws SQLException {
        String sql = "SELECT client_id FROM clients WHERE first_name = ? ";
        int idSerialC = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idSerialC = resultSet.getInt("client_id");
                    return idSerialC;
                }

            }

        }
        return idSerialC;

    }

    public int idSerialReserva(Connection conexion, int serialServicio, int serialCliente) throws SQLException {
        String sql = "SELECT reservation_id FROM reservations WHERE service_id = ?  AND client_id = ?";
        int idSerialR = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, serialServicio);
            preparedStatement.setInt(2, serialCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idSerialR = resultSet.getInt("reservation_id");
                    return idSerialR;
                }

            }

        }
        return idSerialR;

    }

    public float totalFactura(Connection conexion, int serialServicio) throws SQLException {
        String sql = "SELECT price FROM services WHERE service_id = ?  ";
        float precio = 0;
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, serialServicio);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    precio = resultSet.getFloat("price");
                    return precio;
                }

            }

        }
        return precio;

    }

  public String mostrarReservas(Connection conexion) throws SQLException {
    String sql = "SELECT clients.first_name AS cliente, barbers.first_name AS barbero, services.name AS servicio, reservations.available AS disponibilidad, reservations.data_time AS Horario FROM reservations " +
            "JOIN clients ON reservations.client_id = clients.client_id " +
            "JOIN barbers ON reservations.barber_id = barbers.barber_id " +
            "JOIN services ON reservations.service_id = services.service_id;";

    StringBuilder resultadoTabla = new StringBuilder();

    try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            String cliente = resultSet.getString("cliente");
            String barbero = resultSet.getString("barbero");
            String servicio = resultSet.getString("servicio");
            String disponibilidad = resultSet.getString("disponibilidad");
            String horario = resultSet.getString("Horario");

            // Construir la cadena en forma de tabla y agregarla al resultado
            String filaTabla = String.format("| %-20s | %-20s | %-20s | %-15s | %-20s |", cliente, barbero, servicio, disponibilidad, horario);
            resultadoTabla.append(filaTabla).append("\n\n");
            System.out.println(filaTabla);
        }
    }

    // Devolver todas las filas en forma de tabla como una cadena
    return resultadoTabla.toString();
}
}
