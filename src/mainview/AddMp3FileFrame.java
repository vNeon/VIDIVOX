package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;

public class AddMp3FileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	private JFrame thisFrame = this;
	private JFileChooser chooser = null; 
	private EmbeddedMediaPlayer video = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMp3FileFrame frame = new AddMp3FileFrame();
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
	public AddMp3FileFrame() {
		setTitle("Add an mp3 file");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(95, 81, 234, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add mp3 File");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setBounds(12, 12, 328, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("File name:");
		lblNewLabel_1.setBounds(12, 81, 111, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chooser ==null){
					chooser = new JFileChooser();
					//FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("choosertitle");
					//chooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
					chooser.setAcceptAllFileFilterUsed(false);

					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textField.setText(chooser.getSelectedFile().toString());
					}
					chooser = null;
				}
			}
		});
		btnBrowse.setBounds(341, 81, 87, 25);
		contentPane.add(btnBrowse);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3File = new File(textField.getText());
				if(!mp3File.exists() && mp3File.isDirectory()){
					MessageFrame mf;
					if(mp3File.isDirectory()){
						mf= new MessageFrame("Error", "Error:", "File is a directory");
					}else{
						mf= new MessageFrame("Error", "Error:", "File does not exist");
					}
					mf.setVisible(true);
					return;
				}else{
					
					AddMp3File amf= new AddMp3File(textField.getText(), video);
					amf.execute();
				}
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(46, 142, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		btnNewButton_1.setBounds(274, 142, 117, 25);
		contentPane.add(btnNewButton_1);
	}
	
	public void addVideo(EmbeddedMediaPlayer video){
		this.video = video;
	}

}
