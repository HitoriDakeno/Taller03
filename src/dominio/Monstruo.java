package dominio;

import visitor.VisitorMinijuego;

/**
 * Clase abstracta que representa las características
 * generales que comparten todos los monstruos.
 */
public abstract class Monstruo {

    /** Cantidad de vida del monstruo. */
    private int vida;

    /** Velocidad del monstruo. */
    private int velocidad;

    /** Fuerza del monstruo. */
    private int fuerza;

    /** Resistencia del monstruo. */
    private int resistencia;

    /**
     * Constructor de la clase Monstruo.
     *
     * @param vida cantidad de vida
     * @param velocidad velocidad del monstruo
     * @param fuerza fuerza del monstruo
     * @param resistencia resistencia del monstruo
     */
    public Monstruo(int vida, int velocidad, int fuerza, int resistencia) {

        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;

    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    /**
     * Recibe un visitante que realizará el cálculo
     * correspondiente al minijuego.
     *
     * @param visitor visitante del minijuego
     * @return potencial calculado
     */
    public abstract double aceptar(VisitorMinijuego visitor);

    /**
     * Obtiene el nombre del tipo de monstruo.
     *
     * @return nombre del monstruo
     */
    public abstract String getTipo();

    /**
     * Devuelve los atributos del monstruo.
     *
     * @return información del monstruo
     */
    @Override
    public abstract String toString();

}