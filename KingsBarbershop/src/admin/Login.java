package admin;

import com.sun.source.tree.BreakTree;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Login {

    public Login() {
    }

    // Metodo con base de datos
    public int inicioSesionUsers(Connection conexion, String usuario, String contraseña) throws SQLException {
        String sql = "SELECT * FROM users ";
        String us;
        String psw;
        int tipo_usuario = 0;

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<String, String> logeo = new HashMap<>();

                while (resultSet.next()) {
                    us = resultSet.getString("user");
                    psw = resultSet.getString("password");
                    logeo.put(us, psw);
                    tipo_usuario = resultSet.getInt("user_type");
                      if (logeo.containsValue(contraseña) && logeo.containsKey(usuario)) {
                    System.out.println("ingreso");
                    return tipo_usuario;

                } else {
                    System.out.println("No ingreso");
                }
                }
              
            }
        }
        return tipo_usuario;

    }
    public  int online(Connection conexion, String username) {
        String sql = "SELECT user_id FROM users WHERE user = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, username);

            try (ResultSet generatedKeys = preparedStatement.executeQuery()) {
                if (generatedKeys.next()) {
                    System.out.println(generatedKeys.getInt("user_id"));
                    return generatedKeys.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de la excepción en tu aplicación
        }

        return -1; // Indica un error
    }
}
