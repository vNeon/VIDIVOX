package mainview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class AddText extends SwingWorker<Object,Integer> {
	private String message;
	private String outputFile= "out2.mp4";
	private EmbeddedMediaPlayer video;
	private JLabel statuslbl;
	
	/**
	 * Constructor
	 * @param message
	 */
	public AddText(String message, EmbeddedMediaPlayer video, JLabel statuslbl){
		this.message=message;
		this.video=video;
		this.statuslbl= statuslbl;
	}
	
	
	@Override
	protected Object doInBackground() throws Exception {
		String cmd="ffmpeg -y -i big_buck_bunny_1_minute.avi -vf \"drawtext=/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf:text="+message+":fontsize=15:fontcolor=black:x=10:y=450\" " +outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash","-c", cmd);
		Process process= builder.start();
		publish();
		InputStream stdout = process.getInputStream();
		BufferedReader stdoutBuffered = new BufferedReader( new InputStreamReader(stdout));
		while (stdoutBuffered.readLine() != null) {
		}
		process.waitFor();
		process.destroy();
		return null;
	}
	@Override
	
	protected void process(List<Integer> chunks){
		this.statuslbl.setText("Creating Myfile, please wait...");
	}
	@Override
	protected void done(){
		this.statuslbl.setText("Successfully created Myfile!");
		video.playMedia(outputFile);
		video.start();
		video.pause();
	}

}
