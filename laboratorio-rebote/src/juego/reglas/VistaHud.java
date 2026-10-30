package juego.reglas;

import java.awt.Graphics2D;

/**
 * Contrato del HUD. Lo define el paquete de reglas y lo implementa el de
 * pantallas: así la partida muestra un HUD sin depender de pantallas y no se
 * forma un ciclo entre paquetes.
 */
public interface VistaHud {

    void mostrar(Graphics2D g, Marcador datos, int ancho, int alto, boolean pausado);
}
