package ImageResize;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) throws IOException{

		//se face declararea si intializarea celor 2 clase, BufferedImage si Image
		Image image = new Image();
		BufferedImage img = null;
		
		if(args.length == 4){	//se testeaza daca programul primeste sau nu 4 argumente pentru a putea rula in conditii normale programul
			
			String source_path = args[0];					//calea sursa a imaginii originale
			String dest_path = args[1];						//calea destinatie a imaginii prelucrate
			int mod = Integer.valueOf(args[2]);				//modul de functionare (0 perntru ZoomOut si 1 pentru ZoomIn)
			int factor_marime = Integer.valueOf(args[3]);	//factorul de scalare (trebuie sa fie >1)
			
			//se citeste imaginea de la sursa si se trateaza eventuala exceptia de negasire a fisierului
			try{
				File input_file = new File(source_path);
				img = ImageIO.read(input_file);
			}catch(IIOException e){
				System.out.println("Fisierul nu exista!");
			}

					
			Buffer b = new Buffer(img.getHeight(), img.getWidth());	//se declara si initializeaza bufferul
			Producer p1 = new Producer(b, img);						//se declara si initializeaza Thread-ul Producer
			Consumer c1 = new Consumer(b, img, image);				//se declara si initializeaza Thread-ul Consumer
			
			//se pornesc cele 2 Thread-uri
			p1.start();
			c1.start();
			
			//se face sincronizarea pentru a se sfarsi cele 2 thread-uri in acelasi timp pentru a putea face prelucrarea imaginii
			//dupa ce se termina de executat cele 2 thread-uri
			try {
				p1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				c1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//in cazul in care factorul de scalare si modurile sunt bine inserate, se face prelucrarea
			if(factor_marime >= 1){
				if (mod == 1){
					PixelReplication.zoomIn(image, factor_marime);			//prelucrarea de zoom in a imaginii
					image.write(dest_path);									//scrierea imagnii prelucrate in fisierul destinatie
				}
				else if(mod == 0){
					PixelReplication.zoomOut(image, factor_marime); 		//prelucrarea de zoom out a imaginii
					image.write(dest_path);									//scrierea imagnii prelucrate in fisierul destinatie
				}
				else
					System.out.println("Modul trebuie sa fie 0 sau 1"); 	//Afisarea unui mesaj de eroare pentru selectare incorecta a modului
			}
			else
				System.out.println("Factorul de marime nu este >= ca 1!!"); //Afisarea unui mesaj de eroare pentru selectare incorecta a factorului de marime

			
		}
		else{
			System.out.println("Trebuie sa se introduca 4 argumente"); //Afisarea unui mesaj de eroare pentru un numar diferit de 4 argumente
		}
		
	}
}
