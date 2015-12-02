package imagebuffer;
import java.awt.Image;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class ImageSyncBuffer implements IImageSyncBuffer{
	private Queue<ImageContainer> buffer;
	private int lastDisplayedId = 0;
	
	/**
	 * Example usage
	 * @param args
	 */
	public static void main(String[] args) {
//		ImageSyncBuffer buffer = new ImageSyncBuffer();
//		Image t = new Image(1, 1, 1);
//		t.setDate(new Date());
//		System.out.println("t1" + t.getDate());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		Image t2 = new Image(1, 1, 1);
//		t2.setDate(new Date());
//		System.out.println("t2" + t2.getDate());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		Image t3 = new Image(1, 1, 1);
//		t3.setDate(new Date());
//		System.out.println("t3" + t3.getDate());
//		
//		
//		buffer.addImage(t);
//		buffer.addImage(t2);
//		buffer.addImage(t3);
//		System.out.println("earliest" +buffer.getNextImage().getDate());
//		System.out.println("middle" +buffer.getNextImage().getDate());
//		System.out.println("latest" +buffer.getNextImage().getDate());
//		System.out.println("buffersize: " + buffer.getBufferLength());
	}
	
	public ImageSyncBuffer() {
		buffer = new LinkedList<ImageContainer>();
	}

	@Override
	public void addImage(ImageContainer imgContainer) {
		buffer.add(imgContainer);
	}

	@Override
	public Image getNextImage() {
		if (getBufferLength() <= 0)
			return null;
		//last change
		Iterator<ImageContainer> it = buffer.iterator();
		ImageContainer next = it.next();
		while(it.hasNext()) {
			ImageContainer nextnext = it.next();
			if (nextnext.id < next.id) { 
				next = nextnext;
			}
			
		}
		buffer.remove(next);
		this.lastDisplayedId = next.id;
		return next.img;
//		return buffer.poll().img;
	}
	
	public int getLastDisplayedId() {
		return this.lastDisplayedId;
	}
	
	@Override
	public int getBufferLength() {
		return buffer.size();
	}
	
	public int getSmallestId() {
		if (getBufferLength() <= 0)
			return -1;
		//last change
		Iterator<ImageContainer> it = buffer.iterator();
		ImageContainer next = it.next();
		while(it.hasNext()) {
			ImageContainer nextnext = it.next();
			if (nextnext.id < next.id) { 
				next = nextnext;
			}
		}
		return next.id;
	}
	
	
//	public boolean sameSecond(Date date, Date date2) {
//		return false;
//	}
	

}
