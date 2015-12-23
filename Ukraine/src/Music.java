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
			AudioInputStream stream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.addLineListener(new LineListener()
					{
				public void update(LineEvent ev) {
					if (ev.getType() == LineEvent.Type.STOP) {
						playing = false;
						synchronized(clip) {
							clip.notify();
						}
					}
				}
					});
			volumeC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			released = true;
		} catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
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

	
	public void play(boolean breakOld) {
		if (released) {
			if (breakOld) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else if (!isPlaying()) {
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			}
		}
	}

	public void play() {
		play(true);
	}
	
	public void stop() {
		if (playing) {
			clip.stop();
		}
	}

	public void setVolume(float x) {
		if (x<0) x = 0;
		if (x>1) x = 1;
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		volumeC.setValue((max-min)*x+min);
	}
	public float getVolume() {
		float v = volumeC.getValue();
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		return (v-min)/(max-min);
	}

	public void join() {
		if (!released) return;
		synchronized(clip) {
			try {
				while (playing) clip.wait();
			} catch (InterruptedException exc) {}
		}
	}

	public  Music playSound(String s) {
		File f = new File(s);
		Music music = new Music(f);
		music.play();
		return music;
	}


}