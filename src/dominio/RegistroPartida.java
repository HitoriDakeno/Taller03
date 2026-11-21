package dominio;

/**
 * Representa el resultado de un minijuego durante una sesión.
 *
 * Almacena el nombre del minijuego, el monstruo utilizado,
 * la habilidad seleccionada y el puntaje obtenido.
 */
public class RegistroPartida {

    /** Nombre del minijuego realizado. */
    private String nombreMinijuego;

    /** Tipo de monstruo utilizado. */
    private String tipoMonstruo;

    /** Habilidad utilizada durante el minijuego. */
    private String habilidad;

    /** Puntaje obtenido en el minijuego. */
    private int puntaje;

    /**
     * Constructor de RegistroPartida.
     *
     * @param nombreMinijuego nombre del minijuego
     * @param tipoMonstruo tipo de monstruo utilizado
     * @param habilidad habilidad utilizada
     * @param puntaje puntaje obtenido
     */
    public RegistroPartida(String nombreMinijuego, String tipoMonstruo, String habilidad, int puntaje) {

        this.nombreMinijuego = nombreMinijuego;
        this.tipoMonstruo = tipoMonstruo;
        this.habilidad = habilidad;
        this.puntaje = puntaje;

    }

    public String getNombreMinijuego() {

        return nombreMinijuego;

    }

    public String getTipoMonstruo() {

        return tipoMonstruo;

    }

    public String getHabilidad() {

        return habilidad;

    }

    public int getPuntaje() {

        return puntaje;

    }

}