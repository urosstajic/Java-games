package igrica;

import java.awt.*;

public class Igrac extends Figura{

	public Igrac(Polje p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crtaj() {
		Graphics g=polje.getGraphics();
		g.setColor(Color.red);
		g.drawLine(polje.getWidth()/2, 0, polje.getWidth()/2, polje.getHeight()-1);
		g.drawLine(0, polje.getHeight()/2, polje.getWidth()-1, polje.getHeight()/2);
	}

}
