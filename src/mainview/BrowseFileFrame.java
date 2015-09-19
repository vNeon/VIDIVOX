package mainview;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class BrowseFileFrame extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JFrame thisFrame = this;
	private JFileChooser chooser = null; 
	private JLabel label = new JLabel();
	private JLabel videoFilelbl = new JLabel("Video file:");
	private JButton browseVideoFile = new JButton("Browse");
	private JButton confirm = new JButton("Confirm");
	private MessageFrame mf=null;
	private JButton cancel = new JButton("Cancel");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseFileFrame frame = new BrowseFileFrame("Frame", "Add file", new MediaPlayer());
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
	public BrowseFileFrame(String title, String label,final MediaPlayer mediaPlayer){
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		this.label.setText(label);
		this.label.setFont(new Font("Dialog", Font.BOLD, 25));
		this.label.setBounds(12, 12, 328, 33);
		contentPane.add(this.label);
		
		//video file field
		textField = new JTextField();
		textField.setBounds(114, 66, 215, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		//this field is for selecting video file
		videoFilelbl.setBounds(12, 76, 87, 15);
		contentPane.add(videoFilelbl);
		
		//Browse button let user selecting file
		browseVideoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chooser ==null){
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Media File","avi","mp4");
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("choothis.mediaPlayer=mediaPlayer;sertitle");
					chooser.setFileFilter((javax.swing.filechooser.FileFilter) filter);
					chooser.setAcceptAllFileFilterUsed(false);

					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textField.setText(chooser.getSelectedFile().toString());
					}
					chooser = null;
				}
			}
		});
		browseVideoFile.setBounds(341, 66, 87, 25);
		contentPane.add(browseVideoFile);
		
		//Confirm users choice
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File mp3File = new File(textField.getText());
				if(!mp3File.exists() || mp3File.isDirectory()){
					if(mp3File.isDirectory()){
						if(mf==null || mf.getErrorTile().equals("ERROR 2")){
							mf=null;
							mf= new MessageFrame("Error", "ERROR 1", "File is a directory");
						}
					}else{
						if(mf==null|| mf.getErrorTile().equals("ERROR 1")){
							mf=null;
							mf= new MessageFrame("Error", "ERROR 2", "File does not exist");
						}
					}
					mf.setVisible(true);
					return;
				}else{
					
					mediaPlayer.setVideoTitle(textField.getText());
					try {
						Thread.sleep(500);
						mediaPlayer.playVideo();
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
					//AddMp3File amf= new AddMp3File(textField.getText(), video, statuslbl);
					//amf.execute();
				}
				thisFrame.dispose();
			}
		});
		confirm.setBounds(46, 142, 117, 25);
		contentPane.add(confirm);
		
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.dispose();
			}
		});
		cancel.setBounds(274, 142, 117, 25);
		contentPane.add(cancel);
	}
	
}
