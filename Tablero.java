package juego;

import java.util.ArrayList;
import java.util.Arrays;

public class Tablero {
	
	/* ATRIBUTOS */
	
	private final static int TAMANIO = 8;
	
	private Casilla[][] casillas;

	
	/* CONSTRUCTORES */
	
	public Tablero(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	// Constructor del tablero por defecto.
	public Tablero() {
		Casilla[][] nuevoTablero = new Casilla[TAMANIO][TAMANIO];
		for(int j=0; j<TAMANIO; j++) {
			for(int i=0; i<TAMANIO; i++) {
				nuevoTablero[j][i] = new Casilla(((char)(65+i))+""+(j+1)); 
			}
		}
		
		nuevoTablero[3][3].colocar(true);
		nuevoTablero[3][4].colocar(false);
		nuevoTablero[4][3].colocar(false);
		nuevoTablero[4][4].colocar(true);;
		
		this.casillas = nuevoTablero;
	}
	
	
	/* GETTERS Y SETTERS */
	
	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public static int getTamanio() {
		return TAMANIO;
	}

	
	/* MÉTODOS */
	
	// Muestra por pantalla una representación del tablero.
	public void muestraTablero() {
		
		System.out.println("   A B C D E F G H");
		for (int i=0;i<TAMANIO;i++) {
			System.out.print(i+1+"\u2001");
			for (int j=0;j<TAMANIO;j++) {
				if (this.getCasillas()[i][j].isOcupada()==false) {
					System.out.print("[]");
				}else if (this.getCasillas()[i][j].isColor()==false) {
					System.out.print("\u26AB");
				}else {
					System.out.print("\u26AA");
				}
			}
			System.out.print("\n");
		}
	}
	
	// Comprueba si las casillas de la lista se pueden capturar.
	public boolean validarCasillas(ArrayList<Casilla> casillasValidas, Casilla casilla, boolean color) {
		if(casilla.isOcupada()) {
			if(casilla.isColor()==color){
				// Si la casilla es del mismo color, significa que se puede capturar la lista de casillas.
				return true;
			}else {
				// Si es de distinto color, se agrega la ficha a la lista, pero no se pueden capturar.
				casillasValidas.add(casilla);				
			}
		}else {
			// Si la casilla no está ocupada, se devuelve una lista vacía, no se puede capturar ninguna ficha.
			casillasValidas.removeAll(casillasValidas);
		}
		
		return false;
	}
	
	// Comprueba si existen casillas capturables hacia arriba.
	public Casilla[] validarArriba(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila>1) {
			do {
				// Valida casilla y comprueba si se puede capturar la lista.
				if(validarCasillas(casillasValidas, casillas[--fila][columna], color)) {
					// Si encuentra una casilla de color opuesto, 
					// se pueden capturar las fichas de la lista.
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					// Si no se puede capturar comprueba si se ha eliminado la lista.
					if(casillasValidas.size() == 0) {
						// Significa que se encontró una casilla vacía y se devuelve un array vacío.
						return new Casilla[0];
					}
				}
			}while(fila>0);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia abajo.
	public Casilla[] validarAbajo(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila<6) {
			do {
				if(validarCasillas(casillasValidas, casillas[++fila][columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(fila<7);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la izquierda.
	public Casilla[] validarIzquierda(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(columna>1) {
			do {
				if(validarCasillas(casillasValidas, casillas[fila][--columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(columna>0);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la derecha.
	public Casilla[] validarDerecha(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(columna<6) {
			do {
				if(validarCasillas(casillasValidas, casillas[fila][++columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(columna<7);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la diagonal arriba-derecha.
	public Casilla[] validarDiagonal1(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila>1 && columna<6) {
			do {
				if(validarCasillas(casillasValidas, casillas[--fila][++columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(fila>0 && columna<7);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la diagonal abajo-derecha.
	public Casilla[] validarDiagonal2(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila<6 && columna<6) {
			do {
				if(validarCasillas(casillasValidas, casillas[++fila][++columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(fila<7 && columna<7);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la diagonal abajo-izquierda.
	public Casilla[] validarDiagonal3(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila<6 && columna>1) {
			do {
				if(validarCasillas(casillasValidas, casillas[++fila][--columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(fila<7 && columna>0);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si existen casillas capturables hacia la diagonal arriba-izquierda.
	public Casilla[] validarDiagonal4(int fila, int columna, boolean color) {
		
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		
		if(fila>1 && columna>1) {
			do {
				if(validarCasillas(casillasValidas, casillas[--fila][--columna], color)) {
					return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
				}else{
					if(casillasValidas.size() == 0) {
						return new Casilla[0];
					}
				}
			}while(fila>0 && columna>0);
		}
		
		return new Casilla[0];
	}
	
	// Comprueba si una jugada es capaz de capturar alguna casill.
	public Casilla[] validarJugada(int fila, int columna, boolean color) {
	
		ArrayList<Casilla> casillasValidas = new ArrayList<Casilla>();
		if(!casillas[fila][columna].isOcupada()) {
			casillasValidas.addAll(Arrays.asList(validarArriba(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarDerecha(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarAbajo(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarIzquierda(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarDiagonal1(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarDiagonal2(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarDiagonal3(fila, columna, color)));
			casillasValidas.addAll(Arrays.asList(validarDiagonal4(fila, columna, color)));
		}
		
		return casillasValidas.toArray(new Casilla[casillasValidas.size()]);
	}

	// Devuelve las casillas con más capturas.
	public ArrayList<Casilla> mejoresJugadas(boolean color) {
		ArrayList<Casilla> mejoresJugadas = new ArrayList<>();
		Casilla[] capturas;
		
		int mayorCaptura = 0;
			
		for(int i = 0; i<TAMANIO; i++){
			for(int j = 0; j<TAMANIO; j++) {
				capturas = validarJugada(i, j, color);
				if(capturas.length!=0) {
					if(capturas.length==mayorCaptura) {
						mejoresJugadas.add(casillas[i][j]);
					}else if(capturas.length>mayorCaptura) {
						mejoresJugadas.clear();
						mejoresJugadas.add(casillas[i][j]);
						mayorCaptura = capturas.length;
					}
				}
			}
		}
		
		return mejoresJugadas;
	}
	
}
