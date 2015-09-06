package mainview;

import javax.swing.SwingWorker;

public class SaveSpeech extends SwingWorker<Void, Void>{
	
	private String message;
	
	public SaveSpeech (String message){
		this.message = message;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// command used in bash terminal
		// create a temporary file
		String cmd = "echo \"" + message + "\" > tmp.txt";//"text2wave " + message + " -o " + "myWave.wav";
		
		// builds the command and runs it
		// creates a temporary file
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		Process process = builder.start();
		process.waitFor();
		
		//converts text file to wave file
		String cmdText2Wave = "text2wave tmp.txt -o myWave.wav";
		
		// builds the command and runs it
		// converts text file to wave file
		ProcessBuilder builderText2Wave = new ProcessBuilder("/bin/bash", "-c", cmdText2Wave);
		Process processText2Wave = builderText2Wave.start();
		processText2Wave.waitFor();
		
		
		return null;
	}

}
