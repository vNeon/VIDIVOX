package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageFrame extends JFrame {

	private JPanel contentPane;
	private JFrame thisFrame = this;
	private JLabel errorTitle = new JLabel();
	/**
	 * ERROR 1 :illegal file- file is a directory
	 * ERROR 2 :illegal file- file does not exist
	 * ERROR 3 :no file has been selected.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageFrame frame = new MessageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MessageFrame() {
		setBounds(100, 100, 450, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Title");
		title.setFont(new Font("Dialog", Font.BOLD, 25));
		title.setBounds(12, 12, 250, 60);
		contentPane.add(title);
		
		JLabel message = new JLabel("Message");
		message.setBounds(12, 84, 426, 28);
		contentPane.add(message);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(166, 124, 117, 25);
		contentPane.add(btnNewButton);
	}
	/**
	 * Constructor which allows you to construct simple user prompts 
	 * 
	 * @param frameTitle	title of the frame
	 * @param messageTitle	title of the message
	 * @param messageText	the message to the user
	 */
	public MessageFrame(String frameTitle, String messageTitle, String messageText) {
		setBounds(100, 100, 450, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		thisFrame.setTitle(frameTitle);
		contentPane.setLayout(null);
		
		errorTitle.setText(messageTitle);
		errorTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		errorTitle.setBounds(12, 12, 250, 60);
		contentPane.add(errorTitle);
		
		JLabel message = new JLabel(messageText);
		message.setBounds(12, 84, 426, 28);
		contentPane.add(message);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(166, 124, 117, 25);
		contentPane.add(btnNewButton);
	}
	
	public String getErrorTile(){
		return this.errorTitle.getText();
	}
}
