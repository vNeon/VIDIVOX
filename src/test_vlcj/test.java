package test_vlcj;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class test {
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	public static void main(final String[] args) {
		// FIXME: Causes segfault
		// System.loadLibrary("jawt");
		// System.load("/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/lib/libjawt.dylib");

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new test(args);
			}
		});
	}

	private test(String[] args) {
		JFrame frame = new JFrame("vlcj Tutorial");

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		frame.setContentPane(mediaPlayerComponent);

		frame.setLocation(100, 100);
		frame.setSize(1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		mediaPlayerComponent.getMediaPlayer().playMedia("big_buck_bunny_1_minute.avi");
	}
}
