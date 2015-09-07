package mainview;

import javax.swing.SwingWorker;

public class AddMp3File extends SwingWorker<Object,Integer> {
	private String fileName;
	private String outputFile= "out.mp4";
	/**
	 * constructor
	 * @param fileName
	 */
	public AddMp3File(String fileName){
		this.fileName=fileName;
	}
	
	
	
	@Override
	protected Object doInBackground() throws Exception {
		
		String cmd="ffmpeg -i big_buck_bunny_1_minute.avi -i "+fileName+" -filter_complex amix=inputs=2 "+outputFile;
		ProcessBuilder builder= new ProcessBuilder("/bin/bash", "-c",cmd);
		Process process=builder.start();
		process.waitFor();
		process.destroy();
		
		return null;
	}
	@Override
	protected void done(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.getStackTrace();
		}
	}

}
