package dominio;

import taller03.visitor.VisitorMinijuego;

/**
 * Representa un monstruo de tipo Momia.
 *
 * Implementa las habilidades Regenerable y Furioso.
 */
public class Momia extends Monstruo implements Regenerable, Furioso {

    /** Longitud de los vendajes de la momia. */
    private int longitudVendajes;

    /**
     * Constructor de Momia.
     *
     * @param vida cantidad de vida
     * @param velocidad velocidad
     * @param fuerza fuerza
     * @param resistencia resistencia
     * @param longitudVendajes longitud de los vendajes
     */
    public Momia(int vida, int velocidad, int fuerza,
                 int resistencia, int longitudVendajes) {

        super(vida, velocidad, fuerza, resistencia);

        this.longitudVendajes = longitudVendajes;

    }

    public int getLongitudVendajes() {
        return longitudVendajes;
    }

    @Override
    public String regenerarse() {

        return "¡La momia recompone sus vendajes y se recupera!";

    }

    @Override
    public String enfurecerse() {

        return "¡La momia libera toda su furia ancestral!";

    }

    /**
     * Entrega la momia al visitante correspondiente.
     */
    @Override
    public double aceptar(VisitorMinijuego visitor) {

        return visitor.visitar(this);

    }

    @Override
    public String getTipo() {

        return "Momia";

    }

    @Override
    public String toString() {

        return "Momia | Vida " + getVida()
                + " | Vel " + getVelocidad()
                + " | Fue " + getFuerza()
                + " | Res " + getResistencia()
                + " | Vendajes " + longitudVendajes;

    }

}