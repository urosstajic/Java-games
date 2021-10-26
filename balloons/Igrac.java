package baloni;

import java.awt.Color;

public class Igrac extends KruznaFigura{

	public Igrac(Vektor cent, int pp, Vektor brz, Scena s) {
		super(cent, Color.green, pp, brz, s);
		
		// TODO Auto-generated constructor stub
	}
	public void pomeri(double pomeraj) {
		if(centar.dohvX()+pomeraj>=0 && centar.dohvX()+pomeraj<scena.getWidth()) {
			centar.saberi(new Vektor(pomeraj,0));
		}
	}
	@Override
	public void da_li_sudar(KruznaFigura f) {
		if(f instanceof Balon) {
			scena.zaustavi();
		}
	}
}
