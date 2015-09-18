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
import java.io.IOException;

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
	private JTextField videoFileText;

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
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mp3FileText = new JTextField();
		mp3FileText.setBounds(114, 99, 215, 33);
		contentPane.add(mp3FileText);
		mp3FileText.setColumns(10);

		JLabel Title = new JLabel("Add mp3 File");
		Title.setFont(new Font("Dialog", Font.BOLD, 25));
		Title.setBounds(12, 12, 328, 33);
		contentPane.add(Title);

		JLabel mp3FileLabel = new JLabel("mp3 file:");
		mp3FileLabel.setBounds(12, 103, 111, 25);
		contentPane.add(mp3FileLabel);

		JButton browseMp3File = new JButton("Browse");
		browseMp3File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser("mp3");
			}
		});
		browseMp3File.setBounds(341, 103, 87, 25);
		contentPane.add(browseMp3File);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3File = new File(mp3FileText.getText());
				File newFile = new File(saveToText.getText()
						+ System.getProperty("file.separator")
						+ newFileName.getText() + ".avi");
				File directory = new File(saveToText.getText());

				if (!mp3File.exists() || mp3File.isDirectory()
						|| newFile.exists() || !directory.exists()) {
					MessageFrame mf;
					if (mp3File.isDirectory()) {
						mf = new MessageFrame("Error", "Error:",
								"File is a directory");
					} else if (!mp3File.exists()) {
						mf = new MessageFrame("Error", "Error:",
								"mp3 file does not exist");
					} else if (!directory.exists()) {
						mf = new MessageFrame("Error", "Error:",
								"Directory does not exist");
					} else {
						mf = new MessageFrame("Error", "Error:", newFile
								.getName() + " already exists");
					}
					mf.setVisible(true);
					return;
				} else {
					AddMp3File amf = new AddMp3File(mp3FileText.getText(),
							videoFileText.getText(), newFile.getAbsolutePath(), video, statuslbl,
							playVideoCheck.isSelected());
					amf.execute();
				}
				thisFrame.dispose();
			}
		});
		btnNewButton.setBounds(57, 266, 117, 25);
		contentPane.add(btnNewButton);

		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		cancel_btn.setBounds(281, 266, 117, 25);
		contentPane.add(cancel_btn);

		newFileName = new JTextField();
		newFileName.setBounds(114, 140, 215, 33);
		contentPane.add(newFileName);
		newFileName.setColumns(10);

		JLabel newFileLabel = new JLabel("Name of file:");
		newFileLabel.setBounds(12, 149, 111, 15);
		contentPane.add(newFileLabel);

		saveToText = new JTextField();
		saveToText.setBounds(114, 181, 215, 33);
		contentPane.add(saveToText);
		saveToText.setColumns(10);
		try {
			saveToText.setText(new java.io.File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel saveToLabel = new JLabel("Save to:");
		saveToLabel.setBounds(12, 190, 70, 15);
		contentPane.add(saveToLabel);

		JButton browseDirectory = new JButton("Browse");
		browseDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directoryChooser();
			}
		});
		browseDirectory.setBounds(341, 185, 87, 25);
		contentPane.add(browseDirectory);

		playVideoCheck = new JCheckBox("Play video when finished");
		playVideoCheck.setBounds(114, 222, 215, 23);
		contentPane.add(playVideoCheck);

		JLabel videoFileLabel = new JLabel("Video File:");
		videoFileLabel.setBounds(12, 76, 87, 15);
		contentPane.add(videoFileLabel);

		videoFileText = new JTextField();
		videoFileText.setBounds(114, 57, 215, 34);
		contentPane.add(videoFileText);
		videoFileText.setColumns(10);

		JButton browseVid_btn = new JButton("Browse");
		browseVid_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser("video");
			}
		});
		browseVid_btn.setBounds(341, 61, 87, 25);
		contentPane.add(browseVid_btn);
		
		JLabel lblNewLabel = new JLabel(".avi");
		lblNewLabel.setBounds(351, 149, 70, 15);
		contentPane.add(lblNewLabel);
	}

	public void addVideo(EmbeddedMediaPlayer video) {
		this.video = video;
	}

	public void addStatuslbl(JLabel statuslbl) {
		this.statuslbl = statuslbl;
	}
	
	public void addCurrentVideo(String videoFile){
		videoFileText.setText(videoFile);
	}

	public void fileChooser(String location) {
		if (chooser == null) {
			chooser = new JFileChooser();
			// FileNameExtensionFilter filter = new
			// FileNameExtensionFilter("MP3 File","mp3");
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			// chooser.setFileFilter((javax.swing.filechooser.FileFilter)
			// filter);
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
