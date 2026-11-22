package logica;

import java.util.ArrayList;
import java.util.LinkedList;

import dominio.*;
/**
 * Implementa las operaciones de la interfaz Sistema.
 *
 * Esta clase administra los datos y la lógica del juego.
 * No utiliza Scanner, no lee archivos y no imprime por consola.
 */
public class SistemaImpl implements ISistema {

    /** Lista de todos los monstruos cargados. */
    private ArrayList<Monstruo> monstruos;

    /** Lista de jugadores registrados. */
    private ArrayList<Jugador> jugadores;

    /** Historial de los minijuegos de la sesión actual. */
    private LinkedList<RegistroPartida> historial;

    /** Arreglo que almacena los cuatro minijuegos. */
    private MiniJuego[] minijuegos;

    /** Indica qué monstruos ya fueron utilizados. */
    private boolean[] monstruosUsados;

    /** Indica qué minijuegos ya fueron realizados. */
    private boolean[] minijuegosJugados;

    /** Nombre del jugador que inició la sesión actual. */
    private String nombreJugadorActual;

    /** Puntaje acumulado durante la sesión actual. */
    private int puntajeAcumulado;

    /**
     * Constructor de SistemaImplementado.
     *
     * Inicializa las colecciones y los cuatro minijuegos.
     */
    public SistemaImpl() {

        monstruos = new ArrayList<Monstruo>();
        jugadores = new ArrayList<Jugador>();
        historial = new LinkedList<RegistroPartida>();

        minijuegos = new MiniJuego[4];

        minijuegos[0] = new CarreraCementerio();
        minijuegos[1] = new CazaCalabazas();
        minijuegos[2] = new LanzamientoLapidas();
        minijuegos[3] = new SobrevivirAmanecer();

        monstruosUsados = new boolean[0];
        minijuegosJugados = new boolean[4];

        nombreJugadorActual = "";
        puntajeAcumulado = 0;

    }

    /**
     * Valida los datos recibidos y crea el monstruo correspondiente.
     *
     * @param campos atributos obtenidos del archivo Monstruos.txt
     * @return true si se pudo crear el monstruo
     */
    @Override
    public boolean agregarMonstruo(String[] campos) {

        if (campos == null || campos.length == 0) {
            return false;
        }

        try {

            String tipo = campos[0];

            int vida;
            int velocidad;
            int fuerza;
            int resistencia;

            if (tipo.equals("Zombi") && campos.length == 6) {

                vida = Integer.parseInt(campos[1]);
                velocidad = Integer.parseInt(campos[2]);
                fuerza = Integer.parseInt(campos[3]);
                resistencia = Integer.parseInt(campos[4]);

                int mordida = Integer.parseInt(campos[5]);

                if (!validarBase(vida, velocidad, fuerza, resistencia) || !validarRango(mordida, 1, 100)) {

                    return false;

                }

                monstruos.add(new Zombi(vida, velocidad, fuerza, resistencia, mordida));

            } else if (tipo.equals("Vampiro") && campos.length == 7) {

                vida = Integer.parseInt(campos[1]);
                velocidad = Integer.parseInt(campos[2]);
                fuerza = Integer.parseInt(campos[3]);
                resistencia = Integer.parseInt(campos[4]);

                int vuelo = Integer.parseInt(campos[5]);
                int sol = Integer.parseInt(campos[6]);

                if (!validarBase(vida, velocidad, fuerza, resistencia) || !validarRango(vuelo, 1, 100)  || !validarRango(sol, 1, 100)) {
                    return false;

                }

                monstruos.add(new Vampiro(vida, velocidad, fuerza,
                        resistencia, vuelo, sol));

            } else if (tipo.equals("Momia") && campos.length == 6) {

                vida = Integer.parseInt(campos[1]);
                velocidad = Integer.parseInt(campos[2]);
                fuerza = Integer.parseInt(campos[3]);
                resistencia = Integer.parseInt(campos[4]);

                int vendajes = Integer.parseInt(campos[5]);

                if (!validarBase(vida, velocidad, fuerza, resistencia) || !validarRango(vendajes, 1, 300)) {

                    return false;

                }

                monstruos.add(new Momia(vida, velocidad, fuerza, resistencia, vendajes));

            } else if (tipo.equals("HombreLobo") && campos.length == 7) {

                vida = Integer.parseInt(campos[1]);
                velocidad = Integer.parseInt(campos[2]);
                fuerza = Integer.parseInt(campos[3]);
                resistencia = Integer.parseInt(campos[4]);

                int ferocidad = Integer.parseInt(campos[5]);
                int luna = Integer.parseInt(campos[6]);

                if (!validarBase(vida, velocidad, fuerza, resistencia) || !validarRango(ferocidad, 1, 100) || !validarRango(luna, 1, 4)) {

                    return false;

                }

                monstruos.add(new HombreLobo(vida, velocidad, fuerza, resistencia, ferocidad, luna));

            } else {
                return false;

            }

            actualizarMonstruosUsados();

            return true;

        } catch (NumberFormatException e) {

            return false;

        }

    }

    /**
     * Comprueba que los atributos generales estén dentro de sus rangos.
     */
    private boolean validarBase(int vida, int velocidad, int fuerza, int resistencia) {

        return validarRango(vida, 1, 200)
                && validarRango(velocidad, 1, 100)
                && validarRango(fuerza, 1, 100)
                && validarRango(resistencia, 1, 100);

    }

    /**
     * Comprueba que un valor entero esté dentro de un rango.
     */
    private boolean validarRango(int valor, int minimo, int maximo) {

        return valor >= minimo && valor <= maximo;

    }

    /**
     * Ajusta el arreglo de monstruos utilizados cuando se agrega
     * un nuevo monstruo a la colección.
     */
    private void actualizarMonstruosUsados() {

        boolean[] nuevaLista = new boolean[monstruos.size()];

        for (int i = 0; i < monstruosUsados.length; i++) {

            nuevaLista[i] = monstruosUsados[i];

        }

        monstruosUsados = nuevaLista;

    }

    /**
     * Valida y agrega un jugador proveniente de Registros.txt.
     *
     * @param campos nombre y puntaje máximo
     * @return true si el jugador fue agregado
     */
    @Override
    public boolean agregarRegistro(String[] campos) {

        if (campos == null || campos.length != 2) {
            return false;
        }

        try {

            String nombre = campos[0];
            int puntaje = Integer.parseInt(campos[1]);

            if (nombre.length() == 0
                    || nombre.indexOf(';') != -1
                    || puntaje < 0) {

                return false;

            }

            if (existeJugador(nombre)) {
                return false;
            }

            jugadores.add(new Jugador(nombre, puntaje));

            return true;

        } catch (NumberFormatException e) {

            return false;

        }

    }

    @Override
    public int getCantidadMonstruos() {

        return monstruos.size();

    }

    @Override
    public int getCantidadJugadores() {

        return jugadores.size();

    }

    /**
     * Inicializa una sesión nueva para un jugador.
     *
     * Todos los minijuegos y monstruos vuelven a estar disponibles.
     *
     * @param nombre nombre del jugador
     */
    @Override
    public void iniciarSesion(String nombre) {

        nombreJugadorActual = nombre;

        puntajeAcumulado = 0;

        historial.clear();

        minijuegosJugados = new boolean[4];

        monstruosUsados = new boolean[monstruos.size()];

    }

    @Override
    public String getNombreJugadorActual() {

        return nombreJugadorActual;

    }

    @Override
    public int getPuntajeAcumulado() {

        return puntajeAcumulado;

    }

    /**
     * Obtiene la colección completa de monstruos.
     */
    @Override
    public ArrayList<Monstruo> obtenerMonstruos() {

        return monstruos;

    }

    /**
     * Obtiene una nueva lista con los monstruos disponibles.
     */
    @Override
    public ArrayList<Monstruo> obtenerMonstruosDisponibles() {

        ArrayList<Monstruo> disponibles = new ArrayList<Monstruo>();

        for (int i = 0; i < monstruos.size(); i++) {

            if (!monstruosUsados[i]) {

                disponibles.add(monstruos.get(i));

            }

        }

        return disponibles;

    }

    /**
     * Comprueba si un monstruo todavía puede utilizarse.
     *
     * Se compara mediante identidad de objeto, no por tipo.
     * Esto permite utilizar dos zombis diferentes en una sesión.
     */
    @Override
    public boolean estaMonstruoDisponible(Monstruo monstruo) {

        for (int i = 0; i < monstruos.size(); i++) {

            if (monstruos.get(i) == monstruo) {

                return !monstruosUsados[i];

            }

        }

        return false;

    }

    /**
     * Busca la posición de un monstruo dentro de la colección.
     */
    private int buscarIndiceMonstruo(Monstruo monstruo) {

        for (int i = 0; i < monstruos.size(); i++) {

            if (monstruos.get(i) == monstruo) {

                return i;

            }

        }

        return -1;

    }

    /**
     * Obtiene un minijuego mediante su posición.
     *
     * @param indice posición del minijuego
     * @return minijuego solicitado o null si el índice no es válido
     */
    @Override
    public MiniJuego obtenerMinijuego(int indice) {

        if (indice < 0 || indice >= minijuegos.length) {

            return null;

        }

        return minijuegos[indice];

    }

    /**
     * Comprueba si un minijuego ya fue realizado.
     */
    @Override
    public boolean estaMinijuegoJugado(int indice) {

        if (indice < 0 || indice >= minijuegosJugados.length) {

            return true;

        }

        return minijuegosJugados[indice];

    }

    /**
     * Obtiene el puntaje obtenido en un minijuego.
     */
    @Override
    public int obtenerPuntajeMinijuego(int indice) {

        if (indice < 0 || indice >= minijuegos.length) {
            return 0;
        }

        for (int i = 0; i < historial.size(); i++) {

            if (historial.get(i).getNombreMinijuego().equals(minijuegos[indice].getNombre())) {

                return historial.get(i).getPuntaje();

            }

        }

        return 0;

    }

    /**
     * Calcula el potencial de un monstruo en un minijuego.
     *
     * El cálculo se realiza mediante Visitor.
     */
    @Override
    public double calcularPotencial(int indice, Monstruo monstruo) {

        if (indice < 0 || indice >= minijuegos.length || monstruo == null) {

            return 0.0;

        }

        return minijuegos[indice].calcularPotencial(monstruo);

    }

    /**
     * Calcula el puntaje y registra el resultado de un minijuego.
     *
     * Si la habilidad utilizada coincide con la premiada,
     * se aplica el multiplicador 1.5.
     *
     * @param indice posición del minijuego
     * @param monstruo monstruo utilizado
     * @param habilidad habilidad seleccionada
     * @return registro creado o null si no se pudo realizar
     */
    @Override
    public RegistroPartida registrarResultado(int indice,
            Monstruo monstruo, String habilidad) {

        if (indice < 0 || indice >= minijuegos.length
                || monstruo == null
                || estaMinijuegoJugado(indice)
                || !estaMonstruoDisponible(monstruo)) {

            return null;

        }

        double potencial = calcularPotencial(indice, monstruo);

        double multiplicador = 1.0;

        if (habilidad != null && habilidad.equals(minijuegos[indice].getHabilidadPremiada())) {

            multiplicador = 1.5;

        }

        int puntaje = (int) Math.round(potencial * 10.0 * multiplicador);

        String habilidadGuardada = habilidad;

        if (habilidadGuardada == null || habilidadGuardada.length() == 0) {

            habilidadGuardada = "Ninguna";

        }

        RegistroPartida registro = new RegistroPartida(
                minijuegos[indice].getNombre(),
                monstruo.getTipo(),
                habilidadGuardada,
                puntaje
        );

        historial.add(registro);

        puntajeAcumulado += puntaje;

        minijuegosJugados[indice] = true;

        int indiceMonstruo = buscarIndiceMonstruo(monstruo);

        if (indiceMonstruo != -1) {

            monstruosUsados[indiceMonstruo] = true;

        }

        return registro;

    }

    /**
     * Obtiene el historial de la sesión actual.
     */
    @Override
    public LinkedList<RegistroPartida> obtenerHistorial() {

        return historial;

    }

    /**
     * Obtiene una copia de los jugadores ordenada por puntaje
     * descendente y, en caso de empate, por nombre ascendente.
     *
     * Utiliza ordenamiento por selección.
     */
    @Override
    public ArrayList<Jugador> obtenerRanking() {

        ArrayList<Jugador> ranking = new ArrayList<Jugador>();

        for (int i = 0; i < jugadores.size(); i++) {

            ranking.add(jugadores.get(i));

        }

        for (int i = 0; i < ranking.size() - 1; i++) {

            int posicionMayor = i;

            for (int j = i + 1; j < ranking.size(); j++) {

                Jugador actual = ranking.get(j);
                Jugador mayor = ranking.get(posicionMayor);

                if (actual.getPuntajeMaximo() > mayor.getPuntajeMaximo()
                        || (actual.getPuntajeMaximo() == mayor.getPuntajeMaximo()
                        && actual.getNombre().compareToIgnoreCase(
                                mayor.getNombre()) < 0)) {

                    posicionMayor = j;

                }

            }

            Jugador auxiliar = ranking.get(i);

            ranking.set(i, ranking.get(posicionMayor));

            ranking.set(posicionMayor, auxiliar);

        }

        return ranking;

    }

    /**
     * Comprueba si un jugador ya se encuentra registrado.
     */
    @Override
    public boolean existeJugador(String nombre) {

        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {

                return true;

            }

        }

        return false;

    }

    /**
     * Obtiene el récord histórico de un jugador.
     */
    @Override
    public int obtenerRecord(String nombre) {

        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {

                return jugadores.get(i).getPuntajeMaximo();

            }

        }

        return 0;

    }

    /**
     * Termina la sesión y actualiza el récord del jugador cuando corresponde.
     *
     * @return mensaje con el resultado de la sesión
     */
    @Override
    public String terminarSesion() {

        if (historial.size() == 0) {

            return "No jugaste ningún minijuego, no se guardó registro.";

        }

        for (int i = 0; i < jugadores.size(); i++) {

            Jugador jugador = jugadores.get(i);

            if (jugador.getNombre().equalsIgnoreCase(nombreJugadorActual)) {

                if (puntajeAcumulado > jugador.getPuntajeMaximo()) {

                    int anterior = jugador.getPuntajeMaximo();

                    jugador.setPuntajeMaximo(puntajeAcumulado);

                    return "¡Nuevo récord! Superaste tu marca anterior de " + anterior + " puntos.";

                }

                return "No superaste tu récord ("
                        + jugador.getPuntajeMaximo()
                        + "). ¡Suerte para la próxima!";

            }

        }

        jugadores.add(new Jugador(nombreJugadorActual, puntajeAcumulado));

        return "¡Jugador agregado al registro con "
                + puntajeAcumulado + " puntos!";

    }

    /**
     * Indica si se realizó al menos un minijuego en la sesión.
     */
    @Override
    public boolean seJugoEnLaSesion() {

        return historial.size() > 0;

    }

    /**
     * Obtiene la colección de jugadores registrados.
     */
    @Override
    public ArrayList<Jugador> obtenerJugadores() {

        return jugadores;

    }

}