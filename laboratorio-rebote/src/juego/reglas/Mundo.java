package juego.reglas;

import contratos.Actualizable;
import contratos.Dibujable;
import java.awt.Color;
import java.awt.Graphics2D;
import ui.Texto;

/**
 * PROVISORIO (módulo C): reemplazar por el mundo real.
 * Hoy solo existe para que el esqueleto navegue y el HUD tenga algo que leer.
 * El mundo real contiene la Lista de pelotas y aro, la Cola del cargador y la
 * Pila de aciertos, y resuelve física, colisiones y puntaje (ver docs/ACTA.md).
 */
public class Mundo implements Actualizable, Dibujable, Marcador {

    // Constantes del tablero
    public static final int ANCHO = 900;
    public static final int ALTO = 520;

    // Estado provisorio
    private boolean terminado;
    private String motivo = "";

    // Constructor
    public Mundo() {
    }

    // TODO C: lanzar la próxima pelota del cargador con el ángulo y la potencia actuales
    public void lanzar() {
    }

    // TODO C: validar el rango (0 a 90 grados)
    public void setAngulo(double grados) {
    }

    // TODO C: validar el rango (0 a 1)
    public void setPotencia(double potencia) {
    }

    public void terminar(String motivo) {
        this.terminado = true;
        this.motivo = motivo;
    }

    public Resultado getResultado() {
        return new Resultado(0, 0, 0, motivo);
    }

    // TODO C: avanzar la física, mover el aro, detectar aciertos
    @Override
    public void actualizar(double dt) {
    }

    // TODO C: dibujar piso, aro y pelotas (cada entidad se dibuja sola)
    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(new Color(24, 28, 42));
        g.fillRect(0, 0, ANCHO, ALTO);
        g.setColor(new Color(90, 100, 140));
        g.drawRect(0, 0, ANCHO - 1, ALTO - 1);
        Texto.centrado(g, "Mundo en construcción (módulo C)", ANCHO / 2.0, ALTO / 2.0, 22,
                new Color(140, 148, 175));
    }

    // Marcador provisorio
    @Override
    public int getPuntaje() {
        return 0;
    }

    @Override
    public int getPelotasEnCargador() {
        return 0;
    }

    @Override
    public int getAciertos() {
        return 0;
    }

    @Override
    public String getUltimoAcierto() {
        return "";
    }

    @Override
    public String getProximaPelota() {
        return "";
    }

    @Override
    public double getAngulo() {
        return 45;
    }

    @Override
    public double getPotencia() {
        return 0.5;
    }

    @Override
    public String getMensaje() {
        return motivo;
    }

    @Override
    public boolean isTerminado() {
        return terminado;
    }
}
