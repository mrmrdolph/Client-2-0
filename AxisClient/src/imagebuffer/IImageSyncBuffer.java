package imagebuffer;

import java.awt.Image;


public interface IImageSyncBuffer {
	public void addImage(ImageContainer img);
	public Image getNextImage();
	public int getBufferLength();
	
}
