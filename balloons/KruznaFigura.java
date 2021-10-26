package baloni;

import java.awt.Color;

public abstract class KruznaFigura extends Krug{
	private Vektor brzina;
	protected Scena scena;
	public KruznaFigura(Vektor cent, Color bb, int pp,Vektor brz,Scena s) {
		super(cent, bb, pp);
		// TODO Auto-generated constructor stub
		brzina=brz;
		scena=s;
	}
	
	public void obavesti(double vv) {
		centar.saberi(brzina.pomnoziVektor(vv));
		if(centar.dohvX()<0 || centar.dohvY()<0 || centar.dohvX()>=scena.getWidth() || centar.dohvY()>=scena.getHeight()) {
			scena.izbaci(this);
		}
		else {
			iscrtaj(scena);
		}
	}
	public abstract void da_li_sudar(KruznaFigura f);
}
