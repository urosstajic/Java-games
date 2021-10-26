package igra;

import java.awt.*;
import java.awt.event.*;

public class Basta extends Panel implements Runnable{
	private int m,n;
	private Rupa [][]matrica;
	protected int kolicina;
	private int cekanje=0;
	private int brkoraka=0;
	private Thread nit=null;
	
	public Basta(int mm,int nn) {
		m=mm;
		n=nn;
		this.setBackground(Color.green);
		setLayout(new GridLayout(m, n, 20, 20));
		kolicina=100;
		matrica=new Rupa[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				Rupa r=new Rupa(this);
				matrica[i][j]=r;
				matrica[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						((Rupa)e.getComponent()).zgrazi();
					}
				});
				add(r);
			}
		}
	}
	public int dohvBrKoraka() {
		return brkoraka;
	}
	public synchronized void postaviBrKoraka(int b) {
		brkoraka=b;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrica[i][j].postaviBrKoraka(b);
			}
		}
	}
	public synchronized void smanjiKolicinu() {
		kolicina--;
		Igra.dohvIgru().azurirajPovrce(kolicina);
	}
	public void pokreni() {
		nit=new Thread(this);
		nit.start();
	}
	public void zaustavi() {
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(matrica[i][j].da_li_pokrenuta()) {
					matrica[i][j].zaustavi();
				}
			}
		}
		if(nit!=null)nit.interrupt();
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				int mm=(int) (Math.random()*m);
				int nn=(int)(Math.random()*n);
				if(slobodnaaa(matrica[mm][nn])) {
					Krtica kr=new Krtica(matrica[mm][nn]);
					matrica[mm][nn].postaviZivotinju(new Krtica(matrica[mm][nn]));
					matrica[mm][nn].stvori();
					matrica[mm][nn].pokreni();
					repaint();
					Thread.sleep(cekanje);
					cekanje=cekanje-cekanje/100;
				}
				repaint();
			}
		}
		catch(InterruptedException e){}
	}
	public synchronized void postaviCekanje(int c) {
		cekanje=c;
	}
	public boolean slobodnaaa(Rupa r) {
		return r.slobodna();
	}
	
	public synchronized void postaviPovrce(int povrce) {
		kolicina= povrce;
	}
	public int dohvPovrce() {
		return kolicina;
	}

}
