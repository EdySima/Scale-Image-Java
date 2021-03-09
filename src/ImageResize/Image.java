package ImageResize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends ImageAbstract{

	Image(){
		super();
	}
	
	public void read(int[][] buffer_image, int start, int finish, int col){ //asignarea unui sfert de imagine atributului image prin bitii de culoare a imaginii 
		
		//buffer_image -> reprezinta codul culorilor pixelilor unui sfert de imagine
		//start -> reprezinta linia de start a sfertului
		//finish -> reprezinta linia de final a sfertului
		//col -> reprezinta numarul de coloane al sfertului/imaginii
		
		
		//pentru fiecare pixel dintr-un anumit sfert din imagine se seteaza pixelii variabilei image, care contine imaginea prelucrata
		for (int i = start; i < finish; i++){
			for (int j = 0; j < col; j++){
				image.setRGB(j, i, buffer_image[i - start][j]);
				//setRGB are ca parametrii pozitia pixelului care se modifica si codul de culoare al acelui pixel(aflat in buffer_image)
				//codul de culoare pentru un anumit pixel din imagine se afla la pozitia buffer_image[i - start][j]
				//pentru ca buffer_image are ca dimensiune (numar_total_linii_imagine/4)*numar_total_coloane_imagine
			}
		}
	}
	
	public void write(String dest_path) throws IOException{
		if(this.image != null){//se verifica daca imaginea nu este null pentru a se putea scrie in fisierul de iesire
			try{ //se trateaza o eventuala exceptie la eroarea scrierii in fisier a imaginii
				File output_file = new File(dest_path); //se creaza fisierul la path-ul dat de variabila dest_path
				ImageIO.write(image, "bmp", output_file); //se scrie imaginea in fisier
				System.out.println("\nS-a scirs fisierul cu succes!!!"); //mesaj pentru finalizare cu succes a scrierii imaginii in fisier
			}
			catch(IOException e){
				e.printStackTrace();
			} 
		}
		else{
			System.out.println("\nNu exista imagine!");
		}
	}
	
	//Setterii si Getterii pentru atributele clasei
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
