package mainview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class AddText extends SwingWorker<Object,Integer> {
	private String message;
	private String outputFile= "out2.mp4";
	private EmbeddedMediaPlayer video;
	
	/**
	 * Constructor
	 * @param message
	 */
	public AddText(String message, EmbeddedMediaPlayer video){
		this.message=message;
		this.video=video;
	}
	
	
	@Override
	protected Object doInBackground() throws Exception {
		String cmd="ffmpeg -y -i big_buck_bunny_1_minute.avi -vf \"drawtext=/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf:text="+message+":fontsize=15:fontcolor=black:x=10:y=400\" " +outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash","-c", cmd);
		Process process= builder.start();
		
		InputStream stdout = process.getInputStream();
		BufferedReader stdoutBuffered = new BufferedReader(
				new InputStreamReader(stdout));
		while (stdoutBuffered.readLine() != null) {
		}
		process.waitFor();
		process.destroy();
		return null;
	}
	@Override
	protected void done(){
		video.playMedia(outputFile);
		video.start();
	}

}
