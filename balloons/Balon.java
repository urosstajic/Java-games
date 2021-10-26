package baloni;

import java.awt.Color;

public class Balon extends KruznaFigura{

	public Balon(Vektor cent, int pp, Vektor brz, Scena s) {
		super(cent, Color.red, pp, brz, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void da_li_sudar(KruznaFigura f) {}

}
