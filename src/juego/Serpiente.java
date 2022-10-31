package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Serpiente {

	private double x;
	private double y;
	private double diametro;
	private double angulo;
	private Color color;
	private double velocidad;
	private Image iconoSerpiente;
	
	public Serpiente(double x, double y) {
		this.x = x;
		this.y = y;
		this.color = Color.cyan;
		this.diametro = Math.random();
		this.angulo = 4;
		this.velocidad = 4;
		this.iconoSerpiente = Herramientas.cargarImagen("serpiente.png");
	}
	
	public static void agregarSerpiente(Serpiente[] serpientes) {
		for (int i=0 ; i<serpientes.length; i++) {
			if(serpientes[i] == null) {
				serpientes[i] = new Serpiente(850,300);
				return;
			}
		}
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(iconoSerpiente, x, y, 0.2, 0.12);
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
	
	public boolean chocaConMono(Mono m) {
		return (m.getX()+100 - m.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < m.getX() + m.getAncho()/2-35 &&
				
				m.getY()-52 - m.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < m.getY()+100 + m.getAlto()/2-52);
	}
	
	public boolean chocaConPiedra(Piedra p) {
		return (p.getX() - p.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < p.getX() + p.getAncho()/2 &&
				
				p.getY() - p.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < p.getY() + p.getAlto()/2);
	}


}
