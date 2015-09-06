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

public class MediaPlayer extends JFrame implements ActionListener, ChangeListener{

	private final ImageIcon playIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48.png"));
	private final ImageIcon forwardIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2).png"));
	private final ImageIcon backwardIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2) - Copy.png"));
	private final ImageIcon volumeIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (3).png"));
	private final ImageIcon stopIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (1).png"));
	
	private JTextField text= new JTextField();
	private final JLabel volumelbl= new JLabel();
	private final JPanel contentPane =new JPanel();;
	private final JPanel screen = new JPanel();
	private final JPanel controls = new JPanel();
	private final JPanel speech = new JPanel();
	
	private final JButton play = new JButton("");
	private final JButton forward = new JButton("");
	private final JButton backward = new JButton("");
	private final JButton volume = new JButton("");
	private final JButton speak = new JButton("Speak");
	private final JButton cancel = new JButton("Cancel");
	private final JButton save = new JButton("Save");
	
	private final JSlider slider= new JSlider(JSlider.HORIZONTAL, 0,100,50);
	
	private Timer timer= new Timer(500,this);
	private BackgroundVoice bg = null;
	private SkipBackground sg=null;
	
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent=new EmbeddedMediaPlayerComponent();
	private final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 NativeLibrary.addSearchPath(
	            RuntimeUtil.getLibVlcLibraryName(), "/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib"
	        );
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
	public MediaPlayer(){
		
		//Setting contentPane;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(mediaPlayerComponent);
		
		//
		screen.setBackground(SystemColor.menu);
		screen.setBounds(0, 0, 800, 480);
		contentPane.add(screen);
		screen.setLayout(new BorderLayout(0, 0));
		screen.setVisible(true);
		screen.add(mediaPlayerComponent);
		
		
		//Control panel with control buttons
		controls.setBackground(SystemColor.inactiveCaptionBorder);
		controls.setBounds(0, 481, 800, 70);
		contentPane.add(controls);
		controls.setLayout(null);
		
		//Play button
		play.setBounds(260, 5, 100, 55);
		play.setIcon(playIcon);
		controls.add(play);
		play.addActionListener(this);
		
		//Forward button
		forward.setIcon(forwardIcon);
		forward.setBounds(370, 5, 100, 55);
		controls.add(forward);
		forward.addActionListener(this);
		
		//Backward button
		backward.setIcon(backwardIcon);
		backward.setBounds(150, 5, 100, 55);
		controls.add(backward);
		backward.addActionListener(this);
		/*
		//volume label
		volumelbl.setLocation(480, 70);
		volumelbl.setIcon(volumeIcon);
		controls.add(volume);
		volumelbl.setVisible(true);
		*/
		
		//volume button
		volume.setIcon(volumeIcon);
		volume.setBounds(480, 5, 100, 55);
		controls.add(volume);
		volume.addActionListener(this);
		
		// volume slider
		slider.setBounds(585, 10, 200, 65);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);
		
		controls.add(slider);
		//Speech panel
		speech.setBackground(SystemColor.inactiveCaptionBorder);
		speech.setBounds(0, 551, 800, 100);
		contentPane.add(speech);
		speech.setLayout(null);
		
		// input field
		text = new JTextField();
		text.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		text.setBounds(165, 15, 450, 30);
		speech.add(text);
		text.setColumns(10);
		
		// Speak button
		speak.setBounds(252, 48, 97, 41);
		speech.add(speak);
		speak.addActionListener(this);
		
		// Cancel button
		cancel.setBounds(350, 48, 97, 41);
		speech.add(cancel);
		cancel.addActionListener(this);
		
		//save text button// TODO Auto-generated method stub
		save.setBounds(448, 48, 97, 41);
		speech.add(save);
		save.addActionListener(this);
		
		// set Frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(MediaPlayer.class.getResource("/javagui/resources/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setContentPane(contentPane);
		setVisible(true);
		
		//initiate timer
		timer.start();
		
		//set up the video player
		video.playMedia("big_buck_bunny_1_minute.avi");
		video.start();
		video.pause();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (video.getTime()==video.getLength()){
			play.setIcon(playIcon);
		}
		if (e.getSource()==play){
			if(sg !=null){
				sg.cancel(true);
				sg =null;
			}
			if(video.isPlaying()==false){
				play.setIcon(stopIcon);
				video.play();
			}else{
				play.setIcon(playIcon);
				video.pause();
			}
		}else if(e.getSource()==forward){
			if(sg !=null){
				sg.cancel(true);
				sg =null;
			}
			sg= new SkipBackground(true, video);
			sg.execute();

		}else if(e.getSource()==backward){
			if(sg !=null){
				sg.cancel(true);
				sg =null;
			}
			sg= new SkipBackground(false, video);
			sg.execute();
		}else if(e.getSource()==volume){
			
		}else if(e.getSource()==speak){
			if (bg == null || bg.isDone() == true){
				bg = new BackgroundVoice("echo " + text.getText());
				bg.execute();
			}
		}else if(e.getSource()==cancel){
			if (bg != null){
				bg.cancel(true);
			}
		}else if(e.getSource()==save){
			SaveSpeech ss = new SaveSpeech(text.getText());
			ss.execute();
		}	
	}
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		video.setVolume(slider.getValue());
	}
}
