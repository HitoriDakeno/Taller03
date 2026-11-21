package dominio;

import Taller03.visitor.VisitorMinijuego;

/**
 * Representa un monstruo de tipo Zombi.
 *
 * Hereda los atributos generales de Monstruo e implementa
 * las habilidades Mordedor y Regenerable.
 */
public class Zombi extends Monstruo implements Mordedor, Regenerable {

    /** Fuerza de mordida del zombi. */
    private int fuerzaDeMordida;

    /**
     * Constructor de Zombi.
     *
     * @param vida cantidad de vida
     * @param velocidad velocidad
     * @param fuerza fuerza
     * @param resistencia resistencia
     * @param fuerzaDeMordida fuerza de mordida
     */
    public Zombi(int vida, int velocidad, int fuerza,
                 int resistencia, int fuerzaDeMordida) {

        super(vida, velocidad, fuerza, resistencia);

        this.fuerzaDeMordida = fuerzaDeMordida;

    }

    public int getFuerzaDeMordida() {
        return fuerzaDeMordida;
    }

    /**
     * Ejecuta la habilidad de morder.
     */
    @Override
    public String morder() {

        return "¡El zombi clava sus dientes con una mordida aterradora!";

    }

    /**
     * Ejecuta la habilidad de regenerarse.
     */
    @Override
    public String regenerarse() {

        return "¡El zombi recupera parte de su energía!";

    }

    /**
     * Permite que el visitante calcule el potencial del zombi.
     */
    @Override
    public double aceptar(VisitorMinijuego visitor) {

        return visitor.visitar(this);

    }

    @Override
    public String getTipo() {

        return "Zombi";

    }

    /**
     * Muestra los atributos generales y propios del zombi.
     */
    @Override
    public String toString() {

        return "Zombi | Vida " + getVida()
                + " | Vel " + getVelocidad()
                + " | Fue " + getFuerza()
                + " | Res " + getResistencia()
                + " | Mordida " + fuerzaDeMordida;

    }

}