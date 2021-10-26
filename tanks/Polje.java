package igrica;

import java.awt.*;
import java.awt.event.*;

public abstract class Polje extends Canvas{
	private Mreza mreza;
	
	public Polje(Mreza m) {
		mreza=m;
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(mreza.dohvIgru().rezimizmene()!=0)return;
				int[]niz=Polje.this.dohvPoziciju();
				int index=mreza.vrste*niz[0]+niz[1];
				mreza.promeniPolje(index,Polje.this);
				mreza.revalidate();
			}
		});
	}
	public Mreza dohvMrezu() {
		return mreza;
	}
	public int[] dohvPoziciju(){
		int[]niz=new int[2];
		for(int i=0;i<mreza.vrste;i++) {
			for(int j=0;j<mreza.vrste;j++) {
				if(mreza.dohvMatricu()[i][j]==this) {
					niz[0]=i;
					niz[1]=j;
					return niz;
				}
			}
		}
		return null;
	}
	public Polje dohvPolje(int m,int n) {
		int[] tek=dohvPoziciju();
		int mojavrsta=tek[0];
		int mojakolona=tek[1];
		int novom=0,novon=0;
		if(mojavrsta+m>mreza.vrste)novon++;
		novom=(mojavrsta+m)%mreza.vrste;
		if(mojakolona+n>mreza.vrste)novom++;
		novon=(mojakolona+n)%mreza.vrste;
		if(mreza.dohvMatricu()[novom][novon]!=null)return mreza.dohvMatricu()[novom][novon];
		else return null;
	}
	public abstract boolean da_li_dozvoljeno(Figura f);
	
}
