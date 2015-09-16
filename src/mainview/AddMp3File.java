package mainview;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import javax.swing.SwingWorker;

public class AddMp3File extends SwingWorker<Object,Integer> {
	private String fileName;
	private String outputFile= "out.mp4";
	private EmbeddedMediaPlayer video;
	
	/**
	 * constructor
	 * @param fileName
	 */
	public AddMp3File(String fileName, EmbeddedMediaPlayer video){
		this.fileName=fileName;
		this.video=video;
	}
	
	
	
	@Override
	protected Object doInBackground() throws Exception {
		String cmd="ffmpeg -y -i big_buck_bunny_1_minute.avi -i "+fileName+" -filter_complex amix=inputs=2 "+outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash", "-c",cmd);
		Process process=builder.start();
		
		// creates a reader to read the output
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
	protected void process(List<Integer> chunks){
		
	}
	@Override
	protected void done(){
		video.playMedia(outputFile);
		video.start();
		
	}

}
