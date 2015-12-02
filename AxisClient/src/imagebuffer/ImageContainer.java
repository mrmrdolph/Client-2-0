package imagebuffer;
import java.awt.Image;

	/**
	 * To pass the image and id to another function
	 * @author David
	 *
	 */
	public class ImageContainer {
		public Image img;
		public int id;
		
		public ImageContainer(Image img, int id) {
			this.img = img;
			this.id = id;
		}
	}
