package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {

	private double x;
	private double y;
	private double diametro;
	private double angulo;
	private double ancho;
	private double alto;
	private Color color;
	private double velocidad;
	private Image iconoArbol;
	
	public Arbol(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 200;
		this.alto = 40;
		this.color = Color.black;
		this.diametro = Math.random();
		this.angulo = 4;
		this.velocidad = 4;
		this.iconoArbol = Herramientas.cargarImagen("arbol.png");
	}
	
	public static void agregarArbol(Arbol[] arboles) {
		for (int i=0 ; i<arboles.length; i++) {
			if(arboles[i] ==null) {
				arboles[i] = new Arbol(870,550);
				return;
			}
		}
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(iconoArbol, x, y-180, 0, 0.57);
		e.dibujarRectangulo(x-20, y-210, ancho, alto, 0, color);
	}

	public void avanzar() {
		this.x += Math.cos(this.angulo) * this.velocidad;
	}
	
	public boolean chocaConEntorno(Entorno e) {
		if (x < -70 || x > 870) {
			return true;
		}
		return false;
	}
	
	public boolean chocaConMonoArriba(Mono m) {
		return (m.getX() - m.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < m.getX() + m.getAncho()/2 &&
				
				m.getY()< y-270 && m.getY()>275);
	}

	public boolean chocaConMonoAbajo(Mono m) {
		return (m.getX() - m.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < m.getX() + m.getAncho()/2 &&
				
				m.getY()< y+275 && m.getY()>270);
	}


}