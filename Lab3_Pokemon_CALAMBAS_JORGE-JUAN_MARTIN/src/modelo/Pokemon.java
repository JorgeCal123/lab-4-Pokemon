package modelo;

public class Pokemon {

	public final static int PosX=456;
	private String nombre;
	private String imagen;
	private boolean estado;
	
	
	public Pokemon(String nombre, String imagen, boolean estado) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.estado = estado;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	public boolean isEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	
}
