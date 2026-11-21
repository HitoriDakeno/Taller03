//David Villalobos - 21.646.173-8 - ICCI

package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sun.jvm.hotspot.tools.SysPropsDumper;


/**
 * Clase principal del programa
 * 
 * Se encarga de la interacción con el usuario,
 * la lectura de archivos y la escritura de registros.
 * 
 */
public class Main
{
	/** Permite leer las entradas del usuario. */
	private static Scanner lector = new Scanner(System.in);
	
	// Sistema que administra la logica del juego.
	private static ISistema system = new SistemaImpl();
	
	private static String archMonstruos = "Monstruos.txt";
	private static String archRegistros = "Registros.txt";
	
	
	/**
	 * 
	 * metodo principal del programa.
	 * 
	 * Carga los archivos y permite iniciar sesiones
	 * de diferentes jugadores
	 * 
	 * 
	 * @param args argumentos de ejecucion
	 */
	
	public static void main(String[] args) {
		
		System.out.println("Leyendo Archivos....");
		
		cargarMonstruos();
		
		if(system.getCantidadMostruos() == 0)
		{
			System.out.println("No hay mostruos válidos!");
			System.out.println("finalizando programa.");
			return;
		}
		
		cargarRegistros();
		
		System.out.println("- "+system.getCantidadMostruos() + " monstruos cargados desde "+archMonstruos);
		
		System.out.println("- "+system.getCantidadJugadores() + " jugadores cargados desde "+archRegistros);
		
		
		boolean salir = false;
		
		while (salir != true) {
			
			iniciarSesion();
			
			boolean terminar = false;
			
			while(terminar != true) {
				mostrarMenuPrincipal();
				
				int opcion = leerEntero("- ");
				
				switch(opcion) {
				
				case 1:
					menuMiniJuegos();
					break;
				case 2:
					mostrarCatalogo();
					break;
				case 3:
					mostrarHistorial();
					break;
				case 4:
					mostrarRanking();
					break;
					
				case 5:
					terminarSesion();
					terminar = true;
					break;
				case 6:
					terminarSesion();
					terminar = true;
					salir = true;
					break;
				default:
					System.out.println("Opcion fuera de rango.");
					break;
					
				}
				
			}
			
			
		}
	
		System.out.println("¡GRACIAS POR JUGAR!");
		lector.close();
	}
	
	
	/**
	 * lee el archivo Registros.txt
	 * 
	 * si el archivo no existe, el sistema comienza
	 * con una linea de jugadores vacia.
	 * 
	 */
	
	private static void cargarRegistros()
	{
		File arch = new File(archRegistros);
		
		if(!arch.exists()) {
			
			System.out.println("No se encontro "+ archRegistros);
			return;
		}
		
		try {
			Scanner s = new Scanner(arch);
			int numLine = 0;
			
			while(s.hasNextLine()) {
				
				String linea = s.nextLine();
				
				numLine++;
				
				String[] campos = linea.split(";", -1);
				
				if(!system.agregarRegistro(campos)) {
					
					System.out.println("linea " + numLine + "de "+ archRegistros+ " descartada: " + linea);
				}
		
			}
			s.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("No se pudo abrir "+archRegistros);
		}
		
	}

	/**
	 * lee archivo Montruos.txt
	 * 
	 * Cada linea se separa mediante split y se encia al 
	 * sistema para validar y crear el monstruo
	 * 
	 */
	
	private static void cargarMonstruos()
	{
		File arch = new File(archMonstruos);
		
		if(!arch.exists()) {
			
			System.out.println("No se encontro "+ archMonstruos);
			return;
		}
		
		try {
			Scanner s = new Scanner(arch);
			int numLine = 0;
			
			while(s.hasNextLine()) {
				
				String linea = s.nextLine();
				
				numLine++;
				
				String[] campos = linea.split(";", -1);
				
				if(!system.agregarMonstruo(campos)) {
					
					System.out.println("linea " + numLine + "de "+ archMonstruos+ " descartada: " + linea);
				}
		
			}
			s.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("No se pudo abrir "+archMonstruos);
		}
		
	}


	private static void mostrarMenuPrincipal()
	{
		// TODO Auto-generated method stub
		
	}


	private static void menuMiniJuegos()
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Detecta las interfaces que implementa un monstruo
	 * y muestra unicamente las habilidades disponibles.
	 * 
	 * 
	 * @param monstruo monstruo seleccinado
	 * @return habilidad seleccionada
	 */
	
	private static String seleccionarHabilidad(Monstruo monstruo) {
		ArrayList<String> habilidades = new ArrayList<String>();
		
		if(monstruo instanceof Mordedor) {
			habilidades.add("Mordedor");
		}
		
		if(monstruo instanceof Volador) {
			habilidades.add("Volador");
		}
		
		if(monstruo instanceof Regenerable) {
			
			habilidades.add("Regenerable");
		}
		
		if(monstruo instanceof Furioso) {
			habilidades.add("Furioso");
		}
		
		
		System.out.println();
		
		System.out.println("Quieres usar una habilidad?");
		
		for(int i = 0; i < habilidades.size(); i++) {
			System.out.println((i+1) + ") "+nombreHabilidad(habilidades.get(i)));
			
		}
		
		System.out.println("0) No usar habilidad");
		
		int opcion = leerEntero("> ");
		
		if(opcion == 0) {
			return "Ninguna";
		}
		
		if(opcion < 1 || opcion > habilidades.size()) {
			
			System.out.println("Opcion fuera de rango.");
			return "Ninguna";
		}
		
		return habilidades.get(opcion-1);
		
	}
	
	
	
	/**
	 * Ejecuta la habilidad seleccionada meadiante un cast
	 * a la interfaz correspondiente.
	 * 
	 * 
	 * 
	 * 
	 * @param monstruo monstruo que utiliza la habilidad
	 * @param habilidad habilidad seleccionada
	 * @return mensaje generado por la habilidad
	 */
	
	private static String ejecutarHabilidad(Monstruo monstruo, String habilidad) {
		
		if(habilidad.equals("Mordedor")) {
			return ((Mordedor) monstruo).morder();
		} else if(habilidad.equals("Volador")) {
			return ((Volador) monstruo).volar();
		} else if(habilidad.equals("Regenerable")) {
			return ((Regenerable) monstruo).regenerarse();
		} else if(habilidad.equals("Furioso")) {
			return ((Furioso) monstruo).enfurecerse();
		}
		
		return "";
	}
	
	/**
	 * 
	 * Convierte el nombre de una interfaz al nombre
	 * que se mostrara en el menu.
	 * 
	 * @param habilidad nombre de la interfaz
	 * @return nombre visible de la habili	dad
	 */
	private static String nombreHabilidad(String habilidad) {
		
		if(habilidad.equals("Mordedor)")) {
			return "Morder";
			
		} else if(habilidad.equals("Voldor")) {
			
			return "Volar";
		} else if(habilidad.equals("Regenerable")) {
			return "Regenerarse";
		}
		
		return "Enfurecerse";
	}
	
	
	
	
	/**
	 * 
	 * Muestra el catalogo completo de monstruos,
	 * incluyendo sus habilidades y disponibilidad.
	 * 
	 */


	private static void mostrarCatalogo()
	{
		ArratList<Monstruo> monstruos = system.obtenerMonstruos();
		
		System.out.println();
		
		System.out.println(" //// CATÁLOGO DE MONSTRUOS \\\\");
		
		for(int i = 0; i < monstruos.size(); i++) {
			
			Monstruo monstruo = monstruos.get(i);
			
			String estado;
			
			if(system.estaMonstruoDisponible(monstruo)) {
				estado = "DISPONIBLE";
				
			} else {
				
				estado = "USADO";
			}
			
			System.out.println((i+1) + ") "+ monstruo + " | Habilidades: " + obtenerNombresHabilidades(monstruo) + " | " + estado);
			
			
		}
		
	}
	
	
	/**
	 * 
	 * Obtiene los nombres de las habilidades de un monstruo.
	 * 
	 * @param monstruo monstruo consultado
	 * @return lista de habilidades en formato de texto
	 */

	private static String obtenerNombreHabilidades(Monstruo monstruo) {
		
		String habilidades = "";
		
		if(monstruo instanceof Mordedor) {
			habilidades += "Morder";
			
		}
		
		if(monstruo instanceof Volador) {
			if(habilidades.length() > 0) {
				habilidades += ", ";
			}
			
			habilidades += "Volar";
		}
		
		if(monstruo instanceof Regenerable) {
			if(habilidades.length() > 0) {
				habilidades += ", ";
			}
			
			habilidades += "Regenerarse";
		}
		
		if(monstruo instanceof Furioso) {
			if(habilidades.length() > 0) {
				habilidades += ", ";
			}
			
			habilidades += "Enfurcerse";
		}
		
		return habilidades;
		
	}
	
	
	/**
	 * Muestra los resultados de los minijuegos realizados
	 * durante la sesion actual.
	 * 
	 * 
	 */

	private static void mostrarHistorial()
	{
		LinkedList<RegistroPartida> historial = system.obtenerHistorial();
		
		System.out.println();
		
		System.out.println(" //// HISTORIAL DE LA SESIÓN \\\\");
		
		if(historial.size() == 0) {
			
			System.out.println("Todabia no has jugado ningún minijuego.");
			return;
		}
		
		for(int i = 0; i < historial.size(); i++) {
			
			RegistroPartida registro = historial.get(i);
			
			System.out.println((i+1) + ". "+ registro.getNombreMiniJuego() + " | " + registro.getTipoMonstruo() + " | Habilidad: " + registro.getHabilidad() + " | " + registro.getPuntaje() + " pts" );
		
			
			
		}
		
	}
	
	
	
	/**
	 * 
	 * Muestra el ranking de jugadores.
	 * EL sistema entrega la lista previamente ordenada.
	 * 
	 */


	private static void mostrarRanking()
	{
		ArrayList<Jugador> ranking = system.obtenerRanking();
		
		System.out.println();
		
		System.out.println(" //// RANKING \\\\");
		
		if(ranking.size() == 0) {
			
			System.out.println("Todavia no hay jugadores registrados.");
			return;
			
		}
		
		for(int i = 0; i < ranking.size(); i++) {
			
			Jugador jugador = ranking.get(i);
			
			System.out.println((i+1) + ". "+ jugador.getNombre() + " - " + jugador.getPuntajeMaximo()+ " puntos");
			
		}
		
		System.out.println("Puntaje acumulado: " + system.getPuntajeAcumulado() + " pts");
		
		
	}

	
	
	
	/**
	 * 
	 *Termina la sesion actual y guarda los registros
	 *cuando el jugador realizo al menos un minijuego
	 * 
	 */
	
	private static void terminarSesion()
	{
		System.out.println();
		System.out.println("Sesion terminada. Puntaje total: " + system.getPuntajeAcumulado() + " puntos");
		String msj = system.terminarSesion();
		System.out.println(msj);
		
		if(system.seJugoEnLaSesion()) {
			guardarRegistros();
		}

	}
	

	/**
	 * 
	 * Reescribe el archivo Registros.txt con los jugadores
	 * y sus respectivos puntajes maximos.
	 * 
	 */

	private static void guardarRegistros()
	{
		try {
			FileWriter escritor = new FileWriter(archRegistros);
			BufferedWriter buffer = new BufferedWriter(escritor);
			
			ArrayList<Jugador> jugadores = system.obtenerJugadores();
			
			for(int i = 0; i < jugadores.size(); i++) {
				Jugador jugador = jugadores.get(i);
				buffer.write(jugador.getNombre() + ";" + jugador.getPuntajeMaximo());
				buffer.newLine();
				
			}
			buffer.close();
			
			System.out.println("Resgistros guardados en "+archRegistros);
			

		} catch(IOException e) {
			System.out.println("No se pudieron guardar los registros. ");
		}
	}


	/**
	 * 
	 * Solicita un numero entero al usuario.
	 * 
	 * si la entrada no es valida, vuelve a solicitarla
	 * sin provocar que el programa termine.
	 * 
	 * @param msj mensaje que se mostrara antes de leer
	 * @return numero entero ingresado
	 */


	private static int leerEntero(String msj)
	{
		while(true) {
			System.out.println(msj);
			String entrada = lector.nextLine();
			
			try {
				return Integer.parseInt(entrada);
			} catch(NumberFormatException e){
				System.out.println("Debe ingresar un numero. Intente nuevamente.");
			}
		}
	}

}
