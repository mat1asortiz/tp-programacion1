package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Puma[] pumas;
	private Arbol[] arboles;
	private Serpiente[] serpientes;
	private Piedra[] piedras;
	private Mono mono;
	private double tiempoDeJuego;
	private boolean puedeDisparar;
	private boolean puedeBajar;
	private boolean puedeSubir;
	private int tiempoDisparo;
	
	private double piso;
	private int cantSaltos;
	private Image fondo;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo 7 -Ortiz -Albornoz -Valdez - V0.01", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.pumas= new Puma[4];
		
		this.arboles = new Arbol[4];
		
		this.serpientes = new Serpiente[2];
		
		this.piedras = new Piedra[1];
		
		this.puedeDisparar = true;
		this.tiempoDisparo = 0;
		
		this.piso = 550;
		
		this.mono = new Mono(120,550);
		this.fondo = Herramientas.cargarImagen("image0.png");
		
		this.tiempoDeJuego = 0;
		
		this.puedeBajar = true;
		this.puedeSubir = true;
		
		this.cantSaltos = 0;
		

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
		if (mono.vidasRestantes() <= 0) {
			entorno.cambiarFont("Arial", 100, Color.RED);
			entorno.escribirTexto("GAME OVER", entorno.ancho() * 0.15, entorno.alto() / 2);
			return;
		}
		
		if (cantSaltos == 20) {
			entorno.cambiarFont("Arial", 100, Color.RED);
			entorno.escribirTexto("GANASTE!", entorno.ancho() * 0.15, entorno.alto() / 2);
			entorno.cambiarFont("Arial", 60, Color.RED);
			entorno.escribirTexto("Llegaste a 20 puntos", entorno.ancho() * 0.10, entorno.alto() / 4);

			return;
		}
		
		entorno.escribirTexto(mono.imprimirPuntaje(), entorno.ancho() * 0.05, entorno.alto() * 0.05);
		entorno.escribirTexto(mono.imprimirVidas(), entorno.ancho() * 0.8, entorno.alto() * 0.05);
		
		
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
			Piedra.agregarPiedra(this.piedras);
		}
		
		
			for (int i = 0; i < arboles.length; i++) {
				if (arboles[i] != null) {
					arboles[i].dibujar(entorno);
					arboles[i].avanzar();
					if(arboles[i].chocaConMonoArriba(mono)==true) {
						mono.saltoTronco();
						puedeBajar=false;
					} else if (arboles[i].chocaConMonoArriba(mono)==false) {
						puedeBajar=true;
					} if (arboles[i].chocaConMonoAbajo(mono)==true && puedeBajar==true) {
						puedeSubir=false;
					} else if (arboles[i].chocaConMonoAbajo(mono)==false) {
						puedeSubir=true;
					}
					if(arboles[i].chocaConEntorno(entorno)){
						arboles[i]=null;
					}
				}
			}
			
			for (int i = 0; i < serpientes.length; i++) {
				if (serpientes[i] != null) {
					serpientes[i].dibujar(entorno);
					serpientes[i].avanzar();
					if(serpientes[i].chocaConMono(mono)) {
						mono.tocaEnemigo();
//						entorno.cambiarFont("Arial", 100, Color.RED);
//						entorno.escribirTexto("GAME OVER", entorno.ancho() * 0.15, entorno.alto() / 2);
					}
				if(serpientes[i].chocaConEntorno(entorno)){
						serpientes[i]=null;
					}
				}
			}
	
		if (tiempoDeJuego%150 == 0) {
			Arbol.agregarArbol(this.arboles);
			Serpiente.agregarSerpiente(this.serpientes);
			Puma.agregarPuma(this.pumas);
		}

		tiempoDeJuego++;
		
		for (int i = 0; i < pumas.length; i++) {
			if (pumas[i] != null) {
				pumas[i].dibujar(entorno);
				pumas[i].avanzar();
				if(pumas[i].chocaConMono(mono)) {
					mono.tocaEnemigo();
//					entorno.cambiarFont("Arial", 100, Color.RED);
//					entorno.escribirTexto("GAME OVER", entorno.ancho() * 0.15, entorno.alto() / 2);
				}
				if(pumas[i].chocaConEntorno(entorno)){
					pumas[i]=null;
				}
			}
		}
		
		//no creo que haga falta el tiempo de deisparo, seria mas facil ajustando el largo del arreglo.
		for (int i = 0; i < piedras.length; i++) {
			if (piedras[i] != null) {
				piedras[i].dibujar(entorno);
				piedras[i].avanzar();
//				if(piedras[i].chocaConMono(mono)) {
//					mono.tocaEnemigo();
////					entorno.cambiarFont("Arial", 100, Color.RED);
////					entorno.escribirTexto("GAME OVER", entorno.ancho() * 0.15, entorno.alto() / 2);
//				}
				if(piedras[i].chocaConEntorno(entorno)){
					piedras[i]=null;
				}
			}
		}
		
//		tiempoDisparo++;
//		if(tiempoDisparo >= 20) {
//			puedeDisparar = true;
//		}
		
		
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&& mono.getY()>50 && puedeSubir==true) {
			mono.saltar();
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ABAJO) && mono.getY()<550 && puedeBajar==true) {
			mono.bajar();
		}
		
//		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
//			Puma.agregarPuma(this.pumas);
//		}


		mono.dibujar(entorno);
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
