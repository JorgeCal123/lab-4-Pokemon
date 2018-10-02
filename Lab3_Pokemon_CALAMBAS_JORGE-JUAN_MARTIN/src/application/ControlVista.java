package application;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modelo.CampoEntrenamiento;
import modelo.Pokemon;

public class ControlVista {

	@FXML
	private ImageView arena;
	@FXML
	private ImageView portada;
	@FXML
	private ImageView pokemon;
	@FXML
	private ImageView bandera;
	@FXML
	private Button lanzar;
	@FXML
	private Button atrapar;
	@FXML
	private Button Volver;
	@FXML
	private Pane paneArena;
	@FXML
	private Pane panelinicio;
	@FXML
	private Pane paneListaPokemon;
	@FXML
	private Pane paneListaJugador;
	@FXML
	private HBox paneOpciones;
	@FXML
	private ListView listaJugadores;
	@FXML
	private ListView listapokemon;
	@FXML
	private Label puntaje;
	@FXML
	private Button guardar;
	
	private int tipoEntrenamiento;

	private ObservableList<String> dataLista = FXCollections.observableArrayList();
	private ObservableList<String> dataLista2 = FXCollections.observableArrayList();

	private Timeline timeLineAnimation;

	private Main m;
	private String nombreJugador;

	public void initialize() {
		
		m = new Main();
		m.iniciarCampo();
		m.CrearPokemon();
		timeLineAnimation=new Timeline();
		escogerTipoEntrenamientoPokemon();//modifique estabadespues del main
		mensajeConfirmacionUsuario();
	}
	
	public void escogerTipoEntrenamientoPokemon() {
		lanzar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				tipoEntrenamiento = (1);
				ListaPokemon();
			}
		});

		atrapar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				tipoEntrenamiento = (2);
				ListaPokemon();

			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	public void ListaPokemon() {
		listapokemon.getItems().removeAll(dataLista);
		paneArena.setVisible(false);
		panelinicio.setVisible(false);
		paneOpciones.setVisible(false);
		paneListaPokemon.setVisible(true);
		for (int i = 0; i < m.darCampoEntrenamiento().darPokemones().length; i++) {
			dataLista.add(m.darCampoEntrenamiento().darPokemones()[i].getNombre());
		}
		listapokemon.setItems(dataLista);
		listapokemon.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldvalue, String newvalue) {
				paneListaPokemon.setVisible(false);
				paneArena.setVisible(true);
				paneOpciones.setVisible(true);
				tipoEntrenamiento(tipoEntrenamiento, newvalue);

			}
		});

	}
	
	public void tipoEntrenamiento(int tipo, String nombre) {
	//	paneOpciones.setVisible(true);
		if (tipo == CampoEntrenamiento.LANZAR) {
			Pokemon p = m.darCampoEntrenamiento().buscarPokemon(nombre);
			if(p!=null) {
			opcionLanzarPokemon(p);
			}
		} else if (tipo == CampoEntrenamiento.ATRAPAR) {
			Pokemon p = m.darCampoEntrenamiento().buscarPokemon(nombre);
			if(p!=null) {
			opcionAtraparPokemon(p);
			}
		}
	}
	
	

	public void opcionLanzarPokemon(Pokemon poke) {
		timeLineAnimation.stop();
		colocarBandera();

		File ima = new File("img/p0.gif");
		Image po = new Image(ima.toURI().toString());
		pokemon.setImage(po);
		pokemon.setLayoutX(Pokemon.PosX);
		m.darCampoEntrenamiento().velocidadDelJuego();
		pokemon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				File ima = new File(poke.getImagen());
				Image po = new Image(ima.toURI().toString());
				pokemon.setImage(po);
				movimientoPokemonLanzar();
			}
		});

	}
	
	
	public void movimientoPokemonLanzar() {
		int limite=m.darCampoEntrenamiento().darPosicionBander();
		timeLineAnimation.stop();
		timeLineAnimation = new Timeline(new KeyFrame(Duration.millis(m.darCampoEntrenamiento().darvelocidadDelJuego()), new EventHandler<ActionEvent>() {
			int aumento = -1;
			@Override
			public void handle(ActionEvent arg0) {
				pokemon.setLayoutX(pokemon.getLayoutX() + aumento);
				puntaje.setText("Puntaje: "+m.darCampoEntrenamiento().calcularPuntajeLanzamiento());
				if (pokemon.getLayoutX() <= limite) {
					timeLineAnimation.stop();
					timeLineAnimation.onFinishedProperty();
					mensajeDelRecorrido();
				}
			}
		}));
		
		timeLineAnimation.setCycleCount(Timeline.INDEFINITE);
		timeLineAnimation.play();
	}
	
	
	public void opcionAtraparPokemon(Pokemon poke) {
		timeLineAnimation.stop();
		File ima = new File(poke.getImagen());
		Image po = new Image(ima.toURI().toString());
		pokemon.setImage(po);
		pokemon.setLayoutX(Pokemon.PosX);
		colocarBandera();
		movimientoPokemonAtrapar(poke);
	}
	
	
	public void movimientoPokemonAtrapar(Pokemon poke) {

		int limite = m.darCampoEntrenamiento().darPosicionBander();
		poke.setEstado(true);
		timeLineAnimation = new Timeline(new KeyFrame(Duration.millis(m.darCampoEntrenamiento().velocidadDelJuego()), new EventHandler<ActionEvent>() {
			int aumento = -1;

			@Override
			public void handle(ActionEvent arg0) {
				pokemon.setLayoutX(pokemon.getLayoutX() + aumento);
				condicionesAtraparPokemon(poke, limite);
		//		System.out.println(poke.isEstado() +"    "+m.darCampoEntrenamiento().darPosicionBander());
			}
		}));
		timeLineAnimation.setCycleCount(Timeline.INDEFINITE);
		timeLineAnimation.play();
	}

	public void condicionesAtraparPokemon(Pokemon poke, int limite) {

		if (limite > pokemon.getLayoutX()) {
			poke.setEstado(false);
		}

		if (pokemon.getLayoutX() == -100 && poke.isEstado() == false) {
			timeLineAnimation.stop();
		}
		if (poke.isEstado() == true) {

			pokemon.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					timeLineAnimation.stop();
					File p = new File("img/p0.gif");
					Image pokem = new Image(p.toURI().toString());
					pokemon.setImage(pokem);
					m.darCampoEntrenamiento().aumentarPuntajeAtrapar();
					mensajeVolverAJugar();
				//	mensajeNombreUsuario();
					}
			});
		} else {
			pokemon.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					timeLineAnimation.stop();
					mensajeNoAtrapoPokemon(poke);;
				}
			});

		}
	}
	
	public boolean mensajeVolverAJugar() {
		boolean juego;
		Alert confirmar=new Alert(AlertType.CONFIRMATION);
		confirmar.setTitle("Volver a Jugar");
		confirmar.setHeaderText(null);
		confirmar.setContentText("Desea volver a jugar presione Aceptar\nSi desea salir y guardar presiones Cancelar");
		
		Optional<ButtonType> resultado=confirmar.showAndWait();
		if(resultado.get()==ButtonType.OK) {
			ListaPokemon();
			puntaje.setText("Puntaje: "+m.darCampoEntrenamiento().getPuntajeAtrapar());
		}
		else {
		m.darCampoEntrenamiento().agregarUnJugador(nombreJugador, m.darCampoEntrenamiento().getPuntajeLanzar(), m.darCampoEntrenamiento().getPuntajeAtrapar());

		volverInicio();

		}
		juego=true;
		
		return juego;
	}

	public void mensajeNombreUsuario() {
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setContentText("Escribe su nombre");
			try {
				String nombre = dialogo.showAndWait().get();
				if(nombre!=null) {
					//m.darCampoEntrenamiento().agregarUnJugador(nombreJugador);
					nombreJugador=nombre;
					volverInicio();
				}
			}
			catch(NoSuchElementException e) {
				mensajeNombreUsuario();
			}
		
	}
	
	public void mensajeNoAtrapoPokemon(Pokemon poke) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pokemon");
		alert.setHeaderText(null);
		alert.setContentText("Ya no puedes atrapar al pokemon");
		alert.show();
		
	}
	
/*	public void mensajeAtrapar(int i, Pokemon poke) {
		if (i == 1) {
			TextInputDialog dialogo = new TextInputDialog();
			dialogo.setContentText("Felicidades atrapastes a tu pokemon \n" + "Escribe tu nombre");
			try {
			String nombreJugador = dialogo.showAndWait().get();
			if(nombreJugador!=null) {
			m.darCampoEntrenamiento().crearArchivo(nombreJugador, poke.getNombre());
			volverInicio();
			}
			
			}
			catch(NoSuchElementException e) {
			}
			
		}
		if (i == 2) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pokemon");
			alert.setHeaderText(null);
			alert.setContentText("Ya no puedes atrapar al pokemon");
			alert.show();
			
		}

	}

	*/

	public void colocarBandera() {
		m.darCampoEntrenamiento().GenerarposicionBandera();

		File ban = new File("img/b.png");
		Image bander = new Image(ban.toURI().toString());
		bandera.setImage(bander);
		bandera.setLayoutX(m.darCampoEntrenamiento().darPosicionBander() + 10);

	}


	public void mensajeDelRecorrido() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Total Recorrido");
		alert.setHeaderText(null);
		alert.setContentText(m.darCampoEntrenamiento().darRecorridoTotal()+"\n con una velocidad de "+m.darCampoEntrenamiento().darvelocidadDelJuego()+ " m/s");
		alert.show();
	
			m.darCampoEntrenamiento().agregarUnJugador(nombreJugador, m.darCampoEntrenamiento().getPuntajeLanzar(), m.darCampoEntrenamiento().getPuntajeAtrapar());
	

	}
	
	
	public void mensajeConfirmacionUsuario() {
		Alert confirmar=new Alert(AlertType.CONFIRMATION);
		confirmar.setTitle("Usuario");
		confirmar.setHeaderText(null);
		confirmar.setContentText("Para registrarse presione Aceptar\nSi ya esta registrado presione Cancelar");
		
		Optional<ButtonType> resultado=confirmar.showAndWait();
		if(resultado.get()==ButtonType.OK) {
			System.out.println("acepta");
			mensajeNombreUsuario();
		}
		else {
			System.out.println("cancela");
			paneListaJugador.setVisible(true);

		}
	}
	public void volverInicio() {
		paneOpciones.setVisible(false);
		timeLineAnimation.stop();
		paneArena.setVisible(false);
		paneListaPokemon.setVisible(false);
		panelinicio.setVisible(true);
		m.darCampoEntrenamiento().reiniciarPuntajeLanzar();
		puntaje.setText("Puntaje:");
//		timeLineAnimation=new Timeline();
	}

}
