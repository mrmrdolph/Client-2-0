package imagebuffer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;


public class TimeBufferedImage extends BufferedImage {
	
	/**
	 * Example usage
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TimeBufferedImage t = (TimeBufferedImage) ImageIO.read(new File("Path"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TimeBufferedImage(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
	}
	
	private Date date;
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
