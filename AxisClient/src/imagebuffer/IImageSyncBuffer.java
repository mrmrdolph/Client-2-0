package imagebuffer;

import java.awt.Image;


public interface IImageSyncBuffer {
	public void addImage(Image img);
	public Image getNextImage();
	public int getBufferLength();
	
}
