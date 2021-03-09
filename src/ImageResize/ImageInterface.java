package ImageResize;

public interface ImageInterface { //ImageInterface este o interfata care va fi extinsa de catre clasa ImageAbstract.
	
	//Ea contine metodele care trebuie implementate de catre aceasta clasa, precum setter si getteri pentru inaltimea si lungimea imaginilor.
	
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract void setWidth(int width);
	public abstract void setHeight(int height);
}