package baloni;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame{
	private Scena scena;
	
	private Igra() {
		super("Baloni");
		setBounds(150,150,400,400);
		this.setLayout(new BorderLayout());
		scena=new Scena(this);
		add(scena, BorderLayout.CENTER);
		
		addWindowListener((WindowListener) new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustavi();
				dispose();
			}
		});
		
		scena.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(scena.igrac()==null)return;
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:scena.igrac().pomeri(-10);break;
					case KeyEvent.VK_RIGHT:scena.igrac().pomeri(10);break;
				}
				}
			});
		setVisible(true);
		scena.pokreni();
	}
	public static void main(String[]varg) {
		new Igra();
	}
}
