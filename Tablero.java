package PoryectoFinal;

public class Tablero {

	/*ATRIBUTOS*/
	
	private final int TAMANIO = 8;
	private int[][] casillas;
	
	/*CONSTRUCTOR*/
	
	public Tablero(int[][] casillas) {
		this.casillas = casillas;
	}

	/*GETTERS Y SETTERS*/
	
	public int[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(int[][] casillas) {
		this.casillas = casillas;
	}

	public int getTAMANIO() {
		return TAMANIO;
	}
	
	
}
