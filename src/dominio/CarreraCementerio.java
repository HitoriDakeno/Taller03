package dominio;

import taller03.visitor.VisitorMinijuego;

/**
 * Representa el minijuego Carrera del Cementerio.
 *
 * Implementa VisitorMinijuego para calcular el potencial
 * de cada uno de los cuatro tipos de monstruos.
 */
public class CarreraCementerio implements Minijuego, VisitorMinijuego {

    @Override
    public String getNombre() {

        return "Carrera del Cementerio";

    }

    @Override
    public String getDescripcion() {

        return "Recorre el cementerio de punta a punta antes de que el sepulturero te vea.";

    }

    @Override
    public String getHabilidadPremiada() {

        return "Volador";

    }

    /**
     * Envía el monstruo al visitante para calcular su potencial.
     */
    @Override
    public double calcularPotencial(Monstruo monstruo) {

        return monstruo.aceptar(this);

    }

    /**
     * Calcula el potencial de un zombi en la carrera.
     */
    @Override
    public double visitar(Zombi zombi) {

        return zombi.getVelocidad() * zombi.getResistencia() / 100.0;

    }

    /**
     * Calcula el potencial de un vampiro en la carrera.
     */
    @Override
    public double visitar(Vampiro vampiro) {

        return (vampiro.getVelocidad()
                + 2.0 * vampiro.getVelocidadDeVuelo()) / 2.0;

    }

    /**
     * Calcula el potencial de una momia en la carrera.
     */
    @Override
    public double visitar(Momia momia) {

        return momia.getVelocidad() * 100.0
                / (100.0 + momia.getLongitudVendajes());

    }

    /**
     * Calcula el potencial de un hombre lobo en la carrera.
     */
    @Override
    public double visitar(HombreLobo hombreLobo) {

        return (hombreLobo.getVelocidad()
                + hombreLobo.getFerocidad() / 5.0)
                * hombreLobo.getFaseLunar() / 4.0;

    }

}