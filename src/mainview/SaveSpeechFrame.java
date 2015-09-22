package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SaveSpeechFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private String message;
	
	private JFrame thisFrame = this;
	private JTextField textField;
	private MessageFrame mf = null;
	JFileChooser chooser = null;

	/**
	 * Create the frame.
	 */
	public SaveSpeechFrame() {
		setTitle("Save Speech");
		setBounds(100, 100, 550, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 74, 296, 33);
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
				String folderName = textField.getText();
				String pattern = "^[a-zA-Z0-9_]*$";
				String seperator = System.getProperty("file.separator");
				
				//checks if file name is valid
				if (!fileName.matches(pattern)){
					if (mf != null){
						mf.dispose();
					}
					mf = new MessageFrame("Error", "Error:", "Invalid Name!");
					mf.setVisible(true);
					return;
				}else if (fileName.equals("")){
					if (mf != null){
						mf.dispose();
					}
					mf = new MessageFrame("Error", "Error:", "No Name!");
					mf.setVisible(true);
					return;
				}
				
				fileName = fileName + ".mp3";
				
				if(!folderName.equals("")){
					fileName = folderName + seperator + fileName;
				}
				// used to check if the file exists
				
				File tmpFile = new File(fileName);
				File tmpDir = new File(folderName);
				
				// checks if file already exists
				if (tmpFile.exists() && !tmpFile.isDirectory()){
					if(mf != null){
						mf.dispose();
					}
					MessageFrame mf = new MessageFrame("Error", "Error:", "File Already Exists!");
					mf.setVisible(true);
					return;
				}else if (!tmpDir.exists()){
					if(mf != null){
						mf.dispose();
					}
					MessageFrame mf = new MessageFrame("Error", "Error:", "Folder does not Exists!");
					mf.setVisible(true);
					return;
				}
				
				// Creates the wave file the user requests
				SaveSpeech ss = new SaveSpeech(message, fileName);
				ss.execute();
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(46, 207, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnNewButton_1.setBounds(379, 207, 117, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Save Speech");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(12, 12, 313, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblwav = new JLabel(".mp3");
		lblwav.setBounds(426, 82, 70, 25);
		contentPane.add(lblwav);
		
		JLabel lblNewLabel_2 = new JLabel("Save in folder:");
		lblNewLabel_2.setBounds(12, 150, 117, 25);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(118, 146, 296, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		try {
			textField.setText(new java.io.File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Browse button let user browse for a directory to save the file
		JButton btnNewButton_2 = new JButton("Browse");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chooser == null){
					chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Find Directory");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);

					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textField.setText(chooser.getSelectedFile().toString());
					}
					chooser = null;
				}	
			}
		});
		btnNewButton_2.setBounds(423, 150, 115, 25);
		contentPane.add(btnNewButton_2);
	}
	//set the message
	public void setSpeech(String message){
		this.message = message;
	}
}
