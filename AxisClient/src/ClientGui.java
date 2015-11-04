import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import org.omg.CORBA.FREE_MEM;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

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

	/**d
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

	/**d
	 * Create the frame.
	 */
	public ClientGui() {
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
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(135, 206, 250));
		panel_1.add(panel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 250));
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setBackground(new Color(135, 206, 250));
		panel_3.setLayout(null);
		
		JLabel lblIp = new JLabel("I.P");
		lblIp.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblIp.setBounds(10, 8, 23, 14);
		panel_3.add(lblIp);
		lblIp.setForeground(Color.WHITE);
		
		ipTextField = new JTextFieldLimit(14);
		ipTextField.setBounds(30, 5, 86, 20);
		panel_3.add(ipTextField);
		ipTextField.setColumns(10);
		
		portTextField = new JTextFieldLimit(4);
		portTextField.setBounds(136, 5, 38, 20);
		panel_3.add(portTextField);
		portTextField.setColumns(4);
		
		JLabel lblPort = new JLabel("P");
		lblPort.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblPort.setBounds(126, 8, 13, 14);
		panel_3.add(lblPort);
		lblPort.setForeground(Color.WHITE);
		
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
		
		JPanel errorMsgPanel = new JPanel();
		errorMsgPanel.setBackground(new Color(135, 206, 250));
		panel_2.add(errorMsgPanel);
		errorMsgPanel.setLayout(null);
		
		searchingJLabel = new JLabel("");
		searchingJLabel.setBounds(92, 1, 0, 0);
		searchingJLabel.setForeground(Color.BLACK);
		searchingJLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		errorMsgPanel.add(searchingJLabel);
		
		errorMessageJLabel = new ErrorJLabel(""); 
		errorMessageJLabel.setBounds(97, 1, 0, 0);
		errorMessageJLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		errorMessageJLabel.setForeground(Color.RED);
		errorMsgPanel.add(errorMessageJLabel);
		
		JPanel delayPanel = new JPanel();
		delayPanel.setBackground(new Color(135, 206, 250));
		panel_2.add(delayPanel);
		delayPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(22, 0, 50, 50);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ClientGui.class.getResource("/img/time.png")));
		delayPanel.add(lblNewLabel);
		
		delayTextField = new JTextFieldLimit(2);
		delayTextField.setBounds(137, 18, 22, 20);
		delayTextField.setHorizontalAlignment(SwingConstants.CENTER);
		delayPanel.add(delayTextField);
		delayTextField.setColumns(2);
		
		JLabel lblNewLabel_2 = new JLabel("Delay ms");
		lblNewLabel_2.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(77, 22, 50, 14);
		delayPanel.add(lblNewLabel_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setBackground(new Color(135, 206, 250));
		panel_2.add(radioButtonPanel);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("");
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
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Connect");
		btnNewButton_1.setFont(new Font("Nexa Light", Font.PLAIN, 11));
		btnNewButton_1.setBounds(27, 11, 120, 49);
	
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setContentAreaFilled(false); 
	    btnNewButton_1.setBorder(null);
	
		btnNewButton_1.setIcon(new ImageIcon(ClientGui.class.getResource("/img/connec.png")));
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
				    		try {
				    			out = new DataOutputStream(serverSocket.getOutputStream());
				    			out.write(resolutionID.getBytes());  //set by radio buttons!
				    			System.out.println("test: "+getDelay(delayTextField.getText().toString()));
				    			out.write(getDelay(delayTextField.getText().toString()).getBytes()); // 6 BYTES/chars!! range should be 0 - 30000 (30000 equals about 30 seconds)
				    			out.flush();
				    		} catch (IOException e1) {
				    			e1.printStackTrace();
				    		}
				        	ServerHolder server = new ServerHolder(serverSocket, ipTextField.getText(),Integer.parseInt(portTextField.getText()));
				        	panel.add(server);
				        }
				        panel.validate();
				        searching = false;
				        searchingJLabel.setText("");
				    }
				}).start();
				
			}
		});
	
		
	
		
	
		
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
}