package dominio;


import taller03.visitor.VisitorMinijuego;

/**
 * Representa un monstruo de tipo HombreLobo.
 *
 * Implementa las habilidades Mordedor y Furioso.
 */
public class HombreLobo extends Monstruo implements Mordedor, Furioso {

    /** Nivel de ferocidad del hombre lobo. */
    private int ferocidad;

    /** Fase lunar del hombre lobo. */
    private int faseLunar;

    /**
     * Constructor de HombreLobo.
     *
     * @param vida cantidad de vida
     * @param velocidad velocidad
     * @param fuerza fuerza
     * @param resistencia resistencia
     * @param ferocidad nivel de ferocidad
     * @param faseLunar fase de la luna
     */
    public HombreLobo(int vida, int velocidad, int fuerza,
                      int resistencia, int ferocidad, int faseLunar) {

        super(vida, velocidad, fuerza, resistencia);

        this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;

    }

    public int getFerocidad() {
        return ferocidad;
    }

    public int getFaseLunar() {
        return faseLunar;
    }

    @Override
    public String morder() {

        return "¡El hombre lobo ataca con una mordida salvaje!";

    }

    @Override
    public String enfurecerse() {

        return "¡El hombre lobo aumenta su ferocidad!";

    }

    /**
     * Entrega el hombre lobo al visitante correspondiente.
     */
    @Override
    public double aceptar(VisitorMinijuego visitor) {

        return visitor.visitar(this);

    }

    @Override
    public String getTipo() {

        return "HombreLobo";

    }

    @Override
    public String toString() {

        return "HombreLobo | Vida " + getVida()
                + " | Vel " + getVelocidad()
                + " | Fue " + getFuerza()
                + " | Res " + getResistencia()
                + " | Ferocidad " + ferocidad
                + " | Luna " + faseLunar;

    }

}