import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * One Server connected GUI
 * @author David
 *dd
 */
public class ServerHolder extends JPanel {
	private JButton btnRemove;
	private String serverIP;
	private int port;
	private Socket socket;
	private JLabel currentImageJlabel;
	private JPanel settingsPanel;
	private JLabel connectedIPPortlabel;
//hzdhsdak
	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public ServerHolder(Socket s, String ip, int port) {
		
		this.socket = s;
		this.serverIP = ip;
		this.port = port;
			
	
		
		this.setMinimumSize(new Dimension(300, 200));
		//rtprwrwwpoå
		//pahurdianuhdoakjsdhaklsjfhnoajhflnaskjdhfnlaskjdfhnskjdfnf

		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		currentImageJlabel = new JLabel("");
		currentImageJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentImageJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentImageJlabel.setIcon(new ImageIcon(ServerHolder.class.getResource("/img/dummy.png")));
		add(currentImageJlabel);
		
		settingsPanel = new JPanel();
		settingsPanel.setBackground(Color.BLACK);
		add(settingsPanel);
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		
		//yo momma
		connectedIPPortlabel = new JLabel("IP " + ip + " --- Port " + port);
		connectedIPPortlabel.setForeground(Color.WHITE);
		connectedIPPortlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsPanel.add(connectedIPPortlabel);
		
				btnRemove = new JButton("Remove camera");
				btnRemove.setIcon(new ImageIcon(ServerHolder.class.getResource("/img/closebutt.png")));
				btnRemove.setRolloverIcon(new ImageIcon(ServerHolder.class.getResource("/img/closebutthov.png")));
				btnRemove.setForeground(Color.WHITE);
				settingsPanel.add(btnRemove);
				btnRemove.setBorderPainted(false);
				
				btnRemove.setContentAreaFilled(false); 
				
						btnRemove.setFocusPainted(false); 
						btnRemove.setOpaque(false);
						btnRemove.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.DARK_GRAY));
						btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
						btnRemove.addActionListener(new ActionListener() {
							
							
							
							
							public void actionPerformed(ActionEvent e) {
								deleteMe();
								// TODO: CLOSE SOCKETS AND STUFF
								try {
									socket.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						});
		
		
		
		

		/**
		 * Start receiving from serversocket
		 */
		Thread t = new Thread(new ReceiveImageRunner());
		t.start();
	
	}

	private void deleteMe() {
		Container parent = btnRemove.getParent().getParent(); //if not working add another getParent(), cant have enough parents
		Container parentparent = parent.getParent(); 
		parentparent.remove(parent);
		parentparent.validate();
		parentparent.repaint();
	}

	private class ReceiveImageRunner implements Runnable {

		@Override
		public void run() {
			System.out.println("Open to receive from server.");
			/**
			 * TODO: 1. ADD LOOP that receives the images 2. UPDATE Jlabel once
			 * image has been received
			 */

			Image image = null;
			while (true) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				if (socket.isConnected()) {
//					System.out.println("Connected:"+ socket.isConnected());
	//				image = scaleDown(readImg(socket),50,50);
					image = readImg(socket);
					if (image == null) {
//						System.out.println("image null");
						continue;
					} else  {
						
					}
					System.out.println("image received: "+image.toString());
				} else {
					System.out.println("Not connected to socket");
				}
				ImageIcon imgIcon = new ImageIcon(image);
	
				currentImageJlabel.setIcon(imgIcon);
				currentImageJlabel.validate();
			}

 
		}

		public BufferedImage convertBytesToBuffImage(byte[] imgAsBytes) {
			InputStream in = new ByteArrayInputStream(imgAsBytes);
			BufferedImage bImageFromConvert = null;
			try {
				bImageFromConvert = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			return bImageFromConvert;
//			return scaleDown(bImageFromConvert, 0.25, 0.25);
		}
		
		public BufferedImage scaleDown(BufferedImage bImageFromConvert, double widthScale, double heightScale) {
			int w = bImageFromConvert.getWidth();
			int h = bImageFromConvert.getHeight();
			BufferedImage after = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_ARGB);
			AffineTransform at = new AffineTransform();
			at.scale(widthScale, heightScale);
			AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			after = scaleOp.filter(bImageFromConvert, after);
			return after;
		}

		
		public BufferedImage readImg(Socket socket) {
//			System.out.println("START READING");
			try {
				BufferedImage buff = ImageIO.read(socket.getInputStream());
//				System.out.println("END READING");
				return buff;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}