package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CampoEntrenamiento {

	public final static int LANZAR=1;
	public final static int ATRAPAR=2;
	
	
	private Pokemon[] pokemones;
	private int limiteBandera;
	private int velocidad;
	
	public CampoEntrenamiento() {
		pokemones= new Pokemon[10];	
	}
	
	
	public Pokemon[] darPokemones() {
		return pokemones;
	}
	
	public void agregarPokemon(String nom, String ima, boolean est,int i) {
		pokemones[i]=new Pokemon(nom,ima,est);
	}

	
	public Pokemon buscarPokemon(String nombre) {
		Pokemon encontrado=null;
		boolean terminar=false;
		for (int i = 0; i < pokemones.length&&!terminar; i++) {
			if(pokemones[i].getNombre().equals(nombre)) {
				encontrado=pokemones[i];
				terminar=true;
			}
		}
		return encontrado;
	}

	public void GenerarposicionBandera() {
		Random aleatorio = new Random();
		limiteBandera=(int) aleatorio.nextInt(326)+ 30;
	}	
	
	
	public int darPosicionBander() {
		return limiteBandera;
	}

	
	public String darRecorridoTotal() {
		int pospoke=498;
		int total=pospoke-limiteBandera;
	
		String mensaje=" el recorrido total fue: "+total+" m.";
		return mensaje;
	}
	
	
	public static void crearArchivo(String nombre, String poke) {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("Archivos/"+nombre+".txt");
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
				bfwriter.write("El jugador "+nombre+" capturo a "+poke);
			bfwriter.close();
 
			
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		
			
		
	}
	
	public int velocidadDelJuego() {
		Random num=new Random();
		velocidad=num.nextInt(50)+5;
		return velocidad;
		
	}
	public int darvelocidadDelJuego() {
		
		return velocidad;
		
	}
}
