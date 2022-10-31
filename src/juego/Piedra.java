package juego;

import java.awt.Color;

import entorno.Entorno;

public class Piedra {
	private static double x;
	private static double y;
	private double tamanio;
	private double velocidad;
	private double angulo;
	private double alto;
	private double ancho;
	
	private double frente;
	private double atras;
	private double cabeza;
	private double pies;
	
	private Color color;

	public Piedra(double x, double y) {
		this.x = x;
		this.y = y;
		this.angulo = 4;
		this.velocidad = 8;
		this.tamanio = 40;
		this.color = Color.GRAY;

		this.frente = this.x + this.ancho / 2;
		this.atras = this.x - this.ancho / 2;
		this.cabeza = this.y - this.alto / 2;
		this.pies = this.y + this.alto / 2;
	}
	
	public static void agregarPiedra(Piedra[] piedras) {
		for (int i=0 ; i<piedras.length; i++) {
			if(piedras[i] == null) {
				piedras[i] = new Piedra(Mono.getX(),Mono.getY());
				return;
			}
		}
	}
	

	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, tamanio, color);
	}


	public void avanzar() {
		this.x -= Math.cos(this.angulo) * this.velocidad;
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

	public boolean chocaConEntorno(Entorno e) {
		if (x < -70 || x > 870) {
			return true;
		}
		return false;
	}
	

//	public boolean chocaConPuma(Puma p) {
//		return (p.getX() - p.getAncho()/2 < x + diametro/2 && 
//				x - diametro/2 < p.getX() + p.getAncho()/2 &&
//				
//				p.getY() - p.getAlto()/2 < y + diametro/2 &&
//				y - diametro/2 < p.getY() + p.getAlto()/2);
//	}
	
}
