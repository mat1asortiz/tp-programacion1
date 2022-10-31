package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Puma {

	private double x;
	private double y;
	private double diametro;
	private double angulo;
	private Color color;
	private double velocidad;
	private Image iconoPuma;
	
	public Puma(double x, double y) {
		this.x = x;
		this.y = y;
		this.color = Color.cyan;
		this.diametro = Math.random();
		this.angulo = 4;
		this.velocidad = 4;
		this.iconoPuma = Herramientas.cargarImagen("puma01.png");
	}
	
	public static void agregarPuma(Puma[] pumas) {
		for (int i=0 ; i<pumas.length; i++) {
			if(pumas[i] ==null) {
				pumas[i] = new Puma(870,550);
				return;
			}
		}
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(iconoPuma, x, y, 0.2, 0.25);
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
		return (m.getX()+20 - m.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < m.getX() + m.getAncho()/2 &&
				
				m.getY()-52 - m.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < m.getY()+105 + m.getAlto()/2-52);
	}
	
	public boolean chocaConPiedra(Piedra p) {
		return (p.getX() - p.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < p.getX() + p.getAncho()/2 &&
				
				p.getY() - p.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < p.getY() + p.getAlto()/2);
	}



}
