import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import security.KeyStoreStorage;

public class ClientGui extends JFrame {

	private JPanel contentPane;
	private JTextField ipTextField;
	private JTextField portTextField;
	private ErrorJLabel errorMessageJLabel;
	private JLabel searchingJLabel;
	private boolean searching = false;
	private JTextField delayTextField;
	private JRadioButton res1NewRadioButton;
	private JRadioButton res2NewRadioButton_1;
	private JRadioButton res3NewRadioButton_2;
	private JRadioButton res4NewRadioButton_3;
	private String resolutionID = "1";
	private String sync = "0";
	private String crypt= "0";
	private KeyStore keyStore;
	private ArrayList<ServerHolder> serverHolders;
	private boolean syncThreadRunning = false;

	/**df
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGui frame = new ClientGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void removeFromServerHolder(ServerHolder sh) {
		this.serverHolders.remove(sh);
	}
	
	/**d
	 * Create the frame.stuff
	 */
	public ClientGui() {
		try {
			this.keyStore = KeyStoreStorage.createKeyStore(KeyStoreStorage.KEYSTOREFILENAME, KeyStoreStorage.KEYSTOREPW);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.serverHolders = new ArrayList<ServerHolder>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 649);
		this.setResizable(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton();
		
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setIcon(new ImageIcon(ClientGui.class.getResource("/img/axw.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setContentAreaFilled(false); 
	        
		btnNewButton.setFocusPainted(false); 
	    btnNewButton.setOpaque(false);
	
		panel_1.add(btnNewButton);
		
		JPanel errorMsgPanel = new JPanel();
		panel_1.add(errorMsgPanel);
		errorMsgPanel.setBackground(new Color(135, 206, 250));
		errorMsgPanel.setLayout(null);
		
		searchingJLabel = new JLabel("");
		searchingJLabel.setBounds(70, 11, 42, 13);
		searchingJLabel.setForeground(Color.BLACK);
		searchingJLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		errorMsgPanel.add(searchingJLabel);
		
		errorMessageJLabel = new ErrorJLabel("");
		errorMessageJLabel.setBounds(10, 11, 164, 39);
		errorMessageJLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		errorMessageJLabel.setForeground(Color.RED);
		errorMsgPanel.add(errorMessageJLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 250));
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setBackground(new Color(135, 206, 250));
		panel_3.setLayout(null);
		
		JLabel lblIp = new JLabel("IP  a d d r e s s");
		lblIp.setBounds(22, 2, 93, 21);
		panel_3.add(lblIp);
		lblIp.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblIp.setForeground(Color.WHITE);
		
		JLabel lblPort = new JLabel("P o r t");
		lblPort.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblPort.setBounds(133, 1, 38, 23);
		panel_3.add(lblPort);
		lblPort.setForeground(Color.WHITE);
		
		ipTextField = new JTextFieldLimit(14);
		ipTextField.setBounds(22, 21, 93, 20);
		panel_3.add(ipTextField);
		ipTextField.setColumns(10);
		
		portTextField = new JTextFieldLimit(4);
		portTextField.setBounds(131, 21, 38, 20);
		panel_3.add(portTextField);
		portTextField.setColumns(4);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setBackground(new Color(135, 206, 250));
		panel_5.setLayout(null);
		
		delayTextField = new JTextFieldLimit(4);
		delayTextField.setBounds(131, 16, 37, 20);
		panel_5.add(delayTextField);
		delayTextField.setHorizontalAlignment(SwingConstants.CENTER);
		delayTextField.setColumns(2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(16, 0, 32, 53);
		panel_5.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ClientGui.class.getResource("/img/clock.png")));
		
		JLabel lblNewLabel_2 = new JLabel("D e l a y   m s");
		lblNewLabel_2.setBounds(53, 1, 70, 53);
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(Color.WHITE);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_6);
		panel_2.setVisible(false);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
			}
		});
		
		
		
        ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resolutionID = "1";
				if (res1NewRadioButton.isSelected()) {
					System.out.println("1");
					resolutionID = "1";
				} else if (res2NewRadioButton_1.isSelected()) {
					System.out.println("2");
					resolutionID = "2";
				} else if (res3NewRadioButton_2.isSelected()) {
					System.out.println("3");
					resolutionID = "3";
				} else if (res4NewRadioButton_3.isSelected()) {
					System.out.println("4");
					resolutionID = "4";
				}

			}
		};
		
		JPanel delayPanel = new JPanel();
		delayPanel.setBackground(new Color(135, 206, 250));
		panel_2.add(delayPanel);
		delayPanel.setLayout(null);
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("   E n c r y p t");
		chckbxNewCheckBox.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(18, -2, 152, 53);
		delayPanel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(new Color(135, 206, 250));
		chckbxNewCheckBox.setIcon(new ImageIcon (ClientGui.class.getResource("/img/radiounselect.png")));
		chckbxNewCheckBox.setSelectedIcon(new ImageIcon (ClientGui.class.getResource("/img/cypc.png")));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chckbxNewCheckBox.isSelected()){
					crypt = "1";
					System.out.println(crypt);
				}else{
					crypt="0";
					System.out.println(crypt);

				}
				
			}
		});
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		
		JCheckBox chckbxSync = new JCheckBox("   S y n c h r o n i z e");
		chckbxSync.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		chckbxSync.setBounds(19, 1, 152, 53);
		panel_8.add(chckbxSync);
		chckbxSync.setForeground(Color.WHITE);
		chckbxSync.setBackground(new Color(135, 206, 250));
		chckbxSync.setIcon(new ImageIcon (ClientGui.class.getResource("/img/radiounselect.png")));
		chckbxSync.setSelectedIcon(new ImageIcon (ClientGui.class.getResource("/img/synkc.png")));
		chckbxSync.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chckbxSync.isSelected()){
					sync = "1";
					System.out.println(sync);
				}else{
					sync="0";
					System.out.println(sync);

				}
				
			}
		});
		
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setBackground(new Color(135, 206, 250));
		panel_2.add(radioButtonPanel);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioButtonPanel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(ClientGui.class.getResource("/img/screen.png")));
		
		ButtonGroup group = new ButtonGroup();
		
		res2NewRadioButton_1 = new JRadioButton("R2");
		
		res3NewRadioButton_2 = new JRadioButton("R3");
		
		res4NewRadioButton_3 = new JRadioButton("R4");
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		res1NewRadioButton = new JRadioButton("R1");
		
		panel_4.add(res1NewRadioButton);
		panel_4.add(res2NewRadioButton_1);
		panel_4.add(res3NewRadioButton_2);
		panel_4.add(res4NewRadioButton_3);
		
		res1NewRadioButton=setRadioButton(res1NewRadioButton);
		res1NewRadioButton.addActionListener(actionListener);
		res2NewRadioButton_1=setRadioButton(res2NewRadioButton_1);
		res2NewRadioButton_1.addActionListener(actionListener);
		res3NewRadioButton_2=setRadioButton(res3NewRadioButton_2);
		res3NewRadioButton_2.addActionListener(actionListener);
		res4NewRadioButton_3=setRadioButton(res4NewRadioButton_3);
		res4NewRadioButton_3.addActionListener(actionListener);
		group.add(res1NewRadioButton);
		group.add(res2NewRadioButton_1);
		group.add(res3NewRadioButton_2);
		group.add(res4NewRadioButton_3);
		res1NewRadioButton.setSelected(true);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_9);
		panel_9.setLayout(null);
		
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("  C o n n e c t");
		btnNewButton_1.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 0, 164, 49);
	
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setContentAreaFilled(false); 
	    btnNewButton_1.setBorder(null);
	
		btnNewButton_1.setIcon(new ImageIcon(ClientGui.class.getResource("/img/connec.png")));
		btnNewButton_1.setRolloverIcon(new ImageIcon(ClientGui.class.getResource("/img/connec2.png")));
		panel_7.add(btnNewButton_1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		panel.setLayout(new GridLayout(0, 3, 20, 20));
		
		
		  panel.setSize(new Dimension(900, 600));
		
		  final JScrollPane scroll = new JScrollPane(panel);
		  contentPane.add(scroll, BorderLayout.CENTER);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searching) return;
				searchingJLabel.setText("Searching..."); 		//might need to put these into SwingWorker thread
				
				searching = true;
				panel_2.setVisible(false);
				new Thread(new Runnable() {
				    public void run() {
				    	Socket serverSocket = null;
				        boolean socketConnected = true;
				        try {
				        	serverSocket = new Socket(ipTextField.getText(),Integer.parseInt(portTextField.getText()));
				        } catch (NumberFormatException | IOException e1) {
				        	System.out.println("No Server found at " + ipTextField.getText()+":"+Integer.parseInt(portTextField.getText()));
				        	socketConnected = false;
				        	errorMessageJLabel.startMessage("No Server found at " + ipTextField.getText()+":"+Integer.parseInt(portTextField.getText()));
				        	
				        }
				        if (socketConnected) {
				        	DataOutputStream out = null;
				        	PasswordProtection keyPassword = null;
				        	StringBuffer sb = null;
				    		try {
				    			out = new DataOutputStream(serverSocket.getOutputStream());
				    			out.write(resolutionID.getBytes());  //set by radio buttons!
				    			out.write(getDelay(delayTextField.getText().toString()).getBytes()); // 6 BYTES/chars!! range should be 0 - 30000 (30000 equals about 30 seconds)
				    			out.write(crypt.getBytes());
				    			out.write(sync.getBytes());
				    			out.flush();
				    			
				    			/**
				    			 * Get key for encryption
				    			 */
				    			   BufferedInputStream in = new BufferedInputStream(new DataInputStream(serverSocket.getInputStream()));
				    			   sb = new StringBuffer();
				    			   
				    			    for(int i=0; i<8; i++) {
				    					sb.append((char) in.read());
				    			    }
				    			    keyPassword = KeyStoreStorage.storeKey(sb.toString().getBytes(), keyStore, KeyStoreStorage.KEYSTOREFILENAME, sb.toString(), sb.toString());
				    			    System.out.println("Unique Cipher XOR Key received from Server:" + sb.toString());
				    		} catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e1) {
				    			e1.printStackTrace();
				    		}
				        	ServerHolder server = new ServerHolder(serverSocket, ipTextField.getText(),Integer.parseInt(portTextField.getText()), crypt, keyStore, keyPassword, sb.toString(), sync);
				        	serverHolders.add(server);
				        	
				        	panel.add(server);
				        	//start synchronization checks
				        	if (sync.equals("1") && !syncThreadRunning) {
					        	new Thread(new Runnable() {
									
									@Override
									public void run() {
										int filledBuffers = 0;
										syncThreadRunning = true;
										System.out.println("RUN THREAD");
										while(true) {
											if (serverHolders.size() == 0) {
												break;
											}
//											try {
//												Thread.sleep(100);
//											} catch (InterruptedException e) {
//												e.printStackTrace();
//											}
											Iterator<ServerHolder> it = serverHolders.iterator();
											ServerHolder sh = null;
											
											while(it.hasNext()) {
												try {
													sh = it.next();
													//Making sure buffer is filled with at least one
													//and that order is correct
//													if (sh.getImageBuffer().getBufferLength() > 0 && (sh.getImageBuffer().getLastDisplayedId() + 1) == sh.getImageBuffer().getSmallestId()) {
													if (sh.getImageBuffer().getBufferLength() > 0) {
														filledBuffers += 1;
													}
												} catch (ConcurrentModificationException e) {
//													e.printStackTrace();
													System.out.println("ConcurrentModificationException");
													continue;
												}
											}
											if (filledBuffers == serverHolders.size() && filledBuffers > 0) {
												System.out.println("All buffers filled with at least 1 image: showing now");
												it = serverHolders.iterator();
												ServerHolder sh2 = null;
												
												while(it.hasNext()) {
													sh2 = it.next();
													sh2.setImage(sh2.getImageBuffer().getNextImage());
//												    SwingUtilities.invokeLater(new ImageSetterRunnable(sh2));
												}
											}
											filledBuffers = 0;
										}
										syncThreadRunning = false;
									}
								}).start();
				        	}
				        }
				        panel.validate();
				        searching = false;
				        searchingJLabel.setText("");
				    }
				}).start();
				
			}
		});
	
		
	}

	
	public class ImageSetterRunnable implements Runnable {
		
		private ServerHolder sh;
		
		public ImageSetterRunnable(ServerHolder sh) {
			this.sh = sh;
		}
		
		@Override
		public void run() {
			sh.setImage(sh.getImageBuffer().getNextImage());
		}
		
	}
	
	private static String getDelay(String input) {
		String result = "";
		for (int i=0; i<6-input.length();i+=1) {
			result += "0";
		}
		result += input;
		return result;
	}
	
	private JRadioButton setRadioButton (JRadioButton button){
		ImageIcon FbuttonIcon = new ImageIcon (ClientGui.class.getResource("/img/radiounselect.png"));
		ImageIcon TbuttonIcon = new ImageIcon (ClientGui.class.getResource("/img/radioselec.png"));
		button.setIcon(FbuttonIcon);
		button.setSelectedIcon(TbuttonIcon);
		button.setBackground(new Color(135, 206, 250));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		
		return button;
	}
	private JCheckBox setCheck (JCheckBox checkButton){
		ImageIcon FbuttonIcon = new ImageIcon (ClientGui.class.getResource("/img/radiounselect.png"));
		ImageIcon TbuttonIcon = new ImageIcon (ClientGui.class.getResource("/img/radioselec.png"));
		checkButton.setIcon(FbuttonIcon);
		checkButton.setSelectedIcon(TbuttonIcon);
		return checkButton;
	}
}