package ImageResize;

import java.awt.image.BufferedImage;

public class Producer extends Thread{
	private Buffer buffer;			//buffer-ul prin care vor comunica cele 2 Thread-uri
	private BufferedImage image;	//imaginea originala
	private int[][] image_part;	//codurile de culoare pentru un sfert de imagine
	
	public Producer (Buffer b, BufferedImage image){ 	//constructorul clasei, cu parametrii necesari
		this.buffer = b; 								//initializarea bufferului
		this.image = image; 							//initializarea imaginii
		this.image_part = new int[image.getHeight()/4 + 1][image.getWidth()]; 	//initializarea matricii codurilor de culoare cu o dimensiune egala cu sfertul de linii ale imaginii
		System.out.println("Create Producer!");									//Se afiseaza ca a fost apelat Constructorul clasei Producer 
	}
	
	public void run(){ 									//metoda de rulare a thread-ului
		System.out.println("Start Producer!");			//Se afiseaza ca Thread-ul Producer a inceput executia
		
		//se seteaza variabilele de dimensiune a imaginii, pentru a nu fi nevoie apelarea de fiecare data a getterelor pentru fiecare pas al unui for
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int k = 0; k < 4; k++){											//k+1 reprezinta sfertul care urmeaza a fi citit si transmis catre buffer
			for (int i = (height/4)*k; i < (height/4)*(k+1); i++){			//formula pentru startul unui sfertului este (height/4)*k iar formula pentru final este (height/4)*(k+1)
				for (int j = 0; j < width; j++){ 							//coloanele se citesc in intregime
					image_part[i - (height/4)*k][j] = image.getRGB(j,i); 	//se ia codul de culoare si se pune in matricea image_part
				}
			}
			buffer.put(image_part);												//se pune sfertul de imagine in buffer
			System.out.println("Producer a pus in buffer sfertul " + (k+1));	// se afiseaza ca producerul a terminat de pasat un anumit sfert in buffer
			
			//Thread-ul trece pe pozitia Not Runnable pentru a se vedea mai bine comunicarea celor 2 Thread-uri
			//si se trateaza eventualele exceptii care pot aparea
			try {
				sleep(1000);
			}catch(InterruptedException e){}
		}
	}
}
