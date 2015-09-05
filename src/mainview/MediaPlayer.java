package mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

import java.awt.Font;
import java.awt.SystemColor;

public class MediaPlayer extends JFrame implements ActionListener {

	private final ImageIcon playIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48.png"));
	private final ImageIcon forwardIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2).png"));
	private final ImageIcon backwardIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (2) - Copy.png"));
	private final ImageIcon volumeIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (3).png"));
	private final ImageIcon stopIcon= new ImageIcon(MediaPlayer.class.getResource("/javagui/resources/48 (1).png"));
	
	private JTextField text= new JTextField();
	
	private final JFrame frame= new JFrame();
	private final JPanel contentPane =new JPanel();;
	private final JPanel screen = new JPanel();
	private final JPanel controls = new JPanel();
	private final JPanel speech = new JPanel();
	
	private final JButton play = new JButton("");
	private final JButton forward = new JButton("");
	private final JButton backward = new JButton("");
	private final JButton volume = new JButton("");
	private final JButton speak = new JButton("Speak");
	private final JButton save = new JButton("Save");
	
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
		
		
		//Control panel
		controls.setBackground(SystemColor.inactiveCaptionBorder);
		controls.setBounds(0, 481, 800, 70);
		contentPane.add(controls);
		controls.setLayout(null);
		
		//Play button
		play.setBounds(350, 5, 100, 55);
		play.setIcon(playIcon);
		controls.add(play);
		play.addActionListener(this);
		
		//Forward button
		forward.setIcon(forwardIcon);
		forward.setBounds(460, 5, 100, 55);
		controls.add(forward);
		forward.addActionListener(this);
		
		//Backward button
		backward.setIcon(backwardIcon);
		backward.setBounds(240, 5, 100, 55);
		controls.add(backward);
		backward.addActionListener(this);
		
		volume.setIcon(volumeIcon);
		volume.setBounds(591, 5, 100, 55);
		controls.add(volume);
		volume.addActionListener(this);
		
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
		speak.setBounds(291, 48, 97, 41);
		speech.add(speak);
		speak.addActionListener(this);
		
		//save text button// TODO Auto-generated method stub
		save.setBounds(389, 48, 97, 41);
		speech.add(save);
		save.addActionListener(this);
		// set Frame
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MediaPlayer.class.getResource("/javagui/resources/logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setContentPane(contentPane);
		setVisible(true);
		
		//set up the video
		video.canPause();
		video.playMedia("big_buck_bunny_1_minute.avi");
		video.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==play){
			if(play.getIcon().equals(playIcon)){
				play.setIcon(stopIcon);
				video.start();
			}else{
				play.setIcon(playIcon);
				video.pause();
			}
		}else if(e.getSource()==forward){
			
		}else if(e.getSource()==backward){
			
		}else if(e.getSource()==volume){
			
		}else if(e.getSource()==speak){
			
		}else if(e.getSource()==save){
			
		}
		
	}
}
