package dominio;

import taller03.visitor.VisitorMinijuego;

/**
 * Representa el minijuego Caza de Calabazas.
 *
 * Implementa VisitorMinijuego para calcular el potencial
 * de cada tipo de monstruo.
 */
public class CazaCalabazas implements Minijuego, VisitorMinijuego {

    @Override
    public String getNombre() {

        return "Caza de Calabazas";

    }

    @Override
    public String getDescripcion() {

        return "Muerde la mayor cantidad de calabazas del huerto en 60 segundos.";

    }

    @Override
    public String getHabilidadPremiada() {

        return "Mordedor";

    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {

        return monstruo.aceptar(this);

    }

    /**
     * Calcula el potencial de un zombi en la caza de calabazas.
     */
    @Override
    public double visitar(Zombi zombi) {

        return zombi.getFuerzaDeMordida() * zombi.getVida() / 100.0
                + zombi.getResistencia() / 2.0;

    }

    /**
     * Calcula el potencial de un vampiro en la caza de calabazas.
     */
    @Override
    public double visitar(Vampiro vampiro) {

        return (vampiro.getFuerza() + vampiro.getVelocidad()) / 3.0;

    }

    /**
     * Calcula el potencial de una momia en la caza de calabazas.
     */
    @Override
    public double visitar(Momia momia) {

        return momia.getLongitudVendajes() * momia.getFuerza() / 200.0;

    }

    /**
     * Calcula el potencial de un hombre lobo en la caza de calabazas.
     */
    @Override
    public double visitar(HombreLobo hombreLobo) {

        return hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 4.0
                + hombreLobo.getVelocidad() / 4.0;

    }

}