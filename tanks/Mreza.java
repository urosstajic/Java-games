package igrica;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class Mreza extends Panel implements Runnable{
	private Polje[][]matrica;
	private Igra igra;
	private Thread nit;
	private List<Novcic>novac=new ArrayList<>();
	private List<Tenk>tenkovi=new ArrayList<>();
	protected int vrste=17;
	private Igrac igrac=null;
	private int brpoena=0;
	
	public Mreza(int m,Igra ig) {
		igra=ig;
		matrica=new Polje[m][m];
		vrste=m;
		this.setLayout(new GridLayout(vrste,vrste));
		dodajPolja();
		for(int i=0;i<m;i++) {
			for(int j=0;j<m;j++) {
				add(matrica[i][j]);
			}
		}
		
	}
	public void dodajPolja() {
		for(int i=0;i<vrste;i++) {
			for(int j=0;j<vrste;j++) {
				double v=Math.random()*100;
				if(v<80) {
					matrica[i][j]=new Trava(this);
				}
				else {
					matrica[i][j]=new Zid(this);
				}
			}
		}
	}
	public Mreza(Igra i) {
		this(17,i);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(40);
				azuriraj();
				if(tenkovi.size()!=0) {
					for(Tenk t:tenkovi) {
						t.crtaj();
					}
				}
				if(novac.size()!=0) {
					for(Novcic n:novac) {
						n.crtaj();
					}
				}
				if(igrac!=null) {
					igrac.crtaj();
				}
				if(igra.rezimizmene()==0) {
					zavrsi();
					oslobodisve();
					for(int i=0;i<vrste;i++) {
						for(int j=0;j<vrste;j++) {
							matrica[i][j].repaint();
						}
					}
					igra.kreni().setLabel("Pocni");
				}
			}
		} catch (InterruptedException e) {}
		
	}
	
	public Polje[][] dohvMatricu(){
		return matrica;
	}
	
	protected Igra dohvIgru() {
		return igra;
	}
	
	public void inicijalizacija(int brnovcica) {
		for(int i=0;i<vrste;i++) {
			for(int j=0;j<vrste;j++) {
				matrica[i][j].repaint();
			}
		}
		for(int i=0;i<brnovcica;i++) {
			int vr=(int)(Math.random()*vrste);
			int kol=(int)(Math.random()*vrste);
			Novcic n=new Novcic(matrica[vr][kol]);
			while(slobodno(vr,kol)==false || matrica[vr][kol].da_li_dozvoljeno(n)==false) {
				vr=(int)(Math.random()*vrste);
				kol=(int)(Math.random()*vrste);
				n=new Novcic(matrica[vr][kol]);
			}
			novac.add(n);
		}
		int brtenkova=(int)(brnovcica/3);
		for (int i=0; i<brtenkova; i++) {
			int vr=(int) (Math.random()*vrste);
			int kol=(int)(Math.random()*vrste);
			Tenk t = new Tenk(matrica[vr][kol]);
			while(slobodno(vr,kol)==false || matrica[vr][kol].da_li_dozvoljeno(t)==false) {
				vr=(int)(Math.random()*vrste);
				kol=(int)(Math.random()*vrste);
				t=new Tenk(matrica[vr][kol]);
			}
			t.pokreniTenk();
			tenkovi.add(t);
		}
		int vr=(int) (Math.random()*vrste);
		int kol=(int) (Math.random()*vrste);
		while (slobodno(vr, kol) == false || (matrica[vr][kol] instanceof Zid)) {
			vr=(int) (Math.random()*vrste);
			kol=(int) (Math.random()*vrste);
		}
		igrac=new Igrac(matrica[vr][kol]);
		brpoena=0;
		pokreni();
	}
	
	public boolean slobodno(int m,int n) {
		if(igrac!=null){
			if(igrac.polje==matrica[m][n]) return false;
		}
		for(Novcic no:novac){
			if(no.dohvPolje()==matrica[m][n]) return false;
		}
		for(Tenk t:tenkovi){
			if(t.polje==matrica[m][n]) return false;
		}
		return true;
	}
	
	public void zavrsi() { 
		for (int i=0;i<tenkovi.size();i++) {
			tenkovi.get(i).zaustaviTenk();
		}
		if (nit!=null) {
			nit.interrupt(); 
		}
	}
	public void oslobodisve() {
		tenkovi.clear();
		if(novac.size()!=0)novac.clear();
		igrac=null;
	}
	
	
	public Igrac dohvIgraca() {
		return igrac;
	}
	public void pokreni() {
		nit=new Thread(this);
		nit.start();
	}
	
	
	public void azuriraj() {
		for (int i=0;i<novac.size();i++) {
			if (igrac.dohvPolje()==novac.get(i).dohvPolje() && igrac!=null) {
				int poen=igra.dohvPoene()+1;
				novac.remove(i);
				igra.postaviPoene(poen);
			}
		}
		for (int i=0;i<tenkovi.size();i++) {
			if (igrac.dohvPolje()==tenkovi.get(i).dohvPolje() && igrac!=null) {
				zavrsi();
				oslobodisve();
				igra.kreni().setLabel("Pocni");
			}
		}
	}
	public List<Novcic> dohvNovcice() {
		return novac;
	}
	public List<Tenk> dohvTenkove() {
		return tenkovi;
	}
	public void promeniPolje(int index, Polje pp) {
		if(igra.grupa().getSelectedCheckbox().getLabel().equals("Trava")) {
			if(pp instanceof Zid) {
				remove(index);
				Polje t=new Trava(this);
				add(t, index);
				int[]niz=pp.dohvPoziciju();
				matrica[niz[0]][niz[1]]=t;
			}
		}
		else {
			if(pp instanceof Trava) {
				remove(index);
				Polje z=new Zid(this);
				add(z,index);
				int[]niz=pp.dohvPoziciju();
				matrica[niz[0]][niz[1]]=z;
			}
		}
	}
}
