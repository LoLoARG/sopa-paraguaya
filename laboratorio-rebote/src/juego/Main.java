package juego;

import juego.pantallas.Hud;
import juego.pantallas.Menu;
import juego.pantallas.PantallaFin;
import juego.reglas.Partida;
import motor.Ventana;

/**
 * Punto de entrada. Arma las tres escenas y las conecta en ciclo:
 * Menu -> Partida -> PantallaFin -> (Partida | Menu).
 * Se conectan acá, después de construirlas, porque ninguna puede construir a
 * la otra sin crear una dependencia circular entre paquetes.
 */
public class Main {

    // Punto de entrada
    public static void main(String[] args) {
        Partida partida = new Partida(new Hud());
        PantallaFin fin = new PantallaFin(partida);
        Menu menu = new Menu(partida);

        partida.setSiguiente(fin);
        fin.setMenu(menu);

        Ventana.abrir("Laboratorio de rebote", 960, 680, menu);
    }
}
