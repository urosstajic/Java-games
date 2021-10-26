package igrica;

import java.awt.*;

public class Tenk extends Figura implements Runnable{
	private Thread nit;
	public Tenk(Polje p) {
		super(p);
		// TODO Auto-generated constructor stub
		nit=new Thread(this);
	}
	public void pokreniTenk() {
		nit.start();
	}
	public void zaustaviTenk() {
		if(nit!=null)nit.interrupt();
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(500);
				double smer=Math.random();
				Polje novo=this.pomeri(smer);
				Tenk n=new Tenk(novo);
				if(novo!=null && novo.da_li_dozvoljeno(n)){
					Polje staro=polje;
					polje=novo;
					staro.repaint();
				}
			}
		}
		catch(InterruptedException e) {}
	}
	public Polje pomeri(double smer) {
		if(smer>0 &&smer<=0.25) {
			//levo
			int[]poz=polje.dohvPoziciju();
			if(poz[0]==0)return null;
			Polje p=polje.dohvPolje(-1, 0);
			if(!(p instanceof Zid))return p;
		}
		if(smer>0.25 && smer<=0.5) {
			//desno
			int[]poz=polje.dohvPoziciju();
			if(poz[0]==polje.dohvMrezu().vrste-1)return null;
			Polje p=polje.dohvPolje(1, 0);
			if(!(p instanceof Zid))return p;
		}
		if(smer>0.5 && smer<=0.75) {
			//gore
			int[]poz=polje.dohvPoziciju();
			if(poz[1]==0)return null;
			Polje p=polje.dohvPolje(0, -1);
			if(!(p instanceof Zid))return p;
		}
		if(smer>0.75 && smer<=1) {
			//dole
			int[]poz=polje.dohvPoziciju();
			if(poz[1]==polje.dohvMrezu().vrste-1)return null;
			Polje p=polje.dohvPolje(0, 1);
			if(!(p instanceof Zid))return p;
		}
		return null;
	}
	@Override
	public void crtaj() {
		Graphics g=polje.getGraphics();
		g.setColor(Color.black);
		g.drawLine(0, 0, polje.getWidth()-1, polje.getHeight()-1);
		g.drawLine(polje.getWidth()-1, 0, 0, polje.getHeight()-1);
	}

}
