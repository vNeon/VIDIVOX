package generic_frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import mainview.MediaPlayer;
import add_text.AddText;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class BrowseFileFrame extends JFrame {
	private JPanel contentPane;
	private JTextField videoField;
	private JFrame thisFrame = this;
	private JFileChooser fileChooser = null;
	private JFileChooser directoryChooser = null;
	private JLabel label = new JLabel();
	private JLabel videoFilelbl = new JLabel("Video file:");
	private JButton browseVideoFile = new JButton("Browse");
	private JButton browseDirectory = new JButton("Browse");
	private JButton confirm = new JButton("Confirm");
	private MessageFrame mf = null;
	private JButton cancel = new JButton("Cancel");
	private JCheckBox playVideoCheck;
	private JTextField nameOfFile = new JTextField();
	private JLabel nameOfFilelbl = new JLabel("File Name:");
	private JLabel directorylbl = new JLabel("Directory:");
	private JTextField directory = new JTextField();
	private JLabel fileType = new JLabel(".avi");
	private MediaPlayer mediaPlayer = null;


	/**
	 * Create the frame.
	 */
	public BrowseFileFrame(String title, String label) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		this.label.setText(label);
		this.label.setFont(new Font("Dialog", Font.BOLD, 25));
		this.label.setBounds(12, 12, 328, 33);
		contentPane.add(this.label);

		// video file field
		videoField = new JTextField();
		videoField.setBounds(114, 66, 215, 33);
		contentPane.add(videoField);

		// field for selecting video file
		videoFilelbl.setBounds(12, 76, 87, 15);
		contentPane.add(videoFilelbl);

		// field for user to enter the name of the file
		nameOfFile.setBounds(114, 106, 215, 33);
		contentPane.add(nameOfFile);

		// Name of file label
		nameOfFilelbl.setBounds(12, 116, 87, 15);
		contentPane.add(nameOfFilelbl);

		// avi label
		fileType.setBounds(340, 116, 30, 15);
		contentPane.add(fileType);

		// directory label
		directorylbl.setBounds(12, 156, 87, 15);
		contentPane.add(directorylbl);

		// directory text field
		directory.setBounds(114, 146, 215, 33);
		contentPane.add(directory);
		try {
			directory.setText(new java.io.File(".").getCanonicalPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Check box
		playVideoCheck = new JCheckBox("Play video when finished");
		playVideoCheck.setBounds(114, 190, 215, 23);
		contentPane.add(playVideoCheck);

		// Browse button let user select file
		browseVideoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser == null) {
					fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Media File", "avi", "mp4", "mkv");
					fileChooser.setCurrentDirectory(new java.io.File("."));
					fileChooser
							.setDialogTitle("choothis.mediaPlayer=mediaPlayer;sertitle");
					fileChooser
							.setFileFilter((javax.swing.filechooser.FileFilter) filter);
					fileChooser.setAcceptAllFileFilterUsed(false);

					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						videoField.setText(fileChooser.getSelectedFile()
								.toString());
					}
					fileChooser = null;
				}
			}
		});
		browseVideoFile.setBounds(341, 66, 87, 25);
		contentPane.add(browseVideoFile);

		// Browse button let user select directory
		browseDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (directoryChooser == null) {
					directoryChooser = new JFileChooser();
					directoryChooser.setCurrentDirectory(new java.io.File("."));
					directoryChooser.setDialogTitle("Find Directory");
					directoryChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					directoryChooser.setAcceptAllFileFilterUsed(false);

					if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						directorylbl.setText(directoryChooser.getSelectedFile()
								.toString());
					}
					directoryChooser = null;
				}
			}
		});
		browseDirectory.setBounds(341, 146, 87, 25);
		contentPane.add(browseDirectory);

		// if this is add commentary to video then set check box and text fields
		// to visible
		if (thisFrame.getTitle().equals("Open Video File")) {
			playVideoCheck.setVisible(false);
			nameOfFile.setVisible(false);
			nameOfFilelbl.setVisible(false);
			directorylbl.setVisible(false);
			directory.setVisible(false);
			fileType.setVisible(false);
			browseDirectory.setVisible(false);

		} else {
			playVideoCheck.setVisible(true);
			nameOfFile.setVisible(true);
			nameOfFilelbl.setVisible(true);
			directorylbl.setVisible(true);
			directory.setVisible(true);
			fileType.setVisible(true);
			browseDirectory.setVisible(true);
		}
		// Confirm users choice
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File videoFile = new File(videoField.getText());
				if (thisFrame.getTitle().equals("Open Video File")) {
					if (!videoFile.exists() || videoFile.isDirectory()) {
						if (videoField.getText().equals("")) {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 1",
									"Please fill in blank fields");
						} else if (videoFile.isDirectory()) {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 3a",
									videoFile.getName() + " is a directory");
						} else {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 2a",
									videoFile.getName() + " does not exist");
						}
						mf.setVisible(true);
						return;
					} else {
						mediaPlayer.setVideoTitle(videoField.getText());
						mediaPlayer.playVideo();
					}
				} else {
					File newFile = new File(directory.getText()
							+ System.getProperty("file.separator")
							+ nameOfFile.getText() + ".avi");
					File checkFileName = new File(directory.getText()
							+ System.getProperty("file.separator")
							+ nameOfFile.getText());
					File selectedDirectory = new File(directory.getText());
					String pattern = "^[a-zA-Z0-9_]*$";
					if (!videoFile.exists() || videoFile.isDirectory()
							|| newFile.exists()
							|| !selectedDirectory.isDirectory()
							|| !checkFileName.getName().matches(pattern)) {
						if (videoFile.getName().equals("")
								|| nameOfFile.getText().equals("")
								|| directory.getText().equals("")) {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 1",
									"Please fill in blank fields");
						} else if (videoFile.isDirectory()) {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 3a",
									videoFile.getName() + " is a directory");
						} else if (!videoFile.exists()) {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 2a",
									videoFile.getName() + " does not exist");
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
							mf = new MessageFrame("Error", "ERROR 5b", newFile
									.getName() + " is an invalid name");
						} else {
							if (mf != null) {
								mf.dispose();
							}
							mf = new MessageFrame("Error", "ERROR 6",
									selectedDirectory.toString()
											+ " is not a directory");
						}
						mf.setVisible(true);
						return;
					} else {
						AddText at = new AddText(mediaPlayer.getTextMessage(),
								videoFile.getName(), newFile.getName(),
								mediaPlayer.getVideo(), mediaPlayer
										.getStatuslbl(), playVideoCheck
										.isSelected(), mediaPlayer);
						at.execute();
					}
				}
				thisFrame.dispose();
			}
		});
		confirm.setBounds(80, 230, 117, 25);
		contentPane.add(confirm);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		cancel.setBounds(250, 230, 117, 25);
		contentPane.add(cancel);
	}

	public void addCurrentVideo(String videoFile) {
		videoField.setText(videoFile);
	}

	public void addMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
}
