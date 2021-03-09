package ImageResize;

public class Buffer {
	private int[][] buffer_image; //codurile de culoare unui sfert de imagine
	private boolean available;		//atribut pentru sincronizarea thread-urilor
	
	Buffer(int height, int width){					//Constructor cu dimensiunile unui sfert de imagine
		this.buffer_image = new int[height][width];	//initializare atribut buffer_image
		available = false;							//initializare atribut available
	}
	
	public synchronized int[][] get(){ //metoda apelata de thread-ul Consumer
		//Daca thread-ul Consumer a citit informatia din buffer_image, trece in starea de wait
		while (!available){						
			try{ 
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		available = false; 						//Daca thread-ul Consumer a citit informatia din buffer_image, available devine false
		notifyAll();							//Se notifica celelalte Thread-uri ca s-a citit informatia din buffer_image de catre Consumer
		return buffer_image;					//Consumer-ul citeste informatia din buffer
	}
	
	public synchronized void put(int[][] buffer_image){//metoda apelata de thread-ul Producer
		//Daca thread-ul Producer a scris informatia in buffer_image, trece in starea de wait
		while (available){						
			try{ 
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		this.buffer_image = buffer_image;		//Producerul scrie informatia in buffer
		available = true;						//Daca thread-ul Producer a scris informatia in buffer_image, available devine true
		notifyAll();							//Se notifica celelalte Thread-uri ca s-a scris informatia in buffer_image de catreProducer
	}
}


