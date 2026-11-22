package dominio;

import visitor.VisitorMinijuego;

/**
 * Representa un monstruo de tipo Vampiro.
 *
 * Implementa las habilidades Mordedor y Volador.
 */
public class Vampiro extends Monstruo implements Mordedor, Volador {

    /** Velocidad de vuelo del vampiro. */
    private int velocidadDeVuelo;

    /** Resistencia del vampiro ante la luz solar. */
    private int resistenciaAlSol;

    /**
     * Constructor de Vampiro.
     *
     * @param vida cantidad de vida
     * @param velocidad velocidad
     * @param fuerza fuerza
     * @param resistencia resistencia
     * @param velocidadDeVuelo velocidad de vuelo
     * @param resistenciaAlSol resistencia al sol
     */
    public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {

        super(vida, velocidad, fuerza, resistencia);

        this.velocidadDeVuelo = velocidadDeVuelo;
        this.resistenciaAlSol = resistenciaAlSol;

    }

    public int getVelocidadDeVuelo() {
        return velocidadDeVuelo;
    }

    public int getResistenciaAlSol() {
        return resistenciaAlSol;
    }

    @Override
    public String morder() {

        return "El vampiro ataca con sus colmillos!";

    }

    @Override
    public String volar() {

        return "El vampiro despliega sus alas y surca el aire!";

    }

    /**
     * Entrega el vampiro al visitante correspondiente.
     */
    @Override
    public double aceptar(VisitorMinijuego visitor) {

        return visitor.visitar(this);

    }

    @Override
    public String getTipo() {

        return "Vampiro";

    }

    @Override
    public String toString() {

        return "Vampiro | Vida " + getVida()  + " | Vel " + getVelocidad() + " | Fue " + getFuerza() + " | Res " + getResistencia()+ " | Vuelo " + velocidadDeVuelo+ " | Sol " + resistenciaAlSol;

    }

}