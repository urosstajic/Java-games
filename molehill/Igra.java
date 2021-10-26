package igra;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame{
	private Basta basta=new Basta(4,4);
	private boolean radi=false;
	private static Igra igra;
	private Button kreni;
	private Checkbox srednje,lako,tesko;
	private Label labela;
	
	private Igra() {
		super("Igra");
		this.setSize(500,500);
		popuniProzor();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				basta.zaustavi();
				dispose();
			}
		});
		setVisible(true);
	}
	
	private void popuniProzor() {
		this.add(basta, BorderLayout.CENTER);
		kreni=new Button();
		Panel panel=new Panel(new GridLayout(5,1));
		panel.add(new Label("Tezina",Label.CENTER));
		CheckboxGroup grupa=new CheckboxGroup();
		srednje=new Checkbox("Srednje",grupa,false);
		tesko=new Checkbox("Tesko",grupa,false);
		lako=new Checkbox("Lako",grupa,true);
		panel.add(tesko);
		panel.add(srednje);
		panel.add(lako);
		panel.add(kreni);
		kreni.setLabel("Kreni");
		kreni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(kreni.getLabel().equals("Kreni")) {
					if(grupa.getSelectedCheckbox().getLabel().equals("Lako")) {
						
						basta.postaviBrKoraka(10);
						basta.postaviCekanje(1000);
						basta.postaviPovrce(100);
						kreni.setLabel("Stani");
						kreni.invalidate();
						basta.pokreni();
					}
					if(grupa.getSelectedCheckbox().getLabel().equals("Srednje")) {
						
						basta.postaviBrKoraka(8);
						basta.postaviCekanje(750);
						basta.postaviPovrce(100);
						kreni.setLabel("Stani");
						kreni.invalidate();
						basta.pokreni();
					}
					if(grupa.getSelectedCheckbox().getLabel().equals("Tesko")) {
						
						basta.postaviBrKoraka(6);
						basta.postaviCekanje(500);
						basta.postaviPovrce(100);
						kreni.setLabel("Stani");
						kreni.invalidate();
						basta.pokreni();
					}
					labela.setText("Povrce"+basta.dohvPovrce());
					
				}
				else {
					kreni.setLabel("Kreni");
					basta.zaustavi();
				}	
			}
		});
		
		Panel plo=new Panel(new GridLayout(2,1));
		plo.add(panel);
		Panel p=new Panel(new BorderLayout());
		labela=new Label("Povrce 0",Label.CENTER);
		labela.setFont(new Font(null,Font.BOLD,14));
		p.add(labela,BorderLayout.CENTER);
		plo.add(p);
		this.add(plo, BorderLayout.EAST);
	}
	
	public void azurirajPovrce(int kol) {
		labela.setText("Povrce"+kol);
		if (kol == 0) {
			basta.zaustavi();
			kreni.setLabel("Kreni");
		}
	}
	
	public static Igra dohvIgru() {
		if(igra==null)igra=new Igra();
		return igra;
	}

public static void main(String[]varg) {
	Igra.dohvIgru();
}
}