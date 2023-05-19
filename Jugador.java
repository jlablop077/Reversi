package juego;

public class Jugador {
	
	/* ATRIBUTOS */
	
	private boolean color;
	private boolean maquina;
	private int puntuacion;
	
	
	/* CONSTRUCTORES */
	
	public Jugador(boolean color, boolean maquina, int puntuacion) {
		this.color = color;
		this.maquina = maquina;
		this.puntuacion = puntuacion;
	}
	
	public Jugador(boolean color, boolean maquina) {
		this(color, maquina, 0);
	}

	
	/* GETTERS Y SETTERS */
	
	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public boolean isMaquina() {
		return maquina;
	}

	public void setMaquina(boolean maquina) {
		this.maquina = maquina;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	/* MÉTODOS */
	
	// Suma un valor a la puntuación actual.
	public boolean sumaPuntuacion(int valor) {
		
		// Comprueba que el valor introducido no cause que la puntuación sea menor que 0.
		int cantidad = this.puntuacion+valor;
		if(cantidad<0) {
			return false;
		}
		
		// Actualiza la puntuación.
		this.puntuacion = cantidad;
		return true;
	}

	// toString.
	@Override
	public String toString() {
		return "Jugador [color=" + color + ", maquina=" + maquina + ", puntuacion=" + puntuacion + "]";
	}

}
