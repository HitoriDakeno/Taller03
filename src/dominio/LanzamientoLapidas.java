package dominio;

import visitor.VisitorMinijuego;
/**
 * Representa el minijuego Lanzamiento de Lápidas.
 *
 * Implementa VisitorMinijuego para calcular el potencial
 * de cada tipo de monstruo.
 */
public class LanzamientoLapidas implements MiniJuego, VisitorMinijuego {

    @Override
    public String getNombre() {

        return "Lanzamiento de Lápidas";

    }

    @Override
    public String getDescripcion() {

        return "Lanza una lápida lo más lejos posible.";

    }

    @Override
    public String getHabilidadPremiada() {

        return "Furioso";

    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {

        return monstruo.aceptar(this);

    }

    /**
     * Calcula el potencial de un zombi en el lanzamiento.
     */
    @Override
    public double visitar(Zombi zombi) {

        return zombi.getFuerza() * zombi.getResistencia() / 100.0;

    }

    /**
     * Calcula el potencial de un vampiro en el lanzamiento.
     */
    @Override
    public double visitar(Vampiro vampiro) {

        return vampiro.getFuerza() + vampiro.getVelocidadDeVuelo() / 4.0;

    }

    /**
     * Calcula el potencial de una momia en el lanzamiento.
     */
    @Override
    public double visitar(Momia momia) {

        return momia.getFuerza() + momia.getLongitudVendajes() / 10.0;

    }

    /**
     * Calcula el potencial de un hombre lobo en el lanzamiento.
     */
    @Override
    public double visitar(HombreLobo hombreLobo) {

        return hombreLobo.getFuerza() + hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8.0;

    }

}