package juego.pantallas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import juego.reglas.Partida;
import juego.reglas.Resultado;
import motor.Escena;
import ui.Texto;

/**
 * Pantalla de fin (módulo D): muestra el puntaje, que solo se ve en pantalla y
 * no se guarda en ningún archivo. ENTER juega de nuevo, M vuelve al menú.
 */
public class PantallaFin extends Escena {

    // Atributos
    private final Partida partida;
    private Escena menu;
    private Resultado resultado;

    // Constructor: lee el resultado de la partida que acaba de terminar
    public PantallaFin(Partida partida) {
        if (partida == null) {
            throw new IllegalArgumentException("la pantalla de fin necesita la partida");
        }
        this.partida = partida;
    }

    public void setMenu(Escena menu) {
        if (menu == null) {
            throw new IllegalArgumentException("el menú no puede ser nulo");
        }
        this.menu = menu;
    }

    @Override
    public void alEntrar() {
        resultado = partida.getResultado();
    }

    @Override
    public void actualizar(double dt) {
        if (getEntrada().fuePresionada(KeyEvent.VK_ENTER)) {
            getContexto().irA(partida);
        }
        if (getEntrada().fuePresionada(KeyEvent.VK_M) && menu != null) {
            getContexto().irA(menu);
        }
        if (getEntrada().fuePresionada(KeyEvent.VK_ESCAPE)) {
            getContexto().salir();
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        int ancho = getContexto().getAncho();
        int alto = getContexto().getAlto();
        double cx = ancho / 2.0;

        g.setColor(new Color(14, 16, 26));
        g.fillRect(0, 0, ancho, alto);

        Texto.centrado(g, "FIN DEL JUEGO", cx, 150, 52, new Color(235, 100, 100));
        Texto.centrado(g, resultado.getMotivo(), cx, 192, 18, new Color(170, 178, 205));
        Texto.centrado(g, "PUNTAJE FINAL", cx, 270, 16, new Color(140, 148, 175));
        Texto.centrado(g, String.valueOf(resultado.getPuntaje()), cx, 340, 80, new Color(250, 170, 80));
        Texto.centrado(g, "Aciertos " + resultado.getAciertos() + " de " + resultado.getLanzadas() + " lanzadas",
                cx, 400, 18, new Color(235, 238, 250));
        Texto.centrado(g, "ENTER: jugar de nuevo     M: menú     ESC: salir", cx, 500, 20,
                new Color(235, 238, 250));
    }
}
