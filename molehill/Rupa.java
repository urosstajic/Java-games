package igra;

import java.awt.*;

public class Rupa extends Canvas implements Runnable{
	protected Basta basta;
	private Zivotinja zivotinja=null;
	private Thread nit=null;
	private int brojkoraka=0;
	public int trenutnikorak=0;
	private boolean radi;
	private boolean slobodna;
	
	public Rupa(Basta b) {
		basta=b;
		slobodna=true;
		radi=false;
	}
	
	public synchronized void zgrazi() {
		if(zivotinja!=null && trenutnikorak<brojkoraka)zivotinja.udarena();
	}
	
	public void stvori() {
		nit=new Thread(this);
	}
	public void pokreni() {
		nit.start();
		radi=true;
		
	}
	
	public boolean da_li_pokrenuta() {
		return radi;
	}
	
	public void zaustavi() {
		if(zivotinja!=null) {
			if(zivotinja.ud==1) {
				zivotinja.pobegla();
			}
		}
		trenutnikorak=0;
		zivotinja=null;
		if(nit!=null)nit.interrupt();
		radi=false;
		slobodna=true;
		repaint();
	}
	
	public Zivotinja dohvZivotinju() {
		return zivotinja;
	}
	
	public synchronized void postaviZivotinju(Zivotinja z) {
		zivotinja=z;
		slobodna=false;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(100);
				trenutnikorak++;
				repaint();
				if(trenutnikorak==brojkoraka) {
					Thread.sleep(2000);
					this.zaustavi();
				}
			}
		}
		catch(InterruptedException e) {}
	}
	
	public void paint(Graphics g) {
		this.setBackground(new Color(102,51,0));
		if(zivotinja!=null) {
			zivotinja.crtaj();
		}
	}
		
	public int brKoraka() {
		return brojkoraka;
	}
	
	public synchronized void postaviBrKoraka(int b) {
		brojkoraka=b;
	}
	
	public int trenutniBrKoraka() {
		return trenutnikorak;
	}
	
	
	protected boolean slobodna() {
		return slobodna;
	}
}
