package mainview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class AddMp3File extends SwingWorker<Object,Integer> {
	private String fileName;
	private String outputFile= "out.mp4";
	private EmbeddedMediaPlayer video;
	private JLabel statuslbl;
	
	
	/**
	 * constructor
	 * @param fileName
	 * @param video
	 */
	public AddMp3File(String fileName, EmbeddedMediaPlayer video, JLabel statuslbl){
		this.fileName=fileName;
		this.video=video;
		this.statuslbl= statuslbl;
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		String cmd="ffmpeg -y -i big_buck_bunny_1_minute.avi -i "+fileName+" -filter_complex amix=inputs=2 "+outputFile;
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
