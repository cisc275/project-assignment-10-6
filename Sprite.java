import java.awt.image.BufferedImage;

public class Sprite {
	public int xloc;
	public Lane lane;
	public BufferedImage Image;
	public String type;
	
	public int getXloc() {
		return this.xloc;
	}
	public Lane getLane() {
		return this.lane;
	}
	public int getImgWidth() {
		return Image.getWidth();
	}
	public int getImgHeight() {
		return Image.getHeight();
	}
}
