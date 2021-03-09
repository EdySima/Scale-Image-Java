package ImageResize;

import java.awt.image.BufferedImage;

public class PixelReplication {
	public static void zoomIn(Image image, int nr) { //metoda care implementeaza algoritmul pentru marire

		//calcularea noilor marimi in functie de factorul de scalare
		int height = image.getHeight() * nr;
	    int width = image.getWidth() * nr;
	    
	    //declararea si initializarea imaginea temporare cu noile marimi, pe care se face prelucrarea
	    BufferedImage image_zoom_in = new BufferedImage(width, height, image.getImage().getType());

	    //prelucrarea de zoomIn propriuzisa
	    for (int i = 0; i < height; i++){
	      for (int j = 0; j < width; j++){
	    	  //pentru fiecare pixel din noua imagine, se copieaza pixelul din vecinateatea celui original.
	    	  //in functie de noile coorodonate ale noii imagini, pixelii originail se gasesc pe pozitiile i/nr si respectiv j/nr
	    	  image_zoom_in.setRGB(j, i, image.getImage().getRGB(j/nr, i/nr)); //metodele setRGB() si getRGB() primesc ca parametrii mai intai coloana si apoi linia
	      }
	    }
	    
	    //setarea noilor dimensiuni si noii imagini in clasa Image
	    image.setImage(image_zoom_in);
	    image.setHeight(height);
	    image.setWidth(width);
	}
	
	public static void zoomOut(Image image, int nr){ //metoda care implementeaza algoritmul pentru micsorare
		
		//calcularea noilor marimi in functie de factorul de scalare
		int height = image.getHeight() / nr;
	    int width = image.getWidth() / nr;
	    
	    //declararea si initializarea imaginea temporare cu noile marimi, pe care se face prelucrarea
	    BufferedImage image_zoom_out = new BufferedImage(width , height , image.getImage().getType());

	  //prelucrarea de zoomIn propriuzisa
	    for (int i = 0; i < height; i++){
	      for (int j = 0; j < width; j++){
	    	  
	    	  //pentru fiecare pixel din noua imagine, se neglijeaza pixeli dintr-o anumita vecinatate.
	    	  //in functie de noile coorodonate ale noii imagini, pixelii care se iau se gasesc pe pozitiile i*nr si respectiv j*nr
	    	  image_zoom_out.setRGB(j, i , image.getImage().getRGB(j * nr , i * nr));//metodele setRGB() si getRGB() primesc ca parametrii mai intai coloana si apoi linia
	      }
	    }
	    
	    //setarea noilor dimensiuni si noii imagini in clasa Image
	    image.setImage(image_zoom_out);
	    image.setHeight(height);
	    image.setWidth(width);
	}
}