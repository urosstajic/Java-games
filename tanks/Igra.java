package igrica;

import java.awt.*;
import java.awt.event.*;

public class Igra extends Frame{
	private Mreza mreza;
	private MenuBar meni=new MenuBar();
	private MenuItem izmena,igranje;
	private Button kreni=new Button("Pocni");
	private Label poeni;
	private TextField novcic=new TextField("7");
	private Checkbox trava,zid;
	private CheckboxGroup grupa;
	private Menu rezim;
	private int brpoena=0;
	private Frame ovo=this;
	private int rezimizmene;
	
	private Igra() {
		super("Igra");
		this.setBounds(150, 150, 600, 500);
		mreza=new Mreza(this);
		rezimizmene=5;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mreza.zavrsi();
				dispose();
			}
		});
		popuniProzor();
		setVisible(true);
	}

	private void popuniProzor() {
		this.setLayout(new BorderLayout());
		add(mreza, BorderLayout.CENTER);
		this.setMenuBar(meni);
		rezim=new Menu("Rezim");
		izmena=new MenuItem("Rezim izmena");
		igranje=new MenuItem("Rezim igranja");
		rezim.add(izmena);
		rezim.add(igranje);
		meni.add(rezim);
		poeni=new Label();

		izmena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rezimizmene=0;
				kreni.setEnabled(false);
			}
			
		});
		igranje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rezimizmene=1;
				kreni.setEnabled(true);
			}
			
		});
		
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(mreza.dohvIgraca()==null)return;
				Polje trenutno = mreza.dohvIgraca().dohvPolje();
				int[] poz=trenutno.dohvPoziciju();
				Polje novo = null;
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A: //levo
						if(poz[1]==0)break;
						novo = trenutno.dohvPolje(0, -1);
						break;
					case KeyEvent.VK_D: //desno
						if(poz[1]==mreza.vrste-1)break;
						novo = trenutno.dohvPolje(0, 1);
						break;
					case KeyEvent.VK_W: //gore
						if(poz[0]==0)break;
						novo = trenutno.dohvPolje(-1, 0);
						break;
					case KeyEvent.VK_S: //dole
						if(poz[0]==mreza.vrste-1)break;
						novo = trenutno.dohvPolje(1, 0);
						break;
				}
				if (novo instanceof Trava) {
					if(novo!=null) {
						mreza.dohvIgraca().pomeriFiguru(novo);
						trenutno.repaint();
						novo.repaint();
					}
				}
			}
		});
		
		Panel panel=new Panel(new GridLayout(1,2));
		panel.add(new Label("Podloga:"),BorderLayout.CENTER);
		Panel plo=new Panel(new GridLayout(2,1));
		grupa=new CheckboxGroup();
		trava=new Checkbox("Trava",grupa,true);
		zid=new Checkbox("Zid",grupa,false);
		trava.setBackground(Color.green);
		zid.setBackground(Color.LIGHT_GRAY);
		plo.add(trava);
		plo.add(zid);
		panel.add(plo);
		this.add(panel,BorderLayout.EAST);
		
		Panel p=new Panel();
		p.add(new Label("Novcici"));
		p.add(novcic);
		
		p.add(new Label("Poena"));
		p.add(poeni);
		p.add(kreni);
		
		add(p,BorderLayout.SOUTH);
		
		kreni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int brnovcica = 0;
				if (kreni.getLabel().equals("Pocni")) {
					brnovcica = Integer.parseInt(novcic.getText());
					if(rezimizmene==1) {
						mreza.inicijalizacija(brnovcica);
						kreni.setLabel("Stani");
						postaviPoene(0);
						ovo.setFocusable(true);
						ovo.requestFocus();
					}
				}
				else{
					kreni.setLabel("Pocni");
					mreza.zavrsi();
					mreza.oslobodisve();
					brnovcica=0;
				}
			}
		});
		
	}
	
	public Button kreni() {
		return kreni;
	}
	public CheckboxGroup grupa() {
		return grupa;
	}
	protected Label labela() {
		return poeni;
	}
	public TextField novcici() {
		return novcic;
	}
	
	public int dohvPoene() {
		return brpoena;
	}
	public MenuItem igranje() {
		return igranje;
	}
	protected int rezimizmene() {
		return rezimizmene;
	}

	public void postaviPoene(int poena) {
		brpoena = poena;
		poeni.setText(brpoena+"");
		if(Integer.parseInt(novcic.getText())==brpoena) {
			mreza.zavrsi();
			mreza.oslobodisve();
			kreni.setLabel("Pocni");
		}
	}
	
	
	public static void main(String[]varg) {
		new Igra();
	}
}
