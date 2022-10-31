package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mono {

	private static double x;
	private static double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private double velocidad;
	private Integer vidas;
	
	private Integer puntaje; // el puntaje es más que nada una caracteristica del juego, y no del mono

	
	private Image monoQuieto;
	
	public Mono(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 200;
		this.alto = 40;
		this.angulo = 4;
		this.color = Color.yellow;
		this.velocidad = 2;
		this.vidas = 1; // si las vidas son mas, ajustar el numero a ellas.
		this.puntaje = 0;
		
		this.monoQuieto = Herramientas.cargarImagen("Monkey-01.png");
	}
	
	public void dibujar(Entorno e) {
//		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(monoQuieto, x, y, 0, 0.14);
	}

	public void dibujarDisparo(Entorno e) {
		e.dibujarImagen(monoQuieto, x, y, 0, 0.16);
	}
	
	public void moverDerecha(Entorno e) {
		if (x + ancho / 2 < e.ancho()) {
			this.x += velocidad;
		}
	}

	public void moverIzquierda() {
		if (x - ancho / 2 > 0) {
			this.x -= velocidad;
		}

	}
	

	
	public void saltar() {
		this.y += Math.cos(this.angulo) * this.velocidad;
	}
	
	public void bajar() {
		this.y -= Math.cos(this.angulo) * this.velocidad;
	}
	
	

	public void monoCae() {
		if (x - ancho / 2 > 0) {
			this.x -= velocidad;
		}

	}
	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	
	public double vidasRestantes() {
		return vidas;
	}

	public void tocaEnemigo() {
		vidas--;
	}
	public void saltoTronco() {
		puntaje = (puntaje+1)%10;
	}
	
	public String imprimirPuntaje() {
		return "Score: " + puntaje.toString();
	}

	public String imprimirVidas() {
		return "Vidas restantes: " + vidas.toString();
	}

}
