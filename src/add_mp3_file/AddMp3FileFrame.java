package add_mp3_file;

import generic_frames.MessageFrame;

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
import java.io.IOException;

import javax.swing.JCheckBox;

import mainview.MediaPlayer;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddMp3FileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField mp3FileText;
	JComboBox hourBox = new JComboBox();
	JComboBox minuteBox = new JComboBox();
	JComboBox secondBox = new JComboBox();

	private JFrame thisFrame = this;
	private JFileChooser chooser = null;
	private EmbeddedMediaPlayer video = null;
	private JLabel statuslbl;
	private JTextField newFileName;
	private JTextField saveToText;
	private JCheckBox playVideoCheck;
	private JTextField videoFileText;
	private MediaPlayer mediaPlayer = null;
	private MessageFrame mf = null;
	private String currentTime = null;

	/**
	 * Create the frame.
	 */
	public AddMp3FileFrame() {
		setTitle("Add an mp3 file");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// text field where user enters mp3 file
		mp3FileText = new JTextField();
		mp3FileText.setBounds(114, 134, 215, 33);
		contentPane.add(mp3FileText);
		mp3FileText.setColumns(10);

		// title of the frame
		JLabel Title = new JLabel("Add mp3 File");
		Title.setFont(new Font("Dialog", Font.BOLD, 25));
		Title.setBounds(12, 12, 328, 33);
		contentPane.add(Title);

		// label to tell user where to enter the name of the mp3 file
		JLabel mp3FileLabel = new JLabel("mp3 file:");
		mp3FileLabel.setBounds(12, 138, 111, 25);
		contentPane.add(mp3FileLabel);

		// opens JFileChooser to allow user to search for mp3 file
		JButton browseMp3File = new JButton("Browse");
		browseMp3File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser("mp3");
			}
		});
		browseMp3File.setBounds(341, 138, 87, 25);
		contentPane.add(browseMp3File);

		JButton confirm_btn = new JButton("Confirm");
		confirm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File videoFile = new File(videoFileText.getText());
				File mp3File = new File(mp3FileText.getText());
				File newFile = new File(saveToText.getText()
						+ System.getProperty("file.separator")
						+ newFileName.getText() + ".mp4");
				File checkFileName = new File(saveToText.getText()
						+ System.getProperty("file.separator")
						+ newFileName.getText());
				File directory = new File(saveToText.getText());
				String pattern = "^[a-zA-Z0-9_]*$";
				
				//checks that all fields are correct
				if (!videoFile.exists() || videoFile.isDirectory()
						|| !mp3File.exists() || mp3File.isDirectory()
						|| newFile.exists() || !directory.exists()
						|| !checkFileName.getName().matches(pattern)) {

					if (videoFileText.getText().equals("")
							|| mp3FileText.getText().equals("")
							|| newFileName.getText().equals("")
							|| saveToText.getText().equals("")) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 1",
								"Please fill in blank fields");
					} else if (!videoFile.exists()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 2a", videoFile
								.getName() + " does not exist");
					} else if (videoFile.isDirectory()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 3a", videoFile
								.getName() + " is a directory");
					} else if (mp3File.isDirectory()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 3b", mp3File
								.getName() + " is a directory");
					} else if (!mp3File.exists()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 2b", mp3File
								.getName() + " does not exist");
					} else if (!directory.exists()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 6", directory
								.toString() + " does not exist");
					} else if (newFile.exists()) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 5a", newFile
								.getName() + " already exists");
					} else if (!checkFileName.getName().matches(pattern)) {
						if (mf != null) {
							mf.dispose();
						}
						mf = new MessageFrame("Error", "ERROR 5b", newFileName
								.getText() + " is an invalid name");
					}
					mf.setVisible(true);
					return;
				} else {
					String offsetTime = (String) hourBox.getSelectedItem() + ":" + (String) minuteBox.getSelectedItem() + ":" + (String) secondBox.getSelectedItem();
					AddMp3File amf = new AddMp3File(mp3FileText.getText(),
							videoFileText.getText(), offsetTime,newFile.getAbsolutePath(),
							video, statuslbl, playVideoCheck.isSelected(),
							mediaPlayer);
					amf.execute();
				}
				thisFrame.setVisible(false);
				thisFrame.dispose();
			}
		});
		confirm_btn.setBounds(62, 420, 117, 25);
		contentPane.add(confirm_btn);

		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				thisFrame.dispose();
				;
			}
		});
		cancel_btn.setBounds(280, 420, 117, 25);
		contentPane.add(cancel_btn);

		// textField where user enters name of file to be created
		newFileName = new JTextField();
		newFileName.setBounds(114, 267, 215, 33);
		contentPane.add(newFileName);
		newFileName.setColumns(10);

		// label to tell user where to enter the name of new file
		JLabel newFileLabel = new JLabel("Name of file:");
		newFileLabel.setBounds(12, 276, 111, 15);
		contentPane.add(newFileLabel);

		// label to tell user where to save the new file
		saveToText = new JTextField();
		saveToText.setBounds(114, 312, 215, 33);
		contentPane.add(saveToText);
		saveToText.setColumns(10);
		try {
			saveToText.setText(new java.io.File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// label to tell user where to enter the destination of the new file
		JLabel saveToLabel = new JLabel("Save to:");
		saveToLabel.setBounds(12, 321, 70, 15);
		contentPane.add(saveToLabel);

		// opens a JFileChooser to allow the user to find a directory
		JButton browseDirectory = new JButton("Browse");
		browseDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directoryChooser();
			}
		});
		browseDirectory.setBounds(341, 316, 87, 25);
		contentPane.add(browseDirectory);

		// checkbox which allows user to play the video after the processing
		playVideoCheck = new JCheckBox("Play video when finished");
		playVideoCheck.setBounds(114, 361, 215, 23);
		contentPane.add(playVideoCheck);

		// label to tell user where to enter the video file name
		JLabel videoFileLabel = new JLabel("Video File:");
		videoFileLabel.setBounds(12, 101, 87, 15);
		contentPane.add(videoFileLabel);

		// textfield which alllows the user to enter the video file name
		videoFileText = new JTextField();
		videoFileText.setBounds(114, 92, 215, 34);
		contentPane.add(videoFileText);
		videoFileText.setColumns(10);
		
		// opens a JFilechooser to allows user to search for a video file
		JButton browseVid_btn = new JButton("Browse");
		browseVid_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser("video");
			}
		});
		browseVid_btn.setBounds(341, 96, 87, 25);
		contentPane.add(browseVid_btn);
		
		// tells user that the file will have a .avi extension by default
		JLabel lblNewLabel = new JLabel(".mp4");
		lblNewLabel.setBounds(347, 276, 70, 15);
		contentPane.add(lblNewLabel);
		
		hourBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		hourBox.setBounds(114, 179, 50, 24);
		contentPane.add(hourBox);
		
		minuteBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		minuteBox.setBounds(187, 179, 50, 24);
		contentPane.add(minuteBox);
		
		secondBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		secondBox.setBounds(264, 179, 50, 24);
		contentPane.add(secondBox);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(12, 184, 70, 15);
		contentPane.add(lblTime);
		
		JButton btnNewButton = new JButton("Current");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] times = currentTime.split(":");
				hourBox.setSelectedIndex(Integer.parseInt(times[0]));
				minuteBox.setSelectedIndex(Integer.parseInt(times[1]));
				secondBox.setSelectedIndex(Integer.parseInt(times[2]));
			}
		});
		btnNewButton.setBounds(326, 179, 102, 25);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel(":");
		label.setBounds(174, 184, 17, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(249, 184, 17, 15);
		contentPane.add(label_1);
	}

	public void addVideo(EmbeddedMediaPlayer video) {
		this.video = video;
	}

	public void addStatuslbl(JLabel statuslbl) {
		this.statuslbl = statuslbl;
	}

	public void addCurrentVideo(String videoFile) {
		videoFileText.setText(videoFile);
	}

	public void addMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
	
	public void addCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	// Creating file chooser, when the browse button is clicked open a browse and let use choose files 
	public void fileChooser(String location) {
		if (chooser == null) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter;
			if (location.equals("mp3")) {
				filter = new FileNameExtensionFilter("MP3 File", "mp3");
			} else {
				filter = new FileNameExtensionFilter("MP3 File", "mp4", "avi");
			}
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (location.equals("mp3")) {
					mp3FileText.setText(chooser.getSelectedFile().toString());
				} else if (location.equals("video")) {
					videoFileText.setText(chooser.getSelectedFile().toString());
				}
			}
			chooser = null;
		}
	}
	// Create a directory chooser, open a window and let user choose a directory
	public void directoryChooser() {
		if (chooser == null) {
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
