package PoryectoFinal;

public class Casilla {
	
	/*ATRIBUTOS*/
	
	private String posicion ;
	private boolean ocupada;
	private boolean color;
	
	
	/*CONSTRUCTOR*/
	
	public Casilla(String posicion, boolean ocupada, boolean color) {
		this.posicion = posicion;
		this.ocupada = ocupada;
		this.color = color;
	}

	/*GETTERS Y SETTERS*/

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
	
	

}
