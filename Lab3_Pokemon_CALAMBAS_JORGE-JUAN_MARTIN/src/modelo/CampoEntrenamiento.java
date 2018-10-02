package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class CampoEntrenamiento {

	public final static int LANZAR=1;
	public final static int ATRAPAR=2;
	public final static int PUNTAJE_LANZAR=5;

	public final static int PUNTAJE_ATRAPAR=8;

	
	private Pokemon[] pokemones;
	private ArrayList<Jugador> listajugadores;
	private int limiteBandera;
	private int velocidad;
	private int puntajeLanzar;
	private int puntajeAtrapar;

	public CampoEntrenamiento() {
		pokemones= new Pokemon[10];	
		listajugadores=new ArrayList<Jugador>();
		int pospoke=498;
		int total=pospoke-limiteBandera;
		puntajeLanzar=0;
		puntajeAtrapar=0;
		jugadoresAntiguos();
		serializarJugadoresAntiguos();
		//Jugador jug=new Jugador("camilo", 12);

	//	agregarUnJugador(jug);
	//	actualizarListaJugadores();
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
	
	public Jugador buscarJugador(String nombre) {
		Jugador encontrado=null;
		boolean terminar=false;
		for (int i = 0; i < listajugadores.size()&&!terminar; i++) {
			if(listajugadores.get(i).getNombre().equals(nombre)) {
				encontrado=listajugadores.get(i);
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
	
	
/*	public static void crearArchivo(String nombre, String poke) {
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
		
	}*/
	
	
	
	public int velocidadDelJuego() {
		Random num=new Random();
		velocidad=num.nextInt(50)+5;
		return velocidad;
		
	}
	
	
	public int darvelocidadDelJuego() {
		
		return velocidad;
		
	}
	
//	public int darpuntajeLanzamiento() {
//		int distancia=limiteBandera;
//		int aumento=0;
//		int puntaje=0;
//		while(aumento<=distancia) {
//			puntaje=aumento*PUNTAJE_LANZAR;
//			aumento++;
//		}
//		return puntaje;
//	}
	
	public int calcularPuntajeLanzamiento() {
		int	puntaje=puntajeLanzar*PUNTAJE_LANZAR;
			puntajeLanzar++;
	//	System.out.println(puntajeLanzar+" "+PUNTAJE_LANZAR+" = "+puntaje);
		return puntaje;
	}
	public void aumentarPuntajeAtrapar() {
		puntajeAtrapar+=PUNTAJE_ATRAPAR;
	}
	
	
	public String darPuntajeAtrapar() {
		String mensaje="";
		if(puntajeAtrapar==0) {
			mensaje= "no atrapastes a ningun pokemon"; 
		}
		else {
			puntajeAtrapar+=PUNTAJE_ATRAPAR;
			mensaje= "Puntaje: "+puntajeAtrapar;
		}
		
		return mensaje;
	}
	
	
	public void reiniciarPuntajeLanzar() {
		puntajeLanzar=0;
	}
	
	public void reiniciarPuntajeAtrapar() {
		puntajeAtrapar=0;
	}
	
	public void agregarUnJugador(String nombre,int lanzar,int atrapar) {
		Jugador jugador=new Jugador(nombre, lanzar, atrapar);
		File f=new File("Archivos/"+nombre);
		try {
			FileOutputStream fo=new FileOutputStream(f,true);
			ObjectOutputStream os=new ObjectOutputStream(fo);
			
			os.writeObject(jugador);
			listajugadores.add(jugador);
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public void agregarUnJugador(String nombre,int lanzar,int atrapar) {
//		Jugador jugador=new Jugador(nombre, lanzar, atrapar);
//		File f=new File("Archivos/"+jugador.getNombre());
//		try {
//			FileOutputStream fo=new FileOutputStream(f);
//			ObjectOutputStream os=new ObjectOutputStream(fo);
//			
//			os.writeObject(jugador);
//			jugadores.add(jugador);
//			os.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public int getPuntajeLanzar() {
		return puntajeLanzar;
	}


	public void setPuntajeLanzar(int puntajeLanzar) {
		this.puntajeLanzar = puntajeLanzar;
	}


	public int getPuntajeAtrapar() {
		return puntajeAtrapar;
	}


	public void setPuntajeAtrapar(int puntajeAtrapar) {
		this.puntajeAtrapar = puntajeAtrapar;
	}


	public Jugador ArchivoInformacionJugador(String nombre) {
		File f=new File("Archivos/"+nombre);
		Jugador jug =null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			ObjectInputStream ois=new ObjectInputStream(fis);
			 jug =(Jugador)ois.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jug;
	}
//	
//	
//	public void actualizarListaJugadores() {
//		File f=new File("Archivos/jugadores");
//		Jugador jug =null;
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(f);
//			ObjectInputStream ois=new ObjectInputStream(fis);
//			while((jug=(Jugador)ois.readObject())!=null) {
//				listajugadores.add(jug);
//				System.out.println(jug.getNombre());
//		} 
//		}
//			catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		
//	}
	public void guardarJugadores(Jugador jug) {
		File f=new File("Archivos/jugdores");
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			ObjectInputStream ois=new ObjectInputStream(fis);
			 jug =(Jugador)ois.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void recuperarJugadores() {
		File f=new File("Archivos/jugdores");
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Jugador jugadore =(Jugador)ois.readObject();
			System.out.println(jugadore.getNombre());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void jugadoresAntiguos() {
		System.out.println("entra");
		String jugadores[];
		File archivo=new File("Archivos/jugadoresAntiguos.txt");
		String line="";
		try {
			FileReader fw=new FileReader(archivo);
			BufferedReader bf=new BufferedReader(fw);
			System.out.println("entra 3");

			while((line=bf.readLine())!=null) {
				System.out.println("entra 2");

				jugadores=line.split(";");
				Jugador aux=new Jugador(jugadores[0],Integer.parseInt( jugadores[1]), Integer.parseInt(jugadores[2]));
				listajugadores.add(aux);
			}
			bf.close();
	}
		catch(IOException a) {
			
		}
	}
	
	public void serializarJugadoresAntiguos() {
		for (int i = 0; i < listajugadores.size(); i++) {
			System.out.println(listajugadores.get(i).getNombre());
			String nombre=listajugadores.get(i).getNombre();
			int a=listajugadores.get(i).getPuntajeAtrapar();
			int l=listajugadores.get(i).getPuntajelanzar();

			agregarUnJugador(nombre, l, a);
		}
	}
}
