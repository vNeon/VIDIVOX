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
	private String outputFile;
	private EmbeddedMediaPlayer video;
	private JLabel statuslbl;
	private String videoFile;
	private boolean playVideo;
	private MediaPlayer mediaPlayer=null;
	
	/**
	 * Constructor
	 * @param message
	 */
	public AddText(String message,String videoFile,String outputFile, EmbeddedMediaPlayer video, 
			JLabel statuslbl, boolean playVideo, MediaPlayer mediaPlayer){
		this.message=message;
		this.videoFile=videoFile;
		this.outputFile=outputFile;
		this.video=video;
		this.statuslbl= statuslbl;
		this.playVideo= playVideo;
		this.mediaPlayer=mediaPlayer;
	}
	
	
	@Override
	protected Object doInBackground() throws Exception {
		// bash command add text to the start of the video
		String cmd="ffmpeg -y -i "+videoFile+ " -vf \"drawtext=/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf:text="+message+":fontsize=15:fontcolor=black:x=10:y=450\" " +outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash","-c", cmd);
		Process process= builder.start();
		publish();
		InputStream stdout = process.getInputStream();
		BufferedReader stdoutBuffered = new BufferedReader( new InputStreamReader(stdout));
		//Get input from the terminal output of ffmpeg, when no more output is print means the video is complete.
		while (stdoutBuffered.readLine() != null) {
		}
		process.waitFor();
		process.destroy();
		return null;
	}
	@Override
	protected void process(List<Integer> chunks){
		this.statuslbl.setText("Creating "+outputFile+", please wait...");
	}
	@Override
	protected void done(){
		this.statuslbl.setText("Successfully created"+ outputFile);
		if (playVideo){
			video.playMedia(outputFile);
			mediaPlayer.setVideoTitle(outputFile);
			mediaPlayer.setTime();
			video.start();
		}	
	}

}
