package admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class KingsBarbershop {
    
    private static Administrador administrador;

    public static Administrador getAdministrador() {
        return administrador;
    }

    public static void main(String[] args) {
        
        //Conexion base de datos
         String jdbcUrl = "jdbc:postgresql://localhost:5432/kings_barbershop";
        String usuario = "postgres";
        String contraseña = "samuelzapatam22";

        // Intentar establecer la conexión
        try {
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
            System.out.println("¡Conexión exitosa!");

            // Realizar operaciones en la base de datos aquí
            GestionDatos db = new GestionDatos();
            //db.mostrarTabla(conexion);
            // Cerrar la conexión cuando hayas terminado
            conexion.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    
        // fin conexion
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmPrincipal mainForm = new frmPrincipal();
                mainForm.setVisible(true);

                // Crear un temporizador para cambiar a frmLogin después de 9 segundos
                javax.swing.Timer timer = new javax.swing.Timer(3000, new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        mainForm.dispose(); // Cierra frmPrincipal
                        frmLogin loginForm = new frmLogin();
                        loginForm.setVisible(true); // .Abre frmLogin
                    }
                });
                timer.setRepeats(false); // Evita que el temporizador se repita
                timer.start();
            }
        });      
      
        
    }

}
