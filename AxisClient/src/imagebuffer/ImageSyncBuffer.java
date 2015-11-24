package imagebuffer;
import java.awt.Image;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


public class ImageSyncBuffer implements IImageSyncBuffer{
	private Queue<Image> buffer;
	
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
		buffer = new LinkedList<Image>();
	}

	@Override
	public void addImage(Image img) {
		buffer.add(img);
	}

	@Override
	public Image getNextImage() {
		if (getBufferLength() <= 0)
			return null;
//		Iterator<Image> it = buffer.iterator();
//		Image next = it.next();
//		while(it.hasNext()) {
//			Image nextnext = it.next();
//			if (nextnext.getDate().before(next.getDate())) { //TODO: CHECK WHAT getDate().before(..) returns!!
//				next = nextnext;
//			}
//			
//		}
//		buffer.remove(next);
//		return next;
		return buffer.poll();
		
	}
	

	@Override
	public int getBufferLength() {
		return buffer.size();
	}
	
	
	public boolean sameSecond(Date date, Date date2) {
		return false;
	}
	

}
