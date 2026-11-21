package dominio;

/**
 * Representa a un jugador registrado en el sistema.
 *
 * Almacena su nombre y su puntaje máximo histórico.
 */
public class Jugador {

    /** Nombre del jugador. */
    private String nombre;

    /** Puntaje máximo alcanzado por el jugador. */
    private int puntajeMaximo;

    /**
     * Constructor de Jugador.
     *
     * @param nombre nombre del jugador
     * @param puntajeMaximo récord histórico
     */
    public Jugador(String nombre, int puntajeMaximo) {

        this.nombre = nombre;
        this.puntajeMaximo = puntajeMaximo;

    }

    public String getNombre() {

        return nombre;

    }

    public int getPuntajeMaximo() {

        return puntajeMaximo;

    }

    /**
     * Actualiza el récord máximo del jugador.
     *
     * @param puntajeMaximo nuevo puntaje máximo
     */
    public void setPuntajeMaximo(int puntajeMaximo) {

        this.puntajeMaximo = puntajeMaximo;

    }

}