package juego.pantallas;

import java.awt.Color;
import java.awt.Graphics2D;
import juego.reglas.Marcador;
import juego.reglas.VistaHud;
import ui.Texto;

/** HUD de la partida (módulo D). Provisorio: D lo mejora (barra de potencia, cargador, etc.). */
public class Hud implements VistaHud {

    // Colores
    private static final Color TEXTO = new Color(235, 238, 250);
    private static final Color APAGADO = new Color(140, 148, 175);

    @Override
    public void mostrar(Graphics2D g, Marcador datos, int ancho, int alto, boolean pausado) {
        Texto.izquierda(g, "PUNTAJE", 30, 28, 13, APAGADO);
        Texto.izquierda(g, String.valueOf(datos.getPuntaje()), 30, 62, 32, TEXTO);

        Texto.centrado(g, "Cargador: " + datos.getPelotasEnCargador()
                + (datos.getProximaPelota().isEmpty() ? "" : "  (sigue: " + datos.getProximaPelota() + ")"),
                ancho / 2.0, 36, 18, TEXTO);
        Texto.centrado(g, "Ángulo " + (int) datos.getAngulo() + "°   Potencia " + (int) (datos.getPotencia() * 100) + "%",
                ancho / 2.0, 64, 15, APAGADO);

        Texto.derecha(g, "ACIERTOS", ancho - 30, 28, 13, APAGADO);
        Texto.derecha(g, String.valueOf(datos.getAciertos()), ancho - 30, 62, 32, TEXTO);
        if (!datos.getUltimoAcierto().isEmpty()) {
            Texto.derecha(g, datos.getUltimoAcierto(), ancho - 30, 84, 14, new Color(250, 215, 90));
        }

        if (datos.isTerminado()) {
            cartel(g, ancho, alto, "FIN DE LA PARTIDA", datos.getMensaje());
        } else if (pausado) {
            cartel(g, ancho, alto, "PAUSA", "P para seguir");
        }
    }

    private void cartel(Graphics2D g, int ancho, int alto, String titulo, String detalle) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, ancho, alto);
        Texto.centrado(g, titulo, ancho / 2.0, alto / 2.0 - 6, 44, TEXTO);
        Texto.centrado(g, detalle, ancho / 2.0, alto / 2.0 + 30, 18, APAGADO);
    }
}
