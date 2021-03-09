package ImageResize;

import java.awt.image.BufferedImage;

public abstract class ImageAbstract implements ImageInterface{

	//ImageAbstract contine initializarea variabilelor pentru inaltimea si lungimea imaginii si de asemenea pentru imagine
	
	protected int width; 
	protected int height; 
	protected BufferedImage image; 

	//Deoarece aceasta clasa implementeaza interfata ImageInterface, ea contine toti setterii si getterii din interfata
	//Contine setterul si getterul pentru BufferedImage
	
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract BufferedImage getImage();
	public abstract void setWidth(int width);
	public abstract void setHeight(int height);
	public abstract void setImage(BufferedImage image);
	
	//Constructorul pentru initializarea variablilelor
	
	ImageAbstract() {
		width=0;
		height=0;
		image = null;
	}
}
