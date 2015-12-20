import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music implements Runnable {
	boolean isPlayed;
	Clip clip;
	public static AdvancedPlayer explay;
Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException
{
	
}
boolean isPlayed()
{
	return isPlayed;
}
public void run()
{
	while(isPlayed())
	{
		 InputStream potok;
		try {
			potok = new FileInputStream("2.mp3");
			AudioDevice auDev = new JavaSoundAudioDevice();
		 explay = new AdvancedPlayer(potok,auDev);
		 explay.play(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
void start()
{
	isPlayed=true;
	Thread t=new Thread(this);
	t.start();
	
}
void stop()
{
	isPlayed=false;
}
}
