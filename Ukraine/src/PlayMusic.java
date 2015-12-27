import java.io.File;
import java.io.IOException;

public class PlayMusic implements Runnable {
	private String name;
	private boolean isPlay,isPause;
	Music music;
	private File f;
PlayMusic()
{
	isPlay=false;
}
	PlayMusic(String num) throws IOException {
		name = "music\\" + num + ".wav";
		f = new File(name);
	}

	public void run() {
		music = new Music(f);
		music.play();
		System.out.println("zachlo");
		long times=music.clip.getFrameLength();
		
	}

	void start() {
		isPlay = true;
		Thread t = new Thread(this);
		t.start();
		System.out.println("zachlo");
	}
	public boolean isPause()
	{
		return isPause;
	}
	public void pause() throws InterruptedException
	{if(!isPause)
	{
		isPause=true;
		music.pause();
		this.wait();
	}}
	public void resume()
	{if(isPause)
	{this.notify();
		isPause=false;
		music.resume();
	}}
	void stop() {
		isPlay = false;
		music.stop();
		isPause=false;
		music=null;
	}

	boolean isPlay() {
		return isPlay;
	}
}
