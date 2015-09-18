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

import javax.swing.JCheckBox;

public class AddMp3FileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField mp3FileText;
	
	private JFrame thisFrame = this;
	private JFileChooser chooser = null; 
	private EmbeddedMediaPlayer video = null;
	private JLabel statuslbl;
	private JTextField newFileName;
	private JTextField saveToText;
	private JCheckBox playVideoCheck;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mp3FileText = new JTextField();
		mp3FileText.setBounds(114, 81, 215, 25);
		contentPane.add(mp3FileText);
		mp3FileText.setColumns(10);
		
		JLabel Title = new JLabel("Add mp3 File");
		Title.setFont(new Font("Dialog", Font.BOLD, 25));
		Title.setBounds(12, 12, 328, 33);
		contentPane.add(Title);
		
		JLabel mp3FileLabel = new JLabel("mp3 file:");
		mp3FileLabel.setBounds(12, 81, 111, 25);
		contentPane.add(mp3FileLabel);
		
		JButton browseMp3File = new JButton("Browse");
		browseMp3File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser("mp3");
			}
		});
		browseMp3File.setBounds(341, 81, 87, 25);
		contentPane.add(browseMp3File);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3File = new File(mp3FileText.getText());
				File newFile = new File(saveToText.getText()+System.getProperty("file.separator")+newFileName.getText());
				File directory = new File(saveToText.getText());
				if(!mp3File.exists() || mp3File.isDirectory() || newFile.exists() || !directory.exists()){
					MessageFrame mf;
					if(mp3File.isDirectory()){
						mf= new MessageFrame("Error", "Error:", "File is a directory");
					}else if (!mp3File.exists()){
						mf= new MessageFrame("Error", "Error:", "mp3 file does not exist");
					} else if(!directory.exists()){
						mf= new MessageFrame("Error", "Error:", "Directory does not exist");
					}else{
						mf= new MessageFrame("Error", "Error:", newFile.getName() + " already exists");
					}
					mf.setVisible(true);
					return;
				}else{
					AddMp3File amf = new AddMp3File(mp3FileText.getText(), "big_buck_bunny_1_minute.avi" ,newFileName.getText() ,video, statuslbl, playVideoCheck.isSelected());
					amf.execute();
				}
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(70, 231, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		cancel_btn.setBounds(275, 231, 117, 25);
		contentPane.add(cancel_btn);
		
		newFileName = new JTextField();
		newFileName.setBounds(115, 118, 215, 25);
		contentPane.add(newFileName);
		newFileName.setColumns(10);
		
		JLabel newFileLabel = new JLabel("Name of file:");
		newFileLabel.setBounds(12, 123, 111, 15);
		contentPane.add(newFileLabel);
		
		saveToText = new JTextField();
		saveToText.setBounds(114, 155, 215, 25);
		contentPane.add(saveToText);
		saveToText.setColumns(10);
		
		JLabel saveToLabel = new JLabel("Save to:");
		saveToLabel.setBounds(12, 160, 70, 15);
		contentPane.add(saveToLabel);
		
		JButton browseDirectory = new JButton("Browse");
		browseDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directoryChooser();
			}
		});
		browseDirectory.setBounds(341, 155, 87, 25);
		contentPane.add(browseDirectory);
		
		playVideoCheck = new JCheckBox("Play video when finished");
		playVideoCheck.setBounds(114, 188, 215, 23);
		contentPane.add(playVideoCheck);
	}
	
	public void addVideo(EmbeddedMediaPlayer video){
		this.video = video;
	}
	public void addStatuslbl(JLabel statuslbl){
		this.statuslbl=statuslbl;
	}
	public void fileChooser(String location){
		if(chooser ==null){
			chooser = new JFileChooser();
			//FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			//chooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (location.equals("mp3")){
					mp3FileText.setText(chooser.getSelectedFile().toString());
				} else if(location.equals("newFile")){
					newFileName.setText(chooser.getSelectedFile().toString());
				}
			}
			chooser = null;
		}
	}
	public void directoryChooser(){
		if (chooser == null){
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Find Directory");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				saveToText.setText(chooser.getSelectedFile().toString());
			}
			chooser = null;
		}	
	}
}
