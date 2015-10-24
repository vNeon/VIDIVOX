package add_mp3_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import mainview.MediaPlayer;

public class AddMp3File extends SwingWorker<Object,Integer> {
	private String fileName;
	private String vidFile;
	private String outputFile;
	private EmbeddedMediaPlayer video;
	private JLabel statuslbl;
	private boolean playVideo;
	private File outputName;
	private MediaPlayer mediaPlayer;
	private String offsetTime = "00:00:30";
	
	/**
	 * constructor
	 * @param fileName
	 * @param video
	 */
	public AddMp3File(String fileName, String vidFile, String offsetTime, String outputFile ,EmbeddedMediaPlayer video, JLabel statuslbl, boolean playVideo, MediaPlayer mediaPlayer){
		this.fileName = fileName;
		this.vidFile = vidFile;
		this.outputFile = outputFile;
		outputName = new File(outputFile);
		this.video = video;
		this.statuslbl= statuslbl;
		this.playVideo = playVideo;
		this.mediaPlayer = mediaPlayer;
		this.offsetTime = offsetTime;
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		String cmd="ffmpeg -y -i "+vidFile+" -itsoffset "+offsetTime+" -i "+fileName+" -filter_complex amix=inputs=2 -async 1 "+outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash", "-c",cmd);
		Process process=builder.start();
		publish();
		//Read output from the terminal which is the copying process
		InputStream stdout = process.getInputStream();
		BufferedReader stdoutBuffered = new BufferedReader( new InputStreamReader(stdout));
		//while loop exit only when there is no more input from the terminal meaning the file is created completely
		while (stdoutBuffered.readLine() != null) {
		}
		process.waitFor();
		process.destroy();
		return null;
	}
	@Override 
	protected void process(List<Integer> chunks){
		this.statuslbl.setText("Creating "+outputName.getName()+", please wait...");
	}
	@Override
	protected void done(){
		this.statuslbl.setText("Successfully created "+outputName.getName()+"!");
		
		if(playVideo){
			video.playMedia(outputFile);
			mediaPlayer.setVideoTitle(outputFile);
			mediaPlayer.setTime();
			video.start();
		}
	}

}
