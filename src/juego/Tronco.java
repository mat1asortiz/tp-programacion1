package juego;

import java.awt.Color;

import entorno.Entorno;

public class Tronco {

	private double x;
	private double y;
	private double diametro;
	private double ancho;
	private double alto;
	private Color color;
	private double velocidad;
	
	public Tronco(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 200;
		this.alto = 40;
		this.color = Color.yellow;
		this.velocidad = 2;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	
	public boolean chocaConMono(Mono m) {
		return (m.getX() - m.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < m.getX() + m.getAncho()/2 &&
				
				m.getY() - m.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < m.getY() + m.getAlto()/2);
	}

}