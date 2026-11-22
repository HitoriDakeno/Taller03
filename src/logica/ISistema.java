package logica;

import java.util.ArrayList;
import java.util.LinkedList;

import dominio.*;

/**
 *
 * Define las operaciones que debe proporcionar
 * el sistema de Spooky Season.
 */


public interface ISistema {
    /**
     * Recibe los campos de un monstruo y solicita su creación.
     *
     * @param campos atributos separados del archivo
     * @return true si el monstruo fue agregado correctamente
     */
    boolean agregarMonstruo(String[] campos);

    /**
     * Recibe los campos de un jugador y solicita su registro.
     *
     * @param campos datos separados del archivo
     * @return true si el registro fue agregado correctamente
     */
    boolean agregarRegistro(String[] campos);

    int getCantidadMonstruos();

    int getCantidadJugadores();

    void iniciarSesion(String nombre);

    String getNombreJugadorActual();

    int getPuntajeAcumulado();

    ArrayList<Monstruo> obtenerMonstruos();

    ArrayList<Monstruo> obtenerMonstruosDisponibles();

    boolean estaMonstruoDisponible(Monstruo monstruo);

    MiniJuego obtenerMinijuego(int indice);

    boolean estaMinijuegoJugado(int indice);

    int obtenerPuntajeMinijuego(int indice);

    double calcularPotencial(int indice, Monstruo monstruo);

    RegistroPartida registrarResultado(int indice, Monstruo monstruo, String habilidad);

    LinkedList<RegistroPartida> obtenerHistorial();

    ArrayList<Jugador> obtenerRanking();

    boolean existeJugador(String nombre);

    int obtenerRecord(String nombre);

    String terminarSesion();

    boolean seJugoEnLaSesion();

    ArrayList<Jugador> obtenerJugadores();

}