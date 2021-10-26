package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Zid extends Polje{

	public Zid(Mreza m) {
		super(m);
		setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public boolean da_li_dozvoljeno(Figura f) {
		// TODO Auto-generated method stub
		return false;
	}
}
