package kingsbarbershop;



public class KingsBarbershop {

    public static void main(String[] args) {
        Administrador administrador = new Administrador("codigoc13", "3128906454", 000);
        GestionDatos crud = new GestionDatos();
        crud.ingresarAdmin(administrador);
        Vista menu = new Vista();
        menu.inicio();
    }

}
