package juego.reglas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import motor.Escena;

/**
 * Escena de la partida (módulo C). Traduce el teclado a órdenes para el mundo
 * y, cuando la partida termina, pasa a la escena siguiente (pantalla de fin).
 *
 * Provisorio: hoy solo ESC termina la partida. C agrega el resto de los
 * controles (ángulo, potencia, lanzar, pausa) según docs/ACTA.md.
 */
public class Partida extends Escena {

    // Constantes
    private static final double DEMORA_FIN = 0.8;
    private static final int ORIGEN_Y = 100;

    // Atributos
    private final VistaHud hud;
    private Escena siguiente;
    private Mundo mundo;
    private boolean pausado;
    private double demoraFin;

    // Constructor
    public Partida(VistaHud hud) {
        if (hud == null) {
            throw new IllegalArgumentException("la partida necesita un HUD");
        }
        this.hud = hud;
        this.mundo = new Mundo();
    }

    // La escena siguiente se conecta en Main, después de construir las tres escenas
    public void setSiguiente(Escena siguiente) {
        if (siguiente == null) {
            throw new IllegalArgumentException("la escena siguiente no puede ser nula");
        }
        this.siguiente = siguiente;
    }

    // Cada vez que se entra se arranca un mundo nuevo
    @Override
    public void alEntrar() {
        mundo = new Mundo();
        pausado = false;
        demoraFin = 0;
    }

    // Para que la pantalla de fin muestre lo que pasó
    public Resultado getResultado() {
        return mundo.getResultado();
    }

    @Override
    public void actualizar(double dt) {
        if (mundo.isTerminado()) {
            demoraFin += dt;
            if (demoraFin >= DEMORA_FIN) {
                if (siguiente == null) {
                    throw new IllegalStateException("falta conectar la escena siguiente");
                }
                getContexto().irA(siguiente);
            }
            return;
        }
        if (getEntrada().fuePresionada(KeyEvent.VK_ESCAPE)) {
            mundo.terminar("Partida terminada");
            return;
        }
        if (getEntrada().fuePresionada(KeyEvent.VK_P)) {
            pausado = !pausado;
        }
        if (!pausado) {
            mundo.actualizar(dt);
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        int ancho = getContexto().getAncho();
        int alto = getContexto().getAlto();
        g.setColor(new Color(14, 16, 26));
        g.fillRect(0, 0, ancho, alto);

        int origenX = (ancho - Mundo.ANCHO) / 2;
        g.translate(origenX, ORIGEN_Y);
        mundo.dibujar(g);
        g.translate(-origenX, -ORIGEN_Y);

        hud.mostrar(g, mundo, ancho, alto, pausado);
    }
}
