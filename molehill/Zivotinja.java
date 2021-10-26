package igra;

import java.awt.*;

public abstract class Zivotinja {
	protected int ud;
	protected Rupa rupa;
	public Zivotinja(Rupa r) {
		rupa=r;
		ud=0;
	}
	public abstract void udarena();
	public abstract void pobegla();
	public void crtaj() {
		Graphics g=rupa.getGraphics();
		int h=(int) (rupa.getHeight()*((double)(rupa.trenutnikorak))/rupa.brKoraka());
		int w=(int) (rupa.getWidth()*((double)(rupa.trenutnikorak))/rupa.brKoraka());
		crt(g,w,h);
	}
	public abstract void crt(Graphics g,int w,int h);
}
