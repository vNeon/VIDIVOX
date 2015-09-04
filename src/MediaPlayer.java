import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class MediaPlayer {
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public static void main(final String[] args) {
        
        NativeLibrary.addSearchPath(
            RuntimeUtil.getLibVlcLibraryName(), "/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib"
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediaPlayer(args);
            }
        });
    }

    private MediaPlayer(String[] args) {
        JFrame frame = new JFrame("vlcj Tutorial");
        JPanel panel= new JPanel(new BorderLayout());
        JButton mute= new JButton("Mute");
        JButton forward= new JButton("Skip");
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        final EmbeddedMediaPlayer video= mediaPlayerComponent.getMediaPlayer();
        mute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				video.mute();
				
			}
        	
        });
        forward.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent arg0) {
        	// TODO Auto-generated method stub
        	video.skip(5000);
        }
        });
        panel.add(forward, BorderLayout.SOUTH);
        panel.add(mute, BorderLayout.NORTH);
        panel.add(mediaPlayerComponent, BorderLayout.EAST);
        frame.setContentPane(panel);

        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        video.playMedia("big_buck_bunny_1_minute.avi");
    }
}