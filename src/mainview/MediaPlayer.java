package mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class MediaPlayer extends JFrame implements ActionListener,
		ChangeListener {
	
	//Icons were taken from Icongal.com
	private final ImageIcon playIcon = new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/play.png"));
	private final ImageIcon forwardIcon = new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/forward.png"));
	private final ImageIcon backwardIcon = new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/backward.png"));
	private final ImageIcon volumeIcon = new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/volume.png"));
	private final ImageIcon stopIcon = new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/stop.png"));
	private final ImageIcon saveIcon= new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/save2.png"));
	private final ImageIcon speakIcon= new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/speak.png"));
	private final ImageIcon fileIcon= new ImageIcon(
			MediaPlayer.class.getResource("/javagui/resources/openfile.png"));
	
	private JTextField text = new JTextField();
	private final JLabel volumelbl = new JLabel();
	private final JPanel contentPane = new JPanel();;
	private final JPanel screen = new JPanel();
	private final JPanel controls = new JPanel();
	private final JPanel speech = new JPanel();

	private final JButton play = new JButton("");
	private final JButton forward = new JButton("");
	private final JButton backward = new JButton("");
	private final JButton volume = new JButton("");
	private final JButton speak = new JButton("");
	private final JButton cancel = new JButton("Cancel");
	private final JButton save = new JButton("");
	private final JButton openFile= new JButton("");
	private final JButton addCommentary= new JButton("Add");
	private final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

	private Timer timer = new Timer(500, this);

	private SaveSpeechFrame ssf=null;
	private BackgroundVoice bg = null;
	private SkipBackground sg = null;
	private AddMp3FileFrame amff = null;

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	private final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
					"/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib");
			Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MediaPlayer();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MediaPlayer() {

		// Setting contentPane;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		//contentPane.add(mediaPlayerComponent);

		//video screen
		screen.setBackground(SystemColor.menu);
		screen.setBounds(0, 0, 800, 480);
		contentPane.add(screen);
		screen.setLayout(new BorderLayout(0, 0));
		screen.setVisible(true);
		screen.add(mediaPlayerComponent);

		// Control panel with control buttons
		controls.setBackground(SystemColor.inactiveCaptionBorder);
		controls.setBounds(0, 481, 800, 70);
		contentPane.add(controls);
		controls.setLayout(null);

		// Play button
		play.setBounds(320, 5, 100, 55);
		play.setIcon(playIcon);
		controls.add(play);
		play.addActionListener(this);

		// Forward button
		forward.setIcon(forwardIcon);
		forward.setBounds(430, 5, 100, 55);
		controls.add(forward);
		forward.addActionListener(this);

		// Backward button
		backward.setIcon(backwardIcon);
		backward.setBounds(210, 5, 100, 55);
		controls.add(backward);
		backward.addActionListener(this);
		
		// volume label
		volumelbl.setBounds(540, 5, 48, 60);
		volumelbl.setIcon(volumeIcon); controls.add(volume);
		volumelbl.setVisible(true);
		controls.add(volumelbl);

		// volume slider
		slider.setBounds(585, 10, 200, 65);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);
		controls.add(slider);
		
		// Speech panel
		speech.setBackground(SystemColor.inactiveCaptionBorder);
		speech.setBounds(0, 551, 800, 200);
		contentPane.add(speech);
		speech.setLayout(null);

		// input field
		text = new JTextField();
		text.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		text.setBounds(165, 15, 455, 30);
		speech.add(text);
		text.setColumns(10);

		// Speak button
		speak.setBounds(275, 50, 65, 40);
		speak.setBackground(new Color(240, 240, 240));
		speak.setIcon(speakIcon);
		speech.add(speak);
		speak.addActionListener(this);
		

		// Cancel button
		cancel.setBounds(530, 50, 65, 40);
		cancel.setForeground(SystemColor.textHighlight);
		cancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		speech.add(cancel);
		cancel.addActionListener(this);

		// save text button// TODO Auto-generated method stub
		save.setBounds(360, 50, 65, 40);
		save.setIcon(saveIcon);
		speech.add(save);
		save.addActionListener(this);
		
		// Open file button
		openFile.setBounds(445, 50, 65, 40);
		openFile.setIcon(fileIcon);
		speech.add(openFile);
		openFile.addActionListener(this);
		
		// Add comment button
		addCommentary.setBounds(190, 50, 65, 40);
		addCommentary.setFont(new Font("Tahoma", Font.BOLD, 10));
		speech.add(addCommentary);
		addCommentary.addActionListener(this);
		
		// set Frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(MediaPlayer.class.getResource("/javagui/resources/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setContentPane(contentPane);
		setVisible(true);

		// initiate timer
		timer.start();

		// set up the video player
		video.playMedia("big_buck_bunny_1_minute.avi");
		video.start();
		video.pause();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (video.getTime() == video.getLength()) {
			play.setIcon(playIcon);
		}
		if (e.getSource() == play) {
			if (sg != null) {
				sg.cancel(true);
				sg = null;
			}
			if (video.isPlaying() == false) {
				play.setIcon(stopIcon);
				video.play();
			} else {
				play.setIcon(playIcon);
				video.pause();
			}
		} else if (e.getSource() == forward) {
			if (sg != null) {
				sg.cancel(true);
				sg = null;
			}
			sg = new SkipBackground(true, video);
			sg.execute();

		} else if (e.getSource() == backward) {
			if (sg != null) {
				sg.cancel(true);
				sg = null;
			}
			sg = new SkipBackground(false, video);
			sg.execute();

		} else if (e.getSource() == speak) {
			if (bg == null || bg.isDone() == true) {
				bg = new BackgroundVoice("echo " + text.getText());
				bg.execute();
			}
		} else if (e.getSource() == cancel) {
			if (bg != null) {
				bg.cancel(true);
			}
		} else if (e.getSource() == save) {
			if(ssf ==null){
				ssf=null;
				ssf = new SaveSpeechFrame();
				ssf.setVisible(true);
				ssf.setSpeech(text.getText());	
			}else{
				ssf.setVisible(true);
				ssf.setSpeech(text.getText());
			}
		} else if (e.getSource()== openFile){
			if(amff == null){
				amff = new AddMp3FileFrame();
				amff.addVideo(video);
				amff.setVisible(true);
			}else{
				amff.setVisible(true);
			}
		} else if (e.getSource()==addCommentary){
			AddText at= new AddText(text.getText(), video);
			at.execute();
			play.setIcon(stopIcon);
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		video.setVolume(slider.getValue());
	}
}
