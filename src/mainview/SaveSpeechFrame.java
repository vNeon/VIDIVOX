package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveSpeechFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private String message;
	
	private JFrame thisFrame = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveSpeechFrame frame = new SaveSpeechFrame();
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
	public SaveSpeechFrame() {
		setTitle("Save Speech");
		setBounds(100, 100, 550, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 82, 298, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name of file:");
		lblNewLabel.setBounds(12, 82, 104, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// the files name
				String fileName = textField_1.getText();
				String pattern = "^[a-zA-Z0-9_]*$";
				
				//checks if file name is valid
				if (!fileName.matches(pattern)){
					MessageFrame mf = new MessageFrame("Error", "Error:", "Invalid Name!");
					mf.setVisible(true);
					return;
				}else if (fileName.equals("")){
					MessageFrame mf = new MessageFrame("Error", "Error:", "No Name!");
					mf.setVisible(true);
					return;
				}
				
				// used to create the file
				fileName = fileName + ".wav";
				File tmpFile = new File(fileName);
				
				// checks if file already exists
				if (tmpFile.exists() && !tmpFile.isDirectory()){
					MessageFrame mf = new MessageFrame("Error", "Error:", "File Already Exists!");
					mf.setVisible(true);
					return;
				}
				
				// Creates the wave file the user requests
				SaveSpeech ss = new SaveSpeech(message, fileName);
				ss.execute();
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(50, 159, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnNewButton_1.setBounds(379, 159, 117, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Save Speech");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(12, 12, 313, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblwav = new JLabel(".wav");
		lblwav.setBounds(426, 82, 70, 25);
		contentPane.add(lblwav);
	}
	public void setSpeech(String message){
		this.message = message;
	}
}
