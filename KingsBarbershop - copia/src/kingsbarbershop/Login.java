package kingsbarbershop;

import com.sun.source.tree.BreakTree;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends CuentaDeUsuario {

    public Login() {
    }

    public Login(String usuario, Long contraseña) {
        super(usuario, contraseña);
    }

    public void inicioSesion() {
        JFrame frame = new JFrame("INICIAR SESION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // Crear paneles para cada etiqueta y campo de texto
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Etiqueta y campo de texto para el nombre de usuario
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userTextField = new JTextField(15); // Ancho de 15 caracteres
        userPanel.add(userLabel);
        userPanel.add(userTextField);

        // Etiqueta y campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        JTextField passwordField = new JTextField(15); // Ancho de 15 caracteres
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("INGRESAR");

        // Agregar paneles al marco usando BorderLayout para centrarlos
        frame.setLayout(new BorderLayout());
        frame.add(userPanel, BorderLayout.NORTH);
        frame.add(passwordPanel, BorderLayout.CENTER);
        frame.add(loginButton, BorderLayout.SOUTH);

        // Centrar el marco en la pantalla
        frame.setLocationRelativeTo(null);

        // Mostrar el marco
        frame.setVisible(true);
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto

                try {
                    String usuario = userTextField.getText().toLowerCase();
                    long password = Long.parseLong(passwordField.getText());
                    GestionDatos crud1 = new GestionDatos();
                    frame.setVisible(false);
                    if (crud1.validarCuenta(usuario, password)) {
                        // Cerrar la ventana después de ingresar y guardar los datos
                        
                        frame.dispose();  // Liberar recursos asociados con el marco
                    } else {
                        // Mostrar un mensaje de error si la validación de la cuenta falla
                        JOptionPane.showMessageDialog(null, "***USUARIO NO ENCONTRADO***");
                        frame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    // Mostrar un mensaje de error si la contraseña no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una contraseña válida.");
                    inicioSesion();
                    
                }
            }
            
            
        });
          
        
        
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto

                try {
                    String usuario = userTextField.getText().toLowerCase();
                    long password = Long.parseLong(passwordField.getText());
                    GestionDatos crud1 = new GestionDatos();
                    frame.setVisible(false);
                    if (crud1.validarCuenta(usuario, password)) {
                        // Cerrar la ventana después de ingresar y guardar los datos
                        
                        frame.dispose();  // Liberar recursos asociados con el marco
                    } else {
                        // Mostrar un mensaje de error si la validación de la cuenta falla
                        JOptionPane.showMessageDialog(null, "***USUARIO NO ENCONTRADO***");
                        frame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    // Mostrar un mensaje de error si la contraseña no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una contraseña válida.");
                }
            }
            
            
        });
          
        
    }

}
