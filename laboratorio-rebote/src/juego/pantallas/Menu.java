package juego.pantallas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import motor.Escena;
import motor.Temporizador;
import ui.Texto;

/** Pantalla de inicio (módulo D). ENTER o ESPACIO empiezan la partida; ESC cierra el juego. */
public class Menu extends Escena {

    // Atributos
    private final Escena partida;
    private final Temporizador parpadeo = new Temporizador(0.55);
    private boolean visible = true;

    // Constructor: recibe la escena a la que se pasa al jugar
    public Menu(Escena partida) {
        if (partida == null) {
            throw new IllegalArgumentException("el menú necesita la escena de la partida");
        }
        this.partida = partida;
    }

    @Override
    public void alEntrar() {
        visible = true;
        parpadeo.reiniciar();
    }

    @Override
    public void actualizar(double dt) {
        if (parpadeo.avanzar(dt)) {
            visible = !visible;
        }
        if (getEntrada().fuePresionada(KeyEvent.VK_ENTER) || getEntrada().fuePresionada(KeyEvent.VK_SPACE)) {
            getContexto().irA(partida);
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

        Texto.centrado(g, "LABORATORIO", cx, 190, 64, new Color(250, 170, 80));
        Texto.centrado(g, "de rebote", cx, 240, 32, new Color(235, 238, 250));
        Texto.centrado(g, "Lanzá pelotas de distintos materiales y embocalas en el aro que se mueve.", cx, 320, 16,
                new Color(170, 178, 205));
        if (visible) {
            Texto.centrado(g, "ENTER para jugar", cx, 460, 26, new Color(235, 238, 250));
        }
        Texto.centrado(g, "ESC: salir", cx, alto - 28, 14, new Color(140, 148, 175));
    }
}
