package igrica;

public abstract class Figura {
	protected Polje polje;
	public Figura(Polje p) {
		polje=p;
	}
	public Polje dohvPolje() {
		return polje;
	}
	public void pomeriFiguru(Polje p) {
		polje=p;
	}
	public boolean equals(Object o) {
		if(!(o instanceof Figura))return false;
		return polje==((Figura)o).polje;
	}
	public abstract void crtaj();
}
