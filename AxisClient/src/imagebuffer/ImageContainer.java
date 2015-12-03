package imagebuffer;
import java.awt.image.BufferedImage;

	/**
	 * To pass the image and id to another function
	 * @author David
	 *
	 */
	public class ImageContainer {
		public BufferedImage img;
		public int id;
		
		public ImageContainer(BufferedImage imgg, int idd) {
			this.img = imgg;
			this.id = idd;
		}
		
		
	}
