package application;
	
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import modelo.CampoEntrenamiento;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private CampoEntrenamiento campo;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Vista.fxml"));
			Scene scene = new Scene(root,root.getPrefHeight(),root.getPrefWidth());
		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public void iniciarCampo() {
		campo=new CampoEntrenamiento();
	}
	
	public CampoEntrenamiento darCampoEntrenamiento() {
		return campo;
	}
	
	
	
	public void CrearPokemon() {
		campo.agregarPokemon("Arcanine", "img/p1.gif",true, 0);
		campo.agregarPokemon("Pikachu", "img/p2.gif",true, 1);
		campo.agregarPokemon("Charizard", "img/p3.gif",true, 2);
		campo.agregarPokemon("Chicorita", "img/p4.gif",true, 3);
		campo.agregarPokemon("Talonflame", "img/p5.gif",true, 4);
		campo.agregarPokemon("Gengar", "img/p6.gif",true, 5);
		campo.agregarPokemon("Dragonite", "img/p7.gif",true, 6);
		campo.agregarPokemon("Miau", "img/p8.gif",true, 7);
		campo.agregarPokemon("Jolteon", "img/p9.gif",true, 8);
		campo.agregarPokemon("Gyarados", "img/p10.gif",true, 9);
	
	}
}
