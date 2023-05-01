package reversi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partida {
	
	/* ATRIBUTOS */
	
	private int turno;
	private boolean orden;
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;	
	
	/* CONSTRUCTORES */
	
	public Partida(int turno, boolean orden, Tablero tablero, Jugador jugador1, Jugador jugador2) {
		this.turno = turno;
		this.orden = orden;
		this.tablero = tablero;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	public Partida(Jugador jugador1, Jugador jugador2) {
		this(0, false, new Tablero(), jugador1, jugador2);
	}
	
	public Partida() {
		this(new Jugador(false, false), new Jugador(true, true));
	}

	
	/* GETTERS Y SETTERS */
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	public boolean isOrden() {
		return orden;
	}

	public void setOrden(boolean orden) {
		this.orden = orden;
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}
	
	
	/* MÉTODOS */
	
	// Selecciona el módo de juego de la partida, estableciendo si los jugadores serán humanos o máquinas.
	public void elegirModo() {
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
			}catch (InputMismatchException e){
				System.out.println("Introduzca un valor válido: 1, 2 u 3.");
				error = true;
			}
		}while(error);
		keyboard.close();
		
		switch(opcion) {
		case 1:
			jugador1.setMaquina(false);
			jugador2.setMaquina(false);
			System.out.println("Modo seleccionado: Humano VS Humano.");
			break;
		case 2:
			jugador1.setMaquina(false);
			jugador2.setMaquina(true);
			System.out.println("Modo seleccionado: Humano VS Máquina.");
			break;
		case 3:
			jugador1.setMaquina(true);
			jugador2.setMaquina(true);
			System.out.println("Modo seleccionado: Máquina VS Máquina.");
		}
	}
	
	// Elige el orden de los jugadores.
	public void asignarTurnos() {
		Random rnd = new Random();
		boolean orden = rnd.nextBoolean();
		Jugador jAux;
		
		this.orden = orden;
		
		System.out.println();
		if(orden) {
			System.out.print("Comienza el jugador 2");
			jAux = jugador2;
		}else{
			System.out.print("Comienza el jugador 1");
			jAux = jugador1;
		}
		
		if(jAux.isMaquina()) {
			System.out.print(" (máquina).\n");
		}else {
			System.out.print(" (humano).\n");
		}
	}
	
	// Asigna el color con el que juega cada jugador.
	public void asignarColor() {
		Random rnd = new Random();
		boolean color = rnd.nextBoolean();
		
		System.out.println();
		if(color) {
			this.jugador1.setColor(color);
			this.jugador2.setColor(!color);
			
			System.out.println("El jugador 1 recibe fichas blancas.");
			System.out.println("El jugador 2 recibe fichas negras.");
		}else {
			this.jugador1.setColor(!color);
			this.jugador2.setColor(color);
			
			System.out.println("El jugador 1 recibe fichas negras.");
			System.out.println("El jugador 2 recibe fichas blancas.");
		}
	}
	
	// Inicia la partida creada.
	public void iniciarPartida() {
		
		// Establece el turno a 1.
		turno = 1;
		
		// Crea un nuevo talero de juego.
		tablero = new Tablero();
		
		// Establece la puntuación de ambos jugadores a 0.
		jugador1.setPuntuacion(0);
		jugador2.setPuntuacion(0);
		
		boolean fin = false;
		do {
			
		}while(fin);
		
		System.out.println();
	}
	
	// Calcula el ganador de la partida.
	public void calcularGanador() {
		int p1 = jugador1.getPuntuacion();
		int p2 = jugador2.getPuntuacion();
		String color;
		
		if(p1>p2) {
			color = (jugador1.isColor())?"blancas":"negras";
			System.out.println("Ganan las "+color+", jugador 1.");
		}else if(p1<p2){
			color = (jugador2.isColor())?"blancas":"negras";
			System.out.println("Ganan las "+color+", jugador 2.");			
		}else if(p1==p2) {
			System.out.println("Empate entre los jugadores.");
		}
	}
	
}
