package baloni;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;

public class Scena extends Canvas implements Runnable{
	private Igra igra;
	private Thread nit=null;
	private double verovatnoca=0;
	private Igrac ig;
	private List<KruznaFigura>figure=new ArrayList<>();
	public Scena(Igra i) {
		igra=i;
		this.setFocusable(true);
		requestFocus();
	}
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()) {
			Thread.sleep(60);
			repaint();
			verovatnoca=Math.random();
			if(verovatnoca<=0.1) {
				Balon b=new Balon(new Vektor(Math.random()*this.getWidth()*Math.random(),20),20,new Vektor(0,100),this);
				dodaj(b);
			}
			for(int i=0;i<figure.size();i++) {
				if(figure.get(i) instanceof Balon) {
					figure.get(i).obavesti(0.06);
				}
				repaint();
			}
			for(KruznaFigura f:figure) {
				for(KruznaFigura ff:figure) {
					if(f.preklapaSe(ff)) {
						if(f instanceof Igrac && ff instanceof Balon) {
							this.zaustavi();
						}
						if(ff instanceof Igrac && f instanceof Balon) {
							this.zaustavi();
						}
					}
					}
				}
			}
		}
		catch(InterruptedException e) {}
	}
	
	public void zaustavi() {
		if(nit!=null)nit.interrupt();
	}
	
	public void pokreni() {
		nit=new Thread(this);
		inicijalizuj();
		nit.start();
	}
	public void izbaci(KruznaFigura figura) {
		for(int i=0;i<figure.size();i++) {
			if(figura==figure.get(i)) {
				figure.remove(i);
			}
		}
		
	}
	public void dodaj(KruznaFigura f) {
		figure.add(f);
	}
	public void paint(Graphics g) {
		for(int i=0;i<figure.size();i++) {
			figure.get(i).iscrtaj(this);
		}
	}
			
	public void inicijalizuj() {
		Igrac i=new Igrac(new Vektor(this.getWidth()/2,this.getHeight()-50),30,new Vektor(0,0),this);
		ig=i;
		figure.add(ig);
	}
	
	public Igrac igrac() {
		return ig;
	}
}
