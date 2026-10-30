package juego.reglas;

/** Lo que queda al terminar una partida. Inmutable: se crea una vez y solo se lee. */
public final class Resultado {

    // Atributos
    private final int puntaje;
    private final int aciertos;
    private final int lanzadas;
    private final String motivo;

    // Constructor
    public Resultado(int puntaje, int aciertos, int lanzadas, String motivo) {
        if (puntaje < 0 || aciertos < 0 || lanzadas < aciertos) {
            throw new IllegalArgumentException("resultado inválido");
        }
        this.puntaje = puntaje;
        this.aciertos = aciertos;
        this.lanzadas = lanzadas;
        this.motivo = motivo == null ? "" : motivo;
    }

    // Consultas
    public int getPuntaje() {
        return puntaje;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getLanzadas() {
        return lanzadas;
    }

    public String getMotivo() {
        return motivo;
    }
}
