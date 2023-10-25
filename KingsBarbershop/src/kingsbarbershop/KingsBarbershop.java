
package kingsbarbershop;


public class KingsBarbershop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Administrador administrador = new Administrador("Admon1", "3138",  1);
      GestionDatos crud = new  GestionDatos();
              crud.ingresarAdmin(administrador);
              Vista menu = new Vista();
              
      menu.inicio();
        
    }
    
}
