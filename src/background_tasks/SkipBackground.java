package background_tasks;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.SwingWorker;

import mainview.MediaPlayer;

public class SkipBackground extends SwingWorker<Object,Integer>{
	private EmbeddedMediaPlayer video;
	private boolean skipForward;
	
	private int i=1;
	/**
	 * default constructor
	 */
	public SkipBackground() {
	}
	/**
	 * custom constructor
	 */
	public SkipBackground(boolean b,EmbeddedMediaPlayer video) {
		this.skipForward=b;
		this.video=video;
	}

	@Override
	protected Object doInBackground() throws Exception {
		while(!this.isCancelled()){
			publish(i);
			try{
			Thread.sleep(500);
			
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return null;
	}
	
	@Override
	protected void process(List<Integer> chunks){
		//fast forward or backward by 3 seconds
		if(skipForward){
			video.skip(3000);
		}else{
			video.skip(-3000);
			if ((int)video.getTime() <= 0){
				this.cancel(true);
			}
		}
	}
	
	@Override
	protected void done(){
		try{
			this.get();
		}catch (CancellationException c){
			c.getStackTrace();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
