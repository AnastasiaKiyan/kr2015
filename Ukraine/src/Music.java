import java.io.File

;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private boolean released = false;
	private Clip clip = null;
	private FloatControl volumeC = null;
	private boolean playing = false;

	public Music(File f) {
		try {
			AudioInputStream track = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(track);

			volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			released = true;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
			exc.printStackTrace();
			released = false;
		}
	}

	public boolean isReleased() {
		return released;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void play() {
		if (released) {
			clip.start();
			playing = true;
		}
	}

	public void stop() {
		if (playing) {
			System.out.println("v stop zachlo");
			clip.stop();
			clip.close();
		}
	}


}