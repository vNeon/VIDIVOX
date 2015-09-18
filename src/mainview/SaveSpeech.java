package mainview;

import javax.swing.SwingWorker;

public class SaveSpeech extends SwingWorker<Void, Void>{
	
	private String message;
	private String fileName;
		
	public SaveSpeech (String message, String fileName){
		this.message = message;
		this.fileName = fileName;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// command used in bash terminal
		// create a temporary file
		String cmdCreateFile = "echo \"" + message + "\" | text2wave -o tmp.wav";
		
		// builds the command and runs it
		// creates a temporary file
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmdCreateFile);
		Process process = builder.start();
		process.waitFor();
		process.destroy();
		
		//converts text file to wave file
		String cmdText2Wave = "ffmpeg -i tmp.wav -f mp3 " + fileName;
		
		// builds the command and runs it
		// converts text file to wave file
		ProcessBuilder builderText2Wave = new ProcessBuilder("/bin/bash", "-c", cmdText2Wave);
		Process processText2Wave = builderText2Wave.start();
		processText2Wave.waitFor();
		processText2Wave.destroy();

		// command used in bash terminal
		// deletes the temporary file
		String cmdDeleteTxt = "rm tmp.wav";

		// builds the command and runs it
		// deletes temporary file
		ProcessBuilder builderDeleteTxt = new ProcessBuilder("/bin/bash", "-c", cmdDeleteTxt);
		Process processDeleteTxt = builderDeleteTxt.start();
		processDeleteTxt.waitFor();
		processDeleteTxt.destroy();
		
		return null;
	}

}
