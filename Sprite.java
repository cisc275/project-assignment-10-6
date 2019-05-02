import java.awt.image.BufferedImage;

public class Sprite {
	public double xloc;
	public double yloc;
	public BufferedImage Image;
	public String type;
	
	public int getImgWidth() {
		return Image.getWidth();
	}
	public int getImgHeight() {
		return Image.getHeight();
	}
}
