//David Villalobos - 21.646.173-8 - ICCI

package logica;

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

}
