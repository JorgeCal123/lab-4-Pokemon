package modelo;

import java.io.Serializable;

public class Jugador implements Serializable{
	
	
	private String nombre;
	private int puntajelanzar;	
	private int puntajeAtrapar;
	
	
	
	public Jugador(String nombre, int puntajelanzar, int puntajeAtrapar) {
		this.nombre = nombre;
		this.puntajelanzar = puntajelanzar;
		this.puntajeAtrapar = puntajeAtrapar;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntajelanzar() {
		return puntajelanzar;
	}
	public void setPuntajelanzar(int puntajelanzar) {
		this.puntajelanzar = puntajelanzar;
	}
	public int getPuntajeAtrapar() {
		return puntajeAtrapar;
	}
	public void setPuntajeAtrapar(int puntajeAtrapar) {
		this.puntajeAtrapar = puntajeAtrapar;
	}	

	
	
}