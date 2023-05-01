package reversi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		char nuevaPartida = 'N';
		
		do {
			// Se crea una partida sin jugadores.
			Partida  partida = new Partida();
			
			// Elige el modo de juego.
			partida.elegirModo();
			
			// Elige el orden de los jugadores.
			partida.asignarTurnos();
			
			// Asigna el color con el que juega cada jugador.
			partida.asignarColor();
			
			// Inicia el juego.
			partida.iniciarPartida();
			
			System.out.println("¿Quieres reinicar la partida? Y/N");
			
			char opcion = 'N';
			boolean error = false;
			Scanner keyboard = new Scanner(System.in);
			
			do {
				try {
					opcion = Character.toUpperCase(keyboard.nextLine().charAt(0));
					if(!(opcion == 'Y' || opcion == 'N')) {
						throw new InputMismatchException();
					}
				}catch (InputMismatchException e){
					System.out.println("Introduzca un valor válido: Y o N.");
					error = true;
				}
			}while(error);
			keyboard.close();
			
			if(opcion == 'Y') {
				partida.iniciarPartida();
			}
			
			System.out.println("¿Quieres iniciar una nueva partida? Y/N");
			Scanner keyboard1 = new Scanner(System.in);
			
			do {
				try {
					nuevaPartida = Character.toUpperCase(keyboard1.nextLine().charAt(0));
					if(!(opcion == 'Y' || opcion == 'N')) {
						throw new InputMismatchException();
					}
				}catch (InputMismatchException e){
					System.out.println("Introduzca un valor válido: Y o N.");
					error = true;
				}
			}while(error);
			keyboard1.close();
			
		}while(nuevaPartida == 'Y');
	}

}
