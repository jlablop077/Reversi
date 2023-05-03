package Proyecto;

public class Jugador {
	
	/*Atributos*/
	private boolean color, maquina;
	private int puntuacion;
	
	/*Constructor*/
	public Jugador(boolean color, boolean maquina, int puntuacion) {
		super();
		this.color = color;
		this.maquina = maquina;
		this.puntuacion = puntuacion;
	}
	
	/*Getters and setters*/
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
	

}
