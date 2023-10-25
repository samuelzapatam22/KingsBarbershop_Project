
package kingsbarbershop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
               System.out.println("Ya existe");
            } else {
                FileOutputStream archivoOut = new FileOutputStream("Administrador.txt");
                ObjectOutputStream out = new ObjectOutputStream(archivoOut);
                out.writeObject(obj);
                archivoOut.close();
                out.close();

            }
        } catch (Exception e) {
        }
    }
        
        
            public void ingresarPersona(Persona obj, String  nombreArchivo ) {
        System.out.println(obj.getTelefono());
      
       
        try {
              archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                 
                FileOutputStream salida = new FileOutputStream(nombreArchivo, true);
                MyObjectOutputStream salidaAbre = new MyObjectOutputStream(salida);
                salidaAbre.writeObject(obj);
                salida.close();
                salidaAbre.close();
                System.out.println("Exite y escribo ");
               
            } else {
                FileOutputStream salida2 = new FileOutputStream(nombreArchivo,true);
                ObjectOutputStream salidaAbre2 = new ObjectOutputStream(salida2);
                salidaAbre2.writeObject(obj);
                
                salida2.close();
                salidaAbre2.close();
                System.out.println("No Exite y escribo");
                
                
                

            }

        } catch (Exception e) {
        }
        

    }
            
            
             public void leerPersona(String nombreArchivo) {
        Persona persona = new Persona();
        archivo = new File(nombreArchivo);
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            ObjectInputStream entrada = new ObjectInputStream(fileIn);
            if (archivo.exists()) {
                while (true) {
                    try {
                        System.out.println("while");
                        persona = (Persona) entrada.readObject();
                        System.out.println("persona1 " + persona.getNombre());
                    } catch (Exception e) {
                        break;
                    }
                }
                fileIn.close();
                entrada.close();
            }

        } catch (Exception e) {
        }
             }
    
}
