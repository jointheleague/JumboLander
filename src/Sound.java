import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public Clip explosion, victory, engine, bingBong, radioChatter, boo;

	public Sound() {
		try {
			explosion = AudioSystem.getClip();
			explosion.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("explosion.wav")));
			bingBong = AudioSystem.getClip();
			bingBong.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("bingBong.wav")));
			engine = AudioSystem.getClip();
			engine.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("engine.wav")));
			radioChatter = AudioSystem.getClip();
			radioChatter.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("radioChatter.wav")));
			victory = AudioSystem.getClip();
			victory.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("victory.wav")));
			boo = AudioSystem.getClip();
			boo.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource("boo.wav")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playOnLoop(final Clip clip) {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play(final Clip clip) {
		clip.start();
	}
	
	public void stopAmbient() {
		engine.stop();
		bingBong.stop();
		radioChatter.stop();
	}

}
