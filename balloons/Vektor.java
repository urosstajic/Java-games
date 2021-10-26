package baloni;

public class Vektor implements Cloneable{
	private double x,y;
	public Vektor(double xx,double yy) {
		x=xx;
		y=yy;
	}
	public double dohvX() {
		return x;
	}
	public double dohvY() {
		return y;
	}
	public Vektor clone() throws CloneNotSupportedException {
		Vektor v=(Vektor)super.clone();
		v.x=x;
		v.y=y;
		return v;
	}
	public Vektor pomnoziVektor(double vredn) {
		double xx=x*vredn;
		double yy=y*vredn;
		return new Vektor(xx,yy);
	}
	public Vektor saberi(Vektor v) {
		x=x+v.x;
		y=y+v.y;
		return this;
	}
}
