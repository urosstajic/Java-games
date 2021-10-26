package igrica;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Trava extends Polje{

	public Trava(Mreza m) {
		super(m);
		setBackground(Color.GREEN);
	}

	@Override
	public boolean da_li_dozvoljeno(Figura f) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
