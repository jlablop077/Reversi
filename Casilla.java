package juego;

public class Casilla {
	
	/* ATRIBUTOS */
	
	private String posicion;
	private boolean ocupada;
	private boolean color;

	
	/* CONSTRUCTORES */
	
	public Casilla(String posicion, boolean ocupada, boolean color) {
		this.posicion = posicion;
		this.ocupada = ocupada;
		this.color = color;
	}
	
	// Crea una casilla sin ficha.
	public Casilla(String posicion) {
		this(posicion, false, false);
	}

	
	/* GETTERS Y SETTERS */
	
	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

		
	/* MÉTODOS */

	// toString.
	@Override
	public String toString() {
		return "Casilla [posicion=" + posicion + ", ocupada=" + ocupada + ", color=" + color + "]";
	}
	
	// Coloca una ficha con un color determinado.
	public void colocar(boolean color) {
		this.ocupada = true;
		this.color = color;
	}

	
}
