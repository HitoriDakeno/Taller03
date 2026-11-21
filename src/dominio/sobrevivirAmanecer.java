package dominio;

import visitor.VisitorMinijuego;

/**
 * Representa el minijuego Sobrevivir al Amanecer.
 *
 * Implementa VisitorMinijuego para calcular el potencial
 * de cada tipo de monstruo.
 */
public class SobrevivirAmanecer implements MiniJuego, VisitorMinijuego {

    @Override
    public String getNombre() {

        return "Sobrevivir al Amanecer";

    }

    @Override
    public String getDescripcion() {

        return "Aguanta en pie cuando salgan los primeros rayos de sol.";

    }

    @Override
    public String getHabilidadPremiada() {

        return "Regenerable";

    }

    @Override
    public double calcularPotencial(Monstruo monstruo) {

        return monstruo.aceptar(this);

    }

    /**
     * Calcula el potencial de un zombi durante el amanecer.
     */
    @Override
    public double visitar(Zombi zombi) {

        return zombi.getResistencia() * zombi.getVida() / 100.0;

    }

    /**
     * Calcula el potencial de un vampiro durante el amanecer.
     */
    @Override
    public double visitar(Vampiro vampiro) {

        return vampiro.getResistencia()
                * vampiro.getResistenciaAlSol() / 100.0;

    }

    /**
     * Calcula el potencial de una momia durante el amanecer.
     */
    @Override
    public double visitar(Momia momia) {

        return ((momia.getResistencia() + momia.getVida()) / 2.0)
                * (1.0 + momia.getLongitudVendajes() / 300.0);

    }

    /**
     * Calcula el potencial de un hombre lobo durante el amanecer.
     */
    @Override
    public double visitar(HombreLobo hombreLobo) {

        return ((hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0)
                * (5.0 - hombreLobo.getFaseLunar()) / 4.0;

    }

}