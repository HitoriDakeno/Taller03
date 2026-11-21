package dominio;

/**
 * Define las características y operaciones generales
 * que debe implementar cada minijuego.
 */
public interface MiniJuego {

    /**
     * Obtiene el nombre del minijuego.
     *
     * @return nombre del minijuego
     */
    String getNombre();

    /**
     * Obtiene la descripción del minijuego.
     *
     * @return descripción del minijuego
     */
    String getDescripcion();

    /**
     * Obtiene la habilidad que recibe una bonificación
     * durante el minijuego.
     *
     * @return nombre de la habilidad premiada
     */
    String getHabilidadPremiada();

    /**
     * Calcula el potencial de un monstruo.
     *
     * @param monstruo monstruo seleccionado
     * @return potencial calculado
     */
    double calcularPotencial(Monstruo monstruo);

}