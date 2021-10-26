package baloni;

import java.awt.*;

public class Krug {
	protected Vektor centar;
	private Color boja;
	private double precnik;
	public Krug(Vektor cent,Color bb,double pp) {
		centar=cent;
		boja=bb;
		precnik=pp;
	}
	public boolean preklapaSe(Krug k) {
		double b=Math.sqrt(Math.pow(centar.dohvX()-k.centar.dohvX(), 2)+Math.pow(centar.dohvY()-k.centar.dohvY(), 2));
		if(b<precnik/2+k.precnik/2)return true;
		else return false;
	}
	public void iscrtaj(Scena s) {
		Graphics g=s.getGraphics();
		g.setColor(boja);
		g.fillOval((int) (Math.round(centar.dohvX()-precnik/2)), (int)(Math.round(centar.dohvY()-precnik/2)), (int)(precnik),(int)(precnik));
		if(this instanceof Igrac) {
			g.setColor(Color.blue);
			g.fillOval((int)( Math.round(centar.dohvX()-precnik/4)), (int)(Math.round(centar.dohvY()-precnik/4)), (int)(precnik/2),(int)(precnik/2));
		}
	}
}
