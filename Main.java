package juego;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Se crea la partida.
		Partida p1 = new Partida();
		
		// Se elige el modo de juego.
		System.out.println("Elige entre los siguientes modos:"
				+"\n1. Humano VS Humano."
				+"\n2. Humano VS Máquina."
				+"\n3. Máquina VS Máquina.");
		
		byte opcion = 0;
		boolean error = false;
		
		Scanner keyboard = new Scanner(System.in);
		do {
			try {
				opcion = keyboard.nextByte();
				if(opcion < 1 || opcion > 3) {
					throw new InputMismatchException();
				}
				p1.elegirModo(opcion);
				error = false;
			}catch (InputMismatchException e){
				System.out.println("Introduzca un valor válido: 1, 2 u 3.");
				// Limpia el buffer.
				keyboard.nextLine();
				error = true;
			}
		}while(error);	
		
		// Establece los valores iniciales.
		p1.iniciarPartida();
		
		// Cuando la siguiente variable sea true, finaliza la partida.
		boolean paso = false;
		boolean fin = false;
		
		// El bucle comprueba si debe finallizar la partida.
		do {
			p1.muestraPartida();
			
			// Selecciona el jugador al que le toca jugar.
			boolean jugadorActual = p1.getJugadorActual();
			
			// Si jugadorActual es false le toca a jugador1, si es true le toca a jugador2.
			Jugador jugador = jugadorActual?p1.getJugador2():p1.getJugador1();
			
			// Si el jugador es humano le pide que inserte una casilla.
			if(!jugador.isMaquina()) {
				String pos = "";
				boolean posIncorrecta = true;
				
				System.out.printf("\nTurno del %s: %s.\n",jugadorActual?"Jugador 2":"Jugador 1", jugador.isColor()?"Blancas":"Negras");
				
				System.out.println("Introduce una posición donde coloar la ficha:");
				do {
					
					pos = keyboard.next();
					
					posIncorrecta = !p1.colocar(pos, jugador);
					
					// Si la posición indicada no puede capturar ninguna casilla comprueba si hay casillas capturables.
					if(posIncorrecta) {
						ArrayList<Casilla> mejoresCasillas = p1.getTablero().mejoresJugadas(jugador.isColor());
						
						if(mejoresCasillas.size()==0) {
							posIncorrecta = false;
							
							// Si no hay casillas capturables y el anterior jugador pasó, termina la partida.
							fin = paso && true;
							paso = true;
						}else {
							// Si hay casillas capturables muestra las posiciones que pueden capturar más.
							System.out.print("\nLas casillas "+mejoresCasillas.get(0).getPosicion());
							for(int i=1; i<mejoresCasillas.size()-1; i++) {
								System.out.print(", "+mejoresCasillas.get(i).getPosicion());
							}
							
							System.out.print(" y "+mejoresCasillas.get(mejoresCasillas.size()-1).getPosicion()+
									" son las posiciones con más capturas.");
						}
					}
				}while(posIncorrecta);
			}else {
				ArrayList<Casilla> mejoresCasillas = p1.getTablero().mejoresJugadas(jugador.isColor());
				
				// Si no hay casillas capturables termina la partida.
				if(mejoresCasillas.size()==0) {
					fin = true;
				}else {
					// Si hay casillas capturables, captura una aleatoriamente entre las mejores posiciones.
					p1.colocar(mejoresCasillas.get((new Random()).nextInt(mejoresCasillas.size())).getPosicion(), jugador);
				}
			}
			
			p1.setJugadorActual(!jugadorActual);
			
		}while(!fin);
		keyboard.close();
		
		p1.calcularGanador();
	}

}
