import java.io.File;

public class PlayMusic implements Runnable {
	private String name;
	private boolean isPlay;
	Music music;
	private File f;
PlayMusic()
{
	isPlay=false;
}
	PlayMusic(String num) {
		name = "music\\" + num + ".wav";
		f = new File(name);
	}

	public void run() {
		music = new Music(f);
		music.playSound(name);
		System.out.println("zachlo");
	}

	void start() {
		isPlay = true;
		Thread t = new Thread(this);
		t.start();
		System.out.println("zachlo");
	}

	void stop() {
		isPlay = false;
		music.stop();
	}

	boolean isPlay() {
		return isPlay;
	}
}
