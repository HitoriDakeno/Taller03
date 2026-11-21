package visitor;

/**
 * Define las operaciones que debe realizar un visitante
 * sobre cada uno de los tipos de monstruos.
 */
public interface VisitorMinijuego {

    double visitar(Zombi zombi);

    double visitar(Vampiro vampiro);

    double visitar(Momia momia);

    double visitar(HombreLobo hombreLobo);

}