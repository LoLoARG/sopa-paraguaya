package juego.reglas;

/**
 * Datos que el HUD muestra. Lo implementa el Mundo (módulo C) y lo lee el HUD
 * (módulo D): así la pantalla no depende de cómo está armado el mundo.
 */
public interface Marcador {

    int getPuntaje();

    // Tamaño del cargador (Cola de pelotas por lanzar)
    int getPelotasEnCargador();

    // Tamaño del historial (Pila de aciertos)
    int getAciertos();

    // Texto del último acierto, por ejemplo "Goma +20"; vacío si todavía no hubo
    String getUltimoAcierto();

    // Nombre del material de la próxima pelota del cargador; vacío si no quedan
    String getProximaPelota();

    // Grados (0 = horizontal, 90 = vertical) y potencia de 0 a 1
    double getAngulo();

    double getPotencia();

    String getMensaje();

    boolean isTerminado();
}
