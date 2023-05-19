package juego;

import java.util.Random;

public class Partida {
	
	/* ATRIBUTOS */
	
	private boolean jugadorActual;
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;	
	
	
	/* CONSTRUCTORES */
	
	public Partida(boolean jugadorActual, Tablero tablero, Jugador jugador1, Jugador jugador2) {
		this.jugadorActual= jugadorActual;
		this.tablero = tablero;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	public Partida(Jugador jugador1, Jugador jugador2) {
		this(false, new Tablero(), jugador1, jugador2);
	}
	
	public Partida() {
		this(new Jugador(false, false), new Jugador(true, true));
	}

	
	/* GETTERS Y SETTERS */
	
	public boolean getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(boolean jugadorActual) {
		this.jugadorActual = jugadorActual;
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
	public void elegirModo(byte opcion) {
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
		
		// Si jugadorActual es false comienza el jugador 1,
		// si es true comienza el jugador 2.
		jugadorActual = rnd.nextBoolean();
		Jugador jAux;
		
		// Muestra un mensaje nombrando al jugador que comienza.
		System.out.println();
		if(jugadorActual) {
			System.out.print("Comienza el Jugador 2");
			jAux = jugador2;
		}else{
			System.out.print("Comienza el Jugador 1");
			jAux = jugador1;
		}
		
		// Informa de si el jugador que comienza es máquina o humano.
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
			
			System.out.println("El Jugador 1 recibe fichas blancas.");
			System.out.println("El Jugador 2 recibe fichas negras.");
		}else {
			this.jugador1.setColor(color);
			this.jugador2.setColor(!color);
			
			System.out.println("El Jugador 1 recibe fichas negras.");
			System.out.println("El Jugador 2 recibe fichas blancas.");
		}
	}
	
	// Calcula el ganador de la partida.
	public void calcularGanador() {
		
		this.muestraPartida();
		System.out.println();
		
		int puntuacion1 = jugador1.getPuntuacion();
		int puntuacion2 = jugador2.getPuntuacion();
		String color;
		
		if(puntuacion1>puntuacion2) {
			color = (jugador1.isColor())?"blancas":"negras";
			System.out.println("Ganan las "+color+", jugador 1.");
		}else if(puntuacion1<puntuacion2){
			color = (jugador2.isColor())?"blancas":"negras";
			System.out.println("Ganan las "+color+", jugador 2.");			
		}else if(puntuacion1==puntuacion2) {
			System.out.println("Empate entre los jugadores.");
		}
	}
	
	// Coloca si esposible una ficha en una posición determinada.
	public boolean colocar(String pos, Jugador jugador) {
		
		pos = pos.toUpperCase();
		
		int fila = -1;
		int columna = -1;
		
		// El número de caracteres de la posición debe ser 2.
		if(pos.length()==2) {
			fila = pos.charAt(1)-49;
			columna = pos.charAt(0)-65;
		}
		
		// La fila y la columna debe estar en un rango determinado.
		if(fila>=0 && columna>=0 && fila<Tablero.getTamanio() && columna<Tablero.getTamanio()) {
			
			Casilla casilla = tablero.getCasillas()[fila][columna];
			
			if(casilla.isOcupada()) {
				System.out.println("Acción no válida: La casilla "+pos+" ya está ocupada."+casilla.isOcupada());
				return false;
			}else {
				Casilla[] capturas = tablero.validarJugada(fila, columna, jugador.isColor());
				
				if(capturas.length==0) {
					System.out.println("Acción no válida: No se puede captura ninguna casilla.");
					return false;
				}else {
				
					for(Casilla c: capturas) {
						c.setColor(!c.isColor());
					}
				
					casilla.colocar(jugador.isColor());
					jugador.sumaPuntuacion(capturas.length+1);
					
					if(jugadorActual) {
						jugador1.sumaPuntuacion(-capturas.length);
					}else {
						jugador2.sumaPuntuacion(-capturas.length);
					}
				}
			}
			
			return true;
		}else {
			System.out.println("Error: "+pos+" no es una posición del tablero.");
		}
		return false;
	}
	
	// Muestra los datos de la partida.
	public void muestraPartida() {
		
		System.out.printf("\nPuntuación del Jugador 1 (%s): %d\n", jugador1.isColor()?"Blancas":"Negras", jugador1.getPuntuacion());
		System.out.printf("Puntuación del Jugador 2 (%s): %d\n\n", jugador2.isColor()?"Blancas":"Negras", jugador2.getPuntuacion());
		
		tablero.muestraTablero();
	}
	
	// Inicia los datos de la partida creada.
	public void iniciarPartida() {
		
		// Asigna el color de los jugadores.
		asignarColor();
				
		// Asigna el turno de los jugadores.
		asignarTurnos();
		
		// Crea un nuevo talero de juego.
		tablero = new Tablero();
		
		// Establece la puntuación de ambos jugadores a 2.
		jugador1.setPuntuacion(2);
		jugador2.setPuntuacion(2);			
	}
}
