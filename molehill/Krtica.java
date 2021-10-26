package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja{
	public Krtica(Rupa r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void udarena() {
		ud=1;
		rupa.zaustavi();
	}

	@Override
	public void pobegla() {
		ud=0;
		rupa.basta.smanjiKolicinu();
		
	}

	@Override
	public void crt(Graphics g, int w, int h) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(rupa.getWidth()/2-w/2, rupa.getHeight()/2-h/2, w, h);
		}
	}


