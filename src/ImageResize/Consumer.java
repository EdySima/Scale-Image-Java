package ImageResize;

import java.awt.image.BufferedImage;

public class Consumer extends Thread{
	private Buffer buffer;			//buffer-ul prin care vor comunica cele 2 Thread-uri
	private BufferedImage img;		//imaginea originala
	private Image image;			//clasa imagine pentru stocarea imaginii originale si pentru prelucrarea ulterioare
	private int[][] buffer_image;	//codurile de culoare pentru un sfert de imagine
	
	public Consumer (Buffer b, BufferedImage img, Image image){ //constructorul clasei, cu parametrii necesari
		this.buffer = b;		//initializarea buffer-ului
		this.image = image;		//initializarea atributului image
		this.img = img;			//initializarea atributului img		
		
		//Se initializeaza atributul image
		image.setWidth(img.getWidth());
		image.setHeight(img.getHeight());
		image.setImage(new BufferedImage(img.getWidth(), img.getHeight(), img.getType()));
		
		System.out.println("Create Consumer!");//Se afiseaza ca a fost apelat Constructorul clasei Consumer
	}
	
	public void run(){
		System.out.println("Start Consumer!");//Se afiseaza ca a inceput executia Thread-ul Consumer
		
		//se seteaza variabilele de dimensiune a imaginii, pentru a nu fi nevoie apelarea de fiecare data a getterelor pentru fiecare pas al unui for
		int width = img.getWidth();
		int height = img.getHeight();
		
		for (int i = 0; i < 4; i++){ //k+1 reprezinta sfertul care urmeaza a fi citit din buffer
			buffer_image = buffer.get(); //Se citeste din buffer sfertul de imagine
			image.read(buffer_image, (height/4)*i, (height/4)*(i+1), width); //Se scrie un sfert de imagine in atributul de imagine din clasa Image
			System.out.println("Consumer a luat din buffer sfertul " + (i+1)); //Se afiseaza ca Thread-ul Consumer a citit din buffer un sfert de imagine
			
			//Thread-ul trece pe pozitia Not Runnable pentru a se vedea mai bine comunicarea celor 2 Thread-uri
			//si se trateaza eventualele exceptii care pot aparea
			try {
				sleep(1000);
			}catch(InterruptedException e){}
		}
	}
}


