//David Villalobos - 21.646.173-8 - ICCI

package logica;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;


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
					miniJuegos();
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
	
	
	private static void miniJuegos()
	{
		// TODO Auto-generated method stub
		
	}


	private static void mostrarCatalogo()
	{
		// TODO Auto-generated method stub
		
	}


	private static void mostrarHistorial()
	{
		// TODO Auto-generated method stub
		
	}


	private static void mostrarRanking()
	{
		// TODO Auto-generated method stub
		
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
